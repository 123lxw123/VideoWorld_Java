package com.lxw.videoworld.servicelmpl;

import com.lxw.videoworld.spider.DyttHomePagePipeline;
import com.lxw.videoworld.spider.DyttHomePageProcessor;
import com.lxw.videoworld.utils.URLUtil;
import us.codecraft.webmagic.Spider;

/**
 * Created by Zion on 2017/4/17.
 */
public class DyttSpider {

    public static void main(String args[]) {
        Spider.create(new DyttHomePageProcessor()).thread(1)
         .addUrl(URLUtil.URL_DYTT_HOME_PAGE)
         .addPipeline(new DyttHomePagePipeline())
         .run();
    }

}
