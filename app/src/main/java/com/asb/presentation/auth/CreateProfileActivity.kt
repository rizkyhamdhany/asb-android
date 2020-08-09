package com.asb.presentation.auth

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.asb.android.R
import com.asb.core.model.ProfilePostData
import com.asb.core.model.ProfileRespond
import com.asb.core.network.Resource
import com.asb.core.network.Status
import com.asb.core.repository.LoginRepository
import com.asb.core.utils.Preferences
import com.asb.presentation.BaseActivity
import com.google.gson.annotations.SerializedName
import kotlinx.android.synthetic.main.activity_create_profile.*
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.android.inject
import java.text.SimpleDateFormat
import java.util.*


class CreateProfileActivity : BaseActivity() {

    var birthPlace = ""
    var birthDate = ""
    var gender = ""
    var religion = ""
    var suku = ""
    var alamat = ""
    var phone = ""
    val myCalendar = Calendar.getInstance()

    private val prefModule: Preferences by inject()

    private val authRepo: LoginRepository by inject()
    private var postData = MutableLiveData<ProfilePostData>()
    private var process = postData.switchMap { data ->
        liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            emit(authRepo.profileSubmit(data))
        }
    }

    private val observer = Observer<Resource<ProfileRespond>> {
        when (it.status) {
            Status.SUCCESS -> {
                val i = Intent(this, MedhisActivity::class.java)
                startActivity(i)
                finish()
            }
            Status.ERROR -> {
                showDialog(ERROR_MSG)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_profile)
        email_ET.setText(prefModule.getEmail())
        setViewListener()
        process.observe(this, observer)
    }

    fun setViewListener() {
        val date = OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, monthOfYear)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLabel()
        }


        tanggallahir_ET.setOnFocusChangeListener { _, focus ->
            if (focus)
                DatePickerDialog(this@CreateProfileActivity, date, myCalendar[Calendar.YEAR], myCalendar[Calendar.MONTH],
                    myCalendar[Calendar.DAY_OF_MONTH]).show()
        }

        input_pasien_btn.setOnClickListener {
            if (validation()) {
                postData.value = ProfilePostData(
                    tempat_lahir = birthPlace,
                    tanggal_lahir = birthDate,
                    suku = suku,
                    agama = religion,
                    no_hp = phone,
                    email = email_ET.text.toString(),
                    alamat = alamat,
                    jenis_kelamin = gender
                )
            } else {
                showDialog(BLANK_FORM)
            }

        }
    }

    fun validation() : Boolean {
        birthPlace = tempat_ET.text.toString()
        birthDate = tanggallahir_ET.text.toString()
        gender = if (first.isChecked) "L" else "P"
        religion = agama_ET.text.toString()
        suku = suku_ET.text.toString()
        alamat = alamat_ET.text.toString()
        phone = nohp_ET.text.toString()

        return birthPlace.isNotEmpty() &&
                birthDate.isNotEmpty() &&
                gender.isNotEmpty() &&
                suku.isNotEmpty() &&
                alamat.isNotEmpty() &&
                phone.isNotEmpty()
    }

    private fun updateLabel() {
        val myFormat = "yy-MM-dd"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        tanggallahir_ET.setText(sdf.format(myCalendar.time))
    }

}