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
            .setCharset("gb2312");

    private static final Site site1 = Site.me()
            .setUserAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.91 Safari/537.36")
            .setSleepTime(1000)
            .setTimeOut(10000)
            .setCharset("utf-8");

    public static final String URL_YGDY_HOME_PAGE = "http://www.ygdy8.com";
    public static final String URL_YGDY_HOME_DY = URL_YGDY_HOME_PAGE + "/html/gndy/index.html";
    public static final String URL_YGDY_ZXDY = URL_YGDY_HOME_PAGE + "/html/gndy/dyzz/index.html";
    public static final String URL_YGDY_JDDY = URL_YGDY_HOME_PAGE + "/html/gndy/jddy/index.html";
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
    public static final String URL_YGDY_GFJDDY2 = URL_YGDY_HOME_PAGE + "/html/gndy/jddy/20160320/50541_2.html";
    public static final String URL_YGDY_GFJDDY3 = URL_YGDY_HOME_PAGE + "/html/gndy/jddy/20160320/50541_3.html";
    public static final String URL_YGDY_GFJDDY4 = URL_YGDY_HOME_PAGE + "/html/gndy/jddy/20160320/50541_4.html";

    public static final String URL_PHDY_HOME_PAGE = "http://www.piaohua.com";
    public static final String URL_PHDY_DONGZUO = URL_PHDY_HOME_PAGE + "/html/dongzuo/index.html";
    public static final String URL_PHDY_XIJU = URL_PHDY_HOME_PAGE + "/html/xiju/index.html";
    public static final String URL_PHDY_AIQING = URL_PHDY_HOME_PAGE + "/html/aiqing/index.html";
    public static final String URL_PHDY_KEHUAN = URL_PHDY_HOME_PAGE + "/html/kehuan/index.html";
    public static final String URL_PHDY_JUQING = URL_PHDY_HOME_PAGE + "/html/juqing/index.html";
    public static final String URL_PHDY_XUANYI = URL_PHDY_HOME_PAGE + "/html/xuannian/index.html";
    public static final String URL_PHDY_WENYI = URL_PHDY_HOME_PAGE + "/html/wenyi/index.html";
    public static final String URL_PHDY_ZHANZHENG = URL_PHDY_HOME_PAGE + "/html/zhanzheng/index.html";
    public static final String URL_PHDY_KONGBU = URL_PHDY_HOME_PAGE + "/html/kongbu/index.html";
    public static final String URL_PHDY_ZAINAN = URL_PHDY_HOME_PAGE + "/html/zainan/index.html";
    public static final String URL_PHDY_LIANXUJU = URL_PHDY_HOME_PAGE + "/html/lianxuju/index.html";
    public static final String URL_PHDY_DONGMAN = URL_PHDY_HOME_PAGE + "/html/dongman/index.html";
    public static final String URL_PHDY_ZONGYI = URL_PHDY_HOME_PAGE + "/html/zongyijiemu/index.html";
    public static final String URL_PHDY_HUAIJIU = URL_PHDY_HOME_PAGE + "/html/huaijiu/index.html";
    public static final String URL_PHDY_DIANYING = URL_PHDY_HOME_PAGE + "/html/zuixindianying.html";

    public static final String URL_MPDY_HOME_PAGE = "http://maopu.tv";
    public static final String URL_MPDY_DY = URL_MPDY_HOME_PAGE + "/dy/index.html";
    public static final String URL_MPDY_DS = URL_MPDY_HOME_PAGE + "/ds/index.html";
    public static final String URL_MPDY_DM = URL_MPDY_HOME_PAGE + "/dm/index.html";

    public static final String URL_ZXZY_HOME_PAGE = "http://www.kakazycj.com";
    public static final String URL_ZXZY_LIST_PAGE = "http://www.kakazycj.com/?m=vod-index-pg-page.html";

    //    搜索
    public static final String BASE_ZHONGZI_SEARCH_1 = "http://www.zhongziso.com/list_ctime/keyword/page";// 时间排序
    public static final String BASE_ZHONGZI_SEARCH_2 = "http://www.zhongziso.com/list_click/keyword/page";// 点击
    public static final String BASE_ZHONGZI_SEARCH_3 = "http://www.zhongziso.com/list_length/keyword/page";// 大小
    public static final String BASE_DIAOSI_SEARCH = "http://www.diaosisou.net/";// 屌丝搜索首页
    public static final String BASE_DIAOSI_SEARCH_1 = "http://www.diaosisou.net/list/keyword/page/time_d";// 时间排序
    public static final String BASE_DIAOSI_SEARCH_2 = "http://www.diaosisou.net/list/keyword/page/size_d";// 大小
    public static final String BASE_DIAOSI_SEARCH_3 = "http://www.diaosisou.net/list/keyword/page/rala_d";// 相关度

    public static Site getSiteInstance(){
        return site;
    }

    public static Site getSiteInstance1(){
        return site1;
    }
}
