package com.nochita.shabbatCandles.model

import android.os.Parcelable
import com.nochita.shabbatCandles.extentions.parseToMillis
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ShabbatCandlesData (
    val date: String,
    val parashat : String
) : Parcelable {

    fun getDateInMillis(): Long? { //2020-06-05T17:31:00-03:00
        return date.parseToMillis()
    }


}