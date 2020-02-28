package com.asb.core.model

data class Patient(
    val `data`: PatientData
)

data class PatientData(
    val birthdate: String,
    val email: String,
    val gender: String,
    val id: Int,
    val name: String,
    val nip: String,
    val phone_number: String
)