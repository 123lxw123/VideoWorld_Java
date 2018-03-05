package com.lxw.videoworld.spider;

import us.codecraft.webmagic.Page;

/**
 * Created by Zion on 2017/12/31.
 */
public class ZxzySourceTokenLinkProcessor extends BasePhdyProcessor {

    private String url;
    private String firstPartUrl;
    private String link;

    ZxzySourceTokenLinkProcessor(String url, String link, String firstPartUrl){
        this.url = url;
        this.link = link;
        this.firstPartUrl = firstPartUrl;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public void setLink(String link){
        this.link = link;
    }


    @Override
    public void process(Page page) {
        super.process(page);
        String htmlString = page.getRawText();
        String main = htmlString.substring(htmlString.indexOf("main"), htmlString.length());
        int start = main.indexOf("/ppvod/");
        int end = main.indexOf("m3u8") + 4;
        String partOfLink =  main.substring(start, end);
        page.putField("partOfLink", partOfLink);
        page.putField("link", link);
        page.putField("url", url);
        page.putField("firstPartUrl", firstPartUrl);
    }
}