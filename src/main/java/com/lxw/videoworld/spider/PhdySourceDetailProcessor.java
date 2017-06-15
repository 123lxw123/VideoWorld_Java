package com.lxw.videoworld.spider;

import com.lxw.videoworld.config.Constants;
import com.lxw.videoworld.domain.SourceDetail;
import com.lxw.videoworld.utils.HTMLUtil;
import com.lxw.videoworld.utils.StringUtil;
import com.lxw.videoworld.utils.URLUtil;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.http.util.TextUtils;
import us.codecraft.webmagic.Page;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lxw9047 on 2017/4/28.
 */
public class PhdySourceDetailProcessor extends BasePhdyProcessor {
    private String value0;
    private String c_name0;
    private String expiredays0;

    @Override
    public void process(Page page) {
        super.process(page);
        String url = page.getUrl().toString();
        value0 = page.getHtml().regex("'cookie': \"(.*?)\"").toString();
        c_name0 = page.getHtml().regex("'(.*?)', data['cookie']").toString();
        expiredays0 = page.getHtml().regex("data['cookie'], (\\d*)").toString();
        if (TextUtils.isEmpty(value0)) {
            SourceDetail sourceDetail = new SourceDetail();
            sourceDetail.setUrl(url);
            if (!TextUtils.isEmpty(url)) {
                String[] params = url.split("/");
                if (params.length == 8) {
                    if (!TextUtils.isEmpty(params[7]) && params[7].length() > 5) {
                        sourceDetail.setId(params[7].substring(0, params[7].length() - 5));
                    }
                    sourceDetail.setCategory(params[4]);
                    sourceDetail.setDate(params[5] + params[6]);
                } else {

                }
            }
            String showinfo = page.getHtml().css("div#showinfo").toString();
            if (!TextUtils.isEmpty(showinfo)) {
                List<String> imgUrl = page.getHtml().css("div#showinfo").css("img", "src").all();
                List<String> content0 = page.getHtml().css("div#showinfo").regex("<div>(.*?)</div>").all();
                List<String> newContent = new ArrayList<>();
                if (content0 != null && content0.size() > 0) {
                    for (int j = 0; j < content0.size(); j++) {
                        String content = content0.get(j);
                        if (!TextUtils.isEmpty(content)) {
                            content = StringEscapeUtils.unescapeHtml(content).replaceAll("<br/>", "\n").replaceAll("<br>", "\n")
                                    .replaceAll("<br />", "\n").replaceAll("<p>", "").replaceAll("</p>", "\n");
                            content = HTMLUtil.trimHtml2Txt(content, null);
                            content = StringUtil.disposeField(content);
                            if (!TextUtils.isEmpty(content)) {
                                newContent.add(content);
                            }
                        }
                    }
                }
                if (newContent.size() == 0) {
                    String content = "";
                    if (showinfo.contains("[下载地址]")) {
                        content = page.getHtml().css("div#showinfo").regex("迅雷下载地址和剧情：(.*?)[下载地址]").toString();
                    } else if (showinfo.contains("迅雷下载地址列表")) {
                        content = page.getHtml().css("div#showinfo").regex("迅雷下载地址和剧情：(.*?)迅雷下载地址列表").toString();
                    } else if (showinfo.contains("独孤在线播放")) {
                        content = page.getHtml().css("div#showinfo").regex("迅雷下载地址和剧情：(.*?)独孤在线播放").toString();
                    } else if (showinfo.contains("电影截图")) {
                        content = page.getHtml().css("div#showinfo").regex("迅雷下载地址和剧情：(.*?)电影截图").toString();
                    } else {
                        content = page.getHtml().css("div#showinfo").regex("迅雷下载地址和剧情：(.*?)下载地址").toString();
                        if (TextUtils.isEmpty(content)) {
                            content = page.getHtml().css("div#showinfo").regex("迅雷下载地址和剧情：(.*?)迅雷下载").toString();
                        }
                    }
                    content = StringUtil.disposeField(content);
                    if (!TextUtils.isEmpty(content)) {
                        newContent.add(content);
                    }
                }
                List<String> links = new ArrayList<>();
                List<String> links0 = page.getHtml().css("table").links().all();
                if (links0 != null && links0.size() > 0) {
                    for (int i = 0; i < links0.size(); i++) {
                        if (!links0.get(i).contains("html") && !links0.get(i).contains("xiaobeijie")) {
                            links.add(links0.get(i));
                        }
                    }
                }

                String title = page.getHtml().css("h3").regex(">(.*?)</h3>").get();
                if (!TextUtils.isEmpty(title)) {
                    sourceDetail.setTitle(StringUtil.disposeField(title.trim()));
                }
                if (imgUrl != null && imgUrl.size() > 0) {
                    sourceDetail.setImages(StringUtil.disposeField(imgUrl.toString()));
                }
                if (links != null && links.size() > 0) {
                    sourceDetail.setLinks(StringUtil.disposeField(links.toString()));
                }
                if (!TextUtils.isEmpty(newContent.toString())) {
                    sourceDetail.setContent(StringUtil.disposeField(newContent.toString()));
                }
                sourceDetail.setStatus(Constants.STATUS_2);

                // 获取视频详情各种属性
                if (!TextUtils.isEmpty(sourceDetail.getCategory()) && !sourceDetail.getCategory().equals(Constants.CATEGORY_19) && !sourceDetail.getCategory().equals(Constants.CATEGORY_22)) {
                    if (sourceDetail.getCategory().equals(Constants.CATEGORY_21)) {
                        String name = page.getHtml().css("div#showinfo").regex("中文名称:(.*?)<").toString();
                        if (!TextUtils.isEmpty(name)) {
                            sourceDetail.setName(StringUtil.disposeField(name));
                        }
                        String style = page.getHtml().css("div#showinfo").regex("游戏类型:(.*?)<").toString();
                        if (!TextUtils.isEmpty(style)) {
                            sourceDetail.setStyle(StringUtil.disposeField(style));
                        }
                    } else {
                        String name = page.getHtml().css("div#showinfo").regex("◎片　　名(.*?)<").toString();
                        if (!TextUtils.isEmpty(name)) {
                            name = name.replaceAll("</font>", "");
                            sourceDetail.setName(StringUtil.disposeField(name));
                        } else {
                            String name1 = page.getHtml().css("div#showinfo").regex("【原 &nbsp; &nbsp;　名】：(.*?)<").toString();
                            if (!TextUtils.isEmpty(name1)) {
                                name1 = name1.replaceAll("</font>", "");
                                sourceDetail.setName(StringUtil.disposeField(name1));
                            } else {
                                String name2 = page.getHtml().css("div#showinfo").regex("[剧 名]:(.*?)<").toString();
                                if (!TextUtils.isEmpty(name2)) {
                                    name2 = name2.replaceAll("</font>", "");
                                    sourceDetail.setName(StringUtil.disposeField(name2));
                                } else {
                                    String name3 = page.getHtml().css("div#showinfo").regex("【片 &nbsp; &nbsp;　名】：(.*?)<").toString();
                                    if (!TextUtils.isEmpty(name3)) {
                                        name3 = name3.replaceAll("</font>", "");
                                        sourceDetail.setName(StringUtil.disposeField(name3));
                                    } else {
                                        String name4 = page.getHtml().css("div#showinfo").regex("片　　名(.*?)<").toString();
                                        if (!TextUtils.isEmpty(name4)) {
                                            name4 = name4.replaceAll("</font>", "");
                                            sourceDetail.setName(StringUtil.disposeField(name4));
                                        }
                                    }
                                }
                            }
                        }
                        String translate_name = page.getHtml().css("div#showinfo").regex("◎译　　名(.*?)<").toString();
                        if (!TextUtils.isEmpty(translate_name)) {
                            translate_name = translate_name.replaceAll("</font>", "");
                            sourceDetail.setTranslateName(StringUtil.disposeField(translate_name));
                        } else {
                            String translate_name1 = page.getHtml().css("div#showinfo").regex("【译 &nbsp; &nbsp;　名】：(.*?)<").toString();
                            if (!TextUtils.isEmpty(translate_name1)) {
                                translate_name1 = translate_name1.replaceAll("</font>", "");
                                sourceDetail.setTranslateName(StringUtil.disposeField(translate_name1));
                            } else {
                                String translate_name2 = page.getHtml().css("div#showinfo").regex("译　　名(.*?)<").toString();
                                if (!TextUtils.isEmpty(translate_name2)) {
                                    translate_name2 = translate_name2.replaceAll("</font>", "");
                                    sourceDetail.setTranslateName(StringUtil.disposeField(translate_name2));
                                }
                            }
                        }
                        try {
                            String year = page.getHtml().css("div#showinfo").regex("◎年　　代(.*?)<").toString();
                            if (!TextUtils.isEmpty(year)) {
                                year = year.replaceAll("</font>", "");
                                year = StringUtil.disposeField(year);
                                if (!TextUtils.isEmpty(year) && year.length() > 4) {
                                    year = year.substring(0, 4);
                                }
                                sourceDetail.setYear(year);
                            } else {
                                String year1 = page.getHtml().css("div#showinfo").regex("【年 &nbsp; &nbsp;　代】：(.*?)<").toString();
                                if (!TextUtils.isEmpty(year1)) {
                                    year1 = year1.replaceAll("</font>", "");
                                    year1 = StringUtil.disposeField(year1);
                                    if (!TextUtils.isEmpty(year1) && year1.length() > 4) {
                                        year1 = year1.substring(0, 4);
                                    }
                                    sourceDetail.setYear(year1);
                                } else {
                                    String year2 = page.getHtml().css("div#showinfo").regex("年　　代(.*?)<").toString();
                                    if (!TextUtils.isEmpty(year2)) {
                                        year2 = year2.replaceAll("</font>", "");
                                        year2 = StringUtil.disposeField(year2);
                                        if (!TextUtils.isEmpty(year2) && year2.length() > 4) {
                                            year2 = year2.substring(0, 4);
                                        }
                                        sourceDetail.setYear(year2);
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        String area = page.getHtml().css("div#showinfo").regex("◎产　　地(.*?)<").toString();
                        if (!TextUtils.isEmpty(area)) {
                            area = area.replaceAll("</font>", "");
                            sourceDetail.setArea(StringUtil.disposeField(area));
                        } else {
                            String area1 = page.getHtml().css("div#showinfo").regex("◎国　　家(.*?)<").toString();
                            if (!TextUtils.isEmpty(area1)) {
                                area1 = area1.replaceAll("</font>", "");
                                sourceDetail.setArea(StringUtil.disposeField(area1));
                            } else {
                                String area2 = page.getHtml().css("div#showinfo").regex("【国 &nbsp; &nbsp;　家】：(.*?)<").toString();
                                if (!TextUtils.isEmpty(area2)) {
                                    area2 = area2.replaceAll("</font>", "");
                                    sourceDetail.setArea(StringUtil.disposeField(area2));
                                } else {
                                    String area3 = page.getHtml().css("div#showinfo").regex("国　　家(.*?)<").toString();
                                    if (!TextUtils.isEmpty(area3)) {
                                        area3 = area3.replaceAll("</font>", "");
                                        sourceDetail.setArea(StringUtil.disposeField(area3));
                                    }
                                }
                            }
                        }
                        String style = page.getHtml().css("div#showinfo").regex("◎类　　别(.*?)<").toString();
                        if (!TextUtils.isEmpty(style)) {
                            style = style.replaceAll("</font>", "");
                            sourceDetail.setStyle(StringUtil.disposeField(style));
                        } else {
                            String style1 = page.getHtml().css("div#showinfo").regex("【类 &nbsp; &nbsp;　别】：(.*?)<").toString();
                            if (!TextUtils.isEmpty(style1)) {
                                style1 = style1.replaceAll("</font>", "");
                                sourceDetail.setStyle(StringUtil.disposeField(style1));
                            } else {
                                String style2 = page.getHtml().css("div#showinfo").regex("[类 型]:(.*?)<").toString();
                                if (!TextUtils.isEmpty(style2)) {
                                    style2 = style2.replaceAll("</font>", "");
                                    sourceDetail.setStyle(StringUtil.disposeField(style2));
                                } else {
                                    String style3 = page.getHtml().css("div#showinfo").regex("类　　别(.*?)<").toString();
                                    if (!TextUtils.isEmpty(style3)) {
                                        style3 = style3.replaceAll("</font>", "");
                                        sourceDetail.setStyle(StringUtil.disposeField(style3));
                                    }
                                }
                            }
                        }
                        String language = page.getHtml().css("div#showinfo").regex("◎语　　言(.*?)<").toString();
                        if (!TextUtils.isEmpty(language)) {
                            language = language.replaceAll("</font>", "");
                            sourceDetail.setLanguage(StringUtil.disposeField(language));
                        } else {
                            String language1 = page.getHtml().css("div#showinfo").regex("【语 &nbsp; &nbsp;　言】：(.*?)<").toString();
                            if (!TextUtils.isEmpty(language1)) {
                                language1 = language1.replaceAll("</font>", "");
                                sourceDetail.setLanguage(StringUtil.disposeField(language1));
                            } else {
                                String language2 = page.getHtml().css("div#showinfo").regex("语　　言(.*?)<").toString();
                                if (!TextUtils.isEmpty(language2)) {
                                    language2 = language2.replaceAll("</font>", "");
                                    sourceDetail.setLanguage(StringUtil.disposeField(language2));
                                }
                            }
                        }
                        String subtitles = page.getHtml().css("div#showinfo").regex("◎字　　幕(.*?)<").toString();
                        if (!TextUtils.isEmpty(subtitles)) {
                            subtitles = subtitles.replaceAll("</font>", "");
                            sourceDetail.setSubtitles(StringUtil.disposeField(subtitles));
                        } else {
                            String subtitles1 = page.getHtml().css("div#showinfo").regex("【字 &nbsp; &nbsp;　幕】：(.*?)<").toString();
                            if (!TextUtils.isEmpty(subtitles1)) {
                                subtitles1 = subtitles1.replaceAll("</font>", "");
                                sourceDetail.setSubtitles(StringUtil.disposeField(subtitles1));
                            } else {
                                String subtitles2 = page.getHtml().css("div#showinfo").regex("字　　幕(.*?)<").toString();
                                if (!TextUtils.isEmpty(subtitles2)) {
                                    subtitles2 = subtitles2.replaceAll("</font>", "");
                                    sourceDetail.setSubtitles(StringUtil.disposeField(subtitles2));
                                }
                            }
                        }
                        String release_date = page.getHtml().css("div#showinfo").regex("◎上映日期(.*?)<").toString();
                        if (!TextUtils.isEmpty(release_date)) {
                            release_date = release_date.replaceAll("</font>", "");
                            sourceDetail.setReleaseDate(StringUtil.disposeField(release_date));
                        } else {
                            String release_date1 = page.getHtml().css("div#showinfo").regex("【首 &nbsp; &nbsp;　播】：(.*?)<").toString();
                            if (!TextUtils.isEmpty(release_date1)) {
                                release_date1 = release_date1.replaceAll("</font>", "");
                                sourceDetail.setReleaseDate(StringUtil.disposeField(release_date1));
                            } else {
                                String release_date2 = page.getHtml().css("div#showinfo").regex("[首 播]:(.*?)<").toString();
                                if (!TextUtils.isEmpty(release_date2)) {
                                    release_date2 = release_date2.replaceAll("</font>", "");
                                    sourceDetail.setReleaseDate(StringUtil.disposeField(release_date2));
                                } else {
                                    String release_date3 = page.getHtml().css("div#showinfo").regex("上映日期(.*?)<").toString();
                                    if (!TextUtils.isEmpty(release_date3)) {
                                        release_date3 = release_date3.replaceAll("</font>", "");
                                        sourceDetail.setReleaseDate(StringUtil.disposeField(release_date3));
                                    }
                                }
                            }
                        }
                        try {
                            String imdb_score0 = page.getHtml().css("div#showinfo").regex("◎IMDb评分(.*?)<").toString();
                            if (!TextUtils.isEmpty(imdb_score0)) {
                                imdb_score0 = imdb_score0.replaceAll("</font>", "");
                                String[] imdb_score = imdb_score0.trim().split("/");
                                if (imdb_score.length > 0 && !TextUtils.isEmpty(imdb_score[0])) {
                                    String finalScore = StringUtil.disposeField(imdb_score[0]);
                                    if(!TextUtils.isEmpty(finalScore) && !finalScore.equals("暂无评分")  && !finalScore.equals("0")  && !finalScore.equals("0.0")){
                                        if(finalScore.contains("分") && finalScore.length() == 4){
                                            sourceDetail.setImdbScore(finalScore.substring(0, finalScore.length() - 1));
                                        }else{
                                            sourceDetail.setImdbScore(StringUtil.disposeField(finalScore));
                                        }
                                    }
                                }
                                if (imdb_score.length > 1 && !TextUtils.isEmpty(imdb_score[1])) {
                                    sourceDetail.setImdbIntro(StringUtil.disposeField(imdb_score[1]));
                                }
                            } else {
                                String imdb_score1 = page.getHtml().css("div#showinfo").regex("【IMDB评分】：(.*?)<").toString();
                                if (!TextUtils.isEmpty(imdb_score1)) {
                                    imdb_score1 = imdb_score1.replaceAll("</font>", "");
                                    String[] imdb_score = imdb_score1.trim().split("/");
                                    if (imdb_score.length > 0 && !TextUtils.isEmpty(imdb_score[0])) {
                                        String finalScore = StringUtil.disposeField(imdb_score[0]);
                                        if(!TextUtils.isEmpty(finalScore) && !finalScore.equals("暂无评分")  && !finalScore.equals("0")  && !finalScore.equals("0.0")){
                                            if(finalScore.contains("分") && finalScore.length() == 4){
                                                sourceDetail.setImdbScore(finalScore.substring(0, finalScore.length() - 1));
                                            }else{
                                                sourceDetail.setImdbScore(StringUtil.disposeField(finalScore));
                                            }
                                        }
                                    }
                                    if (imdb_score.length > 1 && !TextUtils.isEmpty(imdb_score[1])) {
                                        sourceDetail.setImdbIntro(StringUtil.disposeField(imdb_score[1]));
                                    }
                                }else{
                                    String imdb_score2 = page.getHtml().css("div#showinfo").regex("IMDb评分(.*?)<").toString();
                                    if (!TextUtils.isEmpty(imdb_score2)) {
                                        imdb_score2 = imdb_score2.replaceAll("</font>", "");
                                        String[] imdb_score = imdb_score2.trim().split("/");
                                        if (imdb_score.length > 0 && !TextUtils.isEmpty(imdb_score[0])) {
                                            String finalScore = StringUtil.disposeField(imdb_score[0]);
                                            if(!TextUtils.isEmpty(finalScore) && !finalScore.equals("暂无评分")  && !finalScore.equals("0")  && !finalScore.equals("0.0")){
                                                if(finalScore.contains("分") && finalScore.length() == 4){
                                                    sourceDetail.setImdbScore(finalScore.substring(0, finalScore.length() - 1));
                                                }else{
                                                    sourceDetail.setImdbScore(StringUtil.disposeField(finalScore));
                                                }
                                            }
                                        }
                                        if (imdb_score.length > 1 && !TextUtils.isEmpty(imdb_score[1])) {
                                            sourceDetail.setImdbIntro(StringUtil.disposeField(imdb_score[1]));
                                        }
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        try {
                            String douban_score0 = page.getHtml().css("div#showinfo").regex("◎豆瓣评分(.*?)<").toString();
                            if (!TextUtils.isEmpty(douban_score0)) {
                                douban_score0 = douban_score0.replaceAll("</font>", "");
                                String[] douban_score = douban_score0.trim().split("/");
                                if (douban_score.length > 0 && !TextUtils.isEmpty(douban_score[0])) {
                                    String finalScore = StringUtil.disposeField(douban_score[0]);
                                    if(!TextUtils.isEmpty(finalScore) && !finalScore.equals("暂无评分")  && !finalScore.equals("0")  && !finalScore.equals("0.0")){
                                        if(finalScore.contains("分") && finalScore.length() == 4){
                                            sourceDetail.setDoubanScore(finalScore.substring(0, finalScore.length() - 1));
                                        }else{
                                            sourceDetail.setDoubanScore(StringUtil.disposeField(finalScore));
                                        }
                                    }
                                }
                                if (douban_score.length > 1 && !TextUtils.isEmpty(douban_score[1])) {
                                    sourceDetail.setDoubanIntro(StringUtil.disposeField(douban_score[1]));
                                }
                            } else {
                                String douban_score1 = page.getHtml().css("div#showinfo").regex("【豆瓣评分】：(.*?)<").toString();
                                if (!TextUtils.isEmpty(douban_score1)) {
                                    douban_score1 = douban_score1.replaceAll("</font>", "");
                                    String[] douban_score = douban_score1.trim().split("/");
                                    if (douban_score.length > 0 && !TextUtils.isEmpty(douban_score[0])) {
                                        String finalScore = StringUtil.disposeField(douban_score[0]);
                                        if(!TextUtils.isEmpty(finalScore) && !finalScore.equals("暂无评分")  && !finalScore.equals("0")  && !finalScore.equals("0.0")){
                                            if(finalScore.contains("分") && finalScore.length() == 4){
                                                sourceDetail.setDoubanScore(finalScore.substring(0, finalScore.length() - 1));
                                            }else{
                                                sourceDetail.setDoubanScore(StringUtil.disposeField(finalScore));
                                            }
                                        }
                                    }
                                    if (douban_score.length > 1 && !TextUtils.isEmpty(douban_score[1])) {
                                        sourceDetail.setDoubanIntro(StringUtil.disposeField(douban_score[1]));
                                    }
                                }else{
                                    String douban_score2 = page.getHtml().css("div#showinfo").regex("豆瓣评分(.*?)<").toString();
                                    if (!TextUtils.isEmpty(douban_score2)) {
                                        douban_score2 = douban_score2.replaceAll("</font>", "");
                                        String[] douban_score = douban_score2.trim().split("/");
                                        if (douban_score.length > 0 && !TextUtils.isEmpty(douban_score[0])) {
                                            String finalScore = StringUtil.disposeField(douban_score[0]);
                                            if(!TextUtils.isEmpty(finalScore) && !finalScore.equals("暂无评分")  && !finalScore.equals("0")  && !finalScore.equals("0.0")){
                                                if(finalScore.contains("分") && finalScore.length() == 4){
                                                    sourceDetail.setDoubanScore(finalScore.substring(0, finalScore.length() - 1));
                                                }else{
                                                    sourceDetail.setDoubanScore(StringUtil.disposeField(finalScore));
                                                }
                                            }
                                        }
                                        if (douban_score.length > 1 && !TextUtils.isEmpty(douban_score[1])) {
                                            sourceDetail.setDoubanIntro(StringUtil.disposeField(douban_score[1]));
                                        }
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        String file_format = page.getHtml().css("div#showinfo").regex("◎文件格式(.*?)<").toString();
                        if (!TextUtils.isEmpty(file_format)) {
                            file_format = file_format.replaceAll("</font>", "");
                            sourceDetail.setFileFormat(StringUtil.disposeField(file_format));
                        } else {
                            String file_format1 = page.getHtml().css("div#showinfo").regex("文件格式(.*?)<").toString();
                            if (!TextUtils.isEmpty(file_format1)) {
                                file_format1 = file_format1.replaceAll("</font>", "");
                                sourceDetail.setFileFormat(StringUtil.disposeField(file_format1));
                            }
                        }
                        String file_size = page.getHtml().css("div#showinfo").regex("◎视频尺寸(.*?)<").toString();
                        if (!TextUtils.isEmpty(file_size)) {
                            file_size = file_size.replaceAll("</font>", "");
                            sourceDetail.setFileSize(StringUtil.disposeField(file_size));
                        } else {
                            String file_size1 = page.getHtml().css("div#showinfo").regex("视频尺寸(.*?)<").toString();
                            if (!TextUtils.isEmpty(file_size1)) {
                                file_size1 = file_size1.replaceAll("</font>", "");
                                sourceDetail.setFileSize(StringUtil.disposeField(file_size1));
                            }
                        }
                        String file_amounts = page.getHtml().css("div#showinfo").regex("◎文件大小(.*?)<").toString();
                        if (!TextUtils.isEmpty(file_amounts)) {
                            file_amounts = file_amounts.replaceAll("</font>", "");
                            sourceDetail.setFileAmounts(StringUtil.disposeField(file_amounts));
                        } else {
                            String file_amounts1 = page.getHtml().css("div#showinfo").regex("文件大小(.*?)<").toString();
                            if (!TextUtils.isEmpty(file_amounts1)) {
                                file_amounts1 = file_amounts1.replaceAll("</font>", "");
                                sourceDetail.setFileAmounts(StringUtil.disposeField(file_amounts1));
                            }
                        }
                        String file_length = page.getHtml().css("div#showinfo").regex("◎片　　长(.*?)<").toString();
                        if (!TextUtils.isEmpty(file_length)) {
                            file_length = file_length.replaceAll("</font>", "");
                            sourceDetail.setFileLength(StringUtil.disposeField(file_length));
                        } else {
                            String file_length1 = page.getHtml().css("div#showinfo").regex("【片 &nbsp; &nbsp;　长】：(.*?)<").toString();
                            if (!TextUtils.isEmpty(file_length1)) {
                                file_length1 = file_length1.replaceAll("</font>", "");
                                sourceDetail.setFileLength(StringUtil.disposeField(file_length1));
                            } else {
                                String file_length2 = page.getHtml().css("div#showinfo").regex("片　　长(.*?)<").toString();
                                if (!TextUtils.isEmpty(file_length2)) {
                                    file_length2 = file_length2.replaceAll("</font>", "");
                                    sourceDetail.setFileLength(StringUtil.disposeField(file_length2));
                                }
                            }
                        }
                        String episodes = page.getHtml().css("div#showinfo").regex("◎集　　数(.*?)<").toString();
                        if (!TextUtils.isEmpty(episodes)) {
                            episodes = episodes.replaceAll("</font>", "");
                            sourceDetail.setEpisodes(StringUtil.disposeField(episodes));
                        } else {
                            String episodes1 = page.getHtml().css("div#showinfo").regex("【集 &nbsp; &nbsp;　数】：(.*?)<").toString();
                            if (!TextUtils.isEmpty(episodes1)) {
                                episodes1 = episodes1.replaceAll("</font>", "");
                                sourceDetail.setEpisodes(StringUtil.disposeField(episodes1));
                            } else {
                                String episodes2 = page.getHtml().css("div#showinfo").regex("[集 数]:(.*?)<").toString();
                                if (!TextUtils.isEmpty(episodes2)) {
                                    episodes2 = episodes2.replaceAll("</font>", "");
                                    sourceDetail.setEpisodes(StringUtil.disposeField(episodes2));
                                } else {
                                    String episodes3 = page.getHtml().css("div#showinfo").regex("集　　数(.*?)<").toString();
                                    if (!TextUtils.isEmpty(episodes3)) {
                                        episodes3 = episodes3.replaceAll("</font>", "");
                                        sourceDetail.setEpisodes(StringUtil.disposeField(episodes3));
                                    }
                                }
                            }
                        }
                        if (!TextUtils.isEmpty(sourceDetail.getContent())) {
                            String str = sourceDetail.getContent();
                            if (str.contains("◎编  剧")) {
                                String[] author0 = str.split("◎编  剧");
                                if (author0.length > 1 && !TextUtils.isEmpty(author0[1])) {
                                    String[] temp = author0[1].split("◎");
                                    sourceDetail.setAuthor(StringUtil.disposeField(temp[0]));
                                } else if (author0.length == 1 && !TextUtils.isEmpty(author0[0])) {
                                    String[] temp = author0[0].split("◎");
                                    sourceDetail.setAuthor(StringUtil.disposeField(temp[0]));
                                }
                            } else {
                                if (str.contains("【编     剧】：")) {
                                    String[] author0 = str.split("【编     剧】：");
                                    if (author0.length > 1 && !TextUtils.isEmpty(author0[1])) {
                                        String[] temp = author0[1].split("【");
                                        sourceDetail.setAuthor(StringUtil.disposeField(temp[0]));
                                    } else if (author0.length == 1 && !TextUtils.isEmpty(author0[0])) {
                                        String[] temp = author0[0].split("【");
                                        sourceDetail.setAuthor(StringUtil.disposeField(temp[0]));
                                    }
                                } else {
                                    if (str.contains("[编 剧]:")) {
                                        String[] author0 = str.split("[编 剧]:");
                                        if (author0.length > 1 && !TextUtils.isEmpty(author0[1])) {
                                            String[] temp = author0[1].split("\\[");
                                            sourceDetail.setAuthor(StringUtil.disposeField(temp[0]));
                                        } else if (author0.length == 1 && !TextUtils.isEmpty(author0[0])) {
                                            String[] temp = author0[0].split("\\[");
                                            sourceDetail.setAuthor(StringUtil.disposeField(temp[0]));
                                        }
                                    }
                                }
                            }
                            if (str.contains("◎导  演")) {
                                String[] director0 = str.split("◎导  演");
                                if (director0.length > 1 && !TextUtils.isEmpty(director0[1])) {
                                    String[] temp = director0[1].split("◎");
                                    sourceDetail.setDirector(StringUtil.disposeField(temp[0]));
                                } else if (director0.length == 1 && !TextUtils.isEmpty(director0[0])) {
                                    String[] temp = director0[0].split("◎");
                                    sourceDetail.setDirector(StringUtil.disposeField(temp[0]));
                                }
                            } else {
                                if (str.contains("【导     演】：")) {
                                    String[] director0 = str.split("【导     演】：");
                                    if (director0.length > 1 && !TextUtils.isEmpty(director0[1])) {
                                        String[] temp = director0[1].split("【");
                                        sourceDetail.setDirector(StringUtil.disposeField(temp[0]));
                                    } else if (director0.length == 1 && !TextUtils.isEmpty(director0[0])) {
                                        String[] temp = director0[0].split("【");
                                        sourceDetail.setDirector(StringUtil.disposeField(temp[0]));
                                    }
                                } else {
                                    if (str.contains("[导 演]:")) {
                                        String[] director0 = str.split("[导 演]:");
                                        if (director0.length > 1 && !TextUtils.isEmpty(director0[1])) {
                                            String[] temp = director0[1].split("\\[");
                                            sourceDetail.setDirector(StringUtil.disposeField(temp[0]));
                                        } else if (director0.length == 1 && !TextUtils.isEmpty(director0[0])) {
                                            String[] temp = director0[0].split("\\[");
                                            sourceDetail.setDirector(StringUtil.disposeField(temp[0]));
                                        }
                                    }
                                }
                            }
                            if (str.contains("◎主  演")) {
                                String[] performer0 = str.split("◎主  演");
                                if (performer0.length > 1 && !TextUtils.isEmpty(performer0[1])) {
                                    String[] temp = performer0[1].split("◎");
                                    sourceDetail.setPerformer(StringUtil.disposeField(temp[0]));
                                } else if (performer0.length == 1 && !TextUtils.isEmpty(performer0[0])) {
                                    String[] temp = performer0[0].split("◎");
                                    sourceDetail.setPerformer(StringUtil.disposeField(temp[0]));
                                }
                            } else {
                                if (str.contains("【演     员】：")) {
                                    String[] performer0 = str.split("【演     员】：");
                                    if (performer0.length > 1 && !TextUtils.isEmpty(performer0[1])) {
                                        String[] temp = performer0[1].split("【");
                                        sourceDetail.setPerformer(StringUtil.disposeField(temp[0]));
                                    } else if (performer0.length == 1 && !TextUtils.isEmpty(performer0[0])) {
                                        String[] temp = performer0[0].split("【");
                                        sourceDetail.setPerformer(StringUtil.disposeField(temp[0]));
                                    }
                                } else {
                                    if (str.contains("[演 员]:")) {
                                        String[] performer0 = str.split("[演 员]:");
                                        if (performer0.length > 1 && !TextUtils.isEmpty(performer0[1])) {
                                            String[] temp = performer0[1].split("\\[");
                                            sourceDetail.setPerformer(StringUtil.disposeField(temp[0]));
                                        } else if (performer0.length == 1 && !TextUtils.isEmpty(performer0[0])) {
                                            String[] temp = performer0[0].split("\\[");
                                            sourceDetail.setPerformer(StringUtil.disposeField(temp[0]));
                                        }
                                    }
                                }
                            }
                            if (str.contains("◎简  介")) {
                                String[] intro0 = str.split("◎简  介");
                                if (intro0.length > 1 && !TextUtils.isEmpty(intro0[1])) {
                                    String[] temp = intro0[1].split("◎");
                                    sourceDetail.setIntro(StringUtil.disposeField(temp[0]));
                                } else if (intro0.length == 1 && !TextUtils.isEmpty(intro0[0])) {
                                    String[] temp = intro0[0].split("◎");
                                    sourceDetail.setIntro(StringUtil.disposeField(temp[0]));
                                }
                            } else {
                                if (str.contains("【简     介】：")) {
                                    String[] intro0 = str.split("【简     介】：");
                                    if (intro0.length > 1 && !TextUtils.isEmpty(intro0[1])) {
                                        String[] temp = intro0[1].split("【");
                                        sourceDetail.setIntro(StringUtil.disposeField(temp[0]));
                                    } else if (intro0.length == 1 && !TextUtils.isEmpty(intro0[0])) {
                                        String[] temp = intro0[0].split("【");
                                        sourceDetail.setIntro(StringUtil.disposeField(temp[0]));
                                    }
                                } else {
                                    if (str.contains("[简 介]:")) {
                                        String[] intro0 = str.split("[简 介]:");
                                        if (intro0.length > 1 && !TextUtils.isEmpty(intro0[1])) {
                                            String[] temp = intro0[1].split("\\[");
                                            sourceDetail.setIntro(StringUtil.disposeField(temp[0]));
                                        } else if (intro0.length == 1 && !TextUtils.isEmpty(intro0[0])) {
                                            String[] temp = intro0[0].split("\\[");
                                            sourceDetail.setIntro(StringUtil.disposeField(temp[0]));
                                        }
                                    }
                                }
                            }
                            if (str.contains("◎获奖情况")) {
                                String[] awards0 = str.split("◎获奖情况");
                                if (awards0.length > 1 && !TextUtils.isEmpty(awards0[1])) {
                                    String[] temp = awards0[1].split("◎");
                                    sourceDetail.setAwards(StringUtil.disposeField(temp[0]));
                                } else if (awards0.length == 1 && !TextUtils.isEmpty(awards0[0])) {
                                    String[] temp = awards0[0].split("◎");
                                    sourceDetail.setAwards(StringUtil.disposeField(temp[0]));
                                }
                            }
                        }

                    }
                }

                page.putField("url", url);
                page.putField("sourceDetail", sourceDetail);
            } else {
                String newUrl = page.getHtml().css("script").regex("window.location.href='(.*?)'").toString();
                if (!TextUtils.isEmpty(newUrl)) {
                    page.addTargetRequest(URLUtil.URL_PHDY_HOME_PAGE + newUrl);
                }
            }
        } else {
            setCookie();
            page.addTargetRequest(url);
        }

    }


    public void setCookie() {
        String expiredays = "";
        if (!TextUtils.isEmpty(expiredays0)) {
            Date date = new Date();
            date.setDate(date.getDate() + 365);
            expiredays = "; path=/; expires=" + date.toGMTString();
        }
        String escape = "";
        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine engine = sem.getEngineByExtension("js");

        try {
            escape = engine.eval(" escape('" + value0 + "')").toString();
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        String c_name = "";
        if (!TextUtils.isEmpty(c_name0)) {
            c_name = c_name0;
        }
        PhdySourceDetailProcessor.this.getSite().addCookie(c_name, escape);
        PhdySourceDetailProcessor.this.getSite().addCookie("path", "/");
        PhdySourceDetailProcessor.this.getSite().addCookie("expires", expiredays);
    }
}
