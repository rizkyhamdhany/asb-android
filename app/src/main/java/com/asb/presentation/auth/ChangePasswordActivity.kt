package com.asb.presentation.auth

import android.os.Bundle
import android.widget.Toast
import com.asb.android.R
import com.asb.core.model.ChangePasswordPostData
import com.asb.core.network.Status
import com.asb.core.repository.LoginRepository
import com.asb.presentation.BaseActivity
import kotlinx.android.synthetic.main.change_password_activity.confirm_password
import kotlinx.android.synthetic.main.change_password_activity.new_password
import kotlinx.android.synthetic.main.change_password_activity.submit_btn
import kotlinx.android.synthetic.main.change_password_activity.toolbar
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class ChangePasswordActivity : BaseActivity() {

    private val authRepo: LoginRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.change_password_activity)
        setToolbar(toolbar)
        submit_btn.setOnClickListener {
            val password = new_password.text.toString()
            val confPassword = confirm_password.text.toString()

            if (password != confPassword) {
                Toast.makeText(this, "Password is not match", Toast.LENGTH_SHORT).show()
            }

            GlobalScope.launch {
                val result = authRepo.changePassword(ChangePasswordPostData(password))
                runOnUiThread {
                    if (result.status == Status.SUCCESS) {
                        Toast.makeText(this@ChangePasswordActivity, "Password has been changed !", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this@ChangePasswordActivity, "Something went wrong !", Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }
    }
}