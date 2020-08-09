package com.asb.core.network

import com.asb.core.model.Login
import com.asb.core.model.LoginPostData
import com.asb.core.model.MedcekPostData
import com.asb.core.model.MedcekRespond
import com.asb.core.model.Medcheck
import com.asb.core.model.MedcheckData
import com.asb.core.model.MedhisPostData
import com.asb.core.model.MedhisRespond
import com.asb.core.model.Patient
import com.asb.core.model.ProfilePostData
import com.asb.core.model.ProfileRespond
import com.asb.core.model.ProgramMeRespond
import com.asb.core.model.ProgramPostData
import com.asb.core.model.RefreshTokenData
import com.asb.core.model.RegisterPostData
import com.asb.core.model.RegisterRespond
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PredictyaApi {

    @POST("auth/login")
    suspend fun login(@Body loginPostData: LoginPostData): Login

    @POST("auth/register")
    suspend fun register(@Body postData: RegisterPostData): RegisterRespond

    @POST("auth/refresh")
    suspend fun refreshToken(@Body data: RefreshTokenData): Login

    @POST("patient/update")
    suspend fun profile(@Body data: ProfilePostData): ProfileRespond

    @POST("medhis/update")
    suspend fun medhis(@Body data: MedhisPostData): MedhisRespond

    @POST("medcheck/insert")
    suspend fun medcek(@Body data: MedcekPostData): MedcekRespond

    @POST("program/log")
    suspend fun programLog(@Body data: ProgramPostData): MedcekRespond

    @GET("program/me")
    suspend fun myProgram(): ProgramMeRespond

    @GET("command-center/{command_center_id}/companies/{company_id}/patients/{patient_id}")
    suspend fun getPatient(
        @Path("command_center_id") commandCenterId: Int,
        @Path("company_id") companyId: Int,
        @Path("patient_id") patientId: String
    ): Patient

    @POST("command-center/{command_center_id}/medical")
    suspend fun createMedical(
        @Path("command_center_id") commandCenterId: Int,
        @Body data: MedcheckData
    ): Medcheck
}