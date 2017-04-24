package com.lxw.videoworld.spider;

import com.lxw.videoworld.utils.URLUtil;
import us.codecraft.webmagic.Page;

import java.util.List;

/**
 * Created by Zion on 2017/4/24.
 */
public class YgdyHotListProcessor extends BaseProcessor {
    @Override
    public void process(Page page) {
        super.process(page);
        List<String> urlList = page.getHtml().css("div.co_content2").links().all();
        List<String> titleList = page.getHtml().css("div.co_content2").css("a").regex(">(.*?)</a>").all();
        page.putField("urlList", urlList);
        page.putField("titleList", titleList);
    }

    @Override
    public void addTargetRequest(Page page) {
        super.addTargetRequest(page);
        page.addTargetRequest(URLUtil.URL_YGDY_GFJDDY);
    }
}
