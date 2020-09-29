package com.asb.presentation.auth

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.asb.android.R
import com.asb.core.model.MedcekPostData
import com.asb.core.model.MedcekRespond
import com.asb.core.network.Resource
import com.asb.core.network.Status
import com.asb.core.repository.LoginRepository
import com.asb.presentation.BaseActivity
import com.asb.presentation.main.HomeActivity
import kotlinx.android.synthetic.main.activity_medical_checkup.*
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.android.inject


class MedcekActivity : BaseActivity() {

    private var isFromHome = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isFromHome = intent.getBooleanExtra("isFromHome", false)
        setContentView(R.layout.activity_medical_checkup)
        if (isFromHome) {
            setToolbar(toolbar)
        }
        setViewListener()
        process.observe(this, observer)
    }

    private val authRepo: LoginRepository by inject()
    private var postData = MutableLiveData<MedcekPostData>()
    private var process = postData.switchMap { data ->
        liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            emit(authRepo.medcekSubmit(data))
        }
    }

    private val observer = Observer<Resource<MedcekRespond>> {
        when (it.status) {
            Status.SUCCESS -> {
                if (!isFromHome) {
                    val i = Intent(this, HomeActivity::class.java)
                    startActivity(i)
                }
                finish()
            }
            Status.ERROR -> {
                showDialog(ERROR_MSG)
            }
        }
    }

    private fun setViewListener() {
        input_pasien_btn.setOnClickListener {
            postData.value = MedcekPostData(
                asam_urat = asamurat.text.toString(),
                berat_badan = beratbadan.text.toString(),
                diastolik = diastolik.text.toString(),
                gds = gds.text.toString(),
                harapan = harapan.text.toString(),
                kolesterol = kolesterol.text.toString(),
                lingkar_perut = lingkarperut.text.toString(),
                sistolik = sistolik.text.toString(),
                tinggi_badan = tinggibadan.text.toString()
            )
        }
    }
}