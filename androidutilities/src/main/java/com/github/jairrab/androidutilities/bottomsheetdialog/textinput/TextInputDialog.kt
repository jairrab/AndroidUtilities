package com.github.jairrab.androidutilities.bottomsheetdialog.textinput

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.github.jairrab.androidutilities.R
import com.github.jairrab.androidutilities.bottomsheetdialog.BaseBottomSheetDialogFragment
import com.github.jairrab.androidutilities.extensionfunctions.showToast
import timber.log.Timber
import java.io.Serializable

class TextInputDialog : BaseBottomSheetDialogFragment() {
    @SuppressLint("InflateParams", "LogNotTimber")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.text_input, null, false)

        val titleTextView = view.findViewById<TextView>(R.id.title)
        val textInputTextView = view.findViewById<EditText>(R.id.text_input)
        val okButton = view.findViewById<TextView>(R.id.ok)
        val cancelButton = view.findViewById<TextView>(R.id.cancel)

        val requestCode = targetRequestCode
        val blankReturnToastMessage = requireArguments().getString(BLANK_RETURN_MESSAGE)
        val dismissOnOkWhenBlank = requireArguments().getBoolean(DISMISS_ON_OK_WHEN_BLANK)
        val title = requireArguments().getString(TITLE)
        val text = requireArguments().getString(TEXT)
        val data = requireArguments().getSerializable(DATA)
        val ok = requireArguments().getString(OK)
        val cancel = requireArguments().getString(CANCEL)
        val inputType = requireArguments().getInt(INPUT_TYPE, -1)

        val log = "requestCode: $requestCode | title: $title | text: $text | data: $data"
        Log.v("TextInputDialog", log)

        titleTextView.text = title
        textInputTextView.setText(text)
        okButton.text = ok
        cancelButton.text = cancel
        cancelButton.isVisible = cancel != null
        if (inputType != -1) textInputTextView.inputType = inputType

        okButton.setOnClickListener {
            val textInput = textInputTextView.text.toString()
            Timber.v("Text input is $textInput")

            if (textInput.isEmpty()) {
                if (blankReturnToastMessage != null) {
                    requireContext().showToast(blankReturnToastMessage)
                }
                if (dismissOnOkWhenBlank) {
                    val intent = Intent().apply {
                        val bundle = requireArguments()
                        bundle.putString(TEXT, textInput)
                        bundle.putString(TAG, tag)
                        putExtras(bundle)
                    }

                    targetFragment?.onActivityResult(requestCode, Activity.RESULT_OK, intent)
                    dismiss()
                }
            } else {
                val intent = Intent().apply {
                    val bundle = requireArguments()
                    bundle.putString(TEXT, textInput)
                    bundle.putString(TAG, tag)
                    putExtras(bundle)
                }

                targetFragment?.onActivityResult(requestCode, Activity.RESULT_OK, intent)
                dismiss()
            }
        }

        cancelButton.setOnClickListener {
            val textInput = textInputTextView.text.toString()
            Timber.v("Text input is $textInput")

            val intent = Intent().apply {
                val bundle = requireArguments()
                bundle.putString(TEXT, textInput)
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
        const val TEXT = "TEXT"
        private const val BLANK_RETURN_MESSAGE = "BLANK_RETURN_MESSAGE"
        private const val DISMISS_ON_OK_WHEN_BLANK = "DISMISS_ON_OK_WHEN_BLANK"
        private const val CANCEL = "CANCEL"
        private const val OK = "OK"
        private const val TITLE = "TITLE"
        private const val INPUT_TYPE = "INPUT_TYPE"

        /**
         * For [inputType], please refer to [InputType] static int constants
         */
        fun showTextInput(
            fragment: Fragment,
            requestCode: Int,
            title: String,
            text: CharSequence? = null,
            ok: String? = fragment.getString(android.R.string.ok),
            cancel: String? = fragment.getString(android.R.string.cancel),
            data: Serializable? = null,
            tag: String? = null,
            blankReturnToastMessage: String? = "Please don't leave this entry blank.",
            dismissOnOkWhenBlank: Boolean = blankReturnToastMessage == null,
            inputType: Int = -1,
        ) {
            TextInputDialog().apply {
                arguments = Bundle().apply {
                    putString(TITLE, title)
                    putCharSequence(TEXT, text)
                    putString(BLANK_RETURN_MESSAGE, blankReturnToastMessage)
                    putBoolean(DISMISS_ON_OK_WHEN_BLANK, dismissOnOkWhenBlank)
                    putString(OK, ok)
                    putString(CANCEL, cancel)
                    putSerializable(DATA, data)
                    putInt(INPUT_TYPE, inputType)
                }
                setTargetFragment(fragment, requestCode)
                show(fragment.parentFragmentManager, tag)
            }
        }
    }
}

