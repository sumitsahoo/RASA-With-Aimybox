package com.sumit.assistant.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.sumit.assistant.R
import kotlinx.android.synthetic.main.dialog_fragment_source_selection.*

class SourceSelectionDialogFragment: DialogFragment() {

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

        btn_save.setOnClickListener {


            dialog?.dismiss()

        }

        btn_cancel.setOnClickListener {

            dialog?.dismiss()

        }
    }

    override fun onPause() {
        super.onPause()

        dialog?.dismiss()
    }

}