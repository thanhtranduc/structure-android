package com.thanh.tran.structure.di.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thanh.tran.structure.di.retrofit.APIInterface;
import com.thanh.tran.structure.di.scope.ApplicationScope;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {

    @Provides
    @ApplicationScope
    Gson providesGson() {
        return new GsonBuilder()
                .create();
    }

    @Provides
    @ApplicationScope
    APIInterface getApiInterface(Retrofit retrofit) {
        return retrofit.create(APIInterface.class);
    }

    @Provides
    @ApplicationScope
    Retrofit getRetrofit(Gson gson, OkHttpClient httpClient) {
        return new Retrofit.Builder()
                .baseUrl("https://swapi.co/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient)
                .build();
    }

    @Provides
    @ApplicationScope
    OkHttpClient getOkHttpClient(HttpLoggingInterceptor interceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }

    @Provides
    @ApplicationScope
    HttpLoggingInterceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }
}
