package com.lxw.videoworld.spider;

import com.lxw.videoworld.utils.URLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Spider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zion on 2017/12/31.
 */
@Service("zxzyPageProcessor")
public class ZxzyPageProcessor extends BasePhdyProcessor {

    @Autowired
    private ZxzySourceListProcessor zxzySourceListProcessor;
    @Autowired
    private ZxzySourceListPipeline zxzySourceListPipeline;

    @Override
    public void process(Page page) {
        super.process(page);
        int pageCount = Integer.valueOf(page.getHtml().css("div.pages").regex("当前:1/(.*?)页").toString());
        List<String> urlNewList = new ArrayList<>();
        for (int i = 1; i <= pageCount; i++) {
            urlNewList.add(URLUtil.URL_ZXZY_LIST_PAGE.replace("page", i + ""));
        }
        Spider.create(zxzySourceListProcessor).thread(10)
                .addUrl((String[])urlNewList.toArray(new String[urlNewList.size()]))
                .addPipeline(zxzySourceListPipeline)
                .run();
    }
}