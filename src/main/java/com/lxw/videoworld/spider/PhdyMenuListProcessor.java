package com.lxw.videoworld.spider;

import us.codecraft.webmagic.Page;

import java.util.List;

/**
 * Created by Zion on 2017/4/22.
 */
public class PhdyMenuListProcessor extends BaseProcessor {

    @Override
    public void process(Page page) {
        super.process(page);
        List<String> urlList = page.getHtml().css("div#list").css("a#img").links().all();
        List<String> imgList = page.getHtml().css("div#list").css("img", "src").all();
        List<String> titleList = page.getHtml().css("div#list").css("strong").css("a").regex(">(.*?)</a>").all();
        page.putField("urlList", urlList);
        page.putField("imgList", imgList);
        page.putField("titleList", titleList);
    }
}
