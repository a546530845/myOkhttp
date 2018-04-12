package com.zxg.myokhttpframwork;

/**
 * Author ：zxg on 2018/4/10 11:26
 * email : remotecountry@163.com
 * date : 2018/4/10
 */

public class HttpCodeException extends Exception {
    private int code;

    public HttpCodeException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
