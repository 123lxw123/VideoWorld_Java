package com.lxw.videoworld.spider;

import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Spider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zion on 2017/4/22.
 */
@Service("ygdyMenuPageProcessor")
public class YgdyMenuPageProcessor extends BaseYgdyProcessor {

    @Autowired
    private YgdyHomePagePipeline ygdyHomePagePipeline;

    @Override
    public void process(Page page) {
        super.process(page);
        List<String> urlList = page.getHtml().css("div.x").css("select").css("option").regex("list.*?html").all();
        List<String> pathList = page.getHtml().css("div.path").links().all();
        String path = "";
        if (pathList != null && pathList.size() > 0) {
            path = pathList.get(pathList.size() - 1);
        }
        if (urlList != null && !TextUtils.isEmpty(path) && urlList.size() > 0) {
            List<String> urlNewList = new ArrayList<>();
            for (int i = 0; i < urlList.size(); i++) {
                urlNewList.add(path.replace("index.html", urlList.get(i)));
            }
            Spider.create(new YgdyMenuListProcessor()).thread(10)
                    .addUrl((String[])urlNewList.toArray(new String[urlNewList.size()]))
                    .addPipeline(ygdyHomePagePipeline)
                    .run();
        }
    }
}
