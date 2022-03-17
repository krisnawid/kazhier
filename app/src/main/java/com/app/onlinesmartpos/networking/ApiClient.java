package com.app.onlinesmartpos.networking;





import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.media.session.MediaSession;

import com.app.onlinesmartpos.Constant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class ApiClient {

    private static SharedPreferences sp;
    SharedPreferences.Editor editor;

    private static final String BASE_URL = Constant.BASE_URL;
//    private  static  String token = Constant.TOKEN;
//    private String token = sp.getString(Constant.TOKEN, "");
    private static Retrofit retrofit = null;

//    public static Retrofit addHeader() {
//        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
//                .connectTimeout(40, TimeUnit.SECONDS)
//                .readTimeout(40, TimeUnit.SECONDS)
//                .writeTimeout(40, TimeUnit.SECONDS)
//                .addInterceptor(new Interceptor() {
//                    @Override
//                    public Response intercept(Interceptor.Chain chain) throws IOException {
//                        Request original = chain.request();
//                        Request request = original.newBuilder()
//                                .header("Key", "Value")
//                                .header("Key", "Value")
//                                .method(original.method(), original.body())
//                                .build();
//
//                        return chain.proceed(request);
//                    }
//                })
//                .build();
//
//        retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(okHttpClient)
//                .build();
//        return retrofit;
//    }

    public static Retrofit getApiClient(Context ctx) {

//        sp = this.getContext.getSharedPreferences(Constant.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        sp = ctx.getSharedPreferences(Constant.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        String token = sp.getString(Constant.TOKEN, "");


        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request()
                        .newBuilder()
                        .addHeader("Authorization", "Bearer " + token)
                        .addHeader("Accept", "application/json")
                        .build();
                return chain.proceed(newRequest);
            }
        })
                .retryOnConnectionFailure(true)
                .connectTimeout(10, TimeUnit.MINUTES)
                .readTimeout(10, TimeUnit.MINUTES)
                .writeTimeout(10, TimeUnit.MINUTES)
                .build();

        if (retrofit == null) {

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;

    }

}