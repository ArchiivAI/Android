package com.example.archivai.data.source.remote.responseModels.activity_logs

import com.example.archivai.domain.entities.ActivityLog
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale


data class ActivityLogResponseModelItem(
    val date: String,
    val message: String,
    val userImage: String
)

fun ActivityLogResponseModelItem.toDomain(): ActivityLog {
    val formattedDate = try {
        val parsed = ZonedDateTime.parse(date)
        val formatter = DateTimeFormatter.ofPattern("MMM d, yyyy • hh:mm a", Locale.ENGLISH)
        parsed.format(formatter)
    } catch (e: Exception) {
        date
    }

    return ActivityLog(
        date = formattedDate,
        message = message,
        userImage = userImage
    )
}

