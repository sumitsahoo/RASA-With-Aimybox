package com.sumit.assistant.util

import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import org.koin.core.KoinComponent
import org.koin.core.inject

class AppUtil {

    companion object : KoinComponent {

        private val sharedPreferences: SharedPreferences by inject()

        fun makeStatusbarTransparent(activity: AppCompatActivity) {

            activity.window.apply {
                clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                statusBarColor = Color.TRANSPARENT
            }
        }

        fun toggleAnimationBackground(view: View, isStart: Boolean) {

            if (isStart) {
                //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

                val animDrawable = view.background as AnimationDrawable
                animDrawable.setEnterFadeDuration(10)
                animDrawable.setExitFadeDuration(5000)
                animDrawable.start()
            } else {

                val animDrawable = view.background as AnimationDrawable
                animDrawable.stop()
            }
        }

        fun isSharedPrefKeyPresent(key: String): Boolean {

            return sharedPreferences.contains(key)
        }

        fun saveIntToSharedPref(key: String, value: Int) {

            with(sharedPreferences.edit()) {
                putInt(key, value)
                commit()
            }
        }

        fun getIntFromSharedPref(key: String): Int {

            return sharedPreferences.getInt(key, 0)
        }

        fun saveStringToSharedPref(key: String, value: String) {

            with(sharedPreferences.edit()) {
                putString(key, value)
                commit()
            }
        }

        fun getStringFromSharedPref(key: String): String? {

            return sharedPreferences.getString(key, "")
        }

    }
}