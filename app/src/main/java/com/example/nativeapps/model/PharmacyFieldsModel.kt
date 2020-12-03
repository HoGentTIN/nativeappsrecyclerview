package com.example.nativeapps.model

import android.os.Parcelable
import com.example.nativeapps.data.local.PharmacyFields
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize

data class PharmacyFieldsModel(
    @Json(name = "straat")
    val street: String,
    @Json(name = "naam")
    val name: String,
    @Json(name = "postcode")
    val zip: String,
    @Json(name = "huisnr")
    val number: String,
    @Json(name = "telefoon")
    val phone: String,
    @Json(name = "gemeente")
    val city: String
) : Parcelable {

    fun toDatabaseModel(recordid: String): PharmacyFields {
        return PharmacyFields(street, name, zip, number, phone, city, recordid)
    }
}
