package com.example.nativeapps.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface PharmacyFieldsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<PharmacyFields>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pharmacy: PharmacyFields)
}
