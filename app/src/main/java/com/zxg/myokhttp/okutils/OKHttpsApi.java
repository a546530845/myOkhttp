package com.zxg.myokhttp.okutils;//package com.zxg.frame.http;

import android.content.Context;
import android.util.Log;

import com.zxg.myokhttp.backbean.AppUpdateBackBean;
import com.zxg.myokhttp.config.ApiConstant;
import com.zxg.myokhttp.requestbean.AppUpdateRequestBean;
import com.zxg.myokhttpframwork.HttpUtils;
import com.zxg.myokhttpframwork.OnHttpTaskListener;

public class OKHttpsApi {
    /**
     * 查询app是否需要更新
     *
     * @param mContext
     * @param mAppUpdateRequestBean
     * @param mOnHttpTaskListener
     */
    public static void App_upDate(Context mContext, AppUpdateRequestBean mAppUpdateRequestBean, OnHttpTaskListener<AppUpdateBackBean> mOnHttpTaskListener) {
        HttpUtils.HttpUtilsInstance(mContext).postMap(mContext, ApiConstant.APP_CURRENTVERSION, MapParamsUtils.getAppUpdateParams(mContext, mAppUpdateRequestBean), AppUpdateBackBean.class, mOnHttpTaskListener);
        Log.d("app更新", ApiConstant.APP_CURRENTVERSION);

    }
}
