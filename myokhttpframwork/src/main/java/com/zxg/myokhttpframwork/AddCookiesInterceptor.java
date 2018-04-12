package com.zxg.myokhttpframwork;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者：zxg on 2016/12/27 16:30
 * 添加cookie
 */

public class AddCookiesInterceptor implements Interceptor {
    private Context context;

    public AddCookiesInterceptor(Context context) {
        super();
        this.context = context;

    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request.Builder builder = chain.request().newBuilder();
//        if("http://192.168.1.119:7998/pos-provider/checkLogin?".equals(chain.request().url().toString())){
//        if (ApiConstant.LOGIN.equals(chain.request().url().toString())) {
//
//        } else {
        SharedPreferences sharedPreferences = context.getSharedPreferences(GlobalName.cookieFileName, Context.MODE_PRIVATE);
        //添加cookie
        builder.addHeader("Cookie", sharedPreferences.getString(GlobalName.cookieName, ""));
//        }
        return chain.proceed(builder.build());
    }
}
