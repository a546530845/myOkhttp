package com.zxg.myokhttp.requestbean;


import com.zxg.myokhttp.base.RequestBaseBean;

/**
 * 作者：zxg on 2016/9/30 14:58
 * app更新请求bean
 */
public class AppUpdateRequestBean extends RequestBaseBean {
    /**
     * 客户端类型 2:android 3:ios
     */
    private String clientType;

    public AppUpdateRequestBean(String clientType) {
        this.clientType = clientType;
    }

    public AppUpdateRequestBean() {
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    @Override
    public String toString() {
        return "AppUpdateRequestBean{" +
                "clientType='" + clientType + '\'' +
                "} " + super.toString();
    }
}
