package com.asb.presentation.auth

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.asb.android.R
import com.asb.core.model.RegisterPostData
import com.asb.core.model.RegisterRespond
import com.asb.core.network.Resource
import com.asb.core.network.Status
import com.asb.core.repository.LoginRepository
import com.asb.presentation.BaseActivity
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.android.inject


class RegisterActivity : BaseActivity() {

    private var name = ""
    private var nip = ""
    private var email = ""
    private var password = ""
    private var confPassword = ""

    private val authRepo: LoginRepository by inject()
    private var userMutableLiveData = MutableLiveData<RegisterPostData>()
    private var process = userMutableLiveData.switchMap { register ->
        liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            emit(authRepo.doRegister(register))
        }
    }

    private val observer = Observer<Resource<RegisterRespond>> {
        when (it.status) {
            Status.SUCCESS -> {
                Toast.makeText(this, "Registrasi berhasil, silahkan login untuk melanjutkan", Toast.LENGTH_LONG).show()
                finish()
            }
            Status.ERROR -> {
                showDialog("User telah digunakan !")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setViewListener()
    }

    private fun setViewListener() {
        process.observe(this, observer)
        submit_button.setOnClickListener {
            if (validation()) {
                userMutableLiveData.value = RegisterPostData(
                        name,
                        nip,
                        email,
                        password,
                        confPassword
                )
            }
        }
    }

    private fun validation(): Boolean {
        name = name_ET.text.toString()
        nip = nip_ET.text.toString()
        email = email_ET.text.toString()
        password = password_ET.text.toString()
        confPassword = confpassword_ET.text.toString()

        val checkBlank = name.isNotEmpty() && nip.isNotEmpty() && email.isNotEmpty() && confPassword.isNotEmpty()

        if (!checkBlank) {
            showDialog(BLANK_FORM)
            return false
        }

        val checkPassword = password.length > 5 && confPassword.length > 5 && password == confPassword

        if (!isValidEmail(email)) {
            showDialog("Email tidak valid")
            return false
        }

        if (password.length < 6) {
            showDialog("Password must contain at least 6 character")
            return false
        }

        if (!checkPassword) {
            showDialog("Password not match !")
            return false
        }

        return true
    }

    fun isValidEmail(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }
}