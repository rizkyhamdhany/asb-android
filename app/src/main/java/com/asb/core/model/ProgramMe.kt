package com.asb.core.model

import com.google.gson.annotations.SerializedName

data class ProgramPostData (
    @SerializedName("program_id")
    var program_id: Int,
    @SerializedName("cemilan")
    var cemilan: String,
    @SerializedName("fasting")
    var fasting: String,
    @SerializedName("jalan_kaki")
    var jalan_kaki: String,
    @SerializedName("makan_pagi")
    var makan_pagi: String,
    @SerializedName("makan_siang")
    var makan_siang: String,
    @SerializedName("makan_sore")
    var makan_sore: String,
    @SerializedName("minum1")
    var minum1: String,
    @SerializedName("minum2")
    var minum2: String,
    @SerializedName("olahraga")
    var olahraga: String,
    @SerializedName("sikap")
    var sikap: String,
    @SerializedName("tanggal")
    var tanggal: String,
    @SerializedName("tidur_siang")
    var tidur_siang: String
)

data class ProgramMeRespond(
    val `data`: ProgramMeData
)

data class ProgramMeData(
    @SerializedName("program")
    val program: ProgramData
)

data class ProgramData(
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_active")
    val is_active: Int
)