package com.lxw.videoworld.spider;

import us.codecraft.webmagic.Page;

import java.util.List;

/**
 * Created by lxw9047 on 2017/4/28.
 */
public class YgdySourceDetailProcessor extends BaseProcessor {
    @Override
    public void process(Page page) {
        super.process(page);
        String title = page.getHtml().css("div.title_all").css("font").regex(">(.*?)</font>").get();
        List<String> urlList = page.getHtml().css("div.co_content2").links().all();
        List<String> titleList = page.getHtml().css("div.co_content2").css("a").regex(">(.*?)</a>").all();
        page.putField("urlList", urlList);
        page.putField("titleList", titleList);
    }


}
