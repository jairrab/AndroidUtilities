package com.github.jairrab.androidutilities.utility

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.Toast
import androidx.annotation.AttrRes
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StyleRes
import com.github.jairrab.androidutilities.utility.library.AndroidUtilityLibrary

interface AndroidUtility {
    fun getAttributeColor(@AttrRes attrId: Int): Int
    fun getAttributeColor(@AttrRes attrId: Int, @StyleRes styleId: Int): Int
    fun getAttributeTintedDrawable(@DrawableRes resId: Int, @AttrRes attrId: Int, mutate: Boolean = false): Drawable
    fun getAttributeTintedDrawable(@StyleRes styleId: Int, @DrawableRes resId: Int, @AttrRes attrId: Int, mutate: Boolean = false): Drawable
    fun getColorTintedDrawable(@DrawableRes resId: Int, @ColorRes colorId: Int, mutate: Boolean = false): Drawable
    fun getDrawable(@DrawableRes resId: Int, mutate: Boolean = false): Drawable
    fun getDrawableId(resName: String?): Int?
    fun getResolvedColor(@ColorRes colorId: Int): Int
    fun getResolvedColorTintedDrawable(@DrawableRes resId: Int, colorId: Int, mutate: Boolean = false): Drawable
    fun resetThemeStyle()
    fun showToast(resId: Int, duration:Int = Toast.LENGTH_LONG)
    fun showToast(text:String, duration:Int = Toast.LENGTH_LONG)
    fun updateThemeStyle(@StyleRes styleId: Int)

    fun sendEmailIntent(
        email: String = "support@email.com",
        subject: String = "Tip, Tax and Travel Calculator Feedback",
        chooserTitle: String = "Send email...",
        noEmailApp: String = "We're sorry, no email application was detected.",
    )

    fun showShareIntent(
        subject: String = "Check this out!",
        chooserTitle: String = "Share link",
        extraBody: String = "This app is amazing!",
    )

    companion object {
        fun getInstance(context: Context): AndroidUtility {
            return AndroidUtilityLibrary(context)
        }
    }
}

