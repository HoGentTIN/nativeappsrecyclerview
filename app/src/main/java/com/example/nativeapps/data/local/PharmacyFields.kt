package com.example.nativeapps.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(
    tableName = "pharmacyFields",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Pharmacy::class,
            parentColumns = arrayOf("recordid"),
            childColumns = arrayOf("pharmacyId"),
            onDelete = ForeignKey.CASCADE
        )
    )
)
data class PharmacyFields(
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
    val city: String,
    @ColumnInfo(name = "pharmacyId", index = true)
    var pharmacyId: String?
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
