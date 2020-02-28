package com.asb.presentation.auth

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.asb.core.model.LoginPostData
import com.asb.core.network.Resource
import com.asb.core.repository.LoginRepository
import kotlinx.coroutines.Dispatchers

class LoginViewModel(
    private val loginRepo: LoginRepository
) : ViewModel() {

    private var userMutableLiveData = MutableLiveData<LoginPostData>()
    val isLoading = ObservableBoolean()

    init {
        isLoading.set(false)
    }

    fun getLogin(username: String, password: String) {
        userMutableLiveData.value = LoginPostData(username, password)
    }

    var login = userMutableLiveData.switchMap { login ->
        liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            emit(loginRepo.doLogin(login))
        }
    }
}