package com.lxw.videoworld.utils;

import org.apache.http.util.TextUtils;

/**
 * Created by Zion on 2017/5/20.
 */
public class StringUtil {

    // 处理 HTML 提取的文本内容，去掉首位空白，替换 HTML 特殊字符
    public static String disposeField(String oldStr){
        if(!TextUtils.isEmpty(oldStr)){
            String temp1 = HTMLUtil.trimHtml2Txt(oldStr, null);
            String temp2 = replaceAll12288(temp1);
            String temp3 = temp2.trim();
            return temp3;
        }else {
            return "";
        }
    }

    // 替换 12288 全角空格
    public static String replaceAll12288(String fullStr){
        if(TextUtils.isEmpty(fullStr)){
            return fullStr;
        }
        char[] c = fullStr.toCharArray();
        for (int i = 0; i < c.length; i++) {
           if (c[i] == 12288) { // 空格
                c[i] = (char) 32;
            }
        }
        return new String(c);
    }
}
