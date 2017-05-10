package com.lxw.videoworld.task;

import com.lxw.videoworld.spider.MpdyMenuPageProcessor;
import com.lxw.videoworld.utils.URLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;


/**
 * Created by lxw9047 on 2017/4/20.
 */
@Component("mpdySourceTask")
public class MpdySourceTask {

//    @Autowired
//    private PhdyNewDao phdyNewDao;
//    @Autowired
//    private PhdyMenuListPipeline phdyMenuListPipeline;
//    @Autowired
//    private PhdyNewListPipeline phdyNewListPipeline;
//
    @Autowired
    private MpdyMenuPageProcessor mpdyMenuPageProcessor;

    // 每天凌晨4点执行
    @Scheduled(cron = "0 07 20 * * ?")
    public void getMpdySource() {
        //清空今日更新
//        phdyNewDao.clear();
        // 猫扑电影菜单
        Spider.create(mpdyMenuPageProcessor).thread(1)
                .addUrl(URLUtil.URL_MPDY_DY)
                .addUrl(URLUtil.URL_MPDY_DS)
                .addUrl(URLUtil.URL_MPDY_DM)
                .run();
        // 飘花电影今日更新
//        Spider.create(new PhdyNewListProcessor()).thread(1)
//                .addUrl(URLUtil.URL_PHDY_DIANYING)
//                .addPipeline(phdyMenuListPipeline)
//                .addPipeline(phdyNewListPipeline)
//                .run();
    }
}
