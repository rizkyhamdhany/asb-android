package com.asb.presentation.medcheck

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.asb.core.network.Resource
import com.asb.core.repository.PatientRepository
import com.asb.core.utils.Preferences
import kotlinx.coroutines.Dispatchers


class GetPateintDataViewModel(
    private val patientRepo: PatientRepository,
    private val prefModule: Preferences
) : ViewModel() {

    private var patientMutableLiveData = MutableLiveData<String>()

    private var isDataAvailable = MutableLiveData<Boolean>().apply {
        value = false
    }
    private var name = MutableLiveData<String>()
    private var email = MutableLiveData<String>()
    private var phonenumber = MutableLiveData<String>()
    private var gender = MutableLiveData<String>()
    private var birthdate = MutableLiveData<String>()
    private var error = MutableLiveData<String>()


    fun getName() : LiveData<String> {
        return name
    }

    fun getEmail() : LiveData<String> {
        return email
    }

    fun getPhonenumber() : LiveData<String> {
        return phonenumber
    }

    fun getGender() : LiveData<String> {
        return gender
    }

    fun getBirthdate() : LiveData<String> {
        return birthdate
    }

    fun getIsDataAvailable(): LiveData<Boolean> {
        return isDataAvailable
    }

    fun getError(): LiveData<String> {
        return error
    }

    fun setPatient(
        name: String,
        email: String,
        phonenumber: String,
        gender: String,
        birthdate: String
    ) {
        this.isDataAvailable.postValue(true)
        this.name.postValue(name)
        this.email.postValue(email)
        this.phonenumber.postValue(phonenumber)
        this.gender.postValue(gender)
        this.birthdate.postValue(birthdate)
        this.error.postValue("")
    }

    fun showError(s: String) {
        this.isDataAvailable.postValue(false)
        this.error.postValue(s)
    }

    fun searchPatient(s: String) {
        patientMutableLiveData.value = s
    }

    var patient = patientMutableLiveData.switchMap {
        liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            emit(patientRepo.getPatient(prefModule.getCommandCenter(), prefModule.getCompany(), it))
        }
    }
}
