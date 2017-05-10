package com.lxw.videoworld.spider;

import com.lxw.videoworld.utils.URLUtil;
import org.apache.http.util.TextUtils;
import us.codecraft.webmagic.Page;

import java.util.List;

/**
 * Created by Zion on 2017/4/24.
 */
public class PhdyHotListProcessor extends BasePhdyProcessor {
    @Override
    public void process(Page page) {
        super.process(page);
        List<String> urlList = page.getHtml().css("div#nmr").links().all();
        if(urlList != null && urlList.size() > 0){
            page.putField("urlList", urlList);
        }else{
            String url = page.getHtml().css("script").regex("window.location.href='(.*?)'").toString();
            if(!TextUtils.isEmpty(url)){
                page.addTargetRequest(URLUtil.URL_PHDY_HOME_PAGE + url);
            }
        }
    }
}
