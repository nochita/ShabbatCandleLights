package com.nochita.shabbatCandles.extentions

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

const val FORMAT_DATE = "yyyy-MM-dd'T'hh:mm:ss" // 2020-02-28T19:13:00-03:00"
const val FORMAT_HOURS_AND_MINUTES = "hh:mm"
const val DAY_OF_MONTH = "dd"
const val ONE_MINUTE_IN_MILLS = 60 * 1000

fun Date.formatToDate(format: String = FORMAT_DATE): String {
    val sdf = SimpleDateFormat(format, Locale.getDefault())
    return sdf.format(time)
}

fun String.parseToDate(format: String = FORMAT_DATE): Date? {
    return try {
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        sdf.parse(this)
    } catch (e: ParseException) { null }
}

fun String.parseToMillis(format: String = FORMAT_DATE): Long? {
    return parseToDate(format)?.time
}

fun String.parseToDayOfTheMonth(): String? {
    return parseToDate()?.formatToDate(format = DAY_OF_MONTH)
}

fun String.parseToHoursAndSeconds(): String? {
    return parseToDate()?.formatToDate(format = FORMAT_HOURS_AND_MINUTES)
}