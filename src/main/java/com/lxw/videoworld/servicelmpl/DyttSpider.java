package com.lxw.videoworld.servicelmpl;

import com.lxw.videoworld.spider.BaseProcessor;
import com.lxw.videoworld.spider.DyttHomePagePipeline;
import com.lxw.videoworld.utils.URLUtil;
import us.codecraft.webmagic.Spider;

/**
 * Created by Zion on 2017/4/17.
 */
public class DyttSpider {

    public static void main(String args[]) {
        Spider.create(new BaseProcessor()).thread(10)
         .addUrl(URLUtil.URL_DYTT_HOME_PAGE)
         .addPipeline(new DyttHomePagePipeline())
         .run();
    }

}
