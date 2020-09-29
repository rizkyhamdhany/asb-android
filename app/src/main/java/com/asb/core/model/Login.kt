package com.asb.core.model

import com.google.gson.annotations.SerializedName

data class LoginPostData(
    @SerializedName("email")
    var username: String,
    @SerializedName("password")
    var password: String
)

data class Login(
    val `data`: LoginData
)

data class Status(
    @SerializedName("is_medhis_submitted")
    var isMedhisSubmitted: Int,
    @SerializedName("is_medcheck_submitted")
    var isMedcheckSubmitted: Int,
    @SerializedName("is_on_program")
    var isOnProgram: Int,
    @SerializedName("is_patient")
    var isPatient: Int
)

data class LoginData(
    @SerializedName("token")
    val accessToken: String,
    @SerializedName("status")
    val status: Status
)

data class ChangePasswordPostData(
    @SerializedName("password")
    var password: String
)