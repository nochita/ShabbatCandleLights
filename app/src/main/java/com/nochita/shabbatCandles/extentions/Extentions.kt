package com.nochita.shabbatCandles.extentions

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

const val FORMAT_DATE = "yyyy-MM-dd'T'hh:mm:ss" // 2020-02-28T19:13:00-03:00"

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