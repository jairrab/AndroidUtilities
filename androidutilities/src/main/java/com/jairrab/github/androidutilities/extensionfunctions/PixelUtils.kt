package com.jairrab.github.androidutilities.extensionfunctions

import android.content.res.Resources


fun Float.convertToPixel(): Float {
    val metrics = Resources.getSystem().displayMetrics
    return this * (metrics.densityDpi / 160f)
}

fun Float.convertToPixelInt(): Int {
    val metrics = Resources.getSystem().displayMetrics
    return (this * (metrics.densityDpi / 160f)).toInt()
}

fun Float.convertToDp(): Float {
    val metrics = Resources.getSystem().displayMetrics
    return this / (metrics.densityDpi / 160f)
}