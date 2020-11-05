package com.github.jairrab.androidutilities.bottomsheetdialog.singleselectionlist.adapter

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.github.jairrab.androidutilities.R
import com.github.jairrab.androidutilities.bottomsheetdialog.singleselectionlist.ItemSelection
import com.github.jairrab.androidutilities.databinding.ListHolderBinding
import com.github.jairrab.androidutilities.extensionfunctions.getColorTintedDrawable

class Holder(
    private val binding: ListHolderBinding,
    private val onClick: (Int, ItemSelection) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    private lateinit var itemSelection: ItemSelection

    init {
        binding.root.setOnClickListener {
            onClick(adapterPosition, itemSelection)
        }
    }

    fun update(itemSelection: ItemSelection, selection: ItemSelection?) {
        this.itemSelection = itemSelection
        binding.name.text = itemSelection.name

        val iconInfo = itemSelection.iconInfo
        val iconVisible = iconInfo != null && iconInfo.resId != -1
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

        if (selection == itemSelection) {
            binding.check.isVisible = true
            val resId = R.drawable.ic_outline_check_circle_24
            val colorId = R.color.color_green_500
            val drawable = binding.check.context.getColorTintedDrawable(resId, colorId, true)
            binding.check.setImageDrawable(drawable)
        } else {
            binding.check.isVisible = false
        }
    }
}