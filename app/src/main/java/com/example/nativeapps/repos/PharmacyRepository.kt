package com.example.nativeapps.repos

import com.example.nativeapps.data.local.PharmacyLocalDataSource
import com.example.nativeapps.data.remote.PharmacyRemoteDataSource
import com.example.nativeapps.util.performGetOperation

class PharmacyRepository(
    val pharmacyRemoteDataSource: PharmacyRemoteDataSource,
    val pharmacyLocalDataSource: PharmacyLocalDataSource
) {

    fun getPharmacy(id: String) = pharmacyLocalDataSource.getPharmacy(id)

    fun getPharmacies() = performGetOperation(
        databaseQuery = { pharmacyLocalDataSource.getPharmacies() },
        networkCall = { pharmacyRemoteDataSource.getPharmacies() },
        saveCallResult = { pharmacyLocalDataSource.savePharmacies(it.records) }
    )
}
