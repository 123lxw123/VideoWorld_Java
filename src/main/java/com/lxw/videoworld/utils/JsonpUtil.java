package com.lxw.videoworld.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Zion on 2017/4/17.
 */
public class JsonpUtil {

    public static String getJsonFromJsonp(String jsonp) {
        Pattern pattern = Pattern.compile("_Callback\\((.*)\\);");
        Matcher matcher = pattern.matcher(jsonp);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;

    }

    public static String getJsonFromJsonps(String jsonp) {
        Pattern pattern = Pattern.compile("_preloadCallback\\((.*)\\);");
        Matcher matcher = pattern.matcher(jsonp);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;

    }

    /**
     * the zan jsonp is not format,so must rebuild the jsonp
     *
     * @param jsonp
     * @return
     */
    public static String formatJsonp(String jsonp) {
        BufferedReader bufferedReader = null;
        bufferedReader = new BufferedReader(new StringReader(jsonp));
        String temp = null;
        StringBuilder builder = new StringBuilder();
        try {
            while ((temp = bufferedReader.readLine()) != null) {
                builder.append(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return builder.toString();

    }
}
