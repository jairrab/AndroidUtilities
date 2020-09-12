package com.github.jairrab.androidutilities.extensionfunctions

import android.text.Editable
import java.util.*

fun String.toLowerCaseUs() = toLowerCase(Locale.US)

fun String.distinctify() = "\u200B$this"

fun CharSequence.getNullIfBlank() = if (this.isBlank()) null else this

fun String?.toDoubleNumber() = if (this.isNullOrBlank()) 0.0 else this.toDouble()

fun Editable?.toDoubleNumber() = if (this.isNullOrBlank()) 0.0 else this.toString().toDouble()

fun String.extension() = substringAfterLast('.', "")

fun String.isJpgOrPng() = this.extension().equals("jpg", true) ||
    this.extension().equals("png", true)