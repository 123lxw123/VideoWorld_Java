package com.lxw.videoworld.task;

import com.lxw.videoworld.dao.ZxzySourceDao;
import com.lxw.videoworld.spider.ZxzyPageProcessor;
import com.lxw.videoworld.spider.ZxzySourceDetailPipeline;
import com.lxw.videoworld.spider.ZxzySourceDetailProcessor;
import com.lxw.videoworld.spider.ZxzySourceListPipeline;
import com.lxw.videoworld.utils.URLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

import java.util.List;


/**
 * Created by lxw9047 on 2017/4/20.
 */
@Component("zxzySourceTask")
public class ZxzySourceTask {

    @Autowired
    private ZxzySourceDao zxzySourceDao;
    @Autowired
    private ZxzySourceListPipeline zxzySourceListPipeline;
    @Autowired
    private ZxzySourceDetailPipeline zxzySourceDetailPipeline;

    // 每天凌晨4点执行
    @Scheduled(cron = "0 51 23 * * ?")
    public void getZxzySource() {
        Spider.create(new ZxzyPageProcessor()).thread(1)
                .addUrl(URLUtil.URL_ZXZY_HOME_PAGE)
                .addPipeline(zxzySourceListPipeline)
                .run();
    }

    // 每天凌晨5点执行
    @Scheduled(cron = "0 30 02 * * ?")
    public void getZxzySourceDetail() {
        final List<String> urlList = zxzySourceDao.findAllUrl();
        if (urlList != null && urlList.size() > 0) {
            Spider.create(new ZxzySourceDetailProcessor()).thread(50)
                    .addUrl((String[]) urlList.toArray(new String[urlList.size()]))
                    .addPipeline(zxzySourceDetailPipeline)
                    .run();
        }
    }
}
