package com.lxw.videoworld.domain;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Zion on 2017/6/4.
 */
public class ObjectResponse implements Serializable {
    private int code;
    private String message;
    private Map<String, Object> result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }
}
