package com.lxw.videoworld.task;

import com.lxw.videoworld.dao.PhdyHotDao;
import com.lxw.videoworld.dao.PhdyNewDao;
import com.lxw.videoworld.spider.*;
import com.lxw.videoworld.utils.URLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;


/**
 * Created by lxw9047 on 2017/4/20.
 */
@Component("phdySourceTask")
public class PhdySourceTask {

    @Autowired
    private PhdyHotDao phdyHotDao;
    @Autowired
    private PhdyNewDao phdyNewDao;
    @Autowired
    private PhdyMenuListPipeline phdyMenuListPipeline;
    @Autowired
    private PhdyHotListPipeline phdyHotListPipeline;
    @Autowired
    private PhdyNewListPipeline phdyNewListPipeline;

    @Autowired
    private PhdyMenuPageProcessor phdyMenuPageProcessor;

    // 每天凌晨4点执行
    @Scheduled(cron = "0 29 21 * * ?")
    public void getPhdySource() {
        // 清空排行榜
//        phdyHotDao.clear();
        //清空今日更新
//        phdyNewDao.clear();
        // 飘花电影菜单
//        Spider.create(phdyMenuPageProcessor).thread(5)
//                .addUrl(URLUtil.URL_PHDY_DONGZUO)
//                .run();
        // 飘花电影排行榜
        Spider.create(new PhdyHotListProcessor()).thread(5)
                .addUrl(URLUtil.URL_PHDY_DONGZUO)
                .addPipeline(phdyMenuListPipeline)
                .addPipeline(phdyHotListPipeline)
                .run();
        // 飘花电影今日更新
        Spider.create(new PhdyNewListProcessor()).thread(1)
                .addUrl(URLUtil.URL_PHDY_DIANYING)
                .addPipeline(phdyMenuListPipeline)
                .addPipeline(phdyNewListPipeline)
                .run();
    }
}
