package com.asb.presentation.medcheck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.asb.android.R
import com.asb.core.utils.Preferences
import kotlinx.android.synthetic.main.activity_blood_pressure.diastolic_et
import kotlinx.android.synthetic.main.activity_blood_pressure.systolic_et
import kotlinx.android.synthetic.main.activity_blood_pressure.input_pasien_btn
import org.koin.android.ext.android.inject

class BloodPressureActivity : AppCompatActivity() {

    private val pref: Preferences by inject()
    private var systolic: String? = null
    private var diastolic: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blood_pressure)
        input_pasien_btn.setOnClickListener {
            systolic = systolic_et.text.toString()
            diastolic = diastolic_et.text.toString()
            pref.setPatientBPS(systolic!!.toFloat())
            pref.setPatientBPD(diastolic!!.toFloat())
            setResult(RESULT_OK, null)
            finish()
        }
    }
}
