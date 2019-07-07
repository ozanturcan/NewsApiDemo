package com.boyner.news.util

import java.text.SimpleDateFormat
import java.util.*

class DateUtils {

    companion object {

        fun stringToDate(string: String, format: String = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"): Date {
            return SimpleDateFormat(format).parse(string) ?: Date(java.lang.Long.MIN_VALUE)
        }

    }
}


