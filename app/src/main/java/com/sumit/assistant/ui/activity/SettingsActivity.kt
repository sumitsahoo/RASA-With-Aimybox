package com.sumit.assistant.ui.activity

import android.os.Bundle
import android.widget.Toast
import com.sumit.assistant.BuildConfig
import com.sumit.assistant.R
import com.sumit.assistant.ui.dialog.SourceSelectionDialogFragment
import com.sumit.assistant.util.AppConstants
import com.sumit.assistant.util.AppUtil
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : BaseActivity(), SourceSelectionDialogFragment.NLUDataSourceChangeListener {

    private val SOURCE_DIALOG_FRAGMENT_NAME = "sourceSelectionDialog"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        initViews()
        setupEventHandlers()
        retrieveNLUDataSource()
    }

    private fun initViews() {

        tv_version.text = "v" + BuildConfig.VERSION_NAME

    }

    private fun setupEventHandlers() {

        iv_close.setOnClickListener {
            onBackPressed()
        }

        iv_edit_source.setOnClickListener {
            showSourceSelectionDialog()
        }
    }

    private fun retrieveNLUDataSource() {
        val source =
            AppUtil.getIntFromSharedPref(AppConstants.SharedPreferencesKey.KEY_DATA_SOURCE_TYPE)
        var sourceType: String? = null

        when (source) {
            AppConstants.NLUDataSource.DEFAULT -> sourceType = getString(R.string.radio_default)
            AppConstants.NLUDataSource.CUSTOM_AIMYBOX -> sourceType =
                getString(R.string.radio_aimybox)
            AppConstants.NLUDataSource.CUSTOM_RASA -> sourceType = getString(R.string.radio_rasa)
        }

        tv_nlu_source.text = getString(R.string.tv_engine_selected) + " " + sourceType
    }

    private fun showSourceSelectionDialog() {

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

    override fun onLNUDataSourceChange() {
        retrieveNLUDataSource()
        Toast.makeText(context, getString(R.string.msg_restart_app), Toast.LENGTH_SHORT).show()
    }
}