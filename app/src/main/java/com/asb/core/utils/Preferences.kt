package com.asb.core.utils

import android.content.Context
import android.content.SharedPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val prefModule = module {
    single { Preferences(androidContext()) }
}

class Preferences(context: Context) {
    private val preferences: SharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)

    fun storeToken(content: String){
        preferences.edit().putString(KEY_TOKEN, content).apply()
    }

    fun getToken(): String? {
        return preferences.getString(KEY_TOKEN, "")
    }

    fun storeCompany(content: Int){
        preferences.edit().putInt(KEY_COMPANY, content).apply()
    }

    fun getCompany(): Int {
        return preferences.getInt(KEY_COMPANY, 0)
    }

    fun storeCommandCenter(content: Int){
        preferences.edit().putInt(KEY_COMMAND_CENTER, content).apply()
    }

    fun getCommandCenter(): Int {
        return preferences.getInt(KEY_COMMAND_CENTER, 0)
    }

    fun storeRefreshToken(content: String){
        preferences.edit().putString(KEY_REFRESH_TOKEN, content).apply()
    }

    fun getRefreshToken(): String? {
        return preferences.getString(KEY_REFRESH_TOKEN, "")
    }

    fun storeExpireToken(content: Long) {
        preferences.edit().putLong(KEY_EXPIRE_TOKEN, content).apply()
    }

    fun getExpireToken(): Long {
        return preferences.getLong(KEY_EXPIRE_TOKEN, 0)
    }

    fun setPatientNip(content: String?) {
        preferences.edit().putString(KEY_PATIENT_NIP, content).apply()
    }

    fun getPatientNip(): String? {
        return preferences.getString(KEY_PATIENT_NIP, "")
    }

    fun setPatientWeight(content: String?) {
        preferences.edit().putString(KEY_PATIENT_WEIGHT, content).apply()
    }

    fun getPatientWeight(): String? {
        return preferences.getString(KEY_PATIENT_WEIGHT, "")
    }

    fun setPatientHeight(content: String?) {
        preferences.edit().putString(KEY_PATIENT_HEIGHT, content).apply()
    }

    fun getPatientHeight(): String? {
        return preferences.getString(KEY_PATIENT_HEIGHT, "")
    }

    fun setPatientSmoking(content: Boolean) {
        preferences.edit().putBoolean(KEY_PATIENT_SMOKING, content).apply()
    }

    fun getPatientSmoking(): Boolean? {
        return preferences.getBoolean(KEY_PATIENT_SMOKING, false)
    }

    fun setPatientBlood(content: Boolean) {
        preferences.edit().putBoolean(KEY_PATIENT_BLOOD, content).apply()
    }

    fun getPatientBlood(): Boolean? {
        return preferences.getBoolean(KEY_PATIENT_BLOOD, false)
    }

    fun setPatientAlcohol(content: Boolean) {
        preferences.edit().putBoolean(KEY_PATIENT_ALCOHOL, content).apply()
    }

    fun getPatientAlcohol(): Boolean? {
        return preferences.getBoolean(KEY_PATIENT_ALCOHOL, false)
    }

    fun setPatientGlucose(content: Float) {
        preferences.edit().putFloat(KEY_PATIENT_GLUCOSE, content).apply()
    }

    fun getPatientGlucose(): Float? {
        return preferences.getFloat(KEY_PATIENT_GLUCOSE, 0f)
    }

    fun setPatientCholesterol(content: Float) {
        preferences.edit().putFloat(KEY_PATIENT_CHOLESTEROL, content).apply()
    }

    fun getPatientCholesterol(): Float? {
        return preferences.getFloat(KEY_PATIENT_CHOLESTEROL, 0f)
    }

    fun setPatientTemperature(content: Float) {
        preferences.edit().putFloat(KEY_PATIENT_TEMPERATURE, content).apply()
    }

    fun getPatientTemperature(): Float? {
        return preferences.getFloat(KEY_PATIENT_TEMPERATURE, 0f)
    }

    fun setPatientPulseMax(content: Float) {
        preferences.edit().putFloat(KEY_PATIENT_PULSE_MAX, content).apply()
    }

    fun getPatientPulseMax(): Float? {
        return preferences.getFloat(KEY_PATIENT_PULSE_MAX, 0f)
    }

    fun setPatientPulseMin(content: Float) {
        preferences.edit().putFloat(KEY_PATIENT_PULSE_MIN, content).apply()
    }

    fun getPatientPulseMin(): Float? {
        return preferences.getFloat(KEY_PATIENT_PULSE_MIN, 0f)
    }

    fun setPatientRespiratory(content: Float) {
        preferences.edit().putFloat(KEY_PATIENT_RESPIRATORY, content).apply()
    }

    fun getPatientRespiratory(): Float? {
        return preferences.getFloat(KEY_PATIENT_RESPIRATORY, 0f)
    }

    fun setPatientBPS(content: Float) {
        preferences.edit().putFloat(KEY_PATIENT_BPS, content).apply()
    }

    fun getPatientBPS(): Float? {
        return preferences.getFloat(KEY_PATIENT_BPS, 0f)
    }

    fun setPatientBPD(content: Float) {
        preferences.edit().putFloat(KEY_PATIENT_BPD, content).apply()
    }

    fun getPatientBPD(): Float? {
        return preferences.getFloat(KEY_PATIENT_BPD, 0f)
    }

    fun resetPatient() {
        setPatientNip("")
        setPatientWeight("")
        setPatientHeight("")
        setPatientSmoking(false)
        setPatientBlood(false)
        setPatientAlcohol(false)
        setPatientGlucose(0f)
        setPatientCholesterol(0f)
        setPatientTemperature(0f)
        setPatientPulseMax(0f)
        setPatientPulseMin(0f)
        setPatientRespiratory(0f)
        setPatientBPD(0f)
        setPatientBPS(0f)
    }

    companion object {
        private const val KEY_TOKEN = "token"
        private const val KEY_COMPANY = "company"
        private const val KEY_COMMAND_CENTER = "command_center"
        private const val KEY_REFRESH_TOKEN = "refreshToken"
        private const val KEY_EXPIRE_TOKEN = "expiresIn"
        private const val KEY_PATIENT_NIP = "patient_nip"
        private const val KEY_PATIENT_WEIGHT = "patient_weight"
        private const val KEY_PATIENT_HEIGHT = "patient_height"
        private const val KEY_PATIENT_SMOKING = "patient_smoking"
        private const val KEY_PATIENT_BLOOD = "patient_blood"
        private const val KEY_PATIENT_ALCOHOL = "patient_alcohol"
        private const val KEY_PATIENT_GLUCOSE = "patient_glucose"
        private const val KEY_PATIENT_CHOLESTEROL = "patient_cholesterol"
        private const val KEY_PATIENT_TEMPERATURE = "patient_temperature"
        private const val KEY_PATIENT_PULSE_MAX = "patient_pulse_max"
        private const val KEY_PATIENT_PULSE_MIN = "patient_pulse_min"
        private const val KEY_PATIENT_RESPIRATORY = "patient_respiratory"
        private const val KEY_PATIENT_BPS = "patient_bps"
        private const val KEY_PATIENT_BPD = "patient_bpd"
    }
}