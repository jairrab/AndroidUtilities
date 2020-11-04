package com.github.jairrab.androidutilities.bottomsheetdialog.singleselectionlist

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.github.jairrab.androidutilities.databinding.ListHolderBinding

class Holder(
    private val binding: ListHolderBinding,
    private val onClick: (ListItem) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    private lateinit var listItem: ListItem

    init {
        binding.root.setOnClickListener {
            onClick(listItem)
        }
    }

    fun update(listItem: ListItem) {
        this.listItem = listItem
        binding.name.text = listItem.name

        val iconVisible = listItem.iconResId != -1
        if (iconVisible) {
            binding.icon.isVisible = true
            binding.icon.setImageResource(listItem.iconResId)
        } else {
            binding.icon.isVisible = false
        }
    }
}