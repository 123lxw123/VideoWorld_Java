package com.lxw.videoworld.spider;

import us.codecraft.webmagic.Page;

/**
 * Created by Zion on 2017/12/31.
 */
public class ZxzySourceLinkProcessor extends BasePhdyProcessor {

    private String url;

    ZxzySourceLinkProcessor(String url){
        this.url = url;
    }

    @Override
    public void process(Page page) {
        super.process(page);
        String link = page.getUrl().toString();
        if (link.contains("m3u8")){
            String htmlString = page.getRawText();
            int start = htmlString.indexOf("/ppvod/");
            int end = htmlString.indexOf("m3u8") + 4;
            String partOfLink =  htmlString.substring(start, end);
            page.putField("partOfLink", partOfLink);
        } else {
            String token = page.getHtml().regex("requestToken = \"(.*?)\"").toString();
            page.putField("token", token);
        }
        String firstPartUrl = page.getUrl().toString().substring(0, page.getUrl().toString().indexOf("com") + "com".length());
        page.putField("link", link);
        page.putField("url", url);
        page.putField("firstPartUrl", firstPartUrl);
    }
}