package com.asb.core.network

import com.asb.core.utils.Preferences
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthInterceptor(
    private val pref: Preferences
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val authenticationRequest = originalRequest.newBuilder()
                .addHeaders(pref.getToken())
                .build()
        return chain.proceed(authenticationRequest)
    }

    private fun Request.Builder.addHeaders(token: String?) =
        this.apply { header("Authorization", "Bearer $token") }
}