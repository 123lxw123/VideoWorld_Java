package com.lxw.videoworld.task;

import com.lxw.videoworld.spider.YgdyHomePagePipeline;
import com.lxw.videoworld.spider.YgdyHomePageProcessor;
import com.lxw.videoworld.spider.YgdyMenuPageProcessor;
import com.lxw.videoworld.utils.URLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Spider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lxw9047 on 2017/4/20.
 */
@Component("sourceYgdyTask")
public class SourceYgdyTask {
    @Autowired
    private YgdyHomePagePipeline ygdyHomePagePipeline;

    // 每天凌晨4点执行
    @Scheduled(cron = "0 19 22 * * ?")
    public void getYgdySource() {
        // 阳光电影首页
        Spider.create(new YgdyHomePageProcessor()).thread(1)
                .addUrl(URLUtil.URL_YGDY_HOME_PAGE)
                .addPipeline(ygdyHomePagePipeline)
                .run();
        // 阳光电影菜单
        Spider.create(new YgdyMenuPageProcessor() {
            @Override
            public void addTargetRequest(Page page) {
                super.addTargetRequest(page);
                List<String> menuUrlList = new ArrayList<>();
                menuUrlList.add(URLUtil.URL_YGDY_GNDY);
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
        }).thread(5)
                .addUrl(URLUtil.URL_YGDY_ZXDY)
                .addPipeline(ygdyHomePagePipeline)
                .run();
    }
}
