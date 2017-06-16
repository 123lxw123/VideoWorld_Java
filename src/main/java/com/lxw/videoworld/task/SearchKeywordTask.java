package com.lxw.videoworld.task;

import com.lxw.videoworld.spider.SearchKeywordPipeline;
import com.lxw.videoworld.spider.SearchKeywordProcessor;
import com.lxw.videoworld.utils.URLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;


/**
 * Created by lxw9047 on 2017/4/20.
 */
@Component("searchKeywordTask")
public class SearchKeywordTask {

    @Autowired
    private SearchKeywordPipeline searchKeywordPipeline;

    // 每天凌晨4点执行
    @Scheduled(cron = "0 28,58 02 * * ?")
    public void getSearchKeyword() {
        // 屌丝搜索热词
        Spider.create(new SearchKeywordProcessor()).thread(1)
                .addUrl(URLUtil.BASE_DIAOSI_SEARCH)
                .addPipeline(searchKeywordPipeline)
                .run();
    }
}
