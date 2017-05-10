package com.lxw.videoworld.spider;

import com.lxw.videoworld.utils.URLUtil;
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
@Service("phdyMenuPageProcessor")
public class PhdyMenuPageProcessor extends BasePhdyProcessor {

    @Autowired
    private PhdyMenuListPipeline phdyMenuListPipeline;

    @Override
    public void process(Page page) {
        super.process(page);
        List<String> urlList = page.getHtml().css("div.tk").links().all();
        List<String> pathList = page.getHtml().css("div#dir").links().all();
        String path = "";
        if (pathList != null && pathList.size() > 0) {
            path = pathList.get(pathList.size() - 1);
        }
        if (urlList != null && !TextUtils.isEmpty(path) && urlList.size() > 0) {
            // 计算总页数
            String str0[] = urlList.get(urlList.size() - 1).split("/");
            String str = str0[str0.length - 1];
            int start = str.indexOf("_");
            int end = str.indexOf(".");
            int pageCount = Integer.valueOf(str.substring(start + 1, end));
            List<String> urlNewList = new ArrayList<>();
            for (int i = 1; i <= pageCount; i++) {
                urlNewList.add(path.replace("index.html", "list_" + i + ".html"));
            }
            Spider.create(new PhdyMenuListProcessor()).thread(10)
                    .addUrl((String[])urlNewList.toArray(new String[urlNewList.size()]))
                    .addPipeline(phdyMenuListPipeline)
                    .run();
        } else {
            String url = page.getHtml().css("script").regex("window.location.href='(.*?)'").toString();
            if (!TextUtils.isEmpty(url)) {
                page.addTargetRequest(URLUtil.URL_PHDY_HOME_PAGE + url);
            }
        }
    }
}
