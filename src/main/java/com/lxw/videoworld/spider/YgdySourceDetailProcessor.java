package com.lxw.videoworld.spider;

import com.lxw.videoworld.config.Constants;
import com.lxw.videoworld.domain.SourceDetail;
import com.lxw.videoworld.utils.HTMLUtil;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.http.util.TextUtils;
import us.codecraft.webmagic.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lxw9047 on 2017/4/28.
 */
public class YgdySourceDetailProcessor extends BaseYgdyProcessor {
    @Override
    public void process(Page page) {
        super.process(page);
        SourceDetail sourceDetail = new SourceDetail();
        String url = page.getUrl().toString();
        if(!TextUtils.isEmpty(url)){
            String[] params =  url.split("/");
            if(params.length == 8){
                if(!TextUtils.isEmpty(params[7]) && params[7].length() > 5){
                    sourceDetail.setId(params[7].substring(0, params[7].length() - 5));
                }
                sourceDetail.setCategory(params[4]);
                sourceDetail.setType(params[5]);
                if(!TextUtils.isEmpty(params[6]) && params[6].length() == 8){
                    sourceDetail.setDate(Integer.valueOf(params[6]));
                }
            }else {

            }
        }
        String title = page.getHtml().css("div.title_all").css("font").regex(">(.*?)</font>").get();
        List<String> imgUrl = page.getHtml().css("div.co_content8").css("img", "src").all();
        String content = "";
        String content0 = page.getHtml().css("div#Zoom").toString();
        List<String> imgs = page.getHtml().css("div#Zoom").css("img").all();
        try {
            if (!TextUtils.isEmpty(content0) && imgs != null) {
                if (imgs.size() >= 2) {
                    int start = content0.indexOf(imgs.get(0)) + imgs.get(0).length();
                    int end = content0.indexOf(imgs.get(1));
                    content = content0.substring(start, end);
                    content = StringEscapeUtils.unescapeHtml(content).replaceAll("<br/>", "\n").replaceAll("<br>", "\n")
                            .replaceAll("<br />", "\n").replaceAll("<p>", "").replaceAll("</p>", "\n");
                    content = HTMLUtil.trimHtml2Txt(content, null);

                    if (TextUtils.isEmpty(content.trim()) && imgs.size() >= 3) {
                        int start1 = content0.indexOf(imgs.get(1)) + imgs.get(1).length();
                        int end1 = content0.indexOf(imgs.get(2));
                        content = content0.substring(start1, end1);
                        content = StringEscapeUtils.unescapeHtml(content).replaceAll("<br/>", "\n").replaceAll("<br>", "\n")
                                .replaceAll("<br />", "\n").replaceAll("<p>", "").replaceAll("</p>", "\n");
                        content = HTMLUtil.trimHtml2Txt(content, null);
                        if (TextUtils.isEmpty(content.trim()) && imgs.size() >= 4) {
                            int start2 = content0.indexOf(imgs.get(2)) + imgs.get(2).length();
                            int end2 = content0.indexOf(imgs.get(3));
                            content = content0.substring(start2, end2);
                        }
                    }
                } else if (imgs.size() == 1) {
                    int start = content0.indexOf(imgs.get(0)) + imgs.get(0).length();
                    int end = content0.indexOf("下载地址");
                    content = content0.substring(start, end);
                } else if (imgs.size() == 0) {
                    String div = "<div id=\"Zoom\">";
                    int start = content0.indexOf(div) + div.length();
                    int end = content0.indexOf("下载地址");
                    content = content0.substring(start, end);
                }
            }
            content = StringEscapeUtils.unescapeHtml(content).replaceAll("<br/>", "\n").replaceAll("<br>", "\n")
                    .replaceAll("<br />", "\n").replaceAll("<p>", "").replaceAll("</p>", "\n");
            content = HTMLUtil.trimHtml2Txt(content, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> links = new ArrayList<>();
        List<String> links0 = page.getHtml().css("div.co_content8").css("table").links().all();
        if (links0 != null && links0.size() > 0) {
            for (int i = 0; i < links0.size(); i++) {
                if (!links0.get(i).contains("html")) {
                    links.add(links0.get(i));
                }
            }
        }

        sourceDetail.setUrl(url);
        if(!TextUtils.isEmpty(title)){
            sourceDetail.setTitle(title.trim());
        }
        if(imgUrl != null && imgUrl.size() > 0){
            sourceDetail.setImages(imgUrl.toString());
        }
        if(links != null && links.size() > 0){
            sourceDetail.setLinks(links.toString());
        }
        if(!TextUtils.isEmpty(content)){
            sourceDetail.setContent(content.trim());
        }
        sourceDetail.setStatus(Constants.STATUS_2);

        // 获取视频详情各种属性
        if(!TextUtils.isEmpty(sourceDetail.getCategory()) && !sourceDetail.getCategory().equals(Constants.CATEGORY_2) && !sourceDetail.getCategory().equals(Constants.CATEGORY_3)){
            if(sourceDetail.getCategory().equals(Constants.CATEGORY_5)){
                String name = page.getHtml().css("div#Zoom").regex("中文名称:(.*?)<").toString();
                if(!TextUtils.isEmpty(name)){
                    sourceDetail.setName(name.trim());
                }
                String style = page.getHtml().css("div#Zoom").regex("游戏类型:(.*?)<").toString();
                if(!TextUtils.isEmpty(style)){
                    sourceDetail.setStyle(style.trim());
                }
            }else{
                String name = page.getHtml().css("div#Zoom").regex("◎片　　名(.*?)<").toString();
                if(!TextUtils.isEmpty(name)){
                    sourceDetail.setName(name.trim());
                }
                String translate_name = page.getHtml().css("div#Zoom").regex("◎译　　名(.*?)<").toString();
                if(!TextUtils.isEmpty(translate_name)){
                    sourceDetail.setTranslateName(translate_name.trim());
                }
                String year = page.getHtml().css("div#Zoom").regex("◎年　　代(.*?)<").toString();
                if(!TextUtils.isEmpty(year)){
                    sourceDetail.setYear(Integer.valueOf(year.trim()));
                }
                String area = page.getHtml().css("div#Zoom").regex("◎产　　地(.*?)<").toString();
                if(!TextUtils.isEmpty(area)){
                    sourceDetail.setArea(area.trim());
                }
                String style = page.getHtml().css("div#Zoom").regex("◎类　　别(.*?)<").toString();
                if(!TextUtils.isEmpty(style)){
                    sourceDetail.setStyle(style.trim());
                }
                String language = page.getHtml().css("div#Zoom").regex("◎语　　言(.*?)<").toString();
                if(!TextUtils.isEmpty(language)){
                    sourceDetail.setLanguage(language.trim());
                }
                String subtitles = page.getHtml().css("div#Zoom").regex("◎字　　幕(.*?)<").toString();
                if(!TextUtils.isEmpty(subtitles)){
                    sourceDetail.setSubtitles(subtitles.trim());
                }
                String release_date = page.getHtml().css("div#Zoom").regex("◎上映日期(.*?)<").toString();
                if(!TextUtils.isEmpty(release_date)){
                    sourceDetail.setReleaseDate(release_date.trim());
                }
                String imdb_score0 = page.getHtml().css("div#Zoom").regex("◎IMDb评分(.*?)<").toString();
                if(!TextUtils.isEmpty(imdb_score0)){
                    String[] imdb_score = imdb_score0.trim().split("/");
                    sourceDetail.setImdbScore(Float.valueOf(imdb_score[0]));
                    sourceDetail.setImdbIntro(imdb_score[1]);
                }
                String douban_score0 = page.getHtml().css("div#Zoom").regex("◎豆瓣评分(.*?)<").toString();
                if(!TextUtils.isEmpty(douban_score0)){
                    String[] douban_score = douban_score0.trim().split("/");
                    sourceDetail.setDoubanScore(Float.valueOf(douban_score[0]));
                    sourceDetail.setDoubanIntro(douban_score[1]);
                }
                String file_format = page.getHtml().css("div#Zoom").regex("◎文件格式(.*?)<").toString();
                if(!TextUtils.isEmpty(file_format)){
                    sourceDetail.setFileFormat(file_format.trim());
                }
                String file_size = page.getHtml().css("div#Zoom").regex("◎视频尺寸(.*?)<").toString();
                if(!TextUtils.isEmpty(file_size)){
                    sourceDetail.setFileSize(file_size.trim());
                }
                String file_amounts = page.getHtml().css("div#Zoom").regex("◎文件大小(.*?)<").toString();
                if(!TextUtils.isEmpty(file_amounts)){
                    sourceDetail.setFileAmounts(file_amounts.trim());
                }
                String file_length = page.getHtml().css("div#Zoom").regex("◎片　　长(.*?)<").toString();
                if(!TextUtils.isEmpty(file_length)){
                    sourceDetail.setFileLength(file_length.trim());
                }
                String episodes = page.getHtml().css("div#Zoom").regex("◎集　　数(.*?)<").toString();
                if(!TextUtils.isEmpty(episodes)){
                    sourceDetail.setEpisodes(episodes.trim());
                }
            }
        }

        page.putField("sourceDetail", sourceDetail);
        page.putField("url", url);
    }


}
