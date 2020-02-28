package com.asb.presentation.medcheck

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.asb.android.R
import com.asb.core.utils.Preferences
import kotlinx.android.synthetic.main.activity_patient_detail.alcohol_cb
import kotlinx.android.synthetic.main.activity_patient_detail.blood_cb
import kotlinx.android.synthetic.main.activity_patient_detail.height_et
import kotlinx.android.synthetic.main.activity_patient_detail.input_pasien_btn
import kotlinx.android.synthetic.main.activity_patient_detail.smooking_cb
import kotlinx.android.synthetic.main.activity_patient_detail.weight_et
import org.koin.android.ext.android.inject

class PatientDetailActivity : AppCompatActivity() {

    private val pref: Preferences by inject()
    private var weight: String? = null
    private var height: String? = null
    private var smoking: Boolean = false
    private var blood: Boolean = false
    private var alcohol: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_detail)
        input_pasien_btn.setOnClickListener {
            weight = weight_et.text.toString()
            height = height_et.text.toString()
            smoking = smooking_cb.isChecked
            blood = blood_cb.isChecked
            alcohol = alcohol_cb.isChecked

            pref.setPatientWeight(weight)
            pref.setPatientHeight(height)
            pref.setPatientSmoking(smoking)
            pref.setPatientBlood(blood)
            pref.setPatientAlcohol(alcohol)

            val i = Intent(this, MedcheckActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}
