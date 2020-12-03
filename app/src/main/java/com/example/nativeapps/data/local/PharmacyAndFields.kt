package com.example.nativeapps.data.local

import androidx.room.Embedded
import androidx.room.Relation

data class PharmacyAndFields(

    @Embedded
    val pharmacy: Pharmacy,

    @Relation(parentColumn = "recordid", entityColumn = "pharmacyId")
    val fields: PharmacyFields
) {
    fun generateAddress() = "${fields.street} ${fields.number}\n${fields.zip} ${fields.city}"
}
