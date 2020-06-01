package com.nochita.shabbatCandles.model

import com.nochita.shabbatCandles.extentions.parseToDayOfTheMonth
import com.nochita.shabbatCandles.extentions.parseToMillis

data class ShabbatCandlesData (
    val date: String, ////2020-06-05T17:31:00-03:00
    val parashat : String
) {

    fun getDateInMillis(): Long? {
        return date.parseToMillis()
    }

}