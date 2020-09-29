package com.asb.presentation.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.asb.android.R
import com.asb.core.model.ProgramMeRespond
import com.asb.core.network.Resource
import com.asb.core.network.Status
import com.asb.core.repository.LoginRepository
import com.asb.core.utils.Preferences
import com.asb.presentation.BaseActivity
import com.asb.presentation.auth.CreateProfileActivity
import com.asb.presentation.auth.LoginActivity
import com.asb.presentation.auth.MedcekActivity
import com.asb.presentation.auth.MedhisActivity
import com.asb.presentation.auth.ProgramActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.android.inject

class HomeActivity : BaseActivity() {

    private val prefHelper: Preferences by inject()
    private val authRepo: LoginRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        program.setOnClickListener {
            checkProgram()
        }
        medcheck.setOnClickListener {
            val i = Intent(this, MedcekActivity::class.java)
            i.putExtra("isFromHome", true)
            startActivity(i)
        }
        medhis.setOnClickListener {
            val i = Intent(this, MedhisActivity::class.java)
            i.putExtra("isFromHome", true)
            startActivity(i)
        }
        call_doctor.setOnClickListener {
            val uri = Uri.parse("smsto:0818438043")
            val i = Intent(Intent.ACTION_SENDTO, uri)
            i.setPackage("com.whatsapp")
            startActivity(Intent.createChooser(i, ""))
        }
        logout.setOnClickListener {
            prefHelper.clear()
            startActivity(Intent(this, LoginActivity::class.java))
        }
        account.setOnClickListener {
            val i = Intent(this, CreateProfileActivity::class.java)
            i.putExtra("isFromHome", true)
            startActivity(i)
        }
    }

    private val observer = Observer<Resource<ProgramMeRespond>> {
        when (it.status) {
            Status.SUCCESS -> {
                it.data?.`data`?.program?.let { program ->
                    if (program.is_active == 1) {
                        val i = Intent(this, ProgramActivity::class.java)
                        i.putExtra("id", program.id)
                        startActivity(i)
                    } else {
                        Toast.makeText(this@HomeActivity, "Tidak ada program yang aktif untuk anda !", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            Status.ERROR -> {
                Toast.makeText(this@HomeActivity, "Tidak ada program yang aktif untuk anda !", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun checkProgram() {
        liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            emit(authRepo.getMyProgram())
        }.observe(this, observer)
    }
}
