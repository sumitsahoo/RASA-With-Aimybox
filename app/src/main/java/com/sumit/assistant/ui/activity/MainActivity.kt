package com.sumit.assistant.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.core.app.ActivityOptionsCompat
import com.justai.aimybox.components.AimyboxAssistantFragment
import com.sumit.assistant.R
import kotlinx.android.synthetic.main.layout_activity_main.*


class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_activity_main)

    }

    override fun onStart() {
        super.onStart()

        initViews()
        initAssistant()
        setupEventHandlers()
    }

    private fun initViews() {

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

    }

    private fun initAssistant() {

        val assistantFragment = AimyboxAssistantFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.assistant_container, assistantFragment)
            commit()
        }
    }

    private fun setupEventHandlers(){

        iv_settings.setOnClickListener {
            val settingsIntent = Intent(this, SettingsActivity::class.java)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                iv_logo,
                getString(R.string.transition_name_logo)
            )
            startActivity(settingsIntent, options.toBundle())

        }
    }

    override fun onBackPressed() {
        val assistantFragment = (supportFragmentManager.findFragmentById(R.id.assistant_container)
                as? AimyboxAssistantFragment)
        if (assistantFragment?.onBackPressed() != true) super.onBackPressed()
    }

}