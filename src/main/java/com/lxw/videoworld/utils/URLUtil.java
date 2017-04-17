package com.lxw.videoworld.utils;

import us.codecraft.webmagic.Site;

/**
 * Created by Zion on 2017/4/17.
 */
public class URLUtil {

    private static final Site site = Site.me()
            .setUserAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.91 Safari/537.36")
            .setSleepTime(1000)
            .setTimeOut(10000)
            .setCharset("GB2312");

    public static final String URL_DYTT_HOME_PAGE = "http://www.dytt8.net/index.htm";

    public static Site getSiteInstance(){
        return site;
    }
}
