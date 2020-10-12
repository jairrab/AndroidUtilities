package com.github.jairrab.androidutilities.utility.library

import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.webkit.MimeTypeMap
import androidx.core.content.FileProvider
import com.github.jairrab.androidutilities.extensionfunctions.*
import com.github.jairrab.androidutilities.printutil.PrintUtil
import com.github.jairrab.androidutilities.utility.AndroidUtility
import java.io.File
import java.net.URLConnection
import java.util.*


internal class AndroidUtilityLibrary(
    private val context: Context,
    private val printUtil: PrintUtil
) : AndroidUtility {
    private var styleId: Int? = null

    override fun getAttributeColor(attrId: Int): Int {
        return context.getAttributeColor(attrId)
    }

    override fun getAttributeColor(attrId: Int, styleId: Int): Int {
        return context.getAttributeColor(styleId, attrId)
    }

    override fun getAttributeTintedDrawable(resId: Int, attrId: Int, mutate: Boolean): Drawable {
        return if (styleId == null) {
            context.getAttributeTintedDrawable(resId, attrId, mutate)
                ?: throw NullPointerException("Cannot find drawable")
        } else {
            getAttributeTintedDrawable(styleId!!, resId, attrId, mutate)
        }
    }

    override fun getAttributeTintedDrawable(
        styleId: Int,
        resId: Int,
        attrId: Int,
        mutate: Boolean
    ): Drawable {
        return context.getAttributeTintedDrawable(styleId, resId, attrId, mutate)
            ?: throw NullPointerException("Cannot find drawable")
    }

    override fun getResolvedColor(colorId: Int): Int {
        return context.getColorResolved(colorId)
    }

    override fun getDrawableId(resName: String?): Int? {
        return context.getDrawableId(resName)
    }

    override fun getDrawable(resId: Int, mutate: Boolean): Drawable {
        return context.getDrawableImage(resId, mutate)
            ?: throw NullPointerException("Cannot find drawable")
    }

    override fun getResolvedColorTintedDrawable(
        resId: Int,
        colorId: Int,
        mutate: Boolean
    ): Drawable {
        return context.getResolvedColorTintedDrawable(resId, colorId, mutate)
            ?: throw NullPointerException("Cannot find drawable")
    }

    override fun getColorTintedDrawable(resId: Int, colorId: Int, mutate: Boolean): Drawable {
        return context.getColorTintedDrawable(resId, colorId, mutate)
            ?: throw NullPointerException("Cannot find drawable")
    }

    override fun resetThemeStyle() {
        styleId = null
    }

    override fun showToast(resId: Int, duration: Int) {
        context.showToast(resId, duration)
    }

    override fun showToast(text: String, duration: Int) {
        context.showToast(text, duration)
    }

    override fun updateThemeStyle(styleId: Int) {
        this.styleId = styleId
    }

    override fun isDarkModeEnabled(): Boolean {
        return context.isDarkModeEnabled()
    }

    override fun sendEmailIntent(
        email: String,
        subject: String,
        chooserTitle: String,
        noEmailApp: String
    ) {
        val uri = Uri.fromParts("mailto", email, null)
        val emailIntent = Intent(Intent.ACTION_SENDTO, uri)
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        if (emailIntent.resolveActivity(context.packageManager) != null) {
            context.startActivity(Intent.createChooser(emailIntent, chooserTitle))
        } else {
            showToast(noEmailApp)
        }
    }

    override fun printJob(context: Context, html: String, printJobName: String) {
        printUtil.printJob(context, html, printJobName)
    }

    override fun showShareIntent(subject: String, body: String, chooserTitle: String) {
        val share = Intent(Intent.ACTION_SEND)
        share.type = "text/plain"
        share.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        share.putExtra(Intent.EXTRA_SUBJECT, subject)
        share.putExtra(Intent.EXTRA_TEXT, body)
        context.startActivity(Intent.createChooser(share, chooserTitle))
    }

    override fun showShareIntent(
        subject: String,
        body: String,
        chooserTitle: String,
        noEmailApp: String,
        file: File,
        fileAuthority: String
    ) {
        val uri = FileProvider.getUriForFile(context, fileAuthority, file)
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_TEXT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, body)
        intent.type = getMimeType(uri)
        intent.putExtra(Intent.EXTRA_STREAM, uri)
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(Intent.createChooser(intent, chooserTitle))
        } else {
            showToast(noEmailApp)
        }
    }

    override fun getMimeType(file: File): String? {
        return getMimeTypeFromExtension(file.extension)
    }

    override fun getMimeType(file: File, fileAuthority: String): String? {
        val uri = FileProvider.getUriForFile(context, fileAuthority, file)
        return getMimeType(uri)
    }

    override fun getMimeType(uri: Uri): String? {
        return if (uri.scheme == ContentResolver.SCHEME_CONTENT) {
            context.contentResolver.getType(uri)
        } else {
            val extension = MimeTypeMap.getFileExtensionFromUrl(uri.toString())
            getMimeTypeFromExtension(extension)
        }
    }

    override fun getMimeTypeFromFileName(fileName: String): String? {
        return getMimeTypeFromExtension(fileName.extension())
    }

    override fun getMimeTypeFromExtension(extension: String): String? {
        return MimeTypeMap.getSingleton()
            .getMimeTypeFromExtension(extension.toLowerCase(Locale.US))
            ?: URLConnection.guessContentTypeFromName("filename.$this")
    }

    override fun destroy() {
        printUtil.destroy()
    }
}