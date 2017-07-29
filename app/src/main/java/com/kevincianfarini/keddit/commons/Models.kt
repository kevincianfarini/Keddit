package com.kevincianfarini.keddit.commons

import android.os.Parcel
import android.os.Parcelable
import com.kevincianfarini.keddit.commons.constants.AdapterConstants
import com.kevincianfarini.keddit.commons.interfaces.ViewType

data class RedditNews(
        val after: String,
        val before: String,
        val news: List<RedditNewsItem>
) : Parcelable {
    companion object {
        @JvmField @Suppress("unused")
        val CREATOR = createParcel {
            RedditNews(it)
        }
    }

    protected constructor(parcel: Parcel) : this(parcel.readString(), parcel.readString(), mutableListOf<RedditNewsItem>().apply {
        parcel.readTypedList(this, RedditNewsItem.CREATOR)
    })

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(after)
        dest.writeString(before)
        dest.writeTypedList(news)
    }

    override fun describeContents(): Int = 0
}

data class RedditNewsItem(
        val author: String,
        val title: String,
        val numComments: Int,
        val created: Long,
        val thumbnail: String,
        val url: String
) : ViewType, Parcelable {
    override fun getViewType(): Int = AdapterConstants.NEWS

    companion object {
        @JvmField @Suppress("unused")
        val CREATOR = createParcel {
            RedditNewsItem(it)
        }
    }

    protected constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readLong(),
            parcel.readString(),
            parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(author)
        parcel.writeString(title)
        parcel.writeInt(numComments)
        parcel.writeLong(created)
        parcel.writeString(thumbnail)
        parcel.writeString(url)
    }

    override fun describeContents(): Int = 0
}
