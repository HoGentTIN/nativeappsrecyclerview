package com.example.nativeapps.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.nativeapps.data.local.PharmacyAndFields
import com.example.nativeapps.databinding.ListItemPharmacyBinding

class PharmacyAdapter(private var pharmacyClickListener: PharmacyClickListener) :
    ListAdapter<PharmacyAndFields, PharmacyViewHolder>(PharmacyDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PharmacyViewHolder {
        return PharmacyViewHolder(
            ListItemPharmacyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PharmacyViewHolder, position: Int) {
        val pharmacy = getItem(position)
        holder.bindData(pharmacy)
        holder.itemView.setOnClickListener {
            pharmacyClickListener.onPharmacyClicked(
                pharmacy
            )
        }
    }
}

private class PharmacyDiffCallback : DiffUtil.ItemCallback<PharmacyAndFields>() {
    override fun areItemsTheSame(oldItem: PharmacyAndFields, newItem: PharmacyAndFields): Boolean {
        return oldItem.pharmacy.recordid == newItem.pharmacy.recordid
    }

    override fun areContentsTheSame(oldItem: PharmacyAndFields, newItem: PharmacyAndFields): Boolean {
        return oldItem == newItem
    }
}

interface PharmacyClickListener {
    fun onPharmacyClicked(pharmacy: PharmacyAndFields)
}
