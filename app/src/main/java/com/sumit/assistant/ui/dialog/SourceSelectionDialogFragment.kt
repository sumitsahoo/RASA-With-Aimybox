package com.sumit.assistant.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.DialogFragment
import com.sumit.assistant.R
import com.sumit.assistant.util.AppConstants
import com.sumit.assistant.util.AppUtil
import kotlinx.android.synthetic.main.dialog_fragment_source_selection.*

class SourceSelectionDialogFragment : DialogFragment() {

    private var selectedIndex: Int = AppConstants.NLUDataSource.DEFAULT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(STYLE_NO_TITLE, R.style.FloatingDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_fragment_source_selection, container, false)
    }

    override fun onViewCreated(rootView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(rootView, savedInstanceState)

        rg_data_source.setOnCheckedChangeListener { group, checkedId ->

            when (checkedId) {

                R.id.radio_default -> {

                    selectedIndex = AppConstants.NLUDataSource.DEFAULT

                    ll_input_view.visibility = View.GONE
                }

                R.id.radio_aimybox -> {

                    selectedIndex = AppConstants.NLUDataSource.CUSTOM_AIMYBOX

                    ll_input_view.visibility = View.VISIBLE
                    //et_data_source.hint = getText(R.string.tv_api_key).toString()
                }

                R.id.radio_rasa -> {

                    selectedIndex = AppConstants.NLUDataSource.CUSTOM_RASA

                    ll_input_view.visibility = View.VISIBLE
                    //et_data_source.hint = getText(R.string.tv_url).toString()
                }
            }

        }

        // Fetch default settings and pre select type and value

        selectedIndex =
            AppUtil.getIntFromSharedPref(AppConstants.SharedPreferencesKey.KEY_DATA_SOURCE_TYPE)

        (rg_data_source.getChildAt(selectedIndex) as RadioButton).isChecked = true

        if (selectedIndex > 0) {
            et_data_source.setText(AppUtil.getStringFromSharedPref(AppConstants.SharedPreferencesKey.KEY_DATA_SOURCE_VALUE))
        }

        btn_save.setOnClickListener {

            if (selectedIndex > 0 && (et_data_source.text.isNullOrBlank() || et_data_source.text.isEmpty())) {
                et_data_source.error = getString(R.string.msg_fill_source_data)
                //Toast.makeText(requireContext(), getString(R.string.msg_fill_source_data), Toast.LENGTH_SHORT).show()
            } else {

                // Save details and dismiss dialog

                AppUtil.saveIntToSharedPref(
                    AppConstants.SharedPreferencesKey.KEY_DATA_SOURCE_TYPE,
                    selectedIndex
                )

                if (selectedIndex > 0) {
                    AppUtil.saveStringToSharedPref(
                        AppConstants.SharedPreferencesKey.KEY_DATA_SOURCE_VALUE,
                        et_data_source.text.toString()
                    )
                } else {
                    AppUtil.saveStringToSharedPref(
                        AppConstants.SharedPreferencesKey.KEY_DATA_SOURCE_VALUE,
                        ""
                    )
                }

                val callback = (requireContext() as NLUDataSourceChangeListener)

                if (callback != null)
                    callback.onLNUDataSourceChange()

                dialog?.dismiss()

            }

        }

        btn_cancel.setOnClickListener {

            dialog?.dismiss()

        }
    }

    override fun onPause() {
        super.onPause()

        dialog?.dismiss()
    }

    interface NLUDataSourceChangeListener {
        fun onLNUDataSourceChange()
    }


}