package com.lxw.videoworld.spider;

import us.codecraft.webmagic.Page;

import java.util.List;

/**
 * Created by Zion on 2017/12/31.
 */
public class ZxzySourceListProcessor extends BasePhdyProcessor {

    @Override
    public void process(Page page) {
        super.process(page);
        List<String> titles = page.getHtml().css("div.xing_vb").css("a").smartContent().all();
        List<String> urls = page.getHtml().css("div.xing_vb").css("a").links().all();
        List<String> types = page.getHtml().css("div.xing_vb").css("span.xing_vb5").smartContent().all();
        List<String> dates = page.getHtml().css("div.xing_vb").css("span.xing_vb6").smartContent().all();
        page.putField("titles", titles);
        page.putField("urls", urls);
        page.putField("types", types);
        page.putField("dates", dates);
    }
}