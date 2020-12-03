package com.example.nativeapps.DI

import com.example.nativeapps.BuildConfig
import com.example.nativeapps.data.local.AppDatabase
import com.example.nativeapps.data.local.PharmacyLocalDataSource
import com.example.nativeapps.data.remote.GhentApiService
import com.example.nativeapps.data.remote.PharmacyRemoteDataSource
import com.example.nativeapps.repos.PharmacyRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get(), BuildConfig.BASE_URL) }
    single {
        provideApiService(get())
    }
    single { AppDatabase.getDatabase(androidApplication()).pharmacyDao() }
    single { AppDatabase.getDatabase(androidApplication()).pharmacyFieldsDao() }
    single { PharmacyRemoteDataSource(get()) }
    single { PharmacyLocalDataSource(get(), get()) }
    single { PharmacyRepository(get(), get()) }
}

/**
 * Provided a OkHttpClient. In debug version, an interceptor is added
 * as to log the network information which has been received.
 */
private fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
} else OkHttpClient
    .Builder()
    .build()

/**
 * Provide the retrofti instance
 */
private fun provideRetrofit(
    okHttpClient: OkHttpClient,
    BASE_URL: String
): Retrofit =
    Retrofit.Builder()
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder().add(
                    KotlinJsonAdapterFactory()
                ).build()
            )
        )
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

/**
 * Provide the API service
 */
private fun provideApiService(retrofit: Retrofit): GhentApiService =
    retrofit.create(GhentApiService::class.java)
