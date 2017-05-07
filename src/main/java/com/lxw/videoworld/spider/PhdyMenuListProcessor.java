package com.lxw.videoworld.spider;

import us.codecraft.webmagic.Page;

import java.util.List;

/**
 * Created by Zion on 2017/4/22.
 */
public class PhdyMenuListProcessor extends BasePhdyProcessor {

    @Override
    public void process(Page page) {
        super.process(page);
        List<String> urlList = page.getHtml().css("div#list").css("a.img").links().all();
        page.putField("urlList", urlList);
    }
}
