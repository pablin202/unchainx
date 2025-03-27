package com.pdm.storage.preferences.di

import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.pdm.storage.preferences.SecureStorageDataSource
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val secureStorageModule = module {
    single<SharedPreferences> {
        EncryptedSharedPreferences(
            androidApplication(),
            "wallet_prefs",
            MasterKey(androidApplication()),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }
    single { SecureStorageDataSource(get()) }
}