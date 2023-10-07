package com.compose.app.di.network

import com.compose.app.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder
            .addHeader("Authorization", "Bearer ${BuildConfig.AUTH_TOKEN}")
            .build()
        return chain.proceed(requestBuilder.build())
//https://medium.com/android-news/token-authorization-with-retrofit-android-oauth-2-0-747995c79720
    }
}