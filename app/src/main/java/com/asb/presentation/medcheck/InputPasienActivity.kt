package com.asb.presentation.medcheck

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.asb.android.R
import kotlinx.android.synthetic.main.activity_input_pasien.input_pasien_btn


class InputPasienActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_pasien)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        input_pasien_btn.setOnClickListener {
            val i = Intent(this, MedcheckActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}
