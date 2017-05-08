package com.lxw.videoworld.spider;

import com.lxw.videoworld.utils.URLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Spider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zion on 2017/4/22.
 */
@Service("mpdyMenuPageProcessor")
public class MpdyMenuPageProcessor extends BasePhdyProcessor {

    @Autowired
    private MpdyMenuListPipeline mpdyMenuListPipeline;

    @Override
    public void process(Page page) {
        super.process(page);
        List<String> pathList = page.getHtml().css("a.p_redirect").links().all();
        if (pathList != null && pathList.size() == 2) {
            String path = pathList.get(1);
            String[] path1 = pathList.get(1).split("=");
            path = path.substring(0, path.length() - (path1[path1.length - 1]).length());
            int pageCount = Integer.valueOf(path1[path1.length - 1]);
            List<String> urlNewList = new ArrayList<>();
            for (int i = 1; i <= pageCount; i++) {
                urlNewList.add(path + "index" + i + ".html");
            }
            if (urlNewList.size() == 1) {
                Spider.create(new MpdyMenuListProcessor()).thread(1)
                        .addUrl(urlNewList.get(0))
                        .addPipeline(mpdyMenuListPipeline)
                        .run();
            } else {
                Spider.create(new MpdyMenuListProcessor() {
                    @Override
                    public void addTargetRequest(Page page) {
                        super.addTargetRequest(page);
//                        List<String> targetUrlList = urlNewList;
//                        targetUrlList.remove(0);
//                        page.addTargetRequests(targetUrlList);
                        page.addTargetRequests(urlNewList);
                    }
                }).thread(10)
                        .addUrl(urlNewList.get(0))
                        .addPipeline(mpdyMenuListPipeline)
                        .run();
            }
        }
    }

    @Override
    public void addTargetRequest(Page page) {
        super.addTargetRequest(page);
        List<String> menuUrlList = new ArrayList<>();
        menuUrlList.add(URLUtil.URL_MPDY_DS);
        menuUrlList.add(URLUtil.URL_MPDY_DM);
        page.addTargetRequests(menuUrlList);
    }
}
