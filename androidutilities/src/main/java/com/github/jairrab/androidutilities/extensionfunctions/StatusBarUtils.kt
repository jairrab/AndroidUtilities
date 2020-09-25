package com.github.jairrab.androidutilities.extensionfunctions

import android.app.Activity
import android.content.res.Configuration.*
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
import android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
import android.view.WindowManager

fun Activity.changeStatusBarColor(
    statusBarColor: Int? = null,
    fullScreenUi: Boolean = false,
    keepTranslucentFlagStatus: Boolean = false
) {
    if (!keepTranslucentFlagStatus && SDK_INT >= VERSION_CODES.KITKAT) {
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    }

    if (statusBarColor != null && SDK_INT >= VERSION_CODES.LOLLIPOP) {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = statusBarColor
    }


    if (fullScreenUi) {
        val uiMode = window.context.resources.configuration.uiMode
        window.decorView.systemUiVisibility = if (SDK_INT >= VERSION_CODES.M) {
            when (uiMode and UI_MODE_NIGHT_MASK) {
                UI_MODE_NIGHT_YES -> SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                UI_MODE_NIGHT_NO -> SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                else -> SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            }
        } else {
            SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
    } else {
        val uiMode = window.context.resources.configuration.uiMode
        window.decorView.systemUiVisibility = if (SDK_INT >= VERSION_CODES.M) {
            if (uiMode and UI_MODE_NIGHT_MASK == UI_MODE_NIGHT_NO) {
                SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else return
        } else return
    }
}