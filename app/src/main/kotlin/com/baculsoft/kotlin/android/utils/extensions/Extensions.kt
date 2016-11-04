@file:JvmName("ExtensionsUtils")

package com.baculsoft.kotlin.android.utils.extensions

import android.os.Parcel
import android.os.Parcelable

/**
 * @author Budi Oktaviyan Suryanto (budi@baculsoft.com)
 */
inline fun <reified T : Parcelable> createParcel(crossinline createFromParcel: (Parcel) -> T?): Parcelable.Creator<T> =
        object : Parcelable.Creator<T> {
            override fun createFromParcel(source: Parcel): T? = createFromParcel(source)
            override fun newArray(size: Int): Array<out T?> = arrayOfNulls(size)
        }

fun <T : Parcelable> Parcel.readParcelable(creator: Parcelable.Creator<T>): T? {
    if (null != readString()) {
        return creator.createFromParcel(this)
    } else {
        return null
    }
}