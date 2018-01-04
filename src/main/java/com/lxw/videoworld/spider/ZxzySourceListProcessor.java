package com.lxw.videoworld.spider;

import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;

import java.util.List;

/**
 * Created by Zion on 2017/12/31.
 */
@Service("zxzySourceListProcessor")
public class ZxzySourceListProcessor extends BasePhdyProcessor {

    @Override
    public void process(Page page) {
        super.process(page);
        List<String> titles = page.getHtml().css("div.xing_vb").css("span.xing_vb4").css("a").all();
        List<String> urls = page.getHtml().css("div.xing_vb").css("a").links().regex("(.*?id.*?html)").all();
        List<String> types = page.getHtml().css("div.xing_vb").css("span.xing_vb5").all();
        List<String> dates = page.getHtml().css("div.xing_vb").css("span").regex("(\\d{4}-\\d{2}-\\d{2})").all();
        page.putField("titles", titles);
        page.putField("urls", urls);
        page.putField("types", types);
        page.putField("dates", dates);
    }
}