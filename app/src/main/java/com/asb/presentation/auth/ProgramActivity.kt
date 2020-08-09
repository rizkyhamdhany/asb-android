package com.asb.presentation.auth

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.asb.android.R
import com.asb.core.model.MedcekRespond
import com.asb.core.model.MedhisPostData
import com.asb.core.model.MedhisRespond
import com.asb.core.model.ProgramPostData
import com.asb.core.network.Resource
import com.asb.core.network.Status
import com.asb.core.repository.LoginRepository
import com.asb.presentation.BaseActivity
import com.google.gson.annotations.SerializedName
import kotlinx.android.synthetic.main.activity_medical_history.*
import kotlinx.android.synthetic.main.activity_medical_history.input_pasien_btn
import kotlinx.android.synthetic.main.activity_program.*
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.android.inject


class ProgramActivity : BaseActivity() {

    var programId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        programId = intent.getIntExtra("id", 0)
        setContentView(R.layout.activity_program)
        setViewListener()
        process.observe(this, observer)
    }

    private val authRepo: LoginRepository by inject()
    private var postData = MutableLiveData<ProgramPostData>()
    private var process = postData.switchMap { data ->
        liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            emit(authRepo.programLogSubmit(data))
        }
    }

    private val observer = Observer<Resource<MedcekRespond>> {
        when (it.status) {
            Status.SUCCESS -> {
                finish()
            }
            Status.ERROR -> {
                showDialog(ERROR_MSG)
            }
        }
    }

    private fun setViewListener() {
        input_pasien_btn.setOnClickListener {
            postData.value = ProgramPostData(
                program_id = programId,
                cemilan = cemilan.text.toString(),
                fasting = puasa.text.toString(),
                jalan_kaki = jalan_kaki.text.toString(),
                makan_pagi = makan_pagi.text.toString(),
                makan_siang = makan_siang.text.toString(),
                makan_sore = makan_sore.text.toString(),
                minum1 = minum1.text.toString(),
                minum2 = minum2.text.toString(),
                olahraga = olahraga.text.toString(),
                sikap = sikap.text.toString(),
                tanggal = tanggal.text.toString(),
                tidur_siang = tidur_siang.text.toString()
            )
        }
    }
}