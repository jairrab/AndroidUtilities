package com.github.jairrab.androidutilities.bottomsheetdialog.datepicker

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.github.jairrab.androidutilities.bottomsheetdialog.BaseBottomSheetDialogFragment
import com.github.jairrab.androidutilities.databinding.DateDialogBinding
import java.io.Serializable
import java.util.*

class DatePickerBottomDialog : BaseBottomSheetDialogFragment(false) {
    private var dateSelection = Date().time

    @SuppressLint("InflateParams", "LogNotTimber")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DateDialogBinding.inflate(inflater, container, false)

        val requestCode = targetRequestCode
        val initialDate = requireArguments().getLong(DATE)
        val minimumDate = requireArguments().getLong(MINIMUM_DATE)
        val title = requireArguments().getString(TITLE)
        val data = requireArguments().getSerializable(DATA)

        val log = "requestCode: $requestCode | title: $title | data: $data"
        Log.v(LOG_TAG, log)

        binding.title.text = title
        binding.title.isVisible = !title.isNullOrBlank()

        val date = Date(initialDate)
        val calendar = Calendar.getInstance()
        calendar.time = date

        val setYear = calendar.get(Calendar.YEAR)
        val setMonth = calendar.get(Calendar.MONTH)
        val setDay = calendar.get(Calendar.DATE)

        binding.calendar.apply {
            init(setYear, setMonth, setDay) { _, year, month, dayOfMonth ->
                dateSelection = getTimeInMills(year, month, dayOfMonth)
            }

            if (minimumDate != -1L) {
                minDate = minimumDate
            }
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
        const val MINIMUM_DATE = "MINIMUM_DATE"
        const val TAG = "TAG"
        private const val TITLE = "TITLE"
        private const val LOG_TAG = "DatePickerBottomDialog"

        fun showDatePicker(
            fragment: Fragment,
            requestCode: Int,
            date: Long = Date().time,
            minimumDate: Long = -1,
            title: String? = null,
            data: Serializable? = null,
            tag: String? = null,
        ) {
            DatePickerBottomDialog().apply {
                arguments = Bundle().apply {
                    putString(TITLE, title)
                    putSerializable(DATA, data)
                    putLong(DATE, date)
                    putLong(MINIMUM_DATE, minimumDate)
                }
                setTargetFragment(fragment, requestCode)
                show(fragment.parentFragmentManager, tag)
            }
        }
    }
}