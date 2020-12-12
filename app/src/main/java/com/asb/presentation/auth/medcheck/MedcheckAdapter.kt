package com.asb.presentation.auth.medcheck

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.asb.android.R
import com.asb.core.model.MyMedcekPostData

class MedcheckAdapter(private val dataSet: ArrayList<MyMedcekPostData>) :
    RecyclerView.Adapter<MedcheckAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val berat_badan: TextView
        val tinggi_badan: TextView
        val lingkar_perut: TextView
        val sistolik: TextView
        val diastolik: TextView
        val gds: TextView
        val kolesterol: TextView
        val asam_urat: TextView
        val harapan: TextView
        val created_at: TextView

        init {
            berat_badan = view.findViewById(R.id.berat_badan)
            tinggi_badan = view.findViewById(R.id.tinggi_badan)
            lingkar_perut = view.findViewById(R.id.lingkar_perut)
            sistolik = view.findViewById(R.id.sistolik)
            diastolik = view.findViewById(R.id.diastolik)
            gds = view.findViewById(R.id.gds)
            kolesterol = view.findViewById(R.id.kolesterol)
            asam_urat = view.findViewById(R.id.asam_urat)
            harapan = view.findViewById(R.id.harapan)
            created_at = view.findViewById(R.id.created_at)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MedcheckAdapter.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_medcheck, viewGroup, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: MedcheckAdapter.ViewHolder, position: Int) {
        dataSet[position].let {
            holder.berat_badan.text = it.berat_badan
            holder.tinggi_badan.text = it.tinggi_badan
            holder.lingkar_perut.text = it.lingkar_perut
            holder.sistolik.text = it.sistolik
            holder.diastolik.text = it.diastolik
            holder.gds.text = it.gds
            holder.kolesterol.text = it.kolesterol
            holder.asam_urat.text = it.asam_urat
            holder.harapan.text = it.harapan
            holder.created_at.text = it.created_at.split(" ")[0]
        }
    }

}
