package com.jairrab.github.androidutilities.extensionfunctions

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard() {
    currentFocus
        ?.let(View::hideKeyboard)
        ?: let { findViewById<View>(android.R.id.content)?.hideKeyboard() }
}

fun Activity.showKeyboard() {
    val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}

fun View.showKeyboard() {
    (context as? Activity)?.showKeyboard()
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}