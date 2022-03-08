package se.mobileinteraction.jordbruksverketkmm.utilities

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class DateUtils {
    fun instantToString(date: Instant) : String {
        val localDateTime = date.toLocalDateTime(TimeZone.currentSystemDefault())
        val date = localDateTime.date.toString()
        val hours = localDateTime.hour.toString().padStart(length = 2, '0')
        val minutes = localDateTime.minute.toString().padStart(length = 2, '0')

        return "$date $hours:$minutes"
    }
}