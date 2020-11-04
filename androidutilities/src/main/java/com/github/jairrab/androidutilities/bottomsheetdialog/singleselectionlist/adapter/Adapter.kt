package com.github.jairrab.androidutilities.bottomsheetdialog.singleselectionlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.jairrab.androidutilities.bottomsheetdialog.singleselectionlist.ItemSelection
import com.github.jairrab.androidutilities.databinding.ListHolderBinding

class Adapter(
    private val itemSelections: List<ItemSelection>,
    private val onClick: (Int, ItemSelection) -> Unit
) : RecyclerView.Adapter<Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListHolderBinding.inflate(inflater, parent, false)
        return Holder(binding, onClick)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.update(itemSelections[position])
    }

    override fun getItemCount() = itemSelections.size
}