package com.lxw.videoworld.spider;

import us.codecraft.webmagic.Page;

import java.util.List;

/**
 * Created by Zion on 2017/4/22.
 */
public class MpdyMenuListProcessor extends BasePhdyProcessor {

    @Override
    public void process(Page page) {
        super.process(page);
        List<String> urlList = page.getHtml().css("div.caption").links().all();
        page.putField("urlList", urlList);
    }
}
