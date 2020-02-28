package com.asb.presentation.medcheck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.asb.android.R
import com.asb.core.utils.Preferences
import kotlinx.android.synthetic.main.activity_respiratory.input_pasien_btn
import kotlinx.android.synthetic.main.activity_respiratory.respiratory_et
import org.koin.android.ext.android.inject

class RespiratoryActivity : AppCompatActivity() {

    private val pref: Preferences by inject()
    private var resporatory: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_respiratory)
        input_pasien_btn.setOnClickListener {
            resporatory = respiratory_et.text.toString()
            pref.setPatientRespiratory(resporatory!!.toFloat())
            setResult(RESULT_OK, null)
            finish()
        }
    }
}
