package com.music.musicnote

import android.os.Parcel
import android.os.Parcelable

class AudioFileModel(title: String, path: String, artist: String?): Parcelable {

    var title: String? = title
    var path: String? = path
    var artist: String? = artist

    constructor(parcel: Parcel) : this(
        TODO("title"),
        TODO("path"),
        TODO("artist")
    ) {
        title = parcel.readString()
        path = parcel.readString()
        artist = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(path)
        parcel.writeString(artist)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AudioFileModel> {
        override fun createFromParcel(parcel: Parcel): AudioFileModel {
            return AudioFileModel(parcel)
        }

        override fun newArray(size: Int): Array<AudioFileModel?> {
            return arrayOfNulls(size)
        }
    }
}