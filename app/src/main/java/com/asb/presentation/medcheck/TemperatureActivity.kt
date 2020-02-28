package com.asb.presentation.medcheck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.asb.android.R
import com.asb.core.utils.Preferences
import kotlinx.android.synthetic.main.activity_temperature.input_pasien_btn
import kotlinx.android.synthetic.main.activity_temperature.temperature_et
import org.koin.android.ext.android.inject

class TemperatureActivity : AppCompatActivity() {

    private val pref: Preferences by inject()
    private var temperature: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temperature)
        input_pasien_btn.setOnClickListener {
            temperature = temperature_et.text.toString()
            pref.setPatientTemperature(temperature!!.toFloat())
            setResult(RESULT_OK, null)
            finish()
        }
    }
}
