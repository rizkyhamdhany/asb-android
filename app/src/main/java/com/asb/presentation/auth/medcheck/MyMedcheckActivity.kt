package com.asb.presentation.auth.medcheck

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.RecyclerView
import com.asb.android.R
import com.asb.core.model.MyMedcekPostData
import com.asb.core.network.Status
import com.asb.core.repository.LoginRepository
import com.asb.presentation.BaseActivity
import com.asb.presentation.auth.MedcekActivity
import kotlinx.android.synthetic.main.activity_medical_history.toolbar
import kotlinx.android.synthetic.main.activity_my_medical_history.alergi
import kotlinx.android.synthetic.main.activity_my_medical_history.alkohol
import kotlinx.android.synthetic.main.activity_my_medical_history.batang
import kotlinx.android.synthetic.main.activity_my_medical_history.diabetes
import kotlinx.android.synthetic.main.activity_my_medical_history.hypertensi
import kotlinx.android.synthetic.main.activity_my_medical_history.jantung
import kotlinx.android.synthetic.main.activity_my_medical_history.kanker
import kotlinx.android.synthetic.main.activity_my_medical_history.kolesterol
import kotlinx.android.synthetic.main.activity_my_medical_history.lain_lain
import kotlinx.android.synthetic.main.activity_my_medical_history.merokok
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MyMedcheckActivity : BaseActivity() {

    private val authRepo: LoginRepository by inject()
    private lateinit var adapter: MedcheckAdapter
    private val dataSet: ArrayList<MyMedcekPostData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_medical_checkup)
        setToolbar(toolbar)
        supportActionBar?.title = "Medical Checkup"

        adapter = MedcheckAdapter(dataSet)
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.medcek, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add -> {
                val i = Intent(this, MedcekActivity::class.java)
                i.putExtra("isFromHome", true)
                startActivity(i)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()
        setData()
    }

    private fun setData() {
        dataSet.clear()
        GlobalScope.launch {
            val result = authRepo.getMyMedcheck()
            if (result.status == Status.SUCCESS) {
                runOnUiThread {
                    result.data?.d?.let {
                        dataSet.addAll(it)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }

}