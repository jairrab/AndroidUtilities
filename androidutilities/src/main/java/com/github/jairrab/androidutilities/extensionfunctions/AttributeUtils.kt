package com.github.jairrab.androidutilities.extensionfunctions

import android.content.Context
import android.graphics.Color
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.StyleRes
import androidx.core.content.res.use

@ColorInt
fun Context.getAttributeColor(@AttrRes attrId: Int): Int {
    return obtainStyledAttributes(intArrayOf(attrId)).use {
        it.getColor(0, Color.BLACK)
    }
}

@ColorInt
fun Context.getAttributeColor(
    @StyleRes styleId: Int,
    @AttrRes attrId: Int
): Int {
    return obtainStyledAttributes(styleId, intArrayOf(attrId)).use {
        it.getColor(0, Color.BLACK)
    }
}