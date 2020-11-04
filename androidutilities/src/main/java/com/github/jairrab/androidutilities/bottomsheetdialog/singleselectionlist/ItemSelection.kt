package com.github.jairrab.androidutilities.bottomsheetdialog.singleselectionlist

import android.os.Parcel
import android.os.Parcelable

data class ItemSelection(
    val name: String,
    val iconInfo: IconInfo? = null,
) : Parcelable {
    private constructor(parcel: Parcel) : this(
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

    companion object CREATOR : Parcelable.Creator<ItemSelection> {
        override fun createFromParcel(parcel: Parcel): ItemSelection {
            return ItemSelection(parcel)
        }

        override fun newArray(size: Int): Array<ItemSelection?> {
            return arrayOfNulls(size)
        }
    }
}

