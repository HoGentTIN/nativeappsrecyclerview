package com.example.nativeapps.model

import android.os.Parcelable
import com.example.nativeapps.data.local.Pharmacy
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PharmacyModel(
    val recordid: String,
    val fields: PharmacyFieldsModel
) : Parcelable {
    fun toDatabaseModel(): Pharmacy {
        return Pharmacy(recordid)
    }
}
