package com.lxw.videoworld.spider;

import com.lxw.videoworld.config.Constants;
import com.lxw.videoworld.domain.SourceDetail;
import org.apache.http.util.TextUtils;
import us.codecraft.webmagic.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lxw9047 on 2017/4/28.
 */
public class MpdySourceDetailProcessor extends BasePhdyProcessor {
    @Override
    public void process(Page page) {
        super.process(page);
        SourceDetail sourceDetail = new SourceDetail();
        String url = page.getUrl().toString();
        String[] url0 = url.split("/");
        sourceDetail.setCategory(url0[3]);
        sourceDetail.setId(url0[4].substring(0, url0[4].length() - 5));
        String title = page.getHtml().css("h2.page-header").regex(">(.*?)</h2>").get();
        String date = "";
        String date0 = page.getHtml().css("div.media-heading").regex("发布时间: (.*?)</p>").toString();
        if(!TextUtils.isEmpty(date0) && date0.length() == 10){
            String[] string = date0.split("-");
            date = string[0] + string[1] + string[2];
        }
        List<String> imgUrl = new ArrayList<>();
        List<String> imgUrl0 = page.getHtml().css("div.col-md-7").css("img", "src").all();
        if(imgUrl0 != null && imgUrl0.size() > 1){
            for(int j = 1; j < imgUrl0.size(); j++){
                imgUrl.add(imgUrl0.get(j));
            }
        }
//        List<String> content0 = page.getHtml().css("div#showinfo").regex("<div>(.*?)</div>").all();
//        List<String> newContent = new ArrayList<>();
//        if (content0 != null && content0.size() > 0) {
//            for (int j = 0; j < content0.size(); j++) {
//                String content = content0.get(j);
//                if (!TextUtils.isEmpty(content)) {
//                    content = StringEscapeUtils.unescapeHtml(content).replaceAll("<br/>", "\n").replaceAll("<br>", "\n")
//                            .replaceAll("<br />", "\n").replaceAll("<p>", "").replaceAll("</p>", "\n");
//                    content = HTMLUtil.trimHtml2Txt(content, null);
//                    newContent.add(content);
//                }
//            }
//        }
        List<String> links = new ArrayList<>();
        List<String> oldLinks = new ArrayList<>();
        List<String> links4 = page.getHtml().css("table").links().all();
        if(links4 != null && links4.size() > 0){
            oldLinks.addAll(links4);
        }else{
            List<String> links0 = page.getHtml().css("div.col-md-7").css("a","href").all();
//            List<String> links1 = page.getHtml().css("div.col-md-7").css("a","href").regex("(ftp://.*?)\"").all();
//            List<String> links2 = page.getHtml().css("div.col-md-7").css("a","href").regex("(magnet:.*?)\"").all();
//            List<String> links3 = page.getHtml().css("div.col-md-7").css("a","href").regex("href=\"(.*?torrent)\"").all();

            if (links0 != null && links0.size() > 0) {
                for(int i = 0; i < links0.size(); i++){
                    if(!TextUtils.isEmpty(links0.get(i))  && (oldLinks.get(i).contains("ed2k") ||
                            oldLinks.get(i).contains("ftp") || oldLinks.get(i).contains("magnet") || oldLinks.get(i).contains("torrent"))){
                        oldLinks.add(links0.get(i));
                    }
                }
            }
//            if (links1 != null && links1.size() > 0) {
//                oldLinks.addAll(links1);
//            }
//            if (links2 != null && links2.size() > 0) {
//                oldLinks.addAll(links2);
//            }
//            if (links3 != null && links3.size() > 0) {
//                oldLinks.addAll(links3);
//            }
        }
        if(oldLinks != null && oldLinks.size() > 0){
            for(int i = 0; i < oldLinks.size(); i++){
                if(!TextUtils.isEmpty(oldLinks.get(i))){
                    links.add(oldLinks.get(i));
                }
            }
        }
        sourceDetail.setUrl(url);
        if(!TextUtils.isEmpty(title)){
            sourceDetail.setTitle(title.trim());
        }
        if(!TextUtils.isEmpty(date) && date.length() == 8){
            sourceDetail.setDate(Integer.valueOf(date));
        }
        if(imgUrl != null && imgUrl.size() > 0){
            sourceDetail.setImages(imgUrl.toString());
        }
        if(links != null && links.size() > 0){
            sourceDetail.setLinks(links.toString());
        }

        // 获取视频详情各种属性
        if (!TextUtils.isEmpty(sourceDetail.getCategory()) && !sourceDetail.getCategory().equals(Constants.CATEGORY_2) && !sourceDetail.getCategory().equals(Constants.CATEGORY_3)) {
            String name = page.getHtml().css("div.showinfo").regex("片名:(.*?)<").toString();
            if (!TextUtils.isEmpty(name)) {
                sourceDetail.setName(name.trim());
            }
            String translate_name = page.getHtml().css("div.showinfo").regex("译名:(.*?)<").toString();
            if (!TextUtils.isEmpty(translate_name)) {
                sourceDetail.setTranslateName(translate_name.trim());
            }
            String year = page.getHtml().css("div.showinfo").regex("年代:(.*?)<").toString();
            if (!TextUtils.isEmpty(year)) {
                sourceDetail.setYear(Integer.valueOf(year.trim()));
            }
            String area = page.getHtml().css("div.showinfo").regex("国家:(.*?)<").toString();
            if (!TextUtils.isEmpty(area)) {
                sourceDetail.setArea(area.trim());
            }else{
                String area1 = page.getHtml().css("div.showinfo").regex("地区:(.*?)<").toString();
                if (!TextUtils.isEmpty(area1)) {
                    sourceDetail.setArea(area1.trim());
                }
            }
            String style = page.getHtml().css("div.showinfo").regex("类别:(.*?)<").toString();
            if (!TextUtils.isEmpty(style)) {
                sourceDetail.setStyle(style.trim());
            }
            String language = page.getHtml().css("div.showinfo").regex("语言:(.*?)<").toString();
            if (!TextUtils.isEmpty(language)) {
                sourceDetail.setLanguage(language.trim());
            }
            String subtitles = page.getHtml().css("div.showinfo").regex("字幕:(.*?)<").toString();
            if (!TextUtils.isEmpty(subtitles)) {
                sourceDetail.setSubtitles(subtitles.trim());
            }
            String release_date = page.getHtml().css("div.showinfo").regex("上映日期:(.*?)<").toString();
            if (!TextUtils.isEmpty(release_date)) {
                sourceDetail.setReleaseDate(release_date.trim());
            }else {
                String release_date1 = page.getHtml().css("div.showinfo").regex("首映日期:(.*?)<").toString();
                if (!TextUtils.isEmpty(release_date1)) {
                    sourceDetail.setReleaseDate(release_date1.trim());
                }
            }
            String imdb_score0 = page.getHtml().css("div.showinfo").regex("IMDb评分:(.*?)<").toString();
            if (!TextUtils.isEmpty(imdb_score0)) {
                String[] imdb_score = imdb_score0.trim().split("/");
                sourceDetail.setImdbScore(Float.valueOf(imdb_score[0]));
                sourceDetail.setImdbIntro(imdb_score[1]);
            }
            String douban_score0 = page.getHtml().css("div.showinfo").regex("豆瓣评分:(.*?)<").toString();
            if (!TextUtils.isEmpty(douban_score0)) {
                String[] douban_score = douban_score0.trim().split("/");
                sourceDetail.setDoubanScore(Float.valueOf(douban_score[0]));
                sourceDetail.setDoubanIntro(douban_score[1]);
            }
            String file_format = page.getHtml().css("div.showinfo").regex("文件格式:(.*?)<").toString();
            if (!TextUtils.isEmpty(file_format)) {
                sourceDetail.setFileFormat(file_format.trim());
            }
            String file_size = page.getHtml().css("div.showinfo").regex("视频尺寸:(.*?)<").toString();
            if (!TextUtils.isEmpty(file_size)) {
                sourceDetail.setFileSize(file_size.trim());
            }
            String file_amounts = page.getHtml().css("div.showinfo").regex("文件大小:(.*?)<").toString();
            if (!TextUtils.isEmpty(file_amounts)) {
                sourceDetail.setFileAmounts(file_amounts.trim());
            }
            String file_length = page.getHtml().css("div.showinfo").regex("片长:(.*?)<").toString();
            if (!TextUtils.isEmpty(file_length)) {
                sourceDetail.setFileLength(file_length.trim());
            }
            String episodes = page.getHtml().css("div.showinfo").regex("集数:(.*?)<").toString();
            if (!TextUtils.isEmpty(episodes)) {
                sourceDetail.setEpisodes(episodes.trim());
            }
        }
        sourceDetail.setStatus(Constants.STATUS_2);
        page.putField("url", url);
        page.putField("sourceDetail", sourceDetail);
//        page.putField("content", newContent.toString());
    }


}
