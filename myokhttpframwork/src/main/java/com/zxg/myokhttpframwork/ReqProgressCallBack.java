package com.zxg.myokhttpframwork;

/**
 * Author ：zxg on 2018/4/11 10:57
 * email : remotecountry@163.com
 * date : 2018/4/11
 */

public interface ReqProgressCallBack<T>  extends ReqCallBack<T>{
    /**
     * 响应进度更新
     */
    void onProgress(long total, long current);
}
