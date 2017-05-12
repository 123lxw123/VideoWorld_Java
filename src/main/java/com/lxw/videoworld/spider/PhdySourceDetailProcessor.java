package com.lxw.videoworld.spider;

import com.lxw.videoworld.config.Constants;
import com.lxw.videoworld.domain.SourceDetail;
import com.lxw.videoworld.utils.HTMLUtil;
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
    private  String value0;
    private  String c_name0;
    private  String expiredays0;
    @Override
    public void process(Page page) {
        super.process(page);
        String url = page.getUrl().toString();
        value0 = page.getHtml().regex("'cookie': \"(.*?)\"").toString();
        c_name0 = page.getHtml().regex("'(.*?)', data['cookie']").toString();
        expiredays0 = page.getHtml().regex("data['cookie'], (\\d*)").toString();
        if(TextUtils.isEmpty(value0)){
            SourceDetail sourceDetail = new SourceDetail();
            sourceDetail.setUrl(url);
            if (!TextUtils.isEmpty(url)) {
                String[] params = url.split("/");
                if (params.length == 8) {
                    if (!TextUtils.isEmpty(params[7]) && params[7].length() > 5) {
                        sourceDetail.setId(params[7].substring(0, params[7].length() - 5));
                    }
                    sourceDetail.setCategory(params[4]);
                    sourceDetail.setDate(Integer.valueOf(params[5] + params[6]));
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
                            newContent.add(content);
                        }
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
                    sourceDetail.setTitle(title.trim());
                }
                if (imgUrl != null && imgUrl.size() > 0) {
                    sourceDetail.setImages(imgUrl.toString());
                }
                if (links != null && links.size() > 0) {
                    sourceDetail.setLinks(links.toString());
                }
                if (!TextUtils.isEmpty(newContent.toString())) {
                    sourceDetail.setContent(newContent.toString().trim());
                }
                sourceDetail.setStatus(Constants.STATUS_2);

                // 获取视频详情各种属性
                if (!TextUtils.isEmpty(sourceDetail.getCategory()) && !sourceDetail.getCategory().equals(Constants.CATEGORY_2) && !sourceDetail.getCategory().equals(Constants.CATEGORY_3)) {
                    String name = page.getHtml().css("div.showinfo").regex("◎片　　名(.*?)<").toString();
                    if (!TextUtils.isEmpty(name)) {
                        sourceDetail.setName(name.trim());
                    }
                    String translate_name = page.getHtml().css("div.showinfo").regex("◎译　　名(.*?)<").toString();
                    if (!TextUtils.isEmpty(translate_name)) {
                        sourceDetail.setTranslateName(translate_name.trim());
                    }
                    String year = page.getHtml().css("div.showinfo").regex("◎年　　代(.*?)<").toString();
                    if (!TextUtils.isEmpty(year)) {
                        sourceDetail.setYear(Integer.valueOf(year.trim()));
                    }
                    String area = page.getHtml().css("div.showinfo").regex("◎产　　地(.*?)<").toString();
                    if (!TextUtils.isEmpty(area)) {
                        sourceDetail.setArea(area.trim());
                    }
                    String style = page.getHtml().css("div.showinfo").regex("◎类　　别(.*?)<").toString();
                    if (!TextUtils.isEmpty(style)) {
                        sourceDetail.setStyle(style.trim());
                    }
                    String language = page.getHtml().css("div.showinfo").regex("◎语　　言(.*?)<").toString();
                    if (!TextUtils.isEmpty(language)) {
                        sourceDetail.setLanguage(language.trim());
                    }
                    String subtitles = page.getHtml().css("div.showinfo").regex("◎字　　幕(.*?)<").toString();
                    if (!TextUtils.isEmpty(subtitles)) {
                        sourceDetail.setSubtitles(subtitles.trim());
                    }
                    String release_date = page.getHtml().css("div.showinfo").regex("◎上映日期(.*?)<").toString();
                    if (!TextUtils.isEmpty(release_date)) {
                        sourceDetail.setReleaseDate(release_date.trim());
                    }
                    String imdb_score0 = page.getHtml().css("div.showinfo").regex("◎IMDb评分(.*?)<").toString();
                    if (!TextUtils.isEmpty(imdb_score0)) {
                        String[] imdb_score = imdb_score0.trim().split("/");
                        sourceDetail.setImdbScore(Float.valueOf(imdb_score[0]));
                        sourceDetail.setImdbIntro(imdb_score[1]);
                    }
                    String douban_score0 = page.getHtml().css("div.showinfo").regex("◎豆瓣评分(.*?)<").toString();
                    if (!TextUtils.isEmpty(douban_score0)) {
                        String[] douban_score = douban_score0.trim().split("/");
                        sourceDetail.setDoubanScore(Float.valueOf(douban_score[0]));
                        sourceDetail.setDoubanIntro(douban_score[1]);
                    }
                    String file_format = page.getHtml().css("div.showinfo").regex("◎文件格式(.*?)<").toString();
                    if (!TextUtils.isEmpty(file_format)) {
                        sourceDetail.setFileFormat(file_format.trim());
                    }
                    String file_size = page.getHtml().css("div.showinfo").regex("◎视频尺寸(.*?)<").toString();
                    if (!TextUtils.isEmpty(file_size)) {
                        sourceDetail.setFileSize(file_size.trim());
                    }
                    String file_amounts = page.getHtml().css("div.showinfo").regex("◎文件大小(.*?)<").toString();
                    if (!TextUtils.isEmpty(file_amounts)) {
                        sourceDetail.setFileAmounts(file_amounts.trim());
                    }
                    String file_length = page.getHtml().css("div.showinfo").regex("◎片　　长(.*?)<").toString();
                    if (!TextUtils.isEmpty(file_length)) {
                        sourceDetail.setFileLength(file_length.trim());
                    }
                    String episodes = page.getHtml().css("div.showinfo").regex("◎集　　数(.*?)<").toString();
                    if (!TextUtils.isEmpty(episodes)) {
                        sourceDetail.setEpisodes(episodes.trim());
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
        }else{
            setCookie();
            page.addTargetRequest(url);
        }

    }


    public void setCookie(){
        String expiredays = "";
        if(!TextUtils.isEmpty(expiredays0)){
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
        if(!TextUtils.isEmpty(c_name0)){
            c_name = c_name0;
        }
        PhdySourceDetailProcessor.this.getSite().addCookie(c_name, escape);
        PhdySourceDetailProcessor.this.getSite().addCookie("path", "/");
        PhdySourceDetailProcessor.this.getSite().addCookie("expires", expiredays);
    }
}
