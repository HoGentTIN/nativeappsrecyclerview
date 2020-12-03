package com.example.nativeapps.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nativeapps.data.local.PharmacyAndFields
import com.example.nativeapps.repos.PharmacyRepository

class PharmacyDetailViewModel(private val pharmacyRepository: PharmacyRepository) : ViewModel() {

    private var _pharmacy = MutableLiveData<PharmacyAndFields>()
    val pharmacy: LiveData<PharmacyAndFields>
        get() = _pharmacy

    fun updatePharmacy(pharmacyId: String) {
        _pharmacy.value = pharmacyRepository.getPharmacy(pharmacyId).value
    }
}
