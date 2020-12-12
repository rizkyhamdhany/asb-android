package com.asb.presentation.auth

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.asb.android.R
import com.asb.core.network.Status
import com.asb.core.repository.LoginRepository
import com.asb.presentation.BaseActivity
import kotlinx.android.synthetic.main.activity_medical_history.toolbar
import kotlinx.android.synthetic.main.activity_my_medical_history.alergi
import kotlinx.android.synthetic.main.activity_my_medical_history.alkohol
import kotlinx.android.synthetic.main.activity_my_medical_history.batang
import kotlinx.android.synthetic.main.activity_my_medical_history.diabetes
import kotlinx.android.synthetic.main.activity_my_medical_history.hypertensi
import kotlinx.android.synthetic.main.activity_my_medical_history.jantung
import kotlinx.android.synthetic.main.activity_my_medical_history.kanker
import kotlinx.android.synthetic.main.activity_my_medical_history.kolesterol
import kotlinx.android.synthetic.main.activity_my_medical_history.lain_lain
import kotlinx.android.synthetic.main.activity_my_medical_history.merokok
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MyMedhisActivity : BaseActivity() {

    private val authRepo: LoginRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_medical_history)
        setToolbar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.medhis, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add -> {
                val i = Intent(this, MedhisActivity::class.java)
                i.putExtra("isFromHome", true)
                startActivity(i)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()
        setData()
    }

    private fun setData() {
        GlobalScope.launch {
            val result = authRepo.getMyMedhis()
            if (result.status == Status.SUCCESS) {
                runOnUiThread {
                    result.data?.d?.let {
                        kanker.text = it.kanker.booleanToString()
                        hypertensi.text = it.hypertensi.booleanToString()
                        diabetes.text = it.diabetes.booleanToString()
                        jantung.text = it.jantung.booleanToString()
                        kolesterol.text = it.kolesterol.booleanToString()
                        alergi.text = it.alergi.booleanToString()
                        lain_lain.text = it.lain_lain
                        merokok.text = it.merokok.booleanToString()
                        batang.text = it.batang
                        alkohol.text = it.alkohol.booleanToString()
                    }
                }

            }
        }
    }

    fun Int.booleanToString(): String = if (this == 1) "Yes" else "No"
}