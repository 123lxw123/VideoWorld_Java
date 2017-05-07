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
@Service("ygdyMenuPageProcessor")
public class PhdyMenuPageProcessor extends BaseProcessor {

    @Autowired
    private YgdyHomePagePipeline ygdyHomePagePipeline;

    @Override
    public void process(Page page) {
        super.process(page);
        List<String> urlList = page.getHtml().css("div.page").css("select").css("option").regex("list.*?html").all();
        List<String> pathList = page.getHtml().css("div#dir").links().all();
        String path = "";
        if(pathList != null && pathList.size() > 0){
            path = pathList.get(pathList.size() - 1);
        }
        if(urlList != null && !TextUtils.isEmpty(path) && urlList.size() > 0){
            List<String> urlNewList = new ArrayList<>();
            for(int i = 0; i < urlList.size(); i++){
                urlNewList.add(page + urlList.get(i));
            }
            if(urlNewList.size() == 1){
                Spider.create(new YgdyMenuListProcessor()).thread(1)
                        .addUrl(urlNewList.get(0))
                        .addPipeline(ygdyHomePagePipeline)
                        .run();
            }else{
                Spider.create(new YgdyMenuListProcessor(){
                    @Override
                    public void addTargetRequest(Page page) {
                        super.addTargetRequest(page);
                        List<String> targetUrlList = urlNewList;
                        targetUrlList.remove(0);
                        page.addTargetRequests(targetUrlList);
                    }
                }).thread(10)
                        .addUrl(urlNewList.get(0))
                        .addPipeline(ygdyHomePagePipeline)
                        .run();
            }
        }
    }

    @Override
    public void addTargetRequest(Page page) {
        super.addTargetRequest(page);
        List<String> menuUrlList = new ArrayList<>();
        menuUrlList.add(URLUtil.URL_PHDY_XIJU);
        menuUrlList.add(URLUtil.URL_PHDY_AIQING);
        menuUrlList.add(URLUtil.URL_PHDY_KEHUAN);
        menuUrlList.add(URLUtil.URL_PHDY_JUQING);
        menuUrlList.add(URLUtil.URL_PHDY_XUANYI);
        menuUrlList.add(URLUtil.URL_PHDY_WENYI);
        menuUrlList.add(URLUtil.URL_PHDY_ZHANZHENG);
        menuUrlList.add(URLUtil.URL_PHDY_KONGBU);
        menuUrlList.add(URLUtil.URL_PHDY_ZAINAN);
        menuUrlList.add(URLUtil.URL_PHDY_LIANXUJU);
        menuUrlList.add(URLUtil.URL_PHDY_DONGMAN);
        menuUrlList.add(URLUtil.URL_PHDY_ZONGYI);
        page.addTargetRequests(menuUrlList);
    }
}
