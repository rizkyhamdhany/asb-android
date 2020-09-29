package com.asb.core.repository

import com.asb.core.model.ChangePasswordPostData
import com.asb.core.model.Login
import com.asb.core.model.LoginPostData
import com.asb.core.model.MedcekPostData
import com.asb.core.model.MedcekRespond
import com.asb.core.model.MedhisPostData
import com.asb.core.model.MedhisRespond
import com.asb.core.model.ProfileGetRespond
import com.asb.core.model.ProfilePostData
import com.asb.core.model.ProfileRespond
import com.asb.core.model.ProgramMeRespond
import com.asb.core.model.ProgramPostData
import com.asb.core.model.RefreshTokenData
import com.asb.core.model.RegisterPostData
import com.asb.core.model.RegisterRespond
import com.asb.core.network.PredictyaApi
import com.asb.core.network.Resource
import com.asb.core.network.ResponseHandler
import org.koin.dsl.module
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
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

    suspend fun changePassword(data: ChangePasswordPostData): Resource<ProfileRespond> {
        return try {
            val response = predictyaApi.changePassword(data)
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

    suspend fun profileSubmit(postData: ProfilePostData): Resource<ProfileRespond> {
        return try {
            val response = predictyaApi.profile(postData)
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    suspend fun profileEditSubmit(postData: ProfilePostData): Resource<ProfileRespond> {
        return try {
            val response = predictyaApi.profileEdit(postData)
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    suspend fun getProfile(): Resource<ProfileGetRespond> {
        return try {
            val response = predictyaApi.profileGet()
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    suspend fun medhisSubmit(postData: MedhisPostData): Resource<MedhisRespond> {
        return try {
            val response = predictyaApi.medhis(postData)
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    suspend fun medcekSubmit(postData: MedcekPostData): Resource<MedcekRespond> {
        return try {
            val response = predictyaApi.medcek(postData)
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    suspend fun programLogSubmit(postData: ProgramPostData): Resource<MedcekRespond> {
        return try {
            val response = predictyaApi.programLog(postData)
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    suspend fun getMyProgram(): Resource<ProgramMeRespond> {
        return try {
            val response = predictyaApi.myProgram()
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }
}