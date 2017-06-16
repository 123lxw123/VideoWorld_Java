package com.lxw.videoworld.test_service;

import com.lxw.videoworld.spider.SearchKeywordProcessor;
import com.lxw.videoworld.utils.URLUtil;
import us.codecraft.webmagic.Spider;

/**
 * Created by Zion on 2017/4/17.
 */
public class YgdySpider {

    public static void main(String args[]) {
        Spider.create(new SearchKeywordProcessor()).thread(1)
                .addUrl(URLUtil.BASE_DIAOSI_SEARCH)
                .run();
    }

}
