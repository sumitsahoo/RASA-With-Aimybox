package com.sumit.assistant.application

import android.app.Application
import android.content.Context
import com.justai.aimybox.Aimybox
import com.justai.aimybox.api.aimybox.AimyboxDialogApi
import com.justai.aimybox.components.AimyboxProvider
import com.justai.aimybox.core.Config
import com.justai.aimybox.speechkit.google.platform.GooglePlatformSpeechToText
import com.justai.aimybox.speechkit.google.platform.GooglePlatformTextToSpeech
import com.sumit.assistant.di.module.appModule
import com.sumit.assistant.util.AppConstants
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
        val unitId = UUID.randomUUID().toString()

        val textToSpeech = GooglePlatformTextToSpeech(context, Locale.ENGLISH)
        val speechToText = GooglePlatformSpeechToText(context, Locale.ENGLISH)

        val dialogApi = AimyboxDialogApi(AppConstants.AimyboxSettings.DEFAULT_AIMYBOX_API_KEY, unitId)
        //val dialogApi = RasaDialogApi(unitId, "https://9a09c256.ngrok.io/webhooks/rest/webhook")

        return Aimybox(Config.create(speechToText, textToSpeech, dialogApi))
    }
}