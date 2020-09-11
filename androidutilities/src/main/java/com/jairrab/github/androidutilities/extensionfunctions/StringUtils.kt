package com.jairrab.github.androidutilities.extensionfunctions

import android.text.Editable
import java.util.*

val String.toLowerCaseUs: String
    get() = this.toLowerCase(Locale.US)

val String.distinctify: String
    get() = "\u200B$this"

val CharSequence.nullIfBlank: CharSequence?
    get() {
        if (this.isBlank()) return null
        return this
    }

fun String?.toDoubleNumber() = if (this.isNullOrBlank()) 0.0 else this.toDouble()

fun Editable?.toDoubleNumber() = if (this.isNullOrBlank()) 0.0 else this.toString().toDouble()