package com.sumit.assistant.ui

import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.dell.qrscanner.util.AppUtil
import kotlinx.android.synthetic.main.layout_activity_main.*

open class BaseActivity : AppCompatActivity() {

    override fun onStart() {
        super.onStart()

        initUICustomization()
    }

    private fun initUICustomization() {

        AppUtil.makeStatusbarTransparent(this)

        // Start gradient animation
        Handler().postDelayed({
            if (fl_container != null)
                AppUtil.toggleAnimationBackground(fl_container, true)
        }, 2000)

    }

    override fun onStop() {
        super.onStop()

        if (fl_container != null)
            AppUtil.toggleAnimationBackground(fl_container, false)
    }
}