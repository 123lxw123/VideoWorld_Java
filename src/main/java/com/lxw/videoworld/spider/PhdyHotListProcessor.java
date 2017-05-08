package com.lxw.videoworld.spider;

import com.lxw.videoworld.utils.URLUtil;
import us.codecraft.webmagic.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zion on 2017/4/24.
 */
public class PhdyHotListProcessor extends BasePhdyProcessor {
    @Override
    public void process(Page page) {
        super.process(page);
        List<String> urlList = page.getHtml().css("div#nmr").links().all();
        page.putField("urlList", urlList);
    }

    @Override
    public void addTargetRequest(Page page) {
        super.addTargetRequest(page);
        List<String> menuUrlList = new ArrayList<>();
        menuUrlList.add(URLUtil.URL_PHDY_XIJU);
        menuUrlList.add(URLUtil.URL_PHDY_AIQING);
        menuUrlList.add(URLUtil.URL_PHDY_KEHUAN);
        menuUrlList.add(URLUtil.URL_PHDY_JUQING);
        menuUrlList.add(URLUtil.URL_PHDY_XUANYI);
        menuUrlList.add(URLUtil.URL_PHDY_WENYI);
        menuUrlList.add(URLUtil.URL_PHDY_ZHANZHENG);
        menuUrlList.add(URLUtil.URL_PHDY_KONGBU);
        menuUrlList.add(URLUtil.URL_PHDY_ZAINAN);
        menuUrlList.add(URLUtil.URL_PHDY_LIANXUJU);
        menuUrlList.add(URLUtil.URL_PHDY_DONGMAN);
        menuUrlList.add(URLUtil.URL_PHDY_ZONGYI);
        menuUrlList.add(URLUtil.URL_PHDY_HUAIJIU);
        page.addTargetRequests(menuUrlList);
    }
}
