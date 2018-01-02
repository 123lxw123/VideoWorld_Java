package com.lxw.videoworld.spider;

import com.lxw.videoworld.domain.SourceDetail;
import com.lxw.videoworld.utils.StringUtil;
import org.apache.http.util.TextUtils;
import us.codecraft.webmagic.Page;

import java.util.List;

/**
 * Created by Zion on 2017/12/31.
 */
public class ZxzySourceDetailProcessor extends BasePhdyProcessor {
    @Override
    public void process(Page page) {
        super.process(page);
        SourceDetail sourceDetail = new SourceDetail();
        String title = page.getHtml().css("h2").get();
        String url = page.getUrl().toString();
        String image = page.getHtml().css("img.lazy", "src").links().toString();
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
        String intro = page.getHtml().css("div.vodplayinfo").toString();
        sourceDetail.setTitle(StringUtil.disposeField(title));
        sourceDetail.setName(StringUtil.disposeField(title));
        sourceDetail.setUrl(StringUtil.disposeField(url));
        sourceDetail.setType(StringUtil.disposeField(type));
        sourceDetail.setTranslateName(StringUtil.disposeField(translateName));
        sourceDetail.setDirector(StringUtil.disposeField(director));
        sourceDetail.setPerformer(StringUtil.disposeField(performer));
        sourceDetail.setStyle(StringUtil.disposeField(style));
        sourceDetail.setArea(StringUtil.disposeField(area));
        sourceDetail.setLanguage(StringUtil.disposeField(language));
        sourceDetail.setReleaseDate(StringUtil.disposeField(releaseDate));
        sourceDetail.setFileLength(StringUtil.disposeField(fileLength));
        if (!TextUtils.isEmpty(date) && date.length() >= 10){
            date = date.substring(0, 4) + date.substring(5, 7) + date.substring(8, 10);
            sourceDetail.setDate(StringUtil.disposeField(date));
        }
        if (!TextUtils.isEmpty(doubanScore) && !doubanScore.equals("0.0"))
            sourceDetail.setDoubanScore(StringUtil.disposeField(doubanScore));
        sourceDetail.setDate(StringUtil.disposeField(intro));
        List<String> links = page.getHtml().css("input").regex("\"(http.*?)\"").all();
        if (links != null && links.size() > 0)
            sourceDetail.setLinks(links.toString());
        page.putField("sourceDetail", sourceDetail);
        page.putField("url", url);
    }
}