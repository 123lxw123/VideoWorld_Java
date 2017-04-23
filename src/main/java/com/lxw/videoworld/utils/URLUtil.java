package com.lxw.videoworld.utils;

import us.codecraft.webmagic.Site;

/**
 * Created by Zion on 2017/4/17.
 */
public class URLUtil {

    private static final Site site = Site.me()
            .setUserAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.91 Safari/537.36")
            .setSleepTime(1000)
            .setTimeOut(600000)
            .setCharset("GB2312");

    public static final String URL_YGDY_HOME_PAGE = "http://www.ygdy8.com";
    public static final String URL_YGDY_ZXDY = URL_YGDY_HOME_PAGE + "/html/gndy/dyzz/index.html";
    public static final String URL_YGDY_JDDY = URL_YGDY_HOME_PAGE + "/html/gndy/index.html";
    public static final String URL_YGDY_GNDY = URL_YGDY_HOME_PAGE + "/html/gndy/china/index.html";
    public static final String URL_YGDY_OMDY = URL_YGDY_HOME_PAGE + "/html/gndy/oumei/index.html";
    public static final String URL_YGDY_RHDY = URL_YGDY_HOME_PAGE + "/html/gndy/rihan/index.html";
    public static final String URL_YGDY_HYTV = URL_YGDY_HOME_PAGE + "/html/tv/hytv/index.html";
    public static final String URL_YGDY_RHTV = URL_YGDY_HOME_PAGE + "/html/tv/rihantv/index.html";
    public static final String URL_YGDY_OMTV = URL_YGDY_HOME_PAGE + "/html/tv/oumeitv/index.html";
    public static final String URL_YGDY_ZXZY = URL_YGDY_HOME_PAGE + "/html/zongyi2013/index.html";
    public static final String URL_YGDY_JBZY = URL_YGDY_HOME_PAGE + "/html/2009zongyi/index.html";
    public static final String URL_YGDY_DONGMAN = URL_YGDY_HOME_PAGE + "/html/dongman/index.html";
    public static final String URL_YGDY_GAME = URL_YGDY_HOME_PAGE + "/html/game/index.html";
    public static final String URL_YGDY_GFJDDY = URL_YGDY_HOME_PAGE + "/html/gndy/jddy/20160320/50541.html";

    public static Site getSiteInstance(){
        return site;
    }
}
