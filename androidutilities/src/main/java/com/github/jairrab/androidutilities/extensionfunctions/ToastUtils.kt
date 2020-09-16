package com.github.jairrab.androidutilities.extensionfunctions

import android.content.Context
import android.widget.Toast

fun Context.showToast(text: String, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}

fun Context.showToast(resId: Int, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, resId, Toast.LENGTH_LONG).show()
}