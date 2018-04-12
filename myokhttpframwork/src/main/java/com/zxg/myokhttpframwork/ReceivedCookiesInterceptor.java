package com.zxg.myokhttpframwork;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * 作者：zxg on 2016/12/27 16:14
 */

public class ReceivedCookiesInterceptor implements Interceptor {
    private Context context;

    public ReceivedCookiesInterceptor(Context context) {
        super();
        this.context = context;

    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Response originalResponse = chain.proceed(chain.request());
        //这里获取请求返回的cookie
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            final StringBuffer cookieBuffer = new StringBuffer();
            List<String> s = originalResponse.headers("Set-Cookie");
            String[] cookieArray = s.get(0).split(";");
            for (int i = 0; i < cookieArray.length; i++) {
                OkHttpLogUtil.d("获取的cook数组 = ", cookieArray[i]);
                if (cookieArray[i].contains("JSESSIONID")) {
                    cookieBuffer.append(cookieArray[i]).append(";");
                }
            }
            SharedPreferences sharedPreferences = context.getSharedPreferences(GlobalName.cookieFileName, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(GlobalName.cookieName, cookieBuffer.toString());
            OkHttpLogUtil.d("获取的cookies = ", cookieBuffer.toString());
            editor.commit();
        } else {
            OkHttpLogUtil.d("Set-Cookie = ", "IT'S NULL");
        }

        return originalResponse;
    }
}
