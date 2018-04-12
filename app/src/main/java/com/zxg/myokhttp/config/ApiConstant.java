package com.zxg.myokhttp.config;

/**
 * Author ：zxg on 2017/3/2 15:44
 * email : remotecountry@163.com
 * for http api connect
 */

public class ApiConstant {
    /**
     * app host develop
     */
//    private static final String APP_SERVER_NAME = "http://192.168.1.216:8088/appserver/app_api/";
//    private static final String APP_SERVER_NAME = "http://192.168.1.118:8088/appserver/app_api/";
//    private static final String APP_SERVER_NAME = "http://192.168.1.177:8080/appserver/app_api/";
//    private static final String APP_SERVER_NAME = "http://192.168.1.66:8080/appserver/app_api/";
//    private static final String APP_SERVER_NAME = "http://192.168.1.191.8081/appserver/app_api/";
    //测试
//    private static final String APP_SERVER_NAME = "http://192.168.1.115:8088/appserver/app_api/";
//    private static final String APP_SERVER_NAME = "http://192.168.1.221:8088/appserver/app_api/";
    //预发布
      private static final String APP_SERVER_NAME = "https://uat.dashufs.com/appserver/app_api/";
    //生产
//    private static final String APP_SERVER_NAME = "https://prod.dashufs.com/appserver/app_api/";

    //外网测试
//    private static final String APP_SERVER_NAME = "http://120.77.65.78:59988/appserver/app_api/";//开发
//    private static final String APP_SERVER_NAME = "http://120.77.65.78:59989/appserver/app_api/";//测试
    /*
     * download apk
     */
    public static final String DOWNLOAD = APP_SERVER_NAME + "user/downloadApp?";
    /**
     * upload pic
     */
    public static final String UPLOADPIC_HEAD = APP_SERVER_NAME + "user/updateHeadUrl?";
    /**
     * 获取当前版本信息
     */
    public static final String APP_CURRENTVERSION = APP_SERVER_NAME + "user/getCurrentVersion?";
}
