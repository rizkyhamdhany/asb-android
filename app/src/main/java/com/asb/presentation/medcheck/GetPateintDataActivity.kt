package com.asb.presentation.medcheck

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.asb.android.R
import com.asb.android.databinding.ActivityGetPateintDataBinding
import com.asb.core.model.Patient
import com.asb.core.network.Resource
import com.asb.core.network.Status
import com.asb.core.utils.Preferences
import kotlinx.android.synthetic.main.activity_get_pateint_data.loading_button
import kotlinx.android.synthetic.main.activity_get_pateint_data.toolbar
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class GetPateintDataActivity : AppCompatActivity() {

    private val prefModule: Preferences by inject()
    private val viewModel: GetPateintDataViewModel by viewModel()
    private lateinit var binding: ActivityGetPateintDataBinding
    private var nip: String? = null

    private val observer = Observer<Resource<Patient>> {
        when (it.status) {
            Status.SUCCESS -> {
                it.data?.`data`?.let { patient ->
                    viewModel.setPatient(
                        patient.name,
                        patient.email,
                        patient.phone_number,
                        patient.gender,
                        patient.birthdate
                    )
                    nip = patient.nip
                }

                loading_button.onStopLoading()
            }
            Status.ERROR -> {
                viewModel.showError("Patient not found !")
                loading_button.onStopLoading()
            }
            Status.LOADING -> {
                loading_button.onStartLoading()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_get_pateint_data)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        binding.nipEt.afterTextChangedDelayed {
            if (it.isNotEmpty()) {
                viewModel.searchPatient(it)
            }
        }

        viewModel.patient.observe(this, observer)
        loading_button.setButtonOnClickListener(onClickListener)
    }

    private val onClickListener = View.OnClickListener {
        if (!nip.isNullOrEmpty()) {
            prefModule.setPatientNip(nip)
            val i = Intent(this, PatientDetailActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}
