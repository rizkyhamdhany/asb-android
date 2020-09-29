package com.asb.presentation

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

open class BaseActivity : AppCompatActivity() {

    protected fun showDialog(msg: String) {
        AlertDialog.Builder(this)
                .setTitle("Login")
                .setMessage(msg)
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show()
    }

    protected fun setToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val INVALID_CREDENTIAL = "Invalid Credential"
        const val BLANK_FORM = "Please fill all field !"
        const val ERROR_MSG = "Something went wrong !"
        const val FAILED_SIGN_IN = "Failed to Sign in !"
    }
}