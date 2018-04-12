package com.zxg.myokhttpframwork;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;


/**
 * Author ：zxg on 2018/4/11 10:55
 * email : remotecountry@163.com
 * date : 2018/4/11
 */

public class UploadAndDownLoadHttpUtils {
    private String TAG = "UploadAndDownLoadHttp";

    private static OkHttpClient client = null;
    private static UploadAndDownLoadHttpUtils mHttpUtils = null;
    private Gson gson = null;
    /**
     * 超时时间
     */
    public final static int TIMEOUT = 25;
    /**
     * JSON请求
     */
    public static final MediaType JSON = MediaType
            .parse("application/json; charset=utf-8");


    private String showContentString = "网络错误，请检查网络！";
    private String error_timeOut = "java.net.SocketTimeoutException";
    private String error_canConnect = "java.net.ConnectException";
    //    private  String error_server = "code=500";
//    private  String error_502 = "code=502";
//    private  String error_404 = "code=404";
//    private  String error_405 = "Method Not Allowed";
    private String error_HttpRetry = "java.net.HttpRetryException";
    private String error_NoRouteToHost = "java.net.NoRouteToHostException";
    private String error_PortUnreachable = "java.net.PortUnreachableException";
    private String error_Protocol = "java.net.ProtocolException";
    private String error_SecurityException = "java.lang.SecurityException";

    private String downLoadFail = "下载失败";
    private String downLoadSuccess = "下载成功";
    private String upLoadFail = "上传失败";
    private String upLoadSuccess = "上传成功";

    public UploadAndDownLoadHttpUtils(Context mContext) {

    }
    /**
     * 获取下载存储位置
     *
     * @return
     */
    public String getFileCacheDirectory() {
        File sdcardDir = Environment.getExternalStorageDirectory();
        String fileDirectory = "";
        if (null != sdcardDir) {
            fileDirectory = AppLocation.location_sdcard;
        }
        return fileDirectory;
    }
    /**
     * 单例
     *
     * @param mContext
     * @return
     */
    public static UploadAndDownLoadHttpUtils HttpUtilsInstance(Context mContext) {
        if (null == mHttpUtils) {
            synchronized (UploadAndDownLoadHttpUtils.class) {
                mHttpUtils = new UploadAndDownLoadHttpUtils(mContext);
            }
        }
        okHttpClientInstance(mContext);
        return mHttpUtils;
    }

    /**
     * 单例化Client
     *
     * @param mContext
     * @return
     */
    private static void okHttpClientInstance(Context mContext) {
        if (null == client) {
            synchronized (UploadAndDownLoadHttpUtils.class) {
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                //设置超时
                builder
                        //设置之后，连接超时时间失效
                        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                        .readTimeout(TIMEOUT, TimeUnit.SECONDS)//设置读取超时时间
                        .writeTimeout(TIMEOUT, TimeUnit.SECONDS).//设置写的超时时间
//                .addInterceptor(new AddCookiesInterceptor(mContext)).
        addInterceptor(new LoggingInterceptor()).
//                addInterceptor(new ReceivedCookiesInterceptor(mContext)).
        retryOnConnectionFailure(false).//不失败重连
                        followRedirects(true)//支持重定向
                        .build();
                client = builder.build();
            }
        }
    }


    /**
     * 上传文件
     *
     * @param actionUrl 接口地址
     * @param paramsMap 参数
     * @param callBack  回调
     * @param <T>
     */
    public <T> void upLoadFile(String actionUrl, HashMap<String, Object> paramsMap,  final Class<T> parser, final ReqProgressCallBack<T> callBack) {
        try {
            //补全请求地址
            MultipartBody.Builder builder = new MultipartBody.Builder();
            //设置类型
            builder.setType(MultipartBody.FORM);
            //追加参数
            for (String key : paramsMap.keySet()) {
                Object object = paramsMap.get(key);
                if(null != object){
                    if (!(object instanceof File)) {
                        builder.addFormDataPart(key, object.toString());
                    } else {
                        File file = (File) object;
                        MediaType Image = MediaType.parse("image/jpeg; charset=utf-8");
                        builder.addFormDataPart(key, file.getName(), createProgressRequestBody(Image, file, callBack));
                    }
                }

            }
            //创建RequestBody
            RequestBody body = builder.build();
            //创建Request
            final Request request = new Request.Builder().url(actionUrl).post(body).build();
            OnStart(callBack);
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e(TAG, e.toString());
                    failedCallBack(upLoadFail, callBack, e);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
//                        String string = response.body().string();
//                        Log.e(TAG, "response ----->" + string);

                        if (null == gson) {
                            gson = new Gson();
                        }
                        T result = gson.fromJson(response.body().string(), parser);
                        successCallBack(result, callBack);
                    } else {
                        HttpCodeException exception = new HttpCodeException(response.toString(), response.code());
                        failedCallBack(upLoadFail, callBack, exception);
                    }
                }
            });
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    /**
     * 创建带进度的RequestBody
     *
     * @param contentType MediaType
     * @param file        准备上传的文件
     * @param callBack    回调
     * @param <T>
     * @return
     */
    private <T> RequestBody createProgressRequestBody(final MediaType contentType, final File file, final ReqProgressCallBack<T> callBack) {
        return new RequestBody() {
            @Override
            public MediaType contentType() {
                return contentType;
            }

            @Override
            public long contentLength() {
                return file.length();
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                Source source;
                try {
                    source = Okio.source(file);
                    Buffer buf = new Buffer();
                    long remaining = contentLength();
                    long current = 0;
                    for (long readCount; (readCount = source.read(buf, 2048)) != -1; ) {
                        sink.write(buf, readCount);
                        current += readCount;
                        Log.e(TAG, "current------>" + current);
                        progressCallBack(remaining, current, callBack);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }


    /**
     * 下载文件
     *
     * @param fileUrl     文件url
     */
    public <T> void downLoadFile(String fileUrl,String appDir,HashMap<String, String> paramsMap, final ReqProgressCallBack<T> callBack) {
        final File file = new File(appDir);
        Log.e("运行","进入下载");
        if (file.exists()) {
//            successCallBack((T) file, callBack);
            successCallBack(downLoadSuccess, callBack);
            return;
        }
        OnStart(callBack);
        FormBody.Builder builder = new FormBody.Builder();

        //遍历map,拼接
        if (paramsMap != null) {
            for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                if(null == entry.getValue()){
                    Log.e("值为空","NULL");
                }else {
                    Log.e("值=",entry.getValue().toString());
                    builder.add(entry.getKey(), entry.getValue().toString());
                }

            }
        }
        Log.e("运行","进入下载1");
        RequestBody body = builder.build();
        final Request request = new Request.Builder()
                .url(fileUrl)
                .post(body)
//                    .addHeader("accessToken", SharedPrefHelper.getInstance(mContext).getToken())//需要添加头部header,比如token的时候
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, e.toString());
                failedCallBack(downLoadFail, callBack, e);
                Log.e("运行","进入下载2");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("运行","进入下载888");
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                try {
                    long total = response.body().contentLength();
                    Log.e(TAG, "total------>" + total);
                    long current = 0;
                    is = response.body().byteStream();
                    fos = new FileOutputStream(file);
                    while ((len = is.read(buf)) != -1) {
                        current += len;
                        fos.write(buf, 0, len);
                        Log.e(TAG, "current------>" + current);
                        progressCallBack(total, current, callBack);
                    }
                    fos.flush();
                    Log.e("运行","进入下载3");
//                    successCallBack((T) file, callBack);
                    successCallBack(downLoadSuccess, callBack);
                } catch (IOException e) {
                    Log.e(TAG, e.toString());
                    Log.e("运行","进入下载4");
                    failedCallBack(downLoadFail, callBack, e);
                } finally {
                    try {
                        if (is != null) {
                            is.close();
                        }
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (IOException e) {
                        Log.e(TAG, e.toString());
                        Log.e("运行","进入下载5");
                    }
                }
            }
        });
    }

    private Handler okHttpHandler = new Handler(Looper.getMainLooper());

    /**
     * 统一处理进度信息
     *
     * @param total    总计大小
     * @param current  当前进度
     * @param callBack
     * @param <T>
     */
    private <T> void progressCallBack(final long total, final long current, final ReqProgressCallBack<T> callBack) {
        okHttpHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.onProgress(total, current);
                }
            }
        });
    }

    /**
     * 开始前
     *
     * @param callBack
     */
    private void OnStart(final ReqProgressCallBack callBack) {
        if (callBack != null) {
            okHttpHandler.post(new Runnable() {
                @Override
                public void run() {//在主线程操作
                    callBack.onPreTask();
                }
            });
        }
    }

    /**
     * 成功后
     *
     * @param callBack
     * @param data
     * @param <T>
     */
    private <T> void successCallBack(final T data, final ReqProgressCallBack callBack) {
        if (callBack != null) {
            okHttpHandler.post(new Runnable() {
                @Override
                public void run() {//在主线程操作
                    callBack.successCallBack(data);
                }
            });
        }
    }

    /**
     * 请求发生错误
     *
     * @param callBack
     * @param e
     */
    private <T> void failedCallBack(T data, final ReqProgressCallBack callBack, final Exception e) {
        if (callBack != null) {
            okHttpHandler.post(new Runnable() {
                @Override
                public void run() {
                    callBack.failedCallBack(e);
                }
            });
        }
    }
}
