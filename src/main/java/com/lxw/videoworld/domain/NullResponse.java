package com.lxw.videoworld.domain;

import java.io.Serializable;

/**
 * Created by Zion on 2017/6/4.
 */
public class NullResponse implements Serializable {
    private int code;
    private String message;

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
}
