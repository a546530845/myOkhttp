package com.zxg.myokhttp.base;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/9/21.
 * base基类，定义bean类共同值,app
 */
public class BackBaseBean implements Serializable {
    /**
     * statusCode : 0
     * message : null
     */

    private String statusCode;
    private String message;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "LoginBackBean{" +
                "message='" + message + '\'' +
                ", statusCode='" + statusCode + '\'' +
                "} " + super.toString();
    }

    public BackBaseBean() {
    }

    public BackBaseBean(String message, String statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

}
