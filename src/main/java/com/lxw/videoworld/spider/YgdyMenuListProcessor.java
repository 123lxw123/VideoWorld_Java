package com.lxw.videoworld.spider;

import us.codecraft.webmagic.Page;

import java.util.List;

/**
 * Created by Zion on 2017/4/22.
 */
public class YgdyMenuListProcessor extends BaseYgdyProcessor {

    @Override
    public void process(Page page) {
        super.process(page);
        List<String> urlList = page.getHtml().css("div.co_content8").css("a.ulink").links().all();
        page.putField("urlList", urlList);
    }
}
