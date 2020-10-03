package com.github.jairrab.androidutilities.utility

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.Toast
import androidx.annotation.AttrRes
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StyleRes
import com.github.jairrab.androidutilities.utility.library.AndroidUtilityLibrary
import com.github.jairrab.androidutilities.utility.library.PrintUtil
import java.io.File

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
    fun isDarkModeEnabled(): Boolean

    fun sendEmailIntent(
        email: String = "support@email.com",
        subject: String = "Feedback",
        chooserTitle: String = "Send email...",
        noEmailApp: String = "We're sorry, no email application was detected.",
    )

    fun showShareIntent(
        subject: String = "Check this out!",
        body: String = "This app is amazing!",
        chooserTitle: String = "Share link",
    )

    fun showShareIntent(
        subject: String = "Check this out!",
        body: String = "This app is amazing!",
        chooserTitle: String = "Share link",
        noEmailApp: String = "No app available to share this file",
        file: File,
        fileAuthority: String,
    )

    /**
     * Sends a print job the Android print manager. Context passed must be an activity context.
     * Important: If context wrapper was used on calling activity, the original
     * base context must be passed to [context]
     */
    fun printJob(context: Context, html: String, printJobName: String = "Print Job")
    fun getMimeType(file: File): String?
    fun getMimeType(file: File, fileAuthority: String): String?
    fun getMimeType(uri: Uri): String?
    fun getMimeTypeFromFileName(fileName: String): String?
    fun getMimeTypeFromExtension(extension: String): String?

    fun destroy()

    companion object {
        fun getInstance(context: Context): AndroidUtility {
            return AndroidUtilityLibrary(context, PrintUtil())
        }
    }
}

