package com.github.jairrab.androidutilities.bottomsheetdialog.message

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.github.jairrab.androidutilities.R
import com.github.jairrab.androidutilities.bottomsheetdialog.BaseBottomSheetDialogFragment
import com.github.jairrab.androidutilities.extensionfunctions.convertToPixelInt
import java.io.Serializable

class MessageDialog : BaseBottomSheetDialogFragment() {
    @SuppressLint("InflateParams", "LogNotTimber")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.message_dialog, null, false)

        val titleTextView = view.findViewById<TextView>(R.id.title)
        val messageTextView = view.findViewById<TextView>(R.id.message)
        val okButton = view.findViewById<TextView>(R.id.ok)
        val cancelButton = view.findViewById<TextView>(R.id.cancel)

        val requestCode = targetRequestCode
        val text = requireArguments().getCharSequence(TEXT)
        val title = requireArguments().getString(TITLE)
        val data = requireArguments().getSerializable(DATA)
        val ok = requireArguments().getString(OK)
        val cancel = requireArguments().getString(CANCEL)

        val log = "requestCode: $requestCode | title: $title | text: $text | data: $data"
        Log.v("MessageDialog", log)

        titleTextView.text = title
        messageTextView.text = text
        messageTextView.isVisible = text != null
        okButton.text = ok
        okButton.isVisible = ok != null
        cancelButton.text = cancel
        cancelButton.isVisible = cancel != null

        //add bottom padding when ok and cancel buttons are hidden
        if (ok == null && cancel == null) {
            val paddingLeft = messageTextView.paddingLeft
            val paddingTop = messageTextView.paddingTop
            val paddingRight = messageTextView.paddingRight
            val paddingBottom = messageTextView.paddingBottom + 10f.convertToPixelInt()
            messageTextView.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom)
        }

        okButton.setOnClickListener {
            val intent = Intent().apply {
                val bundle = requireArguments()
                bundle.putString(TAG, tag)
                putExtras(bundle)
            }
            targetFragment?.onActivityResult(requestCode, Activity.RESULT_OK, intent)
            dismiss()
        }

        cancelButton.setOnClickListener {
            val intent = Intent().apply {
                val bundle = requireArguments()
                bundle.putString(TAG, tag)
                putExtras(bundle)
            }
            targetFragment?.onActivityResult(requestCode, Activity.RESULT_CANCELED, intent)
            dismiss()
        }

        return view
    }

    companion object {
        const val DATA = "DATA"
        const val TAG = "TAG"
        private const val TEXT = "TEXT"
        private const val TITLE = "TITLE"
        private const val OK = "OK"
        private const val CANCEL = "CANCEL"

        fun showMessage(
            fragment: Fragment,
            requestCode: Int,
            title: String,
            text: CharSequence? = null,
            ok: String? = fragment.getString(android.R.string.ok),
            cancel: String? = fragment.getString(android.R.string.cancel),
            data: Serializable? = null,
            tag: String? = null,
        ) {
            MessageDialog().apply {
                arguments = Bundle().apply {
                    putCharSequence(TEXT, text)
                    putString(TITLE, title)
                    putString(OK, ok)
                    putString(CANCEL, cancel)
                    putSerializable(DATA, data)
                }
                setTargetFragment(fragment, requestCode)
                show(fragment.parentFragmentManager, tag)
            }
        }
    }
}

