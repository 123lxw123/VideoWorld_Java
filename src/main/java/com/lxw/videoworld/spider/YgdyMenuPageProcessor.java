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
public class YgdyMenuPageProcessor extends BaseYgdyProcessor {

    @Autowired
    private YgdyHomePagePipeline ygdyHomePagePipeline;

    @Override
    public void process(Page page) {
        super.process(page);
        List<String> urlList = page.getHtml().css("div.x").css("select").css("option").regex("list.*?html").all();
        List<String> pathList = page.getHtml().css("div.path").links().all();
        String path = "";
        if(pathList != null && pathList.size() > 0){
            path = pathList.get(pathList.size() - 1);
        }
        if(urlList != null && !TextUtils.isEmpty(path) && urlList.size() > 0){
            List<String> urlNewList = new ArrayList<>();
            for(int i = 0; i < urlList.size(); i++){
                urlNewList.add(path.replace("index.html", urlList.get(i)));
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
//                        List<String> targetUrlList = urlNewList;
//                        targetUrlList.remove(0);
//                        page.addTargetRequests(targetUrlList);
                        page.addTargetRequests(urlNewList);
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
        menuUrlList.add(URLUtil.URL_YGDY_GNDY);
        menuUrlList.add(URLUtil.URL_YGDY_JDDY);
        menuUrlList.add(URLUtil.URL_YGDY_OMDY);
        menuUrlList.add(URLUtil.URL_YGDY_RHDY);
        menuUrlList.add(URLUtil.URL_YGDY_HYTV);
        menuUrlList.add(URLUtil.URL_YGDY_RHTV);
        menuUrlList.add(URLUtil.URL_YGDY_OMTV);
        menuUrlList.add(URLUtil.URL_YGDY_ZXZY);
        menuUrlList.add(URLUtil.URL_YGDY_JBZY);
        menuUrlList.add(URLUtil.URL_YGDY_DONGMAN);
        menuUrlList.add(URLUtil.URL_YGDY_GAME);
        page.addTargetRequests(menuUrlList);
    }
}
