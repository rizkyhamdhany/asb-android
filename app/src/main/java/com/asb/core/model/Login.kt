package com.asb.core.model

import com.google.gson.annotations.SerializedName

data class Login(
    val `data`: LoginData
)

data class LoginData(
    @SerializedName("access_token")
    val accessToken: AccessToken,
    @SerializedName("user")
    val user: UserLogin
)

data class AccessToken(
    val expiresIn: Long,
    val refreshToken: String,
    val token: String,
    val type: String
)

data class LoginPostData(
    @SerializedName("username")
    var username: String,
    @SerializedName("password")
    var password: String
)

data class RefreshTokenPostData(
    @SerializedName("refresh_token")
    var refreshToken: String?
)

data class UserLogin(
    @SerializedName("command_center")
    var commandCenter: CommandCenter,
    @SerializedName("company")
    var company: Company
)

data class CommandCenter(
    @SerializedName("id")
    var id: Int
)

data class Company(
    @SerializedName("id")
    var id: Int
)