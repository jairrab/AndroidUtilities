package com.github.jairrab.androidutilities.extensionfunctions

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import androidx.core.content.ContextCompat

fun SpannableString.spanBold(text: String): SpannableString {
    val s1 = indexOf(text)
    val e1 = s1 + text.length
    setSpan(StyleSpan(Typeface.BOLD), s1, e1, SPAN_EXCLUSIVE_EXCLUSIVE)
    return this
}

fun SpannableString.spanItalicized(text: String): SpannableString {
    val s1 = indexOf(text)
    val e1 = s1 + text.length
    setSpan(StyleSpan(Typeface.ITALIC), s1, e1, SPAN_EXCLUSIVE_EXCLUSIVE)
    return this
}

fun SpannableString.spanRed(text: String): SpannableString {
    val s1 = indexOf(text)
    val e1 = s1 + text.length
    setSpan(ForegroundColorSpan(Color.RED), s1, e1, SPAN_EXCLUSIVE_EXCLUSIVE)
    return this
}

fun SpannableString.spanColor(context: Context, text: String, color: Int): SpannableString {
    val s1 = indexOf(text)
    val e1 = s1 + text.length
    setSpan(
        ForegroundColorSpan(ContextCompat.getColor(context, color)),
        s1, e1, SPAN_EXCLUSIVE_EXCLUSIVE
    )
    return this
}

fun SpannableString.spanResolvedColor(text: String, resolvedColor: Int): SpannableString {
    val s1 = indexOf(text)
    val e1 = s1 + text.length
    setSpan(ForegroundColorSpan(resolvedColor), s1, e1, SPAN_EXCLUSIVE_EXCLUSIVE)
    return this
}

fun SpannableString.spanSize(text: String, proportion: Float): SpannableString {
    val s1 = indexOf(text)
    val e1 = s1 + text.length
    setSpan(RelativeSizeSpan(proportion), s1, e1, SPAN_EXCLUSIVE_EXCLUSIVE)
    return this
}