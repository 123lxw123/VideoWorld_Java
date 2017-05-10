package com.lxw.videoworld.spider;

import com.lxw.videoworld.utils.URLUtil;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BasePhdyProcessor implements PageProcessor {

    public BasePhdyProcessor() {
    }

    @Override
    public Site getSite() {
        return URLUtil.getSiteInstance1();
    }

    @Override
    public void process(Page page) {
        // 转换字符编码
        String pageString = changeEncoding(page.getRawText()).trim();
        page.putField("pageString", pageString);
    }

    // 网页内容转换成 UTF-8 编码
    public String changeEncoding(String pageString) {
        // 默认为utf-8编码
        String charset = "utf-8";
        // 匹配<head></head>之间，出现在<meta>标签中的字符编码
        Pattern pattern = Pattern.compile("<head>([\\s\\S]*?)<meta([\\s\\S]*?)charset\\s*=(\")?(.*?)\"");
        Matcher matcher = pattern.matcher(pageString.toLowerCase());
        if (matcher.find()) {
            charset = matcher.group(4);
            if (charset.equals("gb2312")) {
                try {
                    byte[] gbkBytes = new String(pageString.getBytes("gb2312"), "gbk").getBytes();
                    return new String(gbkBytes, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    return "";
                }
            }
        }
        // 将目标字符编码转化为utf-8编码
        String temp = null;
        try {
            temp = new String(pageString.getBytes(charset), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
        return temp;
    }

}