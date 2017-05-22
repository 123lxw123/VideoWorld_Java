package com.lxw.videoworld.servicelmpl;

import com.lxw.videoworld.spider.YgdySourceDetailPipeline;
import com.lxw.videoworld.spider.YgdySourceDetailProcessor;
import us.codecraft.webmagic.Spider;

/**
 * Created by Zion on 2017/4/17.
 */
public class YgdySpider {

    public static void main(String args[]) {
        Spider.create(new YgdySourceDetailProcessor()).thread(1)
         .addUrl("http://www.ygdy8.com/html/tv/oumeitv/20151009/49266.html")
         .addPipeline(new YgdySourceDetailPipeline())
         .run();
    }

}
