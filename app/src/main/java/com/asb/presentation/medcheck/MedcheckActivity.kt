package com.asb.presentation.medcheck

import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.asb.android.R
import com.asb.core.model.Medcheck
import com.asb.core.model.MedcheckData
import com.asb.core.network.Resource
import com.asb.core.network.Status
import com.asb.core.repository.PatientRepository
import com.asb.core.utils.Preferences
import kotlinx.android.synthetic.main.activity_medcheck.blood_pressure
import kotlinx.android.synthetic.main.activity_medcheck.blood_pressure_result
import kotlinx.android.synthetic.main.activity_medcheck.cholesterol
import kotlinx.android.synthetic.main.activity_medcheck.cholesterol_result
import kotlinx.android.synthetic.main.activity_medcheck.finish_btn
import kotlinx.android.synthetic.main.activity_medcheck.glucose
import kotlinx.android.synthetic.main.activity_medcheck.glucose_result
import kotlinx.android.synthetic.main.activity_medcheck.pulse
import kotlinx.android.synthetic.main.activity_medcheck.pulse_result
import kotlinx.android.synthetic.main.activity_medcheck.respiratory
import kotlinx.android.synthetic.main.activity_medcheck.respiratory_result
import kotlinx.android.synthetic.main.activity_medcheck.temperature
import kotlinx.android.synthetic.main.activity_medcheck.temperature_result
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.android.inject

class MedcheckActivity : AppCompatActivity() {

    private val pref: Preferences by inject()
    private val patientRepo: PatientRepository by inject()

    private val observer = Observer<Resource<Medcheck>> {
        when (it.status) {
            Status.SUCCESS -> {
                pref.resetPatient()
                finish()
            }
            Status.ERROR -> {

            }
            Status.LOADING -> {

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medcheck)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        finish_btn.setOnClickListener {
            liveData(Dispatchers.IO) {
                emit(Resource.loading(null))
                emit(patientRepo.createMedical(pref.getCommandCenter(), MedcheckData(
                    pref.getPatientNip(),
                    pref.getPatientWeight(),
                    pref.getPatientHeight(),
                    pref.getPatientSmoking(),
                    pref.getPatientBlood(),
                    pref.getPatientAlcohol(),
                    pref.getPatientGlucose(),
                    pref.getPatientCholesterol(),
                    pref.getPatientTemperature(),
                    pref.getPatientPulseMin(),
                    pref.getPatientPulseMax(),
                    pref.getPatientRespiratory(),
                    pref.getPatientBPS(),
                    pref.getPatientBPD()
                )))
            }.observe(this, observer)
        }
        cholesterol.setOnClickListener {
            startAina("com.janacare.aina.total_cholesterol", AINA_REQUEST_CODE_CHOLESTEROL)
        }
        glucose.setOnClickListener {
            startAinaMini("com.janacare.ainamini.openAinaMini", AINA_REQUEST_CODE_GLUCOSE)
        }
        temperature.setOnClickListener {
            startTemperature()
        }
        respiratory.setOnClickListener {
            starRespiratory()
        }
        pulse.setOnClickListener {
            startPulse()
        }
        blood_pressure.setOnClickListener {
            startBloodPressure()
        }
    }

    fun isAinaPackageAvailable(context: Context?) : Boolean {
        var pm : PackageManager = context?.packageManager!!
        var packages : List<ApplicationInfo> = pm.getInstalledApplications(0)
        packages.forEach {
            if(it.packageName.contains("com.janacare.aina"))
                return true
        }
        return false
    }

    fun isAinaMiniPackageAvailable(context: Context?) : Boolean {
        var pm : PackageManager = context?.packageManager!!
        var packages : List<ApplicationInfo> = pm.getInstalledApplications(0)
        packages.forEach {
            if(it.packageName.contains("com.janacare.ainamini"))
                return true
        }
        return false
    }

    fun startAina(action: String, requestCode: Int){
        var ainaIntent : Intent? = null
        if (isAinaPackageAvailable(this)) {
            ainaIntent = Intent(action)
            startActivityForResult(ainaIntent, requestCode)
        } else {
            Toast.makeText(this, "Aina app not installed", Toast.LENGTH_SHORT).show()
        }
    }

    fun startAinaMini(action: String, requestCode: Int){
        var ainaIntent : Intent? = null
        if (isAinaMiniPackageAvailable(this)) {
            ainaIntent = Intent(action)
            startActivityForResult(ainaIntent, requestCode)
        } else {
            Toast.makeText(this, "Aina Mini app not installed", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                AINA_REQUEST_CODE_CHOLESTEROL -> {
                    val reading: String? = data?.getStringExtra("reading")
                    reading?.let {
                        pref.setPatientCholesterol(it.toFloat())
                        cholesterol_result.text = it
                    }
                }
                AINA_REQUEST_CODE_GLUCOSE -> {
                    val reading: String? = data?.getStringExtra("reading")
                    reading?.let {
                        pref.setPatientGlucose(it.toFloat())
                        glucose_result.text = it
                    }

                }
                TEMPERATURE_REQUEST_CODE -> {
                    temperature_result.text = pref.getPatientTemperature().toString()
                }
                RESPIRATORY_REQUEST_CODE -> {
                    respiratory_result.text = pref.getPatientRespiratory().toString()
                }
                PULSE_REQUEST_CODE -> {
                    pulse_result.text = "max : ${pref.getPatientPulseMax()} \nmin : ${pref.getPatientPulseMin()}"
                }
                BLOOD_PRESSURE_REQUEST_CODE -> {
                    blood_pressure_result.text = "systolic : ${pref.getPatientBPS()} \ndiastolic : ${pref.getPatientBPD()}"
                }
            }
        }
    }

    private fun startTemperature(){
        val i = Intent(this, TemperatureActivity::class.java)
        startActivityForResult(i, TEMPERATURE_REQUEST_CODE)
    }

    private fun starRespiratory(){
        val i = Intent(this, RespiratoryActivity::class.java)
        startActivityForResult(i, RESPIRATORY_REQUEST_CODE)
    }

    private fun startPulse() {
        val i = Intent(this, PulseActivity::class.java)
        startActivityForResult(i, PULSE_REQUEST_CODE)
    }

    private fun startBloodPressure() {
        val i = Intent(this, BloodPressureActivity::class.java)
        startActivityForResult(i, BLOOD_PRESSURE_REQUEST_CODE)
    }

    companion object {
        const val AINA_REQUEST_CODE_GLUCOSE = 10
        const val AINA_REQUEST_CODE_CHOLESTEROL = 11
        const val TEMPERATURE_REQUEST_CODE = 12
        const val PULSE_REQUEST_CODE = 13
        const val RESPIRATORY_REQUEST_CODE = 14
        const val BLOOD_PRESSURE_REQUEST_CODE = 15
    }
}
