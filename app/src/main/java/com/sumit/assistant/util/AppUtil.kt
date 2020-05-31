package com.dell.qrscanner.util

import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

class AppUtil {

    companion object {

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

    }
}