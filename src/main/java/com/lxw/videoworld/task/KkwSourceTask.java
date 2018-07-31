package com.lxw.videoworld.task;

import com.lxw.videoworld.dao.KkwSourceDao;
import com.lxw.videoworld.spider.KkwMenuPageProcessor;
import com.lxw.videoworld.spider.KkwSourceDetailPipeline;
import com.lxw.videoworld.spider.KkwSourceDetailProcessor;
import com.lxw.videoworld.utils.URLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by lxw9047 on 2017/4/20.
 */
@Component("kkwSourceTask")
public class KkwSourceTask {

    @Autowired
    private KkwSourceDao kkwSourceDao;
    @Autowired
    private KkwMenuPageProcessor kkwMenuPageProcessor;
    @Autowired
    private KkwSourceDetailPipeline kkwSourceDetailPipeline;

    @Scheduled(cron = "0 54 9 * * ?")
    public void getKkwSource() {
        List<String> menuUrlList = new ArrayList<>();
        menuUrlList.add(URLUtil.URL_KKW_DY);
        menuUrlList.add(URLUtil.URL_KKW_DSJ);
        menuUrlList.add(URLUtil.URL_KKW_DM);
        menuUrlList.add(URLUtil.URL_KKW_ZY);
        menuUrlList.add(URLUtil.URL_KKW_WDY);
        Spider.create(kkwMenuPageProcessor).thread(1)
                .addUrl((String[])menuUrlList.toArray(new String[menuUrlList.size()]))
                .run();
    }

    @Scheduled(cron = "0 23 1 * * ?")
    public void getKkwSourceDetail() {
        final List<String> urlList = kkwSourceDao.findAllUrl();
        if (urlList != null && urlList.size() > 0) {
            Spider.create(new KkwSourceDetailProcessor()).thread(4)
                    .addUrl((String[]) urlList.toArray(new String[urlList.size()]))
                    .addPipeline(kkwSourceDetailPipeline)
                    .run();
        }

    }
}
