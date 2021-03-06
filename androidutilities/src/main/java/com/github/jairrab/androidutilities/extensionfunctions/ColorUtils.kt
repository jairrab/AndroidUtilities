package com.github.jairrab.androidutilities.extensionfunctions

import android.content.Context
import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

@ColorInt
fun Context.getColorResolved(@ColorRes colorRes: Int): Int {
    return ContextCompat.getColor(this, colorRes)
}

@ColorInt
fun Context.getColorResolved(resName: String?): Int {
    if (resName == null) return Color.BLACK
    val resId = getColorId(resName) ?: return Color.BLACK
    return ContextCompat.getColor(this, resId)
}

@ColorRes
fun Context.getColorId(resName: String?): Int? {
    if (resName == null) return null
    return resources.getIdentifier(resName, "color", packageName)
}