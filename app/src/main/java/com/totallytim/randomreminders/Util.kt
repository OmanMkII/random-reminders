package com.totallytim.randomreminders

import androidx.room.TypeConverter
import java.util.*
import kotlin.math.floor
import kotlin.math.pow

const val BIN_BASE: Int = 2
const val DEC_BASE: Int = 10
const val HEX_BASE: Int = 16

/**
 * Retrieves the nth digit from a number string of any base.
 *
 * @param num   The number to retrieve the digit from
 * @param index The index of the number to be retrieved
 * @param base  The base of the number
 * @return the nth digit of the number, accounting for base correctly
 */
fun nthDigit(num: Int, index: Int, base: Int): Double {
    val divisor = floor(base.toDouble().pow(index - 1.0))
    return (num / divisor) % base
}

/**
 * Returns the day of the week from the index, starts with Sunday == 0
 *
 * @param day   The nth day of the week
 * @return the String day of the week
 */
fun getDayString(day: Int): String {
    return when (day) {
        0 -> "Sunday"
        1 -> "Monday"
        2 -> "Tuesday"
        3 -> "Wednesday"
        4 -> "Thursday"
        5 -> "Friday"
        6 -> "Saturday"
        else -> throw UnsupportedOperationException(
            String.format(
                "Index '%d' not recognised",
                day
            )
        )
    }
}

/**
 * Gets the index of the listed day of the week, indexed from Sunday == 0
 *
 * @param day   The String day of the week
 * @return the index of the nth day of the week
 */
fun getDayIndex(day: String): Int {
    return when (day) {
        "Sunday" -> 0
        "Monday" -> 1
        "Tuesday" -> 2
        "Wednesday" -> 3
        "Thursday" -> 4
        "Friday" -> 5
        "Saturday" -> 6
        else -> throw UnsupportedOperationException(
            String.format(
                "Day '%s' not recognised",
                day
            )
        )
    }
}

@TypeConverter
fun toCalendar(l: Long?): Calendar? =
    if (l == null) null else Calendar.getInstance().apply { timeInMillis = l }

@TypeConverter
fun fromCalendar(c: Calendar?): Long? = c?.time?.time