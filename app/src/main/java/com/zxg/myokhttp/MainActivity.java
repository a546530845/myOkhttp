package com.zxg.myokhttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;

import com.zxg.myokhttp.backbean.AppUpdateBackBean;
import com.zxg.myokhttp.okutils.OKHttpsApi;
import com.zxg.myokhttp.okutils.OkHttpReponseUtils;
import com.zxg.myokhttp.requestbean.AppUpdateRequestBean;
import com.zxg.myokhttpframwork.OnHttpTaskListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取设置最小宽度，用于dp适配，dp适配也是一样的，只不过dp适配是根据“最小宽度（Smallest-width）限定符”来找的，
        // 即如果当前设备最小宽度（以 dp 为单位）为400dp，那么系统会自动找到对应的values-sw400dp文件夹下的dimens.xml文件，如图
//        密度比值多少，80%的手机的最小宽度dp值(widthPixels / density)都为360dp
        DisplayMetrics dm = new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int widthPixels = dm.widthPixels;
        int heightPixels = dm.heightPixels;

        float density = dm.density;

        float widthDP = widthPixels / density;
        Log.d("width值=",widthPixels+"");
        Log.d("heightPixels值=",heightPixels+"");
        Log.d("屏幕最小宽度dp值=",widthDP+"");
        Log.d("屏幕密度值=",density+"");


        AppUpdateRequestBean mAppUpdateRequestBean = new AppUpdateRequestBean();
        mAppUpdateRequestBean.setClientType("2");

        /**
         * 正常请求
         */
        OKHttpsApi.App_upDate(this, mAppUpdateRequestBean, new OnHttpTaskListener<AppUpdateBackBean>() {
            @Override
            public void onPreTask() {

            }

            @Override
            public void onTaskError(Exception throwable) {

            }

            @Override
            public void onFinishTask(AppUpdateBackBean bean) {
                if (OkHttpReponseUtils.chkResponse(bean, MainActivity.this)) {
                    try {
                    } catch (Exception e) {
                       Log.d("App_upDate版本请求", e.toString()+"无版本升级信息");
                    }
                }
            }
        });
//        /**
//         * 下载请求,只提供参数，具体参数自行设置
//         */
//        HttpsApi.downLoadApp2(this, upgradeUrl,mDownLoadAppRequestBean.getFilepath(), mDownLoadAppRequestBean, new ReqProgressCallBack<String>() {
//            @Override
//            public void onPreTask() {
//                AppUpDateUtils.isAppUpgrading = true;// 正在下载App
//                mHandler.sendEmptyMessage(PROGRESS_UPDATE);
//                LogUtil.e("华博开始下载", upgradeUrl);
//            }
//
//            @Override
//            public void failedCallBack(Exception throwable) {
//                AppUpDateUtils.isAppUpgrading = false;
//
//                LogUtil.e("下载失败原因=", throwable.getMessage());
//                ToastUtil.showShort(mContext, "下载失败");
//
//                customDialog.dismiss();
//            }
//
//            @Override
//            public void successCallBack(String bean) {
//                mHandler.sendEmptyMessage(PROGRESS_COMPLETE);
//                LogUtil.e("下载完成", "xxxxxx");
//                customDialog.dismiss();
//            }
//
//            @Override
//            public void onProgress(long total, long current) {
//                // 发送消息更新进度条
//                LogUtil.e("current,total=OkHTTP:", current + "----" + total + "");
//                hadDownloadSize = (int) (((double) current / total) * 100);
//                mHandler.sendEmptyMessage(PROGRESS_UPDATE);
//            }
//        });
//        /**
//         * 上传请求
//         */
//        UpLoadPicRequestBean mUpLoadPicRequestBean = new UpLoadPicRequestBean();
//        mUpLoadPicRequestBean.setPictureFile(new File(imagePath));
//        HttpsApi.UpLoadHeadAndCode2(this, mUpLoadPicRequestBean, new ReqProgressCallBack<UpLoadPicBackBean>() {
//            @Override
//            public void onProgress(long total, long current) {
//
//            }
//
//            @Override
//            public void onPreTask() {
//                ShowProDialog(mContext, getResources().getString(R.string.dialog_loading_hb), true);
//            }
//
//            @Override
//            public void failedCallBack(Exception throwable) {
//                DissProDialog();
//            }
//
//            @Override
//            public void successCallBack(UpLoadPicBackBean bean) {
//                DissProDialog();
//                if (OkHttpReponseUtils.chkResponse(bean, mContext)) {
//                    ToastUtil.showShort(mContext, getResources().getString(R.string.headpic_aty_success_hb));
//                    MyApplication.mLoginBackBean.getData().getAccountData().setMsHeadUrl(bean.getData().getMsHeadUrl());
//
//                    universialImageUtil.initImageLoader().displayImage(MyApplication.mLoginBackBean.getData().getAccountData().getMsHeadUrl(), atyHeadshowHead, universialImageUtil.getOptions(0));
//
////                    GlideImgManager.glideLoader(mContext, MyApplication.mLoginBackBean.getData().getAccountData().getMsHeadUrl(), R.mipmap.head_big, R.mipmap.head_big, atyHeadshowHead, 0);
//
//                    EventBus.getDefault().post(new MessageEvent("更新头像了"));
////                            MyApplication.mLoginBackBean.getSubject().getShopMemberVo().setHeadUrl(bean.getSubject().getUrl());
//                } else {
////                    ToastUtil.showShort(mContext, bean.getMessage());
//                }
//            }
//
//        });

    }
}
