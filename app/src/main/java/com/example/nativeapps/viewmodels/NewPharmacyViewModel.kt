package com.example.nativeapps.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nativeapps.model.PharmacyFieldsModel
import com.example.nativeapps.repos.PharmacyRepository
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator
import kotlinx.coroutines.launch
import timber.log.Timber

class NewPharmacyViewModel(private val pharmacyRepository: PharmacyRepository) : ViewModel() {

    var _name = MutableLiveData<String>()
    val getPharmacyName: LiveData<String> = _name

    private var _nameValid = MutableLiveData<Boolean>()
    val nameValid: LiveData<Boolean>
        get() = _nameValid

    var _street = MutableLiveData<String>()
    val getPharmacyStreet: LiveData<String> = _street

    private var _streetValid = MutableLiveData<Boolean>()
    val streetValid: LiveData<Boolean>
        get() = _streetValid

    val formValid = MediatorLiveData<Boolean>().apply {
        addSource(_name) { value = isFormValid() }
        addSource(_street) { value = isFormValid() }
    }

    private fun isFormValid(): Boolean {
        isNameValid()
        isStreetValid()
        return (_nameValid.value!! && _streetValid.value!!)
    }

    private fun isNameValid() {
        if (_name.value == null) {
            _nameValid.value = false
        } else {
            _nameValid.value = _name.value!!.validator().nonEmpty().check()
        }
    }

    private fun isStreetValid() {
        Timber.i("Checking street valid")
        if (_street.value == null) {
            _streetValid.value = false
        } else {
            _streetValid.value = _street.value!!.validator().nonEmpty().check()
        }
    }

    fun buttonAddPharmacy() {
        val model = PharmacyFieldsModel(_street.value!!, _name.value!!, "test", "test", "test", "test")
        viewModelScope.launch { pharmacyRepository.createPharmacy(model) }
    }
}
