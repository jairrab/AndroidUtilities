package com.github.jairrab.androidutilities.bottomsheetdialog.singleselectionlist

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

data class ListItem(
    val name: String,
    val iconInfo: IconInfo? = null,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readParcelable(IconInfo::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeParcelable(iconInfo, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ListItem> {
        override fun createFromParcel(parcel: Parcel): ListItem {
            return ListItem(parcel)
        }

        override fun newArray(size: Int): Array<ListItem?> {
            return arrayOfNulls(size)
        }
    }
}

class IconInfo(
    @DrawableRes val resId: Int = -1,
    @ColorRes val tintColor: Int = -1,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(resId)
        parcel.writeInt(tintColor)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<IconInfo> {
        override fun createFromParcel(parcel: Parcel): IconInfo {
            return IconInfo(parcel)
        }

        override fun newArray(size: Int): Array<IconInfo?> {
            return arrayOfNulls(size)
        }
    }
}