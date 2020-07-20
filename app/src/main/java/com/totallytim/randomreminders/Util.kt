package com.totallytim.randomreminders

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.TypeConverter
import com.totallytim.randomreminders.database.Reminder
import com.totallytim.randomreminders.database.ReminderDatabase
import com.totallytim.randomreminders.database.Setting
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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

/**
 * Converts the given Long to a calendar instance; enables storage in a non-text format for ease of
 * use.
 *
 * @param l the input Long in calendar format
 * @return a new calendar instance of the complete format, else null iff input is null
 */
@TypeConverter
fun toCalendar(l: Long?): Calendar? =
    if (l == null) null else Calendar.getInstance().apply { timeInMillis = l }

/**
 * Converts the input Calendar instance to a Long type for easier storage.
 *
 * @param c the input Calendar instance
 * @return a Long of the proper format
 */
@TypeConverter
fun fromCalendar(c: Calendar?): Long? = c?.time?.time

/**
 * Converts the input list of Settings to a String list for simpler transfer (screw you Android)
 *
 * @param settings  The input Settings type array
 * @return  an array of String settings for display
 */
@TypeConverter
fun asStringArray(settings: List<Setting>): Array<String> {
    val arraySettings: MutableList<String> = mutableListOf()
    for(entry in settings) {
        val arrayEntry = entry.field + ":" + entry.dataType.toString() + ":" + entry.data
        arraySettings.add(arrayEntry)
    }
    return arraySettings.toTypedArray()
}

/**
 * Converts a Type T Array to a Mutable list, because *apparently* that's not something possible by
 * conventional means...
 *
 * @param array type T array
 * @return a Mutable Array for type T
 */
@TypeConverter
fun <T> asMutableList(array: Array<T>): MutableList<T> {
    var output: MutableList<T> = mutableListOf()
    for (t in array) {
        output.add(t)
    }
    return output
}

/**
 * Populates the database with bogus data for testing purposes.
 *
 * @param database  A reference to the database
 */
suspend fun populateDatabase(database: ReminderDatabase) {
    return withContext(Dispatchers.IO) {
        // Raw data
        val names = arrayOf(
            "Reminder 1",
            "Reminder 2",
            "Reminder 3",
            "Reminder 4",
            "Reminder 5"
        )
        val frequency = arrayOf(1L, 2L, 3L, 4L, 5L)
        val description = arrayOf(
            "First reminder.",
            "",
            "Third",
            "Fourth",
            ""
        )
        // Reminders
        val reminders = arrayOf<Reminder?>(null, null, null, null, null)
        for (i in 0..4) {
            reminders[i] = Reminder(names[i], frequency[i], (1 / frequency[i]).toFloat(),
                null, description[i])
            // TODO
            //        reminders[i]!!.setNextOccurrence()
            database.reminderDatabaseDao.updateExistingReminder(reminders[i]!!)
        }
    }
}

// temporary holder for recycler view
class TextItemViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)

// TODO: proper recycler view