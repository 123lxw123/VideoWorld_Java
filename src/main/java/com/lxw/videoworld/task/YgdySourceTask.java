package com.lxw.videoworld.task;

import com.lxw.videoworld.dao.YgdyHotDao;
import com.lxw.videoworld.dao.YgdySourceDao;
import com.lxw.videoworld.spider.*;
import com.lxw.videoworld.utils.URLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Spider;

import java.util.List;


/**
 * Created by lxw9047 on 2017/4/20.
 */
@Component("ygdySourceTask")
public class YgdySourceTask {
    @Autowired
    private YgdyHomePagePipeline ygdyHomePagePipeline;
    @Autowired
    private YgdyHotListPipeline ygdyHotListPipeline;
    @Autowired
    private YgdyClassicalListPipeline ygdyClassicalListPipeline;
    @Autowired
    private YgdySourceDetailPipeline ygdySourceDetailPipeline;
    @Autowired
    private YgdyMenuPageProcessor ygdyMenuPageProcessor;
    @Autowired
    private YgdySourceDao ygdySourceDao;
    @Autowired
    private YgdyHotDao ygdyHotDao;


    // 每天凌晨4点执行
    @Scheduled(cron = "0 00 04 * * ?")
    public void getYgdySource() {
        // 清空热门排行榜
        ygdyHotDao.clear();
        // 阳光电影首页
        Spider.create(new YgdyHomePageProcessor()).thread(1)
                .addUrl(URLUtil.URL_YGDY_HOME_PAGE)
                .addPipeline(ygdyHomePagePipeline)
                .run();
        // 阳光电影排行
        Spider.create(new YgdyHotListProcessor()).thread(2)
                .addUrl(URLUtil.URL_YGDY_HOME_DY)
                .addPipeline(ygdyHomePagePipeline)
                .addPipeline(ygdyHotListPipeline)
                .run();
        // 阳光电影菜单
        Spider.create(ygdyMenuPageProcessor).thread(5)
                .addUrl(URLUtil.URL_YGDY_ZXDY)
                .run();
        // 阳光电影高分经典
        Spider.create(new YgdyClassicalListProcessor()).thread(2)
                .addUrl(URLUtil.URL_YGDY_GFJDDY)
                .addPipeline(ygdyHomePagePipeline)
                .addPipeline(ygdyClassicalListPipeline)
                .run();
    }

    // 每天凌晨4点执行
    @Scheduled(cron = "0 00 05 * * ?")
    public void getYgdySourceDetail() {
        //      // 阳光电影详情
        final List<String> urlList = ygdySourceDao.findAllUrlNoDetail();
        if (urlList != null && urlList.size() > 0) {
            if (urlList.size() == 1) {
                Spider.create(new YgdySourceDetailProcessor()).thread(1)
                        .addUrl(urlList.get(0))
                        .run();
            } else {
                final List<String> urlList1 = urlList;
                urlList1.remove(0);
                Spider.create(new YgdySourceDetailProcessor() {
                    @Override
                    public void addTargetRequest(Page page) {
                        super.addTargetRequest(page);
                        page.addTargetRequests(urlList1);
                    }
                }).thread(50)
                        .addUrl(urlList.get(0))
                        .addPipeline(ygdySourceDetailPipeline)
                        .run();
            }
        }

    }
}
