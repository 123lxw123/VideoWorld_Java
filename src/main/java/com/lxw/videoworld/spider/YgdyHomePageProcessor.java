package com.lxw.videoworld.spider;

import us.codecraft.webmagic.Page;

import java.util.List;

/**
 * Created by Zion on 2017/4/18.
 */
public class YgdyHomePageProcessor extends BaseProcessor{
    @Override
    public void process(Page page) {
        super.process(page);
        List<String> urlList = page.getHtml().css("div.co_content2").links().all();
        List<String> titleList = page.getHtml().css("div.co_content2").css("a").regex(">(.*?)</a>").all();
//        List<String> urlList = page.getHtml().css("div.co_content4").links().all();
//        List<String> titleList = page.getHtml().css("div.co_content4").css("a").regex(">(.*?)</a>").all();
        urlList.addAll(page.getHtml().css("div.co_content4").links().all());
        titleList.addAll(page.getHtml().css("div.co_content4").css("a").regex(">(.*?)</a>").all());
        page.putField("urlList", urlList);
        page.putField("titleList", titleList);
    }
}
