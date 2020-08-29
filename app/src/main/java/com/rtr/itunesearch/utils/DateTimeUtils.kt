package com.rtr.itunesearch.utils


import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by RAHUL T R
 * Copyright (c) 2020 . All rights reserved.
 */

/**
 * Util class for date time
 */

const val FORMAT_RESPONSE = "yyyy-MM-dd'T'HH:mm:ss'Z'"
const val FORMAT_DISPLAY = "dd-MM-yyyy"

object DateTimeUtils {

    /**
     * Method to convert string date time to mills
     */
    fun getTimeStampFromDateTime(dateTime: String): Long {
        val formatter: DateFormat =
            SimpleDateFormat(FORMAT_RESPONSE, Locale.getDefault())
        val date = formatter.parse(dateTime) as Date
        return date.time
    }

    /**
     * Method to convert millis to string
     */
    fun getDateFromMillis(timeInMillis: Long): String {
        val date = Date(timeInMillis)
        val sdf = SimpleDateFormat(FORMAT_DISPLAY, Locale.getDefault())
        return sdf.format(date) as String
    }
}