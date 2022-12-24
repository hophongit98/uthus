package com.example.food_list.utils

import java.util.concurrent.TimeUnit

/**
 * Created by Phillip Truong
 * date 24/12/2022.
 */
object DateUtils {

    /**
     * Convert a millisecond duration to a string format
     *
     * @param timeInMs A duration to convert to a string form hh:mm:ss, dd
     * @return A string of the form "11:05:56, 11 days".
     */
    fun getDurationBreakdown(timeInMs: Long): String? {
        var millis = timeInMs
        val days: Long = TimeUnit.MILLISECONDS.toDays(millis)
        millis -= TimeUnit.DAYS.toMillis(days)
        val hours: Long = TimeUnit.MILLISECONDS.toHours(millis)
        millis -= TimeUnit.HOURS.toMillis(hours)
        val minutes: Long = TimeUnit.MILLISECONDS.toMinutes(millis)
        millis -= TimeUnit.MINUTES.toMillis(minutes)
        val seconds: Long = TimeUnit.MILLISECONDS.toSeconds(millis)
        return "$hours:$minutes:$seconds, $days days"
    }
}