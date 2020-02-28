package com.asb.presentation.medcheck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.asb.android.R
import com.asb.core.utils.Preferences
import kotlinx.android.synthetic.main.activity_pulse.input_pasien_btn
import kotlinx.android.synthetic.main.activity_pulse.max_et
import kotlinx.android.synthetic.main.activity_pulse.min_et
import org.koin.android.ext.android.inject

class PulseActivity : AppCompatActivity() {

    private val pref: Preferences by inject()
    private var max: String? = null
    private var min: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pulse)
        input_pasien_btn.setOnClickListener {
            max = max_et.text.toString()
            min = min_et.text.toString()
            pref.setPatientPulseMin(min!!.toFloat())
            pref.setPatientPulseMax(max!!.toFloat())
            setResult(RESULT_OK, null)
            finish()
        }
    }
}
