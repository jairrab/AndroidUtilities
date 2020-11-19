package com.github.jairrab.androidutilities.bottomsheetdialog.datepicker

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.jairrab.androidutilities.bottomsheetdialog.BaseBottomSheetDialogFragment
import com.github.jairrab.androidutilities.databinding.DateDialogBinding
import java.io.Serializable
import java.util.*

class DatePickerBottomDialog : BaseBottomSheetDialogFragment() {
    private var dateSelection = Date().time

    @SuppressLint("InflateParams", "LogNotTimber")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DateDialogBinding.inflate(inflater, container, false)

        val requestCode = targetRequestCode
        val date = requireArguments().getLong(DATE)
        val title = requireArguments().getString(TITLE)
        val data = requireArguments().getSerializable(DATA)
        val initialDate = if (date == 0L) Date().time else date

        val log = "requestCode: $requestCode | title: $title | data: $data"
        Log.v(LOG_TAG, log)

        binding.title.text = title

        binding.calendar.date = initialDate
        binding.calendar.setOnDateChangeListener { _, year, month, dayOfMonth ->
            dateSelection = getTimeInMills(year, month, dayOfMonth)
        }

        binding.ok.setOnClickListener {
            val intent = Intent().apply {
                val bundle = requireArguments()
                bundle.putString(TAG, this@DatePickerBottomDialog.tag)
                bundle.putLong(DATE, dateSelection)
                putExtras(bundle)
            }
            targetFragment?.onActivityResult(requestCode, Activity.RESULT_OK, intent)

            dismiss()
        }

        binding.cancel.setOnClickListener { dismiss() }

        binding.title.text = title
        return binding.root
    }

    private fun getTimeInMills(year: Int, month: Int, dayOfMonth: Int): Long {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        return calendar.timeInMillis
    }

    companion object {
        const val DATA = "DATA"
        const val DATE = "DATE"
        const val TAG = "TAG"
        private const val TITLE = "TITLE"
        private const val LOG_TAG = "DatePickerBottomDialog"

        fun showDatePicker(
            fragment: Fragment,
            requestCode: Int,
            date: Long = 0,
            title: String? = null,
            data: Serializable? = null,
            tag: String? = null,
        ) {
            DatePickerBottomDialog().apply {
                arguments = Bundle().apply {
                    putString(TITLE, title)
                    putSerializable(DATA, data)
                    putLong(DATE, date)
                }
                setTargetFragment(fragment, requestCode)
                show(fragment.parentFragmentManager, tag)
            }
        }
    }
}