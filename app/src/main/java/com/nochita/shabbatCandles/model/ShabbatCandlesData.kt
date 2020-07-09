package com.nochita.shabbatCandles.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nochita.shabbatCandles.extentions.parseToMillis
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "data")
data class ShabbatCandlesData (
    @PrimaryKey val date: String, //2020-06-05T17:31:00-03:00
    val parashat : String
) : Parcelable {

    fun getDateInMillis(): Long? {
        return date.parseToMillis()
    }

}