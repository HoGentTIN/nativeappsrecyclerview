package com.example.nativeapps.adapters

import androidx.recyclerview.widget.RecyclerView
import com.example.nativeapps.data.local.PharmacyAndFields
import com.example.nativeapps.databinding.ListItemPharmacyBinding

class PharmacyViewHolder(private var binding: ListItemPharmacyBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindData(pharmacy: PharmacyAndFields, listener: PharmacyClickListener) {
        binding.pharmacy = pharmacy

        itemView.setOnClickListener {
            listener.onPharmacyClicked(
                pharmacy
            )
        }

        binding.btnPharmacyDetailCall.setOnClickListener {
            listener.onCallClicked(pharmacy.fields.phone)
        }

        binding.btnPharmacyDetailRoute.setOnClickListener {
            listener.onRouteClicked(pharmacy.generateAddress())
        }
    }
}
