package com.github.jairrab.androidutilities.bottomsheetdialog.singleselectionlist

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.github.jairrab.androidutilities.databinding.ListHolderBinding
import com.github.jairrab.androidutilities.extensionfunctions.getColorTintedDrawable

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

        val iconInfo = listItem.iconInfo
        val iconVisible = iconInfo?.resId != -1
        if (iconVisible) {
            binding.icon.isVisible = true
            val resId = iconInfo?.resId ?: return
            if (resId != -1) {
                val tintColor = iconInfo.tintColor
                if (tintColor != -1) {
                    val drawable = binding.icon.context.getColorTintedDrawable(resId, tintColor)
                    binding.icon.setImageDrawable(drawable)
                } else {
                    binding.icon.setImageResource(resId)
                }
            }
        } else {
            binding.icon.isVisible = false
        }
    }
}