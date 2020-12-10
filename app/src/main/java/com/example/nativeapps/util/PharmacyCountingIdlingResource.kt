package com.example.nativeapps.util

import androidx.test.espresso.idling.CountingIdlingResource

/**
 * Espresso can’t handle network calls automatically. This is because it is not
 * aware of other threads executing a network call.
 * We use the android counting idling resource implementation to solve the issue.
 */
object PharmacyCountingIdlingResource {
    private val RESOURCE = "GLOBAL"

    @JvmField val countingIdlingResource = CountingIdlingResource(RESOURCE)

    fun increment() {
        countingIdlingResource.increment()
    }

    fun decrement() {
        if (!countingIdlingResource.isIdleNow) {
            countingIdlingResource.decrement()
        }
    }
}
