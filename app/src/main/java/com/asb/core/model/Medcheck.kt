package com.asb.core.model

import com.google.gson.annotations.SerializedName

data class Medcheck(
    val `data`: MedcheckRespond
)

data class MedcheckRespond(
    val alcohol: Boolean,
    val blood_pressure: Boolean,
    val checked: Boolean,
    val cholesterol: Int,
    val command_center_id: Int,
    val created_at: String,
    val glucose: Double,
    val height: Int,
    val id: Int,
    val nip: String,
    val smoking: Boolean,
    val unit_id: Int,
    val updated_at: String,
    val weight: Int
)

data class MedcheckData(
    @SerializedName("nip")
    var nip: String?,
    @SerializedName("weight")
    var weight: String?,
    @SerializedName("height")
    var height: String?,
    @SerializedName("smoking")
    var smoking: Boolean?,
    @SerializedName("blood_pressure")
    var blood_pressure: Boolean?,
    @SerializedName("alcohol")
    var alcohol: Boolean?,
    @SerializedName("glucose")
    var glucose: Float?,
    @SerializedName("cholesterol")
    var cholesterol: Float?,
    @SerializedName("temperature")
    var temperature: Float?,
    @SerializedName("pulse_minimum")
    var pulse_minimum: Float?,
    @SerializedName("pulse_maximum")
    var pulse_maximum: Float?,
    @SerializedName("respiratory_rate")
    var respiratory_rate: Float?,
    @SerializedName("blood_pressure_systolic")
    var blood_pressure_systolic: Float?,
    @SerializedName("blood_pressure_diastolic")
    var blood_pressure_diastolic: Float?
)