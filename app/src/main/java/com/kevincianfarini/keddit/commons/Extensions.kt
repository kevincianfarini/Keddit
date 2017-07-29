package com.kevincianfarini.keddit.commons

import android.os.Parcel
import android.os.Parcelable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.kevincianfarini.keddit.R
import com.squareup.picasso.Picasso

/**
 * Created by kevin on 7/14/17.
 */

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

fun ImageView.loadImg(imageUrl: String) {
    if (TextUtils.isEmpty(imageUrl)) {
        Picasso.with(context).load(R.mipmap.ic_launcher).into(this)
    } else {
        Picasso.with(context).load(imageUrl).into(this)
    }
}

inline fun <reified T : Parcelable> createParcel(crossinline createFromParcel: (Parcel) -> T): Parcelable.Creator<T> =
        object : Parcelable.Creator<T> {
            override fun createFromParcel(p0: Parcel): T? = createFromParcel(p0)
            override fun newArray(p0: Int): Array<out T?> = arrayOfNulls(p0)
        }
