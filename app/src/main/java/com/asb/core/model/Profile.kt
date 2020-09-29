package com.asb.core.model

import com.google.gson.annotations.SerializedName

data class ProfilePostData (
    @SerializedName("tempat_lahir")
    var tempat_lahir: String,
    @SerializedName("tanggal_lahir")
    var tanggal_lahir: String,
    @SerializedName("suku")
    var suku: String,
    @SerializedName("agama")
    var agama: String,
    @SerializedName("no_hp")
    var no_hp: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("alamat")
    var alamat: String,
    @SerializedName("jenis_kelamin")
    var jenis_kelamin: String
)

data class ProfileRespond(
    val `data`: ProfileData
)

data class ProfileGetRespond(
    val `data`: ProfilePatient
)

data class ProfilePatient(
    @SerializedName("patient")
    val patient: ProfilePostData
)



data class ProfileData(
    @SerializedName("message")
    val message: String
)