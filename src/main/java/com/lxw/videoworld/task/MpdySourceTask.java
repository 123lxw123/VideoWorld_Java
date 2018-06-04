package com.lxw.videoworld.task;

import com.lxw.videoworld.dao.MpdySourceDao;
import com.lxw.videoworld.spider.MpdyMenuPageProcessor;
import com.lxw.videoworld.spider.MpdySourceDetailPipeline;
import com.lxw.videoworld.spider.MpdySourceDetailProcessor;
import com.lxw.videoworld.utils.URLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

import java.util.List;


/**
 * Created by lxw9047 on 2017/4/20.
 */
@Component("mpdySourceTask")
public class MpdySourceTask {

    @Autowired
    private MpdySourceDao mpdySourceDao;
    @Autowired
    private MpdySourceDetailPipeline mpdySourceDetailPipeline;
    @Autowired
    private MpdyMenuPageProcessor mpdyMenuPageProcessor;

    // 每天凌晨4点执行
    @Scheduled(cron = "0 0 02 * * ?")
    public void getMpdySource() {
        try{
            //清空今日更新
//        phdyNewDao.clear();
        }catch (Exception e){
            e.printStackTrace();
        }

        // 猫扑电影菜单
        Spider.create(mpdyMenuPageProcessor).thread(1)
                .addUrl(URLUtil.URL_MPDY_DY)
                .addUrl(URLUtil.URL_MPDY_DS)
                .addUrl(URLUtil.URL_MPDY_DM)
                .run();
    }

    // 每天凌晨5点执行
    @Scheduled(cron = "0 0 03 * * ?")
    public void getMpdySourceDetail() {
        // 猫扑电影详情
        final List<String> urlList = mpdySourceDao.findAllUrl();
        if (urlList != null && urlList.size() > 0) {
            Spider.create(new MpdySourceDetailProcessor()).thread(4)
                    .addUrl((String[]) urlList.toArray(new String[urlList.size()]))
                    .addPipeline(mpdySourceDetailPipeline)
                    .run();
        }
    }
}
