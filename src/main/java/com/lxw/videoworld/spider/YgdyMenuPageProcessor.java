package com.lxw.videoworld.spider;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Spider;

import java.util.List;

/**
 * Created by Zion on 2017/4/22.
 */
public class YgdyMenuPageProcessor extends BaseProcessor {
    @Override
    public void process(Page page) {
        super.process(page);
        List<String> urlList = page.getHtml().css("div.x").css("option").regex("value='(.*?)'").all();
        if(urlList != null && urlList.size() > 0){
            if(urlList.size() == 1){
                Spider.create(new YgdyMenuListProcessor()).thread(1)
                        .addUrl(urlList.get(0))
                        .addPipeline(new YgdyHomePagePipeline())
                        .run();
            }else{
                Spider.create(new YgdyMenuListProcessor(){
                    @Override
                    public void addTargetRequest(Page page) {
                        super.addTargetRequest(page);
                        List<String> targetUrlList = urlList;
                        targetUrlList.remove(0);
                        page.addTargetRequests(targetUrlList);
                    }
                }).thread(10)
                        .addUrl(urlList.get(0))
                        .addPipeline(new YgdyHomePagePipeline())
                        .run();
            }
        }
    }
}
