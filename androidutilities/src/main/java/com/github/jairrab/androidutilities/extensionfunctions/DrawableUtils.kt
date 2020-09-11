package com.github.jairrab.androidutilities.extensionfunctions

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.AttrRes
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StyleRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat

@DrawableRes
fun Context.getDrawableId(resName: String?): Int? {
    if (resName == null) return null
    return resources.getIdentifier(resName, "drawable", packageName)
}

fun Context.getAttributeTintedDrawable(
    @DrawableRes resId: Int,
    @AttrRes attrId: Int,
    mutate: Boolean = false,
): Drawable? {
    val drawable = getDrawableImage(resId, mutate) ?: return null
    return drawable.getResolvedColorTintedDrawable(getAttributeColor(attrId))
}

fun Context.getAttributeTintedDrawable(
    @StyleRes styleId: Int,
    @DrawableRes resId: Int,
    @AttrRes attrId: Int,
    mutate: Boolean = false,
): Drawable? {
    val drawable = getDrawableImage(resId, mutate) ?: return null
    return drawable.getResolvedColorTintedDrawable(getAttributeColor(styleId, attrId))
}

fun Context.getColorTintedDrawable(
    @DrawableRes resId: Int,
    @ColorRes colorId: Int,
    mutate: Boolean = false
): Drawable? {
    val resolvedColor = getColorResolved(colorId)
    return getResolvedColorTintedDrawable(resId, resolvedColor, mutate)
}

fun Context.getResolvedColorTintedDrawable(
    @DrawableRes resId: Int,
    colorId: Int,
    mutate: Boolean = false
): Drawable? {
    val drawable = getDrawableImage(resId, mutate) ?: return null
    return drawable.getResolvedColorTintedDrawable(colorId)
}

fun Drawable.getResolvedColorTintedDrawable(colorId: Int): Drawable? {
    DrawableCompat.setTint(this, colorId)
    return this
}

fun Context.getDrawableImage(
    @DrawableRes resId: Int,
    mutate: Boolean = false
): Drawable? {
    val drawable = ContextCompat.getDrawable(this, resId) ?: return null
    return if (mutate) drawable.mutate() else drawable
}