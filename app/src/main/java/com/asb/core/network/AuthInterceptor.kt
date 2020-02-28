package com.asb.core.network

import com.google.gson.Gson
import com.asb.android.BuildConfig
import com.asb.core.model.Login
import com.asb.core.utils.Preferences
import okhttp3.Interceptor
import okhttp3.MultipartBody
import okhttp3.Request
import okhttp3.Response

class AuthInterceptor(
    private val pref: Preferences
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        return when {
            pref.getToken().equals("") -> {
                chain.proceed(originalRequest)
            }
            pref.getExpireToken() < System.currentTimeMillis() -> {
                val requestBody = MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("refresh_token", pref.getRefreshToken().toString())
                    .build()
                val refreshTokenRequest = originalRequest
                    .newBuilder()
                    .post(requestBody)
                    .url("${BuildConfig.API_URL}/auth/refresh")
                    .build()
                val refreshResponse = chain.proceed(refreshTokenRequest)

                if (refreshResponse.isSuccessful) {
                    val respondData =
                        Gson().fromJson(refreshResponse.body?.string(), Login::class.java)
                            .data.accessToken
                    respondData.let {
                        pref.storeToken(it.token)
                        pref.storeExpireToken(it.expiresIn)
                        pref.storeRefreshToken(it.refreshToken)
                    }
                    val newCall = originalRequest.newBuilder().addHeaders(respondData.token).build()
                    chain.proceed(newCall)
                } else {
                    chain.proceed(originalRequest)
                }
            }
            else -> {
                val authenticationRequest = originalRequest.newBuilder()
                    .addHeaders(pref.getToken())
                    .build()
                chain.proceed(authenticationRequest)
            }
        }
    }

    private fun Request.Builder.addHeaders(token: String?) =
        this.apply { header("Authorization", "Bearer $token") }
}