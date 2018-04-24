package com.example.kon3050.lastfm.data.api.factory

import android.util.Log
import com.example.kon3050.lastfm.data.api.interceptors.ApiInterceptor
import com.example.kon3050.lastfm.data.utils.LiveDataCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceFactory {
    private  val TAG = ServiceFactory::class.java.simpleName
    private const val MAX_READ_TIME_OUT_SECONDS = 180
    private const val MAX_CONNECTION_TIME_OUT_SECONDS = 60

    fun <T> createRetrofitService(clazz: Class<T>, baseUrl: String, sdcInterceptor: ApiInterceptor?): T {
        val httpClientBuilder = OkHttpClient.Builder()
        if (sdcInterceptor != null) {
            httpClientBuilder.addInterceptor(sdcInterceptor)
        }

        //FIXME: We have to implement something in order to know if it is debug mode or prod mode
        val loggerInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Log.i(TAG, "OkHttp $message") })

        loggerInterceptor.level = HttpLoggingInterceptor.Level.BODY

        //Logger interceptor added
        val client = httpClientBuilder
                .addInterceptor(loggerInterceptor)
                .readTimeout(MAX_READ_TIME_OUT_SECONDS.toLong(), TimeUnit.SECONDS)
                .connectTimeout(MAX_CONNECTION_TIME_OUT_SECONDS.toLong(), TimeUnit.SECONDS)
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

        return retrofit.create(clazz)
    }
}