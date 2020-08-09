package com.asb.core.model

import com.google.gson.annotations.SerializedName

data class MedcekPostData (
    @SerializedName("asam_urat")
    var asam_urat: String,
    @SerializedName("berat_badan")
    var berat_badan: String,
    @SerializedName("diastolik")
    var diastolik: String,
    @SerializedName("gds")
    var gds: String,
    @SerializedName("harapan")
    var harapan: String,
    @SerializedName("kolesterol")
    var kolesterol: String,
    @SerializedName("lingkar_perut")
    var lingkar_perut: String,
    @SerializedName("sistolik")
    var sistolik: String,
    @SerializedName("tinggi_badan")
    var tinggi_badan: String
)

data class MedcekRespond(
    val `data`: MedcekData
)

data class MedcekData(
    @SerializedName("message")
    val message: String
)