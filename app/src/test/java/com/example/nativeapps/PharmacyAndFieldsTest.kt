package com.example.nativeapps

import com.example.nativeapps.data.local.Pharmacy
import com.example.nativeapps.data.local.PharmacyAndFields
import com.example.nativeapps.data.local.PharmacyFields
import org.junit.Assert.assertEquals
import org.junit.Test

class PharmacyAndFieldsTest {

    @Test
    fun testGenerateAddress() {
        val pharmacyAndFields = PharmacyAndFields(
            Pharmacy("id"),
            PharmacyFields(
                "Spanjestraat", "COOP", "0612", "1", "090612","Hemel", "id"))

        val address = pharmacyAndFields.generateAddress()

        assertEquals("Spanjestraat 1\n0612 Hemel", address)
    }
}
