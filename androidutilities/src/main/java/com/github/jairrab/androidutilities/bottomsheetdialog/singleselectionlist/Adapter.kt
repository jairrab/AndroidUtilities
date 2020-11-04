package com.github.jairrab.androidutilities.bottomsheetdialog.singleselectionlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.jairrab.androidutilities.databinding.ListHolderBinding

class Adapter(
    private val items:List<ListItem>,
    private val onClick: (ListItem) -> Unit
): RecyclerView.Adapter<Holder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListHolderBinding.inflate(inflater, parent, false)
        return Holder(binding, onClick)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.update(items[position])
    }

    override fun getItemCount() = items.size
}