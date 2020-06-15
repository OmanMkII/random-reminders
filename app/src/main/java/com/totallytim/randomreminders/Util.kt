package com.totallytim.randomreminders

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

fun getDayIndex(day: String?): Int {
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