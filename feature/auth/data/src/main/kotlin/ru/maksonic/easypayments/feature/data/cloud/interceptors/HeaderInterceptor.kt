package ru.maksonic.easypayments.feature.data.cloud.interceptors

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * @Author maksonic on 28.11.2023
 */
class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val modifiedRequest: Request = chain.request().newBuilder()
            .addHeader("app-key", "12345")
            .addHeader("v", "1")
            .build()

        return chain.proceed(modifiedRequest)
    }
}