package com.lxw.videoworld.task;

import com.lxw.videoworld.spider.PhdyMenuPageProcessor;
import com.lxw.videoworld.utils.URLUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;


/**
 * Created by lxw9047 on 2017/4/20.
 */
@Component("phdySourceTask")
public class PhdySourceTask {

    // 每天凌晨4点执行
    @Scheduled(cron = "0 57 00 * * ?")
    public void getPhdySource() {
        // 飘花电影菜单
        Spider.create(new PhdyMenuPageProcessor()).thread(5)
                .addUrl(URLUtil.URL_PHDY_DONGZUO)
                .run();
    }
}
