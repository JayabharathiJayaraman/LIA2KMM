package se.mobileinteraction.jordbruksverketkmm.utilities

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class DateUtils {
    fun instantToString(date: Instant) : String {
        val localDateTime = date.toLocalDateTime(TimeZone.currentSystemDefault())
        val date = localDateTime.date.toString()

        val hours = localDateTime.hour
        var hour = ""
        if (hours < 10) {
            hour = "0$hours"
        } else {
            hour = "$hours"
        }

        val minutes = localDateTime.minute
        var minute = ""
        if (minutes < 10) {
            minute = "0$minutes"
        } else {
            minute = "$minutes"
        }

        return "$date $hour:$minute"
    }
}