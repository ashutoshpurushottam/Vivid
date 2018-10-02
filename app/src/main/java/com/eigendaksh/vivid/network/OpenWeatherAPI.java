package com.eigendaksh.vivid.network;

import android.content.Context;

import com.eigendaksh.vivid.BuildConfig;
import com.eigendaksh.vivid.preferences.UserPreferenceStore;

import java.io.File;
import java.io.IOException;

import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ashutosh Purushottam
 * EigenDaksh - App Development
 */

public class OpenWeatherAPI {

    private static final String CACHE_DIR = "vivid";
    private static final int CACHE_SIZE = 10 * 1024 * 1024;
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";

    private static File getCacheDir(Context context) {
        if (BuildConfig.DEBUG) {
            return new File(context.getExternalCacheDir(), CACHE_DIR);
        } else {
            return new File(context.getCacheDir(), CACHE_DIR);
        }
    }

    private static OkHttpClient.Builder getOkHttpClientBuilder(Context context) {
        Cache cache = new Cache(getCacheDir(context), CACHE_SIZE);
        return new OkHttpClient.Builder().cache(cache);
    }

    public static OpenWeatherService getService(Context context, UserPreferenceStore store) {
        OkHttpClient client =
                getOkHttpClientBuilder(context).
                        addInterceptor(new OpenWeatherMapInterceptor(store))
                        .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(
                        RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();


        return retrofit.create(OpenWeatherService.class);
    }


    private static class OpenWeatherMapInterceptor implements Interceptor {


        UserPreferenceStore mPreferenceStore;

        public OpenWeatherMapInterceptor(UserPreferenceStore mPreferenceStore) {
            this.mPreferenceStore = mPreferenceStore;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            // This can be extended to add headers to our requests
            // This is only implemented as an example
            Request originalRequest = chain.request();
            Request newRequest = originalRequest.newBuilder()
                    .build();

            return chain.proceed(newRequest);
        }


    }
}
