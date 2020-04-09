package com.cheise_proj.translator_ui_challenge.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "language")
data class LanguageEntity(
    var itemOne: String,
    var itemTwo: String
) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    ) {
        id = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(itemOne)
        parcel.writeString(itemTwo)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LanguageEntity> {
        override fun createFromParcel(parcel: Parcel): LanguageEntity {
            return LanguageEntity(parcel)
        }

        override fun newArray(size: Int): Array<LanguageEntity?> {
            return arrayOfNulls(size)
        }
    }
}