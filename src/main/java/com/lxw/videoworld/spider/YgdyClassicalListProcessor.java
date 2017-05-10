package com.lxw.videoworld.spider;

import us.codecraft.webmagic.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zion on 2017/4/24.
 */
public class YgdyClassicalListProcessor extends BaseYgdyProcessor {
    @Override
    public void process(Page page) {
        super.process(page);
        List<String> urlList = new ArrayList<>();
        List<String> originalUrlList = page.getHtml().css("div.co_content8").links().all();
        if(originalUrlList != null && originalUrlList.size() > 1){
            for(int i = 0; i < originalUrlList.size() - 1; i++){
                if(originalUrlList.get(i).length() > 24){
                    urlList.add(originalUrlList.get(i));
                }
            }
        }
        page.putField("urlList", urlList);
    }
}
