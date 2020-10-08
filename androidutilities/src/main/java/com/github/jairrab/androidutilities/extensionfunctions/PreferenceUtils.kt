package com.github.jairrab.androidutilities.extensionfunctions

import android.content.SharedPreferences
import androidx.core.content.edit
import java.util.*

fun SharedPreferences.saveDouble(
    key: String,
    number: Double,
    commit: Boolean = false
) {
    val a = number.toLongBits()
    this.edit(commit = commit) { putLong(key, a) }
}

fun SharedPreferences.getDouble(
    key: String,
    defaultValue: Double = 0.0
): Double {
    val a = this.getLong(key, -1)
    if (a == -1L) return defaultValue
    return a.toDoubleBits()
}

fun SharedPreferences.saveDate(
    date: Date,
    key: String,
    commit: Boolean = false
) {
    val a = date.time
    this.edit(commit = commit) { putLong(key, a) }
}

fun SharedPreferences.getDate(
    key: String,
    defaultValue: Date = Date()
): Date {
    val a = this.getLong(key, -1)
    if (a == -1L) return defaultValue
    return Date(a)
}

fun Double.toLongBits(): Long {
    return java.lang.Double.doubleToRawLongBits(this)
}

fun Long.toDoubleBits(): Double {
    return java.lang.Double.longBitsToDouble(this)
}