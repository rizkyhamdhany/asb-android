package com.asb.core.model

import com.google.gson.annotations.SerializedName

data class RegisterPostData (
    @SerializedName("name")
    var name: String,
    @SerializedName("nip")
    var nip: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("password")
    var password: String,
    @SerializedName("conf_password")
    var confPassword: String
)

data class RegisterRespond(
    val `data`: RegisterData
)

data class RegisterData(
    @SerializedName("message")
    val message: String
)