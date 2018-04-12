//package com.zxg.myokhttpframwork;
//
//import android.app.Activity;
//import android.content.Context;
//import android.os.Environment;
//import android.view.View;
//import android.view.WindowManager;
//import android.widget.Button;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
///**
// * Author ：zxg on 2018/4/10 17:05
// * email : remotecountry@163.com
// * date : 2018/4/10
// */
//
//public class OKHttpAppUpDateUtils {
//    private Activity mContext;
//    private boolean cancelDownload;
//    private File downloadAppFile_huabo;
//    private File downloadAppFile_xinye;
//
//    public static final int NO_SD_CARD = 113;
//    public static final int PROGRESS_UPDATE = 114;
//    public static final int PROGRESS_COMPLETE = 115;
//    public static final int PROGRESS_REMAIN_DIALOG = 116;
//    private int hadDownloadSize;
//
//
//    private ProgressBar force_progressbar_huabo;//强制升级的dialog上的进度条
//    private TextView force_dialog_content_huabo;//强制升级的dialog上的进度条内容
//    private ProgressBar force_progressbar_xingye;//强制升级的dialog上的进度条
//    private TextView force_dialog_content_xingye;//强制升级的dialog上的进度条内容
//    private CustomDialog customDialog_huabo;//强制升级dialog华博
//    private CustomDialog customDialog_xinye;//强制升级dialog兴业
//
//    private boolean posNeedUpdate = false;
//    private boolean xinyeNeedUpdate = false;
//
//    private Button mButton_huabo;
//    private Button mButton_xinye;
//
//    protected Handler mHandler = new Handler() {
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case NO_SD_CARD:// 没有SD卡
//                    ToastUtil.showShort(mContext, "升级失败，请插入SD卡");
//                    break;
//                case PROGRESS_UPDATE:// 进度条更新
//                    force_dialog_content_huabo.setText(hadDownloadSize + "/100");
//                    LogUtil.e("进度 = ", hadDownloadSize + "");
//                    force_progressbar_huabo.setProgress(hadDownloadSize);
//                    break;
//                case PROGRESS_COMPLETE:// 更新完成
//                    cancelDownload = true;
//                    MyApplication.isAppUpgrading = false;
//                    LogUtil.e("apk路径 = ", downloadAppFile_huabo.getAbsolutePath());
//                    AboutApp.installApk(downloadAppFile_huabo.getAbsolutePath(), mContext);
//
//                    hadDownloadSize = 0;
//                    force_dialog_content_huabo.setText(hadDownloadSize + "/100");
//                    force_progressbar_huabo.setProgress(hadDownloadSize);
//                    force_dialog_content_huabo.setVisibility(View.GONE);
//                    mButton_huabo.setClickable(true);
//                    mButton_huabo.setEnabled(true);
//
//                    break;
//                case PROGRESS_REMAIN_DIALOG:// 更新失败
//                    ToastUtil.showShort(mContext,"请再次点击更新");
//                    cancelDownload = true;
//                    MyApplication.isAppUpgrading = false;
//
//                    hadDownloadSize = 0;
//                    force_dialog_content_huabo.setText(hadDownloadSize + "/100");
//                    force_progressbar_huabo.setProgress(hadDownloadSize);
//                    force_dialog_content_huabo.setVisibility(View.GONE);
//                    mButton_huabo.setClickable(true);
//                    mButton_huabo.setEnabled(true);
//
//                    break;
//            }
//        }
//
//        ;
//    };
//    protected Handler mHandler_xingye = new Handler() {
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case NO_SD_CARD:// 没有SD卡
//                    ToastUtil.showShort(mContext, "升级失败，请插入SD卡");
//                    break;
//                case PROGRESS_UPDATE:// 进度条更新
//                    force_dialog_content_xingye.setText(hadDownloadSize + "/100");
//                    LogUtil.e("进度 = ", hadDownloadSize + "");
//                    force_progressbar_xingye.setProgress(hadDownloadSize);
//                    break;
//                case PROGRESS_COMPLETE:// 更新完成
//                    cancelDownload = true;
//                    MyApplication.isAppUpgrading = false;
//                    LogUtil.e("apk路径 = ", downloadAppFile_xinye.getAbsolutePath());
//                    AboutApp.installApk(downloadAppFile_xinye.getAbsolutePath(), mContext);
//
//                    hadDownloadSize = 0;
//                    force_dialog_content_xingye.setText(hadDownloadSize + "/100");
//                    force_progressbar_xingye.setProgress(hadDownloadSize);
//                    force_dialog_content_xingye.setVisibility(View.GONE);
//                    mButton_xinye.setClickable(true);
//                    mButton_xinye.setEnabled(true);
//                    if(null != customDialog_xinye){
//                        customDialog_xinye.dismiss();
//                    }
//                    break;
//                case PROGRESS_REMAIN_DIALOG:// 更新失败
//                    ToastUtil.showShort(mContext,"请再次点击更新");
//                    cancelDownload = true;
//                    MyApplication.isAppUpgrading = false;
//
//                    hadDownloadSize = 0;
//                    force_dialog_content_xingye.setText(hadDownloadSize + "/100");
//                    force_progressbar_xingye.setProgress(hadDownloadSize);
//                    force_dialog_content_xingye.setVisibility(View.GONE);
//                    mButton_xinye.setClickable(true);
//                    mButton_xinye.setEnabled(true);
//
//                    break;
//            }
//        }
//
//        ;
//    };
//
//    public OKHttpAppUpDateUtils(Activity context) {
//        mContext = context;
//    }
//    public void startUp() {
//        startUp(null);
//    }
//    *//**
//            * 启动发送的请求，查看是否需要更新app
//    *//*
//    public void startUp(final OnUpdateListener<AppUpdateBackBean> mOnUpdateListener) {
//        AppUpdateRequestBean mAppUpdateRequestBean = new AppUpdateRequestBean();
//        OKHttpsApi.App_upDate(mContext, mAppUpdateRequestBean, new OnHttpTaskListener<AppUpdateBackBean>() {
//            @Override
//            public void onPreTask() {
//                if(null != mOnUpdateListener){
//                    mOnUpdateListener.onPreTask();
//                }
//            }
//
//            @Override
//            public void onTaskError(Exception throwable) {
//                if(null != mOnUpdateListener){
//                    mOnUpdateListener.onTaskError(throwable);
//                }
//            }
//
//            @Override
//            public void onFinishTask(AppUpdateBackBean bean) {
//                if (ResponseUtils.chkResponse(bean, mContext)) {
//                    try {
//                        LogUtil.e("本地Code = ", AboutApp.getVersionCode(mContext) + "");
//                        LogUtil.e("兴业本地Code = ", AboutApp.getVersionCodeXinye(mContext) + "");
//                        //一下为判断升级方式，需要根据返回参数具体操作
//                        if(null != bean && null != bean.getContent()){
//                            LogUtil.e("升级数据个数=",bean.getContent().size()+"");
//                            for(int i=0;i<bean.getContent().size();i++){
//                                if(null != bean.getContent().get(i)){
//                                    switch (bean.getContent().get(i).getVersionType()+""){
//                                        case AppType.POS:
//                                            if(null == bean.getContent().get(i)){
//                                                posNeedUpdate = false;
//                                            }else {
//                                                if (Integer.valueOf(bean.getContent().get(i).getVersionCode()) > AboutApp.getVersionCode(mContext)) {
//                                                    String downLoadUrl = Constant.DOWNLOADURL;
//                                                    customDialog_huabo = LoadingDialog_huabo(mContext, downLoadUrl, bean.getContent().get(i));
//                                                    posNeedUpdate = true;
//                                                }else {
//                                                    posNeedUpdate = false;
//                                                }
//                                            }
//
//                                            break;
//                                        case AppType.XINYEAPP:
//                                            if(null == bean.getContent().get(i)){
//                                                xinyeNeedUpdate = false;
//                                            }else {
//                                                if (Integer.valueOf(bean.getContent().get(i).getVersionCode()) > AboutApp.getVersionCodeXinye(mContext)) {
//                                                    String downLoadUrl = Constant.DOWNLOADURL;
//                                                    customDialog_xinye = LoadingDialog_xingye(mContext, downLoadUrl, bean.getContent().get(i));
//                                                    xinyeNeedUpdate = true;
//                                                }else {
//                                                    xinyeNeedUpdate = false;
//                                                }
//                                            }
//
//                                            break;
//                                    }
//                                }
//                            }
//                            if(posNeedUpdate == false && xinyeNeedUpdate == false){
//                                if(null != mOnUpdateListener){
//                                    mOnUpdateListener.onFinishTaskOnlyShowToast(bean);
//                                }
//                            }else {
//                                if(null != mOnUpdateListener){
//                                    mOnUpdateListener.onFinishTask(bean);
//                                }
//                            }
//                        }
//                    }catch (Exception e){
//                        LogUtil.e("版本更新e=",e.toString());
//                        if(null != mOnUpdateListener){
//                            mOnUpdateListener.onTaskError(e);
//                        }
//                    }
//
//
////                    //1是兴业更新信息
////                    if (null != bean.getContent().get(1) && bean.getContent().get(0).getVersionType() == 4 && (bean.getContent().get(1).getVersionCode() < (AboutApp.getVersionCodeXinye(mContext)))) {
////                        String downLoadUrl = Constant.DOWNLOADURL;
////                        customDialog_xinye = LoadingDialog_xingye(mContext, downLoadUrl, bean.getContent().get(1));
////                    }
////                    //0是pos更新信息
////                    if (null != bean.getContent().get(0) && bean.getContent().get(0).getVersionType() == 1 && (bean.getContent().get(0).getVersionCode() < (AboutApp.getVersionCode(mContext)))) {
////                        String downLoadUrl = Constant.DOWNLOADURL;
////                        customDialog_huabo = LoadingDialog_huabo(mContext, downLoadUrl, bean.getContent().get(0));
////                    }
//                }else {
//                    if(null != mOnUpdateListener){
//                        mOnUpdateListener.onTaskError(new Exception("服务器返回错误"));
//                    }
//                }
//            }
//        });
//    }
//
//
//    *//**
//            * 显示加载dialog
//    *
//            * @return
//            *//*
//    private CustomDialog LoadingDialog_huabo(final Context mContext, final String upgradeUrl, final AppUpdateBackBean.ContentBean bean) {
//        final CustomDialog customDialog = new CustomDialog(mContext, R.layout.force_update_dialog, R.style.Theme_dialog);
//        WindowManager.LayoutParams lp = customDialog.getWindow().getAttributes();
//        lp.dimAmount = 0.5f;
//        customDialog.getWindow().setAttributes(lp);
//        customDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//
//        force_progressbar_huabo = (ProgressBar) customDialog.findViewById(R.id.force_dialog_upgrade);
//        force_dialog_content_huabo = (TextView) customDialog.findViewById(force_dialog_content);
//        TextView force_dialog_content_info = (TextView) customDialog.findViewById(R.id.force_dialog_content_info);
//        if (null != bean && null != bean.getVersionDesc()) {
//            force_dialog_content_info.setText(bean.getVersionDesc());
//        }
//        mButton_huabo = (Button) customDialog.findViewById(R.id.btn_Confirm);
//        mButton_huabo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mButton_huabo.setClickable(false);
//                mButton_huabo.setEnabled(false);
//                if (MyApplication.isAppUpgrading) {
//                    ToastUtil.showShort(mContext, "当前正在升级，您不需要重新下载！");
//                } else {
//                    // SD卡不存在就不去升级
//                    boolean isSDCardExists = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
//                    if (!isSDCardExists) {
//                        MyApplication.isAppUpgrading = false;// 下载终止
//                        mHandler.sendEmptyMessage(NO_SD_CARD);
//                        return;
//                    }
//                    final String appName = "downLoad_" + "huabo_" + bean.getVersionCode() + ".apk";
//                    downloadAppFile_huabo = new File(getFileCacheDirectory() + "/" + appName);
//
//                    final DownLoadAppRequestBean mDownLoadAppRequestBean = new DownLoadAppRequestBean();
//                    mDownLoadAppRequestBean.setFileId(String.valueOf(bean.getFileId()));
//                    mDownLoadAppRequestBean.setFilepath((getFileCacheDirectory() + "/" + appName));
//                    mDownLoadAppRequestBean.setType(AppType.POS);//设置下载app的类型
//                    // 子线程下载更新
//                    new Thread() {
//                        public void run() {
//                            MyApplication.isAppUpgrading = true;// 正在下载App
//                            cancelDownload = false;
//                            // 文件格式
//                            if (upgradeUrl != null && upgradeUrl.length() > 0) {
//                                downLoadNewApp_huabo(appName, upgradeUrl,mDownLoadAppRequestBean);
//                            }
//                        };
//                    }.start();
//
//
//
//
//                }
//            }
//        });
//
//        customDialog.show();
//        customDialog.setCancelable(false);
//        customDialog.setCanceledOnTouchOutside(false);
//        return customDialog;
//    }
//
//    *//**
//            * 显示加载dialog
//    *
//            * @return
//            *//*
//    private CustomDialog LoadingDialog_xingye(final Context mContext, final String upgradeUrl, final AppUpdateBackBean.ContentBean bean) {
//        final CustomDialog customDialog = new CustomDialog(mContext, R.layout.force_update_dialog, R.style.Theme_dialog);
//        WindowManager.LayoutParams lp = customDialog.getWindow().getAttributes();
//        lp.dimAmount = 0.5f;
//        customDialog.getWindow().setAttributes(lp);
//        customDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//
//        force_progressbar_xingye = (ProgressBar) customDialog.findViewById(R.id.force_dialog_upgrade);
//        force_dialog_content_xingye = (TextView) customDialog.findViewById(force_dialog_content);
//        TextView force_dialog_content_info = (TextView) customDialog.findViewById(R.id.force_dialog_content_info);
//        if (null != bean && null != bean.getVersionDesc()) {
//            force_dialog_content_info.setText(bean.getVersionDesc());
//        }
//        mButton_xinye = (Button) customDialog.findViewById(R.id.btn_Confirm);
//        mButton_xinye.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mButton_xinye.setClickable(false);
//                mButton_xinye.setEnabled(false);
//                if (MyApplication.isAppUpgrading) {
//                    ToastUtil.showShort(mContext, "当前正在升级，您不需要重新下载！");
//                } else {
//                    // SD卡不存在就不去升级
//                    boolean isSDCardExists = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
//                    if (!isSDCardExists) {
//                        MyApplication.isAppUpgrading = false;// 下载终止
//                        mHandler_xingye.sendEmptyMessage(NO_SD_CARD);
//                        return;
//                    }
//                    final String appName = "downLoad_" + "xingye_" + bean.getVersionCode() + ".apk";
//                    downloadAppFile_xinye = new File(getFileCacheDirectory_xinye() + "/" + appName);
//
//
//                    final DownLoadAppRequestBean mDownLoadAppRequestBean = new DownLoadAppRequestBean();
//                    mDownLoadAppRequestBean.setFileId(String.valueOf(bean.getFileId()));//设置兴业的fileid
//                    mDownLoadAppRequestBean.setFilepath((getFileCacheDirectory_xinye() + "/" + appName));
//                    mDownLoadAppRequestBean.setType(AppType.XINYEAPP);//设置下载app的类型
//                    // 子线程下载更新
//                    new Thread() {
//                        public void run() {
//                            MyApplication.isAppUpgrading = true;// 正在下载App
//                            cancelDownload = false;
//                            // 文件格式
//                            if (upgradeUrl != null && upgradeUrl.length() > 0) {
//                                downLoadNewApp_xinye(appName, upgradeUrl,mDownLoadAppRequestBean);
//                            }
//                        };
//                    }.start();
//                }
//            }
//        });
//
//        customDialog.show();
//        customDialog.setCancelable(false);
//        customDialog.setCanceledOnTouchOutside(false);
//        return customDialog;
//    }
//
//    *//**
//            * 获取下载存储位置
//    *
//            * @return
//            *//*
//    public String getFileCacheDirectory() {
//        File sdcardDir = Environment.getExternalStorageDirectory();
//        String fileDirectory = "/mnt/sdcard";
//        if (null != sdcardDir) {
//            fileDirectory = sdcardDir.getAbsolutePath() + "/pospay/apk";
//        }
//        return fileDirectory;
//    }
//
//    *//**
//            * 获取兴业下载存储位置
//    *
//            * @return
//            *//*
//    public String getFileCacheDirectory_xinye() {
//        File sdcardDir = Environment.getExternalStorageDirectory();
//        String fileDirectory = "/mnt/sdcard";
//        if (null != sdcardDir) {
//            fileDirectory = sdcardDir.getAbsolutePath() + "/xingyeapk/apk";
//        }
//        return fileDirectory;
//    }
//
//    *//**
//            * 下载App
//    *
//            * @param appName
//    * @param downLoadUrl
//    *//*
//    private void downLoadNewApp_xinye(String appName, String downLoadUrl,DownLoadAppRequestBean mDownLoadAppRequestBean) {
//        InputStream is = null;
//        FileOutputStream fos = null;
//        boolean isSDCardExists = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
//        // SD卡不存在就不去升级
//        if (!isSDCardExists) {
//            MyApplication.isAppUpgrading = false;// 下载终止
//            mHandler_xingye.sendEmptyMessage(NO_SD_CARD);
//            return;
//        }
//        try {
//            MyApplication.isAppUpgrading = true;// 正在下载App
//            cancelDownload = false;
//            LogUtil.e("兴业开始下载", "xxxxxx");
//
//            File directory = new File(getFileCacheDirectory());
//            if (!directory.exists()) {
//                directory.mkdirs();
//            }
//            String url_xinye = downLoadUrl+"fileId="+mDownLoadAppRequestBean.getFileId()+"&type="+mDownLoadAppRequestBean.getType();
//            URL url = new URL(url_xinye);
//            // 打开连接
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestProperty("Accept-Encoding", "identity");
//            // 获得输入流
//            is = connection.getInputStream();
//            // 文件总大小
//            double fileSize = connection.getContentLength();
//            fos = new FileOutputStream(downloadAppFile_xinye);
//            long notifUpdataPeriod = System.currentTimeMillis();
//            byte[] buffer = new byte[1024];
//            int len = 0;
//            double hasDownLoadFileSize = 0;
//            do {
//                if ((len = is.read(buffer)) != -1) {
//                    fos.write(buffer, 0, len);
//                    hasDownLoadFileSize += len;
//                    hadDownloadSize = (int) (hasDownLoadFileSize / fileSize * 100);
//                    LogUtil.e("hadDownloadSize = ", hadDownloadSize + "");
//                    LogUtil.e("fileSize = ", fileSize + "");
//                    // 1s通知2次
//                    if (System.currentTimeMillis() - notifUpdataPeriod > 500) {
//                        // 发送消息更新进度条
//                        notifUpdataPeriod = System.currentTimeMillis();
//
//                        // 发送消息更新进度条
//                        LogUtil.e("OkXinye=current,total=", hasDownLoadFileSize + "----" + fileSize + "");
//                        hadDownloadSize = (int) (((double) hasDownLoadFileSize / fileSize) * 100);
//                        mHandler_xingye.sendEmptyMessage(PROGRESS_UPDATE);
//
//                    }
//                } else {
//
//                    *//*
//                    * 下载完成
//                            *//*
//                            mHandler_xingye.sendEmptyMessage(PROGRESS_COMPLETE);
//                    LogUtil.e("下载完成", "xxxxxx");
//
//                    fos.flush();
//                }
//            } while (!cancelDownload);
//        } catch (Exception e) {
//            e.printStackTrace();
//            LogUtil.e("兴业下载e=",e.toString());
//
//            MyApplication.isAppUpgrading = false;
//            ToastUtil.showShort(mContext, "下载失败");
//            mHandler_xingye.sendEmptyMessage(PROGRESS_REMAIN_DIALOG);
//        } finally {
//            // 下载终止
//            MyApplication.isAppUpgrading = false;
//            try {
//                if (is != null) {
//                    is.close();
//                }
//                if (fos != null) {
//                    fos.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//    *//**
//            * 下载App
//    *
//            * @param appName
//    * @param downLoadUrl
//    *//*
//    private void downLoadNewApp_huabo(String appName, String downLoadUrl,DownLoadAppRequestBean mDownLoadAppRequestBean) {
//        InputStream is = null;
//        FileOutputStream fos = null;
//        boolean isSDCardExists = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
//        // SD卡不存在就不去升级
//        if (!isSDCardExists) {
//            MyApplication.isAppUpgrading = false;// 下载终止
//            mHandler.sendEmptyMessage(NO_SD_CARD);
//            return;
//        }
//        try {
//
//            MyApplication.isAppUpgrading = true;// 正在下载App
//            cancelDownload = false;
//            LogUtil.e("华博开始下载", "xxxxxx");
//
//            File directory = new File(getFileCacheDirectory());
//            if (!directory.exists()) {
//                directory.mkdirs();
//            }
//            String url_huabo = downLoadUrl+"fileId="+mDownLoadAppRequestBean.getFileId()+"&type="+mDownLoadAppRequestBean.getType();
//            URL url = new URL(url_huabo);
//            // 打开连接
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestProperty("Accept-Encoding", "identity");
//            // 获得输入流
//            is = connection.getInputStream();
//            // 文件总大小
//            double fileSize = connection.getContentLength();
//            fos = new FileOutputStream(downloadAppFile_huabo);
//            long notifUpdataPeriod = System.currentTimeMillis();
//            byte[] buffer = new byte[1024];
//            int len = 0;
//            double hasDownLoadFileSize = 0;
//            do {
//                if ((len = is.read(buffer)) != -1) {
//                    fos.write(buffer, 0, len);
//                    hasDownLoadFileSize += len;
//                    hadDownloadSize = (int) (hasDownLoadFileSize / fileSize * 100);
//                    LogUtil.e("hadDownloadSize = ", hadDownloadSize + "");
//                    LogUtil.e("fileSize = ", fileSize + "");
//                    // 1s通知2次
//                    if (System.currentTimeMillis() - notifUpdataPeriod > 500) {
//                        // 发送消息更新进度条
//                        notifUpdataPeriod = System.currentTimeMillis();
//
//                        // 发送消息更新进度条
//                        LogUtil.e("OKHuabo=current,total=", hasDownLoadFileSize + "----" + fileSize + "");
//                        hadDownloadSize = (int) (((double) hasDownLoadFileSize / fileSize) * 100);
//                        mHandler.sendEmptyMessage(PROGRESS_UPDATE);
//
//                    }
//                } else {
//                    *//*
//                    * 下载完成
//                            *//*
//                            mHandler.sendEmptyMessage(PROGRESS_COMPLETE);
//                    cancelDownload = true;
//                    LogUtil.e("下载完成", "xxxxxx");
//
//                    fos.flush();
//                }
//            } while (!cancelDownload);
//        } catch (Exception e) {
//            e.printStackTrace();
//            LogUtil.e("华博下载e=",e.toString());
//            MyApplication.isAppUpgrading = false;
////                            ToastUtil.showShort(mContext, "下载失败");
//            *//*
//            * 下载失败
//                    *//*
//                    mHandler.sendEmptyMessage(PROGRESS_REMAIN_DIALOG);
//        } finally {
//            // 下载终止
//            MyApplication.isAppUpgrading = false;
//            try {
//                if (is != null) {
//                    is.close();
//                }
//                if (fos != null) {
//                    fos.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
