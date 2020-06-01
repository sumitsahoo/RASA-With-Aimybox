package com.sumit.assistant.ui.activity

import android.os.Bundle
import com.sumit.assistant.BuildConfig
import com.sumit.assistant.R
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        initViews()
        setupEventHandlers()
    }

    private fun initViews(){

        tv_version.text = "v" + BuildConfig.VERSION_NAME

    }

    private fun setupEventHandlers(){

        iv_close.setOnClickListener {
            onBackPressed()
        }
    }

}