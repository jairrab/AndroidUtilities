package com.github.jairrab.androidutilities.utility

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.AttrRes
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StyleRes
import com.github.jairrab.androidutilities.utility.library.AndroidUtilityLibrary

interface AndroidUtility {
    fun updateThemeStyle(@StyleRes styleId: Int)
    fun resetThemeStyle()
    fun getResolvedColor(@ColorRes colorId: Int): Int
    fun getDrawableId(resName: String?): Int?

    fun getDrawable(
        @DrawableRes resId: Int,
        mutate: Boolean = false
    ): Drawable

    fun getResolvedColorTintedDrawable(
        @DrawableRes resId: Int,
        colorId: Int,
        mutate: Boolean = false
    ): Drawable

    fun getColorTintedDrawable(
        @DrawableRes resId: Int,
        @ColorRes colorId: Int,
        mutate: Boolean = false
    ): Drawable

    fun getAttributeTintedDrawable(
        @DrawableRes resId: Int,
        @AttrRes attrId: Int,
        mutate: Boolean = false
    ): Drawable

    fun getAttributeTintedDrawable(
        @StyleRes styleId: Int,
        @DrawableRes resId: Int,
        @AttrRes attrId: Int,
        mutate: Boolean = false
    ): Drawable

    fun getAttributeColor(@AttrRes attrId: Int): Int

    fun getAttributeColor(
        @AttrRes attrId: Int,
        @StyleRes styleId: Int
    ): Int

    companion object {
        fun getInstance(context: Context): AndroidUtility {
            return AndroidUtilityLibrary(context)
        }
    }
}

