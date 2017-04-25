package com.lxw.videoworld.spider;

import com.lxw.videoworld.utils.URLUtil;
import us.codecraft.webmagic.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zion on 2017/4/24.
 */
public class YgdyClassicalListProcessor extends BaseProcessor {
    @Override
    public void process(Page page) {
        super.process(page);
        List<String> urlList = new ArrayList<>();
        List<String> titleList = new ArrayList<>();
        List<String> originalUrlList = page.getHtml().css("div.co_content8").links().all();
        if(originalUrlList != null && originalUrlList.size() > 1){
            for(int i = 0; i < originalUrlList.size() - 1; i++){
                if(originalUrlList.get(i).length() > 24){
                    urlList.add(originalUrlList.get(i));
                    titleList.add("");
                }
            }
        }
        page.putField("urlList", urlList);
        page.putField("titleList", titleList);
    }

    @Override
    public void addTargetRequest(Page page) {
        super.addTargetRequest(page);
        page.addTargetRequest(URLUtil.URL_YGDY_GFJDDY2);
        page.addTargetRequest(URLUtil.URL_YGDY_GFJDDY3);
        page.addTargetRequest(URLUtil.URL_YGDY_GFJDDY4);
    }
}
