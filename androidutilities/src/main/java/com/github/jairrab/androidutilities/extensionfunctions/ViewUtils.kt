package com.github.jairrab.androidutilities.extensionfunctions

import android.content.Context
import android.os.VibrationEffect
import android.os.VibrationEffect.DEFAULT_AMPLITUDE
import android.os.Vibrator
import android.view.HapticFeedbackConstants
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

const val DEFAULT_VIBRATION_DURATION = 3L

val View.inflater: LayoutInflater
    get() = LayoutInflater.from(context)

fun View.vibrate(
    useDeviceSetting: Boolean,
    duration: Long = DEFAULT_VIBRATION_DURATION,
    amplitude: Int? = null
) {
    when (useDeviceSetting) {
        true -> performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
        else -> context.vibrate(duration, amplitude)
    }
}

fun Context.vibrate(
    duration: Long = DEFAULT_VIBRATION_DURATION,
    amplitude: Int? = null
) {
    val vb = getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
        vb?.vibrate(VibrationEffect.createOneShot(duration, amplitude ?: DEFAULT_AMPLITUDE))
    } else {
        vb?.vibrate(duration)
    }
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