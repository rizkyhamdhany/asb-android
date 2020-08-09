package com.asb.core.model

import com.google.gson.annotations.SerializedName

data class MedhisPostData (
    @SerializedName("alergi")
    var alergi: String,
    @SerializedName("alkohol")
    var alkohol: String,
    @SerializedName("batang")
    var batang: String,
    @SerializedName("diabetes")
    var diabetes: String,
    @SerializedName("hypertensi")
    var hypertensi: String,
    @SerializedName("jantung")
    var jantung: String,
    @SerializedName("kanker")
    var kanker: String,
    @SerializedName("kolesterol")
    var kolesterol: String,
    @SerializedName("lain_lain")
    var lain_lain: String,
    @SerializedName("merokok")
    var merokok: String
)

data class MedhisRespond(
    val `data`: MedhisData
)

data class MedhisData(
    @SerializedName("message")
    val message: String
)