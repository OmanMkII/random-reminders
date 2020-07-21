package com.totallytim.randomreminders.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.totallytim.randomreminders.fromCalendar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

/**
 * The Reminder database: a collection of the user's reminders that hold information about what the
 * reminder is, as well as the next occurrence of it and the frequency of it.
 */
@Entity(tableName = "reminders_table")
data class Reminder(
    /**
     * The name of this reminder (non-unique).
     */
    @PrimaryKey
    @ColumnInfo(name = "reminder_name")
    var name: String = "",

    /**
     * The frequency with which this reminder will recur.
     */
    @ColumnInfo(name = "frequency")
    var frequency: Long = 0L,

    /**
     * The variance from the mean at which this occurs, that is:
     *
     * NEXT_OCCURRENCE = NOW + T(frequency +/- Range(variance))
     */
    @ColumnInfo(name = "variance")
    var variance: Float = 0f,

    /**
     * The next time this reminder instance will occur.
     */
    @ColumnInfo(name = "next_occurrence")
    var nextOccurrence: Long? = fromCalendar(Calendar.getInstance()),

    /**
     * Additional information about this reminder that will be presented when triggered.
     */
    @ColumnInfo(name = "description")
    var description: String? = null
)

/**
 * Sets the next occurrence of the reminder randomly based on the average next occurrence and the
 * variance from it.
 *
 * @param reminder  The reminder being set
 */
suspend fun resetNextOccurrence(database: ReminderDatabase, reminder: Reminder) {
    withContext(Dispatchers.IO) {
        val calendar = Calendar.getInstance()
        val rand = (2 * reminder.variance) * Math.random() - reminder.variance
        calendar.add(Calendar.DAY_OF_WEEK, (reminder.frequency + rand).toInt())

        reminder.nextOccurrence = fromCalendar(calendar)
        database.reminderDatabaseDao.updateExistingReminder(reminder)
    }
}