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

import java.util.ArrayList;
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
    @Scheduled(cron = "0 30 02 * * ?")
    public void getYgdySource() {
        try{
            // 清空热门排行榜
//            ygdyHotDao.clear();
        }catch (Exception e){
            e.printStackTrace();
        }

        // 阳光电影首页
        Spider.create(new YgdyHomePageProcessor()).thread(1)
                .addUrl(URLUtil.URL_YGDY_HOME_PAGE)
                .addPipeline(ygdyHomePagePipeline)
                .run();
        // 阳光电影排行
        Spider.create(new YgdyHotListProcessor()).thread(2)
                .addUrl(URLUtil.URL_YGDY_HOME_DY)
                .addUrl(URLUtil.URL_YGDY_GFJDDY)
                .addPipeline(ygdyHomePagePipeline)
                .addPipeline(ygdyHotListPipeline)
                .run();
        // 阳光电影菜单
        List<String> menuUrlList = new ArrayList<>();
        menuUrlList.add(URLUtil.URL_YGDY_ZXDY);
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
        Spider.create(ygdyMenuPageProcessor).thread(1)
                .addUrl((String[]) menuUrlList.toArray(new String[menuUrlList.size()]))
                .run();
        // 阳光电影高分经典
        Spider.create(new YgdyClassicalListProcessor()).thread(2)
                .addUrl(URLUtil.URL_YGDY_GFJDDY)
                .addUrl(URLUtil.URL_YGDY_GFJDDY2)
                .addUrl(URLUtil.URL_YGDY_GFJDDY3)
                .addUrl(URLUtil.URL_YGDY_GFJDDY4)
                .addPipeline(ygdyHomePagePipeline)
                .addPipeline(ygdyClassicalListPipeline)
                .run();
    }

    // 每天凌晨5点执行
    @Scheduled(cron = "0 0 05 * * ?")
    public void getYgdySourceDetail() {
        //      // 阳光电影详情
        final List<String> urlList = ygdySourceDao.findAllUrl();
        if (urlList != null && urlList.size() > 0) {
            Spider.create(new YgdySourceDetailProcessor()).thread(4)
                    .addUrl((String[]) urlList.toArray(new String[urlList.size()]))
                    .addPipeline(ygdySourceDetailPipeline)
                    .run();
        }

    }
}
