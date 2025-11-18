package com.kalay.themoviedb.data.remote.interceptor

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor(
    private val apiKey: String,
) : Interceptor {

    private val queryName = "api_key"
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalUrl: HttpUrl = original.url

        if (originalUrl.queryParameter(queryName) != null) {
            return chain.proceed(original)
        }

        val newUrl = originalUrl.newBuilder()
            .addQueryParameter(queryName, apiKey)
            .build()

        val newRequest = original.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}
