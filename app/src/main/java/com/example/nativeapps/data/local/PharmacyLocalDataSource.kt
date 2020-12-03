package com.example.nativeapps.data.local

import com.example.nativeapps.model.PharmacyModel

class PharmacyLocalDataSource(val pharmacyDao: PharmacyDao, val pharmacyFieldsDao: PharmacyFieldsDao) {

    fun getPharmacies() = pharmacyDao.getAllPharmacies()

    fun getPharmacy(id: String) = pharmacyDao.getPharmacy(id)

    fun savePharmacies(list: List<PharmacyModel>) {
        val pharmacyList = ArrayList<Pharmacy>()
        list.forEach { pharmacyModel -> pharmacyList.add(pharmacyModel.toDatabaseModel()) }
        pharmacyDao.insertAll(pharmacyList)

        val fieldsList = ArrayList<PharmacyFields>()
        list.forEach { pharmacy -> fieldsList.add(pharmacy.fields.toDatabaseModel(pharmacy.recordid)) }
        pharmacyFieldsDao.insertAll(fieldsList)
    }
}
