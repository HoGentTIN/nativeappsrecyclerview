package com.example.nativeapps.data.remote

import com.example.nativeapps.model.GetPharmaciesApiModel
import retrofit2.Response
import retrofit2.http.GET

interface GhentApiService {

    @GET("api/records/1.0/search/?dataset=locaties-apotheken-gent&q=&facet=gemeente&facet=postcode")
    suspend fun getPharmacies(): Response<GetPharmaciesApiModel>
}
