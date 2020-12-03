package com.example.nativeapps.data.remote

import com.example.nativeapps.data.BaseDataSource

class PharmacyRemoteDataSource(val apiService: GhentApiService) : BaseDataSource() {

    suspend fun getPharmacies() = getResult { apiService.getPharmacies() }
}
