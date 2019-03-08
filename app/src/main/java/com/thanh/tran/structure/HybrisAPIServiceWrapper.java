//package com.thanh.tran.structure;
//
//import android.content.Context;
//
//import java.util.concurrent.TimeUnit;
//
//import okhttp3.OkHttpClient;
//import okhttp3.logging.HttpLoggingInterceptor;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
///**
// * Created by DANGNGOCDUC on 5/22/2017.
// */
//
//public class HybrisAPIServiceWrapper {
//
//    private volatile static HybrisAPIServiceWrapper instance;
//    private static OkHttpClient mOkHttpClient;
//    public static OkHttpClient mOkHttpClientNoHeader;
//
//    public static HybrisAPIServiceWrapper getInstance(Context context) {
//        if (instance == null) {
//            synchronized (HybrisAPIServiceWrapper.class) {
//                if (instance == null) {
//                    instance = new HybrisAPIServiceWrapper();
//                }
//            }
//        }
//        return instance;
//    }
//
//    public static HybrisAPIServiceWrapper getInstanceReFreshTokent(Context context) {
//
//        if (instance == null) {
//            synchronized (HybrisAPIServiceWrapper.class) {
//                if (instance == null) {
//                    instance = new HybrisAPIServiceWrapper();
//                }
//            }
//        }
//        return instance;
//    }
//
//    public HybrisAPIService createHybrisAPIService(String baseurl) {
//        initOkHttpClient();
//        return new Retrofit.Builder()
//                .client(mOkHttpClient)
//                .baseUrl(baseurl)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build().create(HybrisAPIService.class);
//    }
//
//    public HybrisAPIService createHybrisAPIService(String baseurl, OkHttpClient okHttpClient) {
//        return new Retrofit.Builder()
//                .client(okHttpClient)
//                .baseUrl(baseurl)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build().create(HybrisAPIService.class);
//    }
//
//    public HybrisAPIService createHybrisAPIServiceNoHeader(String baseurl) {
//        initOkHttpClientNoHeader();
//        return new Retrofit.Builder()
//                .client(mOkHttpClientNoHeader)
//                .baseUrl(baseurl)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build().create(HybrisAPIService.class);
//    }
//
//    private static void initOkHttpClient() {
//        if (mOkHttpClient == null) {
//            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//            HttpLoggingInterceptor interceptorH = new HttpLoggingInterceptor();
//            interceptorH.setLevel(HttpLoggingInterceptor.Level.HEADERS);
//            OkHttpClient.Builder builderHttp = new OkHttpClient.Builder()
//                    .addInterceptor(new HybrisTokenHeaderRequestInterceptor(MyApplication.getInstance()))
//                    .readTimeout(MyConstant.SERVER_TIME_OUT, TimeUnit.SECONDS)
//                    .connectTimeout(MyConstant.SERVER_TIME_OUT, TimeUnit.SECONDS)
//                    .writeTimeout(MyConstant.SERVER_TIME_OUT, TimeUnit.SECONDS);
//            if (MyConstant.isDebug) {
//                builderHttp.addInterceptor(interceptor);
//                builderHttp.addInterceptor(interceptorH);
//
//            }
//            if (MyConstant.isMockRegister) {
//                builderHttp.addInterceptor(new InterceptorRegister(MyApplication.getInstance()));
//            }
//            mOkHttpClient = builderHttp.build();
//
//        }
//    }
//
//
//    private static void initOkHttpClientNoHeader() {
//        if (mOkHttpClientNoHeader == null) {
//            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//            HttpLoggingInterceptor interceptorH = new HttpLoggingInterceptor();
//            interceptorH.setLevel(HttpLoggingInterceptor.Level.HEADERS);
//            OkHttpClient.Builder builderHttp = new OkHttpClient.Builder()
//                    .addInterceptor(new HybrisTokenErrorRequestInterceptor(MyApplication.getInstance()))
//                    .readTimeout(MyConstant.SERVER_TIME_OUT, TimeUnit.SECONDS)
//                    .connectTimeout(MyConstant.SERVER_TIME_OUT, TimeUnit.SECONDS)
//                    .writeTimeout(MyConstant.SERVER_TIME_OUT, TimeUnit.SECONDS);
//
//            if (MyConstant.isDebug) {
//                builderHttp.addInterceptor(interceptor);
//            }
//            if (MyConstant.isMockRegister) {
//                builderHttp.addInterceptor(new InterceptorRegister(MyApplication.getInstance()));
//            }
//            mOkHttpClientNoHeader = builderHttp.build();
//
//        }
//    }
//}
