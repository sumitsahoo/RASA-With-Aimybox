package com.sumit.assistant.application

import android.app.Application
import android.content.Context
import com.justai.aimybox.Aimybox
import com.justai.aimybox.api.DialogApi
import com.justai.aimybox.api.aimybox.AimyboxDialogApi
import com.justai.aimybox.components.AimyboxProvider
import com.justai.aimybox.core.Config
import com.justai.aimybox.dialogapi.rasa.RasaDialogApi
import com.justai.aimybox.speechkit.google.platform.GooglePlatformSpeechToText
import com.justai.aimybox.speechkit.google.platform.GooglePlatformTextToSpeech
import com.sumit.assistant.di.module.appModule
import com.sumit.assistant.util.AppConstants
import com.sumit.assistant.util.AppUtil
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import java.util.*

class AssistantApplication : Application(), AimyboxProvider {

    override val aimybox by lazy { createAimybox(this) }

    override fun onCreate() {
        super.onCreate()

        // Initialize Koin DI

        startKoin {
            // Android context
            androidContext(this@AssistantApplication)
            // Koin modules
            modules(arrayListOf(appModule))
        }
    }

    private fun createAimybox(context: Context): Aimybox {

        // Check if NLU source is set, if not set to default i.e. 0

        if (!AppUtil.isSharedPrefKeyPresent(AppConstants.SharedPreferencesKey.KEY_DATA_SOURCE_TYPE)) {
            AppUtil.saveIntToSharedPref(
                AppConstants.SharedPreferencesKey.KEY_DATA_SOURCE_TYPE,
                AppConstants.NLUDataSource.DEFAULT
            )
        }

        // Get NLU Data Source

        val dataSource =
            AppUtil.getIntFromSharedPref(AppConstants.SharedPreferencesKey.KEY_DATA_SOURCE_TYPE)

        val unitId = UUID.randomUUID().toString()

        val textToSpeech = GooglePlatformTextToSpeech(context, Locale.ENGLISH)
        val speechToText = GooglePlatformSpeechToText(context, Locale.ENGLISH)

        var dialogApi: DialogApi<*, *>? = null

        when (dataSource) {
            AppConstants.NLUDataSource.DEFAULT -> {
                dialogApi =
                    AimyboxDialogApi(AppConstants.AimyboxSettings.DEFAULT_AIMYBOX_API_KEY, unitId)
            }

            AppConstants.NLUDataSource.CUSTOM_AIMYBOX -> {
                val customApiKey =
                    AppUtil.getStringFromSharedPref(AppConstants.SharedPreferencesKey.KEY_DATA_SOURCE_VALUE)
                dialogApi = AimyboxDialogApi(customApiKey!!, unitId)
            }

            AppConstants.NLUDataSource.CUSTOM_RASA -> {
                val rasaUrl =
                    AppUtil.getStringFromSharedPref(AppConstants.SharedPreferencesKey.KEY_DATA_SOURCE_VALUE)
                dialogApi = RasaDialogApi(unitId, rasaUrl!!)
            }
        }

        return Aimybox(Config.create(speechToText, textToSpeech, dialogApi!!))
    }
}