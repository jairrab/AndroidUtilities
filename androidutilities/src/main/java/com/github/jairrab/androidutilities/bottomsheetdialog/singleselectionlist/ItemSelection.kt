package com.github.jairrab.androidutilities.bottomsheetdialog.singleselectionlist

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class ItemSelection(
    val name: String,
    val iconInfo: IconInfo? = null,
    val dataSerializable: Serializable? = null,
    val dataParcelable: Parcelable? = null,
) : Parcelable

