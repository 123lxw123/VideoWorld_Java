package com.lxw.videoworld.utils;

import com.google.gson.Gson;
import com.lxw.videoworld.domain.NullResponse;
import com.lxw.videoworld.domain.ObjectResponse;
import com.lxw.videoworld.domain.StringResponse;

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
        StringResponse response = new StringResponse();
        response.setCode(code);
        response.setMessage(message);
        response.setResult(result);
        return new Gson().toJson(response);
    }

    public static String formatResponse(Map<String, Object> result){
        return formatResponse(null, result);
    }

    public static String formatResponse(String message, Map<String, Object> result){
        return formatResponse(ErrorUtil.CODE_SUCCESS, message, result);
    }

    public static String formatResponse(int code, String message, Map<String, Object> result){
        ObjectResponse response = new ObjectResponse();
        response.setCode(code);
        response.setMessage(message);
        response.setResult(result);
        return new Gson().toJson(response);
    }

    public static String formatResponse(int code, String message){
        NullResponse response = new NullResponse();
        response.setCode(code);
        response.setMessage(message);
        return new Gson().toJson(response);
    }
}
