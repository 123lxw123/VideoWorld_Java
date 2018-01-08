package com.lxw.videoworld.spider;

import com.lxw.videoworld.domain.SourceDetail;
import com.lxw.videoworld.utils.StringUtil;
import org.apache.http.util.TextUtils;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zion on 2017/12/31.
 */
@Service("zxzySourceDetailProcessor")
public class ZxzySourceDetailProcessor extends BasePhdyProcessor {

    @Override
    public void process(Page page) {
        super.process(page);
        SourceDetail sourceDetail = new SourceDetail();
        String title = page.getHtml().css("h2").get();
        String url = page.getUrl().toString();
        List<String> images = page.getHtml().css("img.lazy", "src").all();
        String type = page.getHtml().css("dd").css("a").all().get(1);
        String translateName = page.getHtml().css("li").regex("别名：<span>(.*?)</span>").toString();
        String director = page.getHtml().css("li").regex("导演：<span>(.*?)</span>").toString();
        String performer = page.getHtml().css("li").regex("主演：<span>(.*?)</span>").toString();
        String style = page.getHtml().css("li").regex("类型：<span>(.*?)</span>").toString();
        String area = page.getHtml().css("li").regex("地区：<span>(.*?)</span>").toString();
        String language = page.getHtml().css("li").regex("语言：<span>(.*?)</span>").toString();
        String releaseDate = page.getHtml().css("li").regex("上映：<span>(.*?)</span>").toString();
        String fileLength = page.getHtml().css("li").regex("片长：<span>(.*?)</span>").toString();
        String date = page.getHtml().css("li").regex("更新：<span>(.*?)</span>").toString();
        String doubanScore = page.getHtml().css("lable").toString();
        String intro = page.getHtml().regex("<!-- 开始 -->(.*?)<!--结束-->").toString();
        sourceDetail.setTitle(StringUtil.disposeField(title));
        sourceDetail.setName(StringUtil.disposeField(title));
        sourceDetail.setUrl(StringUtil.disposeField(url));
        type = StringUtil.disposeField(type);
        if (type.contains("动漫")) sourceDetail.setCategory("dm");
        else if (type.contains("综艺")) sourceDetail.setCategory("zy");
        else if (type.contains("片")) sourceDetail.setCategory("dy");
        else if (type.contains("剧")) sourceDetail.setCategory("ds");
        sourceDetail.setType(type);
        sourceDetail.setTranslateName(StringUtil.disposeField(translateName));
        sourceDetail.setDirector(StringUtil.disposeField(director));
        sourceDetail.setPerformer(StringUtil.disposeField(performer));
        sourceDetail.setStyle(StringUtil.disposeField(style));
        sourceDetail.setArea(StringUtil.disposeField(area));
        sourceDetail.setLanguage(StringUtil.disposeField(language));
        sourceDetail.setReleaseDate(StringUtil.disposeField(releaseDate));
        if (!StringUtil.disposeField(fileLength).isEmpty() && !StringUtil.disposeField(fileLength).equals("0"))
            sourceDetail.setFileLength(StringUtil.disposeField(fileLength));
        if (images != null && images.size() > 0) {
            sourceDetail.setImages(StringUtil.disposeField(images.toString()));
        }
        if (!TextUtils.isEmpty(date) && date.length() >= 10){
            date = date.substring(0, 4) + date.substring(5, 7) + date.substring(8, 10);
            sourceDetail.setDate(StringUtil.disposeField(date));
        }
        if (!TextUtils.isEmpty(doubanScore) && !doubanScore.equals("0.0"))
            sourceDetail.setDoubanScore(StringUtil.disposeField(doubanScore));
        if (!TextUtils.isEmpty(StringUtil.disposeField(intro))){
            intro = StringUtil.disposeField(intro);
            if (intro.startsWith("剧情介绍：")) intro = intro.substring("剧情介绍：".length(), intro.length());
            sourceDetail.setIntro(StringUtil.disposeField(intro));
        }
        List<String> links = page.getHtml().css("input").regex("\"(http.*?)\"").all();
        if (links != null && links.size() > 0){
            List<String> newLinks = new ArrayList<>();
            for (int i = 0; i < links.size(); i++){
                if (links.get(i).contains("m3u8")) newLinks.add(links.get(i));
            }
            if (newLinks.size() > 0) links = newLinks;
            sourceDetail.setLinks(links.toString());
        }
        page.putField("sourceDetail", sourceDetail);
        page.putField("links", links);
        page.putField("url", url);
    }
}