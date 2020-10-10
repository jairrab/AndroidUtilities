package com.github.jairrab.androidutilities.listadapterutil

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

class DiffUtilItemCallback<T : ListAdapterHolder>(
    private val compareFunction: ((T, T) -> Boolean)? = null
) : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        if (compareFunction != null) return compareFunction.invoke(oldItem, newItem)
        return oldItem == newItem
    }
}