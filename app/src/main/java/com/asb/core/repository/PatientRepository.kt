package com.asb.core.repository

import com.asb.core.model.Medcheck
import com.asb.core.model.MedcheckData
import com.asb.core.model.Patient
import com.asb.core.network.PredictyaApi
import com.asb.core.network.Resource
import com.asb.core.network.ResponseHandler
import org.koin.dsl.module
import java.lang.Exception

val patientModule = module {
    factory { PatientRepository(get(), get()) }
}

open class PatientRepository(
    private val predictyaApi: PredictyaApi,
    private val responseHandler: ResponseHandler
) {
    suspend fun getPatient(commandCenterId: Int, companyId: Int, patientId: String): Resource<Patient> {
        return try {
            val response = predictyaApi.getPatient(commandCenterId, companyId, patientId)
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    suspend fun createMedical(commandCenterId: Int, postData: MedcheckData): Resource<Medcheck> {
        return try {
            val response = predictyaApi.createMedical(commandCenterId, postData)
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }
}