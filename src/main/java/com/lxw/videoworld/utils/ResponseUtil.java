package com.lxw.videoworld.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zion on 2017/6/3.
 */
public class ResponseUtil {
    public static String formatResponse(String result){
        return formatResponse(null, result);
    }

    public static String formatResponse(String message, String result){
        return formatResponse(ErrorUtil.CODE_SUCCESS, message, result);
    }

    public static String formatResponse(int code, String message, String result){
        Map<String, Object> response = new HashMap();
        response.put("code", code);
        response.put("message", message);
        response.put("result", result);
        return response.toString();
    }
}
