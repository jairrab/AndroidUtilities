package com.github.jairrab.androidutilities.bottomsheetdialog

import android.app.Dialog
import android.os.Bundle
import android.view.View
import com.github.jairrab.androidutilities.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetDialogFragment : BottomSheetDialogFragment(){
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            setOnShowListener {
                val bottomSheetDialog = it as BottomSheetDialog
                val bottomSheet = bottomSheetDialog.findViewById<View>(R.id.design_bottom_sheet)
                    ?: return@setOnShowListener
                val behavior = BottomSheetBehavior.from(bottomSheet)
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
                behavior.skipCollapsed = true
            }
        }
    }
}