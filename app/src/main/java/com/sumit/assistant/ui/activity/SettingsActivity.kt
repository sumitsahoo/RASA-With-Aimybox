package com.sumit.assistant.ui.activity

import android.os.Bundle
import com.sumit.assistant.BuildConfig
import com.sumit.assistant.R
import com.sumit.assistant.ui.dialog.SourceSelectionDialogFragment
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : BaseActivity() {

    private val SOURCE_DIALOG_FRAGMENT_NAME = "sourceSelectionDialog"

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

        iv_edit_source.setOnClickListener {
            showSourceSelectionDialog()
        }
    }

    private fun showSourceSelectionDialog(){

        val ft = supportFragmentManager.beginTransaction()
        val prev = supportFragmentManager.findFragmentByTag(SOURCE_DIALOG_FRAGMENT_NAME)

        if (prev != null) {
            ft.remove(prev)
        }

        ft.addToBackStack(null)

        val dialog = SourceSelectionDialogFragment()
        dialog.isCancelable = false
        dialog.show(ft, SOURCE_DIALOG_FRAGMENT_NAME)
    }
}