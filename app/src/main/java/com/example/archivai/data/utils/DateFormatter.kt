package com.example.archivai.data.utils

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class DateFormatter {
    fun String.formatIsoDate(input: String): String {
        val parsedDate = ZonedDateTime.parse(input)
        val formatter = DateTimeFormatter.ofPattern("MMM d, yyyy 'at' hh:mm a", Locale.ENGLISH)
        return parsedDate.format(formatter)
    }
}