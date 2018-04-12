package com.zxg.myokhttp.okutils;//package com.zxg.frame.http;


import android.content.Context;
import android.util.Log;

import com.zxg.myokhttp.requestbean.AppUpdateRequestBean;

import java.util.HashMap;
import java.util.Map;

public class MapParamsUtils {
    /**
     * 获取基础参数
     *
     * @param
     * @return
     */
    public static Map getBaseParams(Context mContext) {
        Map<String, String> mMaps = new HashMap<>();
       try {
            // 必须参数,每次上传必须要有的参数
            mMaps.put("accessToken", "");
        } catch (Exception e) {
            // TODO: handle exception
            Log.d("获取基础param", e.toString());
        }
        return mMaps;
    }

    /**
     * 得到更新的参数
     *
     * @param mAppUpdateRequestBean
     * @return
     */
    public static Map getAppUpdateParams(Context mContext, AppUpdateRequestBean mAppUpdateRequestBean) {
        Map<String, String> mMaps = new HashMap<>();
        try {
            // 必须参数
            mMaps.put("clientType", mAppUpdateRequestBean.getClientType());

        } catch (Exception e) {
            // TODO: handle exception
            Log.d("获取app更新param", e.toString());
        }
        return mMaps;
    }

}
