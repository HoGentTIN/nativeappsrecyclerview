package com.example.nativeapps.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface PharmacyDao {

    @Transaction
    @Query("select * from pharmacies")
    fun getAllPharmacies(): LiveData<List<PharmacyAndFields>>

    @Transaction
    @Query("select * from pharmacies where recordid=:id")
    fun getPharmacy(id: String): LiveData<PharmacyAndFields>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<Pharmacy>)
}
