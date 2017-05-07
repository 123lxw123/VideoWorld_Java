package com.lxw.videoworld.servicelmpl;

/**
 * Created by Zion on 2017/4/17.
 */
public class YgdySpider {

    public static void main(String args[]) {
//        Spider.create(new YgdyHomePageProcessor()).thread(1)
//         .addUrl(URLUtil.URL_YGDY_HOME_PAGE)
//         .addPipeline(new YgdyHomePagePipeline())
//         .run();
        String string = "list_123.html";
        int a1 = string.indexOf("_");
        int a2 = string.indexOf(".");
        String str= string.substring(a1,a2);
    }

}
