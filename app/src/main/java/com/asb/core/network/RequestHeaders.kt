package com.asb.core.network

import com.asb.android.BuildConfig
import okhttp3.Request

fun request(originalRequest: Request, token: String): Request {
    return originalRequest.newBuilder()
        .addHeader("Authorization", "Bearer $token")
        .addHeader("appVersion", BuildConfig.VERSION_NAME).build()

}