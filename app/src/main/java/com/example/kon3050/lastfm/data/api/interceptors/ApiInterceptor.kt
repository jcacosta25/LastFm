package com.example.kon3050.lastfm.data.api.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class ApiInterceptor @Inject
constructor() : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url().newBuilder().addQueryParameter(API_KEY, "5d4fe96f3b2e5f6ab18b64dbc3b5ee26").build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }

    companion object {

        @JvmStatic
        private val API_KEY = "api_key"
    }

}