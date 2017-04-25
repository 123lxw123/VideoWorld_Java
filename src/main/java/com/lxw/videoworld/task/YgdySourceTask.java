package com.lxw.videoworld.task;

import com.lxw.videoworld.spider.YgdyClassicalListPipeline;
import com.lxw.videoworld.spider.YgdyClassicalListProcessor;
import com.lxw.videoworld.spider.YgdyHomePagePipeline;
import com.lxw.videoworld.spider.YgdyHotListPipeline;
import com.lxw.videoworld.utils.URLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

/**
 * Created by lxw9047 on 2017/4/20.
 */
@Component("sourceYgdyTask")
public class YgdySourceTask {
    @Autowired
    private YgdyHomePagePipeline ygdyHomePagePipeline;
    @Autowired
    private YgdyHotListPipeline ygdyHotListPipeline;
    @Autowired
    private YgdyClassicalListPipeline ygdyClassicalListPipeline;

    // 每天凌晨4点执行
    @Scheduled(cron = "0 04 00 * * ?")
    public void getYgdySource() {
        // 阳光电影首页
//        Spider.create(new YgdyHomePageProcessor()).thread(1)
//                .addUrl(URLUtil.URL_YGDY_HOME_PAGE)
//                .addPipeline(ygdyHomePagePipeline)
//                .run();
        // 阳光电影排行
//        Spider.create(new YgdyHotListProcessor()).thread(2)
//                .addUrl(URLUtil.URL_YGDY_HOME_DY)
//                .addPipeline(ygdyHomePagePipeline)
//                .addPipeline(ygdyHotListPipeline)
//                .run();
        // 阳光电影高分经典
        Spider.create(new YgdyClassicalListProcessor()).thread(2)
                .addUrl(URLUtil.URL_YGDY_GFJDDY)
                .addPipeline(ygdyHomePagePipeline)
                .addPipeline(ygdyClassicalListPipeline)
                .run();
        // 阳光电影菜单
//        Spider.create(ygdyMenuPageProcessor).thread(5)
//                .addUrl(URLUtil.URL_YGDY_ZXDY)
//                .run();
    }
}
