package com.asb.core.network

import com.asb.core.model.Login
import com.asb.core.model.LoginPostData
import com.asb.core.model.Medcheck
import com.asb.core.model.MedcheckData
import com.asb.core.model.Patient
import com.asb.core.model.RefreshTokenData
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PredictyaApi {

    @POST("auth")
    suspend fun login(@Body loginPostData: LoginPostData): Login

    @POST("auth/refresh")
    suspend fun refreshToken(@Body data: RefreshTokenData): Login

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