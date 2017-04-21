package com.lxw.videoworld.servicelmpl;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Zion on 2017/4/17.
 */
public class YgdySpider {

    @Autowired

    public static void main(String args[]) {
//        Spider.create(new YgdyHomePageProcessor()).thread(1)
//         .addUrl(URLUtil.URL_YGDY_HOME_PAGE)
//         .addPipeline(new YgdyHomePagePipeline())
//         .run();
        String c = "/html";
        String[] d = c.split("/");
        int i = d.length;

    }

}
