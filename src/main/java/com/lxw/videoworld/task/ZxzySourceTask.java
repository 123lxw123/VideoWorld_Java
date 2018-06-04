package com.lxw.videoworld.task;

import com.lxw.videoworld.dao.ZxzySourceDao;
import com.lxw.videoworld.spider.*;
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
    private ZxzyPageProcessor zxzyPageProcessor;
    @Autowired
    private ZxzySourceDetailProcessor zxzySourceDetailProcessor;
    @Autowired
    private ZxzySourceDetailPipeline zxzySourceDetailPipeline;

    // 每天凌晨4点执行
    @Scheduled(cron = "0 45 02 * * ?")
    public void getZxzySource() {
        Spider.create(zxzyPageProcessor).thread(1)
                .addUrl(URLUtil.URL_ZXZY_HOME_PAGE)
                .run();
    }

    // 每天凌晨5点执行
    @Scheduled(cron = "0 0 0/12 * * ?")
    public void getZxzySourceDetail() {
        final List<String> urlList = zxzySourceDao.findAllUrl();
        if (urlList != null && urlList.size() > 0) {
            Spider.create(zxzySourceDetailProcessor).thread(4)
                    .addUrl((String[]) urlList.toArray(new String[urlList.size()]))
                    .addPipeline(zxzySourceDetailPipeline)
                    .run();
        }
    }
}
