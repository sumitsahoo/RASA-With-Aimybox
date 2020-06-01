package com.sumit.assistant.di.module

import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.sumit.assistant.util.AppConstants
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {

    // EncryptedSharedPreferences

    single<SharedPreferences> {
        // Step 1: Create or retrieve the Master Key for encryption/decryption
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

        // Step 2: Initialize/open an instance of EncryptedSharedPreferences
        EncryptedSharedPreferences.create(
            AppConstants.SharedPreferencesKey.PREF_NAME,
            masterKeyAlias,
            androidContext(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    /*single<SharedPreferences> {
        androidContext().getSharedPreferences(
            AppConstants.SharedPreferencesSettings.PREF_NAME,
            Context.MODE_PRIVATE
        )
    }*/
}