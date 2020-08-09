package com.asb.presentation.auth

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.asb.core.model.Login
import com.asb.core.network.Resource
import com.asb.core.network.Status
import com.asb.presentation.main.HomeActivity
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.asb.android.R
import com.asb.core.model.LoginPostData
import com.asb.core.repository.LoginRepository
import com.asb.core.utils.Preferences
import com.asb.presentation.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.android.inject


class LoginActivity : BaseActivity() {

    private var email = ""
    private var pass = ""
    private val loginRepo: LoginRepository by inject()
    private val prefModule: Preferences by inject()
    private var userMutableLiveData = MutableLiveData<LoginPostData>()
    private var login = userMutableLiveData.switchMap { login ->
        liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            emit(loginRepo.doLogin(login))
        }
    }

    private val observer = Observer<Resource<Login>> {
        when (it.status) {
            Status.SUCCESS -> {
                it.data?.`data`?.let { res ->
                    prefModule.storeToken(res.accessToken)
                }
//                showSuccess()
            }
            Status.ERROR -> showError()
            Status.LOADING -> showLoading()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (prefModule.getToken().isNullOrBlank()) {
            setContentView(R.layout.activity_login)
            login.observe(this, observer)
            setViewListener()
        } else {
            goToHomeScreen()
        }

    }

    private fun setViewListener() {
        submit_button.setOnClickListener {
            if (validation())
                userMutableLiveData.value = LoginPostData(email, pass)
            else
                showDialog(BLANK_FORM)
        }

        register_text.setOnClickListener {
            val i = Intent(this, RegisterActivity::class.java)
            startActivity(i)
        }
    }

    private fun validation() : Boolean {
        email = username.text.toString()
        pass = password.text.toString()

        return email.isNotEmpty() && pass.isNotEmpty()
    }

    private fun showLoading() {
    }

    private fun showError() {
        showDialog(INVALID_CREDENTIAL)
    }

    private fun showSuccess() {
        goToHomeScreen()
    }

    private fun goToHomeScreen() {
        val i = Intent(this, HomeActivity::class.java)
        startActivity(i)
        finish()
    }
}