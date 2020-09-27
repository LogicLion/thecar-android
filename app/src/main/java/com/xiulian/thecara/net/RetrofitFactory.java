package com.xiulian.thecara.net;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.webkit.WebSettings;


import com.xiulian.thecara.base.App;
import com.xiulian.thecara.constant.Const;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author wzh
 * @date 2019/4/11
 */
public class RetrofitFactory {

    public static NetRequest createRetrofit() {
        Interceptor defaultInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
                        .header("Content-Type", "text/DM-")
                        .header("charset", "utf-8")
                        .header("user-agent", getUserAgent(App.getInstance()))//bxtrip需要的header
                        .header("Authorization", "")
                        .build();
                return chain.proceed(request);
            }
        };

        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印retrofit日志
                Log.i("retrofit-log", "请求结果 = " + message);
            }
        });

        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(defaultInterceptor)
                .readTimeout(Const.HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(Const.HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .build();
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(Const.BASE_URL)
                .client(httpClient)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(NetRequest.class);
    }

    public NetRequest createRetrofit(final String token, final String absStr) {
        Interceptor defaultInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
                        .header("charset", "utf-8")
                        .header("Content-Type", "text/DM-")
                        .header("user-agent", getUserAgent(App.getInstance()))//bxtrip需要的header
                        .header("Authorization", "")
                        .build();
                return chain.proceed(request);
            }
        };

        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印retrofit日志
                Log.i("retrofit-log", "请求结果 = " + message);
            }
        });

        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(defaultInterceptor)
                .readTimeout(Const.HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(Const.HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .build();
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(Const.BASE_URL)
                .client(httpClient)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(NetRequest.class);
    }

    public NetRequest createRetrofitHttps(final String token, final String absStr) {
        Interceptor defaultInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
                        .header("charset", "utf-8")
                        .header("content-type", "application/json")
                        .header("user-agent", getUserAgent(App.getInstance()))//bxtrip需要的header
                        .header("Authorization", "")
                        .build();
                return chain.proceed(request);
            }
        };

        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印retrofit日志
                Log.i("retrofit-log", "请求结果 = " + message);
            }
        });

        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(defaultInterceptor)
                .readTimeout(Const.HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(Const.HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .hostnameVerifier(getHostnameVerifier())
                .build();
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(Const.BASE_URL_HTTPS)
                .client(httpClient)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(NetRequest.class);
    }

    /**
     * 不校验域名
     * @return
     */
    public static HostnameVerifier getHostnameVerifier() {
        HostnameVerifier   hostnameVerifier= new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
        return hostnameVerifier;
    }



    //获取user-agent信息
    private static String getUserAgent(Context context) {
        String userAgent = "";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            try {
                userAgent = WebSettings.getDefaultUserAgent(context);
            } catch (Exception e) {
                userAgent = System.getProperty("http.agent");
            }
        } else {
            userAgent = System.getProperty("http.agent");
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0, length = userAgent.length(); i < length; i++) {
            char c = userAgent.charAt(i);
            if (c <= '\u001f' || c >= '\u007f') {
                sb.append(String.format("\\u%04x", (int) c));
            } else {
                sb.append(c);
            }
        }
        if (getAppInfo(context) != null) {
            return sb.toString()+getAppInfo(context);
        }else {
            return sb.toString();
        }

    }


    //获取app版本号
    private static String getAppInfo(Context context) {
        try {
            String pkName = context.getPackageName();
            String versionName = context.getPackageManager().getPackageInfo(
                    pkName, 0).versionName;
            int versionCode = context.getPackageManager()
                    .getPackageInfo(pkName, 0).versionCode;
            return "  versionName/" + versionName + "  versionCode/" + versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
