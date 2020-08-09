package com.asb.presentation.auth

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.asb.android.R
import com.asb.core.model.MedhisPostData
import com.asb.core.model.MedhisRespond
import com.asb.core.network.Resource
import com.asb.core.network.Status
import com.asb.core.repository.LoginRepository
import com.asb.presentation.BaseActivity
import kotlinx.android.synthetic.main.activity_medical_history.*
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.android.inject


class MedhisActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medical_history)
        setViewListener()
        process.observe(this, observer)
    }

    private val authRepo: LoginRepository by inject()
    private var postData = MutableLiveData<MedhisPostData>()
    private var process = postData.switchMap { data ->
        liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            emit(authRepo.medhisSubmit(data))
        }
    }

    private val observer = Observer<Resource<MedhisRespond>> {
        when (it.status) {
            Status.SUCCESS -> {
                val i = Intent(this, MedcekActivity::class.java)
                startActivity(i)
                finish()
            }
            Status.ERROR -> {
                showDialog(ERROR_MSG)
            }
        }
    }

    private fun setViewListener() {
        input_pasien_btn.setOnClickListener {
            postData.value = MedhisPostData(
                alergi = if (alergi.isChecked) "1" else "0",
                alkohol = if (alkohol.isChecked) "1" else "0",
                batang = batang.text.toString(),
                diabetes = if (diabetes.isChecked) "1" else "0",
                hypertensi = if (hypertensi.isChecked) "1" else "0",
                jantung = if (jantung.isChecked) "1" else "0",
                kanker = if (kanker.isChecked) "1" else "0",
                kolesterol = if (kolesterol.isChecked) "1" else "0",
                lain_lain = lainlain.text.toString(),
                merokok = if (merokok.isChecked) "1" else "0"
            )
        }
    }
}