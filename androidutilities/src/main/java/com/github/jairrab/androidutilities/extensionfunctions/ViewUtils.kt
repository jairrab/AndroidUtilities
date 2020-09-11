package com.github.jairrab.androidutilities.extensionfunctions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

val View.inflater: LayoutInflater
    get() = LayoutInflater.from(context)

fun View.vibrate() {
    //todo
}

fun ViewGroup.setEnableViewGroup(enabled: Boolean) {
    for (i in 0 until this.childCount) {
        val child = this.getChildAt(i)
        child.isEnabled = enabled
        if (child is ViewGroup) {
            child.setEnableViewGroup(enabled)
        }
    }
}