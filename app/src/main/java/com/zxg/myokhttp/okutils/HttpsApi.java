package com.zxg.myokhttp.okutils;

import android.content.Context;
import android.util.Log;

import com.zxg.myokhttp.config.ApiConstant;
import com.zxg.myokhttp.requestbean.DownLoadAppRequestBean;
import com.zxg.myokhttp.requestbean.UpLoadPicRequestBean;
import com.zxg.myokhttpframwork.ReqProgressCallBack;
import com.zxg.myokhttpframwork.UploadAndDownLoadHttpUtils;

import java.util.HashMap;

/**
 * Author ：zxg on 2017/3/2 16:58
 * email : remotecountry@163.com
 */

public class HttpsApi {

    public static<T> void downLoadApp2(Context mContext, String url, String dirUrl , DownLoadAppRequestBean mDownLoadAppRequestBean, ReqProgressCallBack<T> reqProgressCallBack) {
        UploadAndDownLoadHttpUtils.HttpUtilsInstance(mContext).downLoadFile( url,dirUrl, getAppDownLoad(mContext,mDownLoadAppRequestBean), reqProgressCallBack);
    }
    private static HashMap getAppDownLoad(Context mContext, DownLoadAppRequestBean mDownLoadAppRequestBean) {
        HashMap<String, String> mMaps = new HashMap<>();
        try {
            mMaps.put("type", mDownLoadAppRequestBean.getType());//设置下载类型
            mMaps.put("fileId", mDownLoadAppRequestBean.getFileId());//特例添加fileId
        } catch (Exception e) {
            // TODO: handle exception
            Log.e("下载", e.toString());
        }
        return mMaps;
    }
    public static<T> void UpLoadHeadAndCode2(Context mContext, UpLoadPicRequestBean mUpLoadPicRequestBean, final Class<T> parser, ReqProgressCallBack<T> reqProgressCallBack) {
        //upate head
        UploadAndDownLoadHttpUtils.HttpUtilsInstance(mContext).upLoadFile(ApiConstant.UPLOADPIC_HEAD,getUpdateParams(mContext,mUpLoadPicRequestBean),parser,reqProgressCallBack);
    }
    /**
     * 得到更新的参数
     *
     * @return
     */
    private static HashMap getUpdateParams(Context mContext, UpLoadPicRequestBean mUpLoadPicRequestBean) {
        HashMap<String, Object> mMaps = new HashMap<>();
        try {
            // 必须参数
            mMaps.put("accessToken", "ddddd");//自己填入
            mMaps.put("pictureFile", mUpLoadPicRequestBean.getPictureFile());
        } catch (Exception e) {
            // TODO: handle exception
            Log.e("update图片", e.toString());
        }
        return mMaps;
    }

}
