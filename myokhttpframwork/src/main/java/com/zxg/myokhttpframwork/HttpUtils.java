package com.zxg.myokhttpframwork;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Author ：zxg on 2017/3/22 14:21
 * email : remotecountry@163.com
 * http请求基类
 */

public class HttpUtils {
    private static final String TAG = "HttpUtils";
    private static OkHttpClient client = null;
    private static HttpUtils mHttpUtils = null;
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


    private Handler handler = new Handler(Looper.getMainLooper());

    public HttpUtils(Context mContext) {

    }

    /**
     * 单例
     *
     * @param mContext
     * @return
     */
    public static HttpUtils HttpUtilsInstance(Context mContext) {
        if (null == mHttpUtils) {
            synchronized (UploadAndDownLoadHttpUtils.class) {
                mHttpUtils = new HttpUtils(mContext);
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
     * 判断是否网络连接
     */
    private boolean isOnline(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            return true;
        }
        return false;
    }

    /**
     * 检查错误具体方法
     *
     * @param ex
     * @param mContext
     */
    private void checkThrowEx(Exception ex, Context mContext) {
        OkHttpLogUtil.d(TAG + ":ExceptionMessage=", ex.toString());
        try {
            if (ex instanceof HttpCodeException) {
                if (((HttpCodeException) ex).getCode() == 500) {
                    //服务器500错误
                    Toast.makeText(mContext, mContext.getResources().getString(R.string.http_server_error_again), Toast.LENGTH_SHORT).show();
                } else if (((HttpCodeException) ex).getCode() == 404) {
                    Toast.makeText(mContext, mContext.getResources().getString(R.string.http_server_error_404), Toast.LENGTH_SHORT).show();
                } else if (((HttpCodeException) ex).getCode() == 405) {
                    Toast.makeText(mContext, mContext.getResources().getString(R.string.http_server_error_again), Toast.LENGTH_SHORT).show();
                }
            } else {
                if (ex.toString().contains(error_timeOut)) {
                    //连接超时
                    Toast.makeText(mContext, mContext.getResources().getString(R.string.http_timeout), Toast.LENGTH_SHORT).show();
                } else if (ex.toString().contains(error_canConnect)) {
                    //连不上服务器
                    Toast.makeText(mContext, mContext.getResources().getString(R.string.http_cantConnect), Toast.LENGTH_SHORT).show();
                } else if (ex.toString().contains(error_HttpRetry)) {
                    //重连错误
                    Toast.makeText(mContext, mContext.getResources().getString(R.string.http_server_error_httpretry), Toast.LENGTH_SHORT).show();
                } else if (ex.toString().contains(error_NoRouteToHost)) {
                    //没有路由到主机
                    Toast.makeText(mContext, mContext.getResources().getString(R.string.http_server_error_NoRouteToHost), Toast.LENGTH_SHORT).show();
                } else if (ex.toString().contains(error_PortUnreachable)) {
                    //端口不能读取
                    Toast.makeText(mContext, mContext.getResources().getString(R.string.http_server_error_PortUnreachable), Toast.LENGTH_SHORT).show();
                } else if (ex.toString().contains(error_Protocol)) {
                    //协议错误
                    Toast.makeText(mContext, mContext.getResources().getString(R.string.http_server_error_Protocol), Toast.LENGTH_SHORT).show();
                } else if (ex.toString().contains(error_SecurityException)) {
                    //安全错误
                    Toast.makeText(mContext, mContext.getResources().getString(R.string.http_server_error_Protocol), Toast.LENGTH_SHORT).show();
                }
            }

        } catch (Exception e) {
            OkHttpLogUtil.d("解析错误进入错误", e.toString());
        }

    }

    /**
     * post请求  map是body
     *
     * @param url
     * @param map
     */
    public <T> void postMap(final Context mContext, final String url, Map<String, String> map, final Class<T> parser, final OnHttpTaskListener<T> mOnHttpTaskListener) {

        try {
            if (isOnline(mContext)) {
                FormBody.Builder builder = new FormBody.Builder();

                //遍历map,拼接
                if (map != null) {
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        if(null == entry.getValue()){
                        }else {
                            builder.add(entry.getKey(), entry.getValue().toString());
                        }
                    }
                }
                RequestBody body = builder.build();
                final Request request = new Request.Builder()
                        .url(url)
                        .post(body)
//                    .addHeader("accessToken", SharedPrefHelper.getInstance(mContext).getToken())//需要添加头部header,比如token的时候
                        .build();

                OnStart(mOnHttpTaskListener);
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        OkHttpLogUtil.d(TAG + "HTTP请求失败：", e.toString());
                        OnError(mOnHttpTaskListener, e, mContext);
                        //如果在onError中提示失败的信息，则可以忽略下面的方法
                        CheckException(mContext, e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            try {
                                if (null == gson) {
                                    gson = new Gson();
                                }
                                OkHttpLogUtil.d(TAG, "JSON开始解析");
                                T result = gson.fromJson(response.body().string(), parser);
                                onSuccess(mOnHttpTaskListener, result);
                                OkHttpLogUtil.d(TAG, "JSON解析成功");
                            } catch (Exception e) {
                                OnError(mOnHttpTaskListener, e, mContext);
                                OkHttpLogUtil.d(TAG, "JSON解析出错" + e.toString());
                            }
                        } else {
                            HttpCodeException exception = new HttpCodeException(response.toString(), response.code());
                            OnError(mOnHttpTaskListener, exception, mContext);
                            CheckException(mContext, exception);
                            OkHttpLogUtil.d("返回成功错误=", response.toString());
                        }
                    }
                });
            } else {
                OnError(mOnHttpTaskListener, new Exception("HAS NO NETWORK"), mContext);
                Toast.makeText(mContext, showContentString, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            OkHttpLogUtil.d(TAG + ":http基类Exception=", e.toString());
        }

    }
    /**
     * 开始前
     *
     * @param callBack
     */
    private void OnStart(final OnHttpTaskListener callBack) {
        if (callBack != null) {
            handler.post(new Runnable() {
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
    private <T> void onSuccess(final OnHttpTaskListener callBack, final T data) {
        if (callBack != null) {
            handler.post(new Runnable() {
                @Override
                public void run() {//在主线程操作
                    callBack.onFinishTask(data);
                }
            });
        }
    }

    /**
     * 请求发生错误
     *
     * @param callBack
     * @param e
     * @param mContext
     */
    private void OnError(final OnHttpTaskListener callBack, final Exception e, final Context mContext) {
        if (callBack != null) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    callBack.onTaskError(e);
                }
            });
        }
    }

    /**
     * 检查错误
     *
     * @param mContext
     * @param e
     */
    private void CheckException(final Context mContext, final Exception e) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                checkThrowEx(e, mContext);
            }
        });
    }

}
