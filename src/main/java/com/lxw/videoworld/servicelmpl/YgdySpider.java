package com.lxw.videoworld.servicelmpl;

/**
 * Created by Zion on 2017/4/17.
 */
public class YgdySpider {

    public static void main(String args[]) {
//        Spider.create(new YgdyHomePageProcessor()).thread(1)
//         .addUrl(URLUtil.URL_YGDY_HOME_PAGE)
//         .addPipeline(new YgdyHomePagePipeline())
//         .run();

        String aa ="      ";
        String bb = getUnicode(aa);

    }
    public static String getUnicode(String source){
        String returnUniCode=null;
        String uniCodeTemp=null;
        for(int i=0;i<source.length();i++){
            uniCodeTemp = "\\u"+Integer.toHexString((int)source.charAt(i));//使用char类的charAt()的方法
            returnUniCode=returnUniCode==null?uniCodeTemp:returnUniCode+uniCodeTemp;
        }
        System.out.print(source +" 's unicode = "+returnUniCode);
        return returnUniCode;//返回一个字符的unicode的编码值
    }
}
