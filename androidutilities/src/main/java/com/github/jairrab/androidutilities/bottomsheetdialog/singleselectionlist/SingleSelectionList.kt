package com.github.jairrab.androidutilities.bottomsheetdialog.singleselectionlist

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.jairrab.androidutilities.bottomsheetdialog.BaseBottomSheetDialogFragment
import com.github.jairrab.androidutilities.databinding.ListDialogBinding
import java.io.Serializable

class SingleSelectionList : BaseBottomSheetDialogFragment() {
    @SuppressLint("InflateParams", "LogNotTimber")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = ListDialogBinding.inflate(inflater, null, false)

        val titleTextView = binding.title
        val recyclerView = binding.recyclerView

        val requestCode = targetRequestCode
        val title = requireArguments().getString(TITLE)
        val data = requireArguments().getSerializable(DATA)
        val list = requireArguments().getParcelableArrayList<ListItem>(LIST) ?: ArrayList()

        val log = "requestCode: $requestCode | title: $title | data: $data"
        Log.v("MessageDialog", log)

        titleTextView.text = title
        titleTextView.isVisible = title != null

        recyclerView.run {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = Adapter(list) {
                val intent = Intent().apply {
                    val bundle = requireArguments()
                    bundle.putString(TAG, this@SingleSelectionList.tag)
                    bundle.putParcelable(LIST_ITEM_SELECTION, it)
                    putExtras(bundle)
                }
                targetFragment?.onActivityResult(requestCode, Activity.RESULT_OK, intent)
                dismiss()
            }
        }

        return binding.root
    }

    companion object {
        const val DATA = "DATA"
        const val LIST_ITEM_SELECTION = "LIST_ITEM_SELECTION"
        const val TAG = "TAG"
        private const val TITLE = "TITLE"
        private const val OK = "OK"
        private const val CANCEL = "CANCEL"
        private const val LIST = "LIST"

        fun showSelections(
            fragment: Fragment,
            requestCode: Int,
            title: String? = null,
            data: Serializable? = null,
            list: List<ListItem>,
            tag: String? = null,
        ) {
            SingleSelectionList().apply {
                arguments = Bundle().apply {
                    putString(TITLE, title)
                    putSerializable(DATA, data)
                    putParcelableArrayList(LIST, ArrayList(list))
                }
                setTargetFragment(fragment, requestCode)
                show(fragment.parentFragmentManager, tag)
            }
        }
    }
}

