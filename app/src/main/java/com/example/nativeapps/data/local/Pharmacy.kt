package com.example.nativeapps.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pharmacies")
class Pharmacy(
    @PrimaryKey
    val recordid: String
)
