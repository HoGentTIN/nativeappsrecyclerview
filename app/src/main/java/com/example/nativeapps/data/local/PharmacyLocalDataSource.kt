package com.example.nativeapps.data.local

import com.example.nativeapps.model.PharmacyFieldsModel
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

    suspend fun createPharmacy(pharmacy: PharmacyFieldsModel) {
        // Getting a new id (in onze implementatie is dit een string van +20 karakters,
        // we maken hier dus zelf ook een nieuwe random string
        val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        val randomString = (1..22)
            .map { i -> kotlin.random.Random.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("")
        val newPharmacy = Pharmacy(randomString)
        pharmacyDao.insert(newPharmacy)
        pharmacyFieldsDao.insert(pharmacy.toDatabaseModel(randomString))
    }
}
