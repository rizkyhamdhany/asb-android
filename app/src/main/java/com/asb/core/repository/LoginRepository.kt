package com.asb.core.repository

import com.asb.core.model.Login
import com.asb.core.model.LoginPostData
import com.asb.core.model.RefreshTokenData
import com.asb.core.model.RegisterPostData
import com.asb.core.model.RegisterRespond
import com.asb.core.network.PredictyaApi
import com.asb.core.network.Resource
import com.asb.core.network.ResponseHandler
import org.koin.dsl.module
import java.lang.Exception

val loginModule = module {
    factory { LoginRepository(get(), get()) }
}

open class LoginRepository(
    private val predictyaApi: PredictyaApi,
    private val responseHandler: ResponseHandler
) {
    suspend fun doLogin(loginPostData: LoginPostData): Resource<Login> {
        return try {
            val response = predictyaApi.login(loginPostData)
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    suspend fun doRegister(postData: RegisterPostData): Resource<RegisterRespond> {
        return try {
            val response = predictyaApi.register(postData)
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    suspend fun refreshToken(token: String): Resource<Login> {
        return try {
            val response = predictyaApi.refreshToken(RefreshTokenData(token))
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }
}