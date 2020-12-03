package com.example.nativeapps.DI

import com.example.nativeapps.viewmodels.PharmacyDetailViewModel
import com.example.nativeapps.viewmodels.PharmacyOverviewViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PharmacyOverviewViewModel(get()) }
    viewModel { PharmacyDetailViewModel(get()) }
}
