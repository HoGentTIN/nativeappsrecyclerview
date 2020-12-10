package com.example.nativeapps.data.local

import androidx.room.Embedded
import androidx.room.Ignore
import androidx.room.Relation

data class PharmacyAndFields(

    @Embedded
    var pharmacy: Pharmacy,

    @Relation(parentColumn = "recordid", entityColumn = "pharmacyId")
    val fields: PharmacyFields
) {

    @Ignore
    fun generateAddress() = "${fields.street} ${fields.number} ${fields.zip} ${fields.city}"
}
