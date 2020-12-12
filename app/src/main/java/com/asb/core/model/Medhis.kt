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

data class MyMedhisRespond(
    @SerializedName("data")
    val d: MyMedhisRespondData
)

data class MedhisData(
    @SerializedName("message")
    val message: String
)

data class MyMedhisRespondData(
    @SerializedName("kanker")
    val kanker: Int,
    @SerializedName("hypertensi")
    val hypertensi: Int,
    @SerializedName("diabetes")
    val diabetes: Int,
    @SerializedName("jantung")
    val jantung: Int,
    @SerializedName("kolesterol")
    val kolesterol: Int,
    @SerializedName("alergi")
    val alergi: Int,
    @SerializedName("lain_lain")
    val lain_lain: String,
    @SerializedName("merokok")
    val merokok: Int,
    @SerializedName("batang")
    val batang: String,
    @SerializedName("alkohol")
    val alkohol: Int
)