package com.zxg.myokhttp.okutils;//package com.zxg.frame.http;

import android.content.Context;

import com.zxg.myokhttp.config.ApiConstant;
import com.zxg.myokhttp.requestbean.UpLoadPicRequestBean;
import com.zxg.myokhttpframwork.OkHttpLogUtil;
import com.zxg.myokhttpframwork.ReqProgressCallBack;
import com.zxg.myokhttpframwork.UploadAndDownLoadHttpUtils;

import java.util.HashMap;

public class OKHttpsUpAndDownApi {
    /**
     * 上传文件接口
     *
     * @param mContext
     * @param mUpLoadPicRequestBean
     */
    public static<T> void UpLoadHeadAndCode2(Context mContext, UpLoadPicRequestBean mUpLoadPicRequestBean, final Class<T> parser, ReqProgressCallBack<T> reqProgressCallBack) {
        //upate head，url可以从外面传进来，也可以在里面设置ApiConstant.UPLOADPIC_HEAD
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
            mMaps.put("accessToken", "SharedPrefHelper.getInstance(mContext).getToken()");//accessToken从bean类里面取，或者从sp里面取
            mMaps.put("pictureFile", mUpLoadPicRequestBean.getPictureFile());
        } catch (Exception e) {
            // TODO: handle exception
            OkHttpLogUtil.d("update图片", e.toString());
        }
        return mMaps;
    }
}
