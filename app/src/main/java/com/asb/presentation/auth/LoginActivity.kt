package com.asb.presentation.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.asb.android.databinding.ActivityLoginBinding
import com.asb.core.model.Login
import com.asb.core.network.Resource
import com.asb.core.network.Status
import androidx.databinding.DataBindingUtil
import com.asb.presentation.main.HomeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.appcompat.app.AlertDialog
import com.asb.core.utils.Preferences
import org.koin.android.ext.android.inject


class LoginActivity : AppCompatActivity() {

    private val prefModule: Preferences by inject()
    private val viewModel: LoginViewModel by viewModel()
    private lateinit var binding: ActivityLoginBinding

    private val observer = Observer<Resource<Login>> {
        when (it.status) {
            Status.SUCCESS -> {
                it.data?.`data`?.let { res ->
                    prefModule.storeToken(res.accessToken.token)
                    prefModule.storeExpireToken(res.accessToken.expiresIn)
                    prefModule.storeRefreshToken(res.accessToken.refreshToken)
                    prefModule.storeCompany(res.user.company.id)
                    prefModule.storeCommandCenter(res.user.commandCenter.id)
                }
                showSuccess()
            }
            Status.ERROR -> showError()
            Status.LOADING -> showLoading()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (prefModule.getToken().isNullOrBlank()) {
            binding = DataBindingUtil.setContentView(this, com.asb.android.R.layout.activity_login)
            binding.viewModel = viewModel
            viewModel.login.observe(this, observer)
        } else {
            goToHomeScreen()
        }

    }

    private fun showLoading() {
        binding.viewModel?.isLoading?.set(true)
    }

    private fun showError() {
        binding.viewModel?.isLoading?.set(false)
        showDialog(INVALID_CREDENTIAL)
    }

    private fun showSuccess() {
        binding.viewModel?.isLoading?.set(false)
        goToHomeScreen()
    }

    private fun goToHomeScreen() {
        val i = Intent(this, HomeActivity::class.java)
        startActivity(i)
        finish()
    }

    private fun showDialog(msg: String) {
        AlertDialog.Builder(this)
            .setTitle("Login")
            .setMessage(msg)
            .setNegativeButton(android.R.string.no, null)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }

    companion object {
        private const val INVALID_CREDENTIAL = "Invalid Credential"
        private const val FAILED_SIGN_IN = "Failed to Sign in !"
    }
}