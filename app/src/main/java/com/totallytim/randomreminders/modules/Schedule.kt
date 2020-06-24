package com.totallytim.randomreminders.modules

import androidx.lifecycle.LiveData
import com.totallytim.randomreminders.database.Day
import com.totallytim.randomreminders.database.Reminder
import com.totallytim.randomreminders.database.ReminderDatabase
import com.totallytim.randomreminders.fromCalendar
import java.lang.IllegalArgumentException
import java.util.*

/**
 * The schedule for when all reminders that exist will trigger events in the main application, stores
 * values as compact hexdec time values and caches them locally, before storing them according to the
 * DAO.
 */
class Schedule(var week: LiveData<MutableList<Day>>,
               var reminders: LiveData<MutableList<Reminder>>,
               var database: ReminderDatabase) {

    init {
        // TODO: grab reminders from database (or null)
        // TODO: grab schedule from database (insert a default if null)
    }

    /**
     * Returns what days are available from their string name.
     *
     * @param day   The day of the week
     * @return the data object containing when any reminders may trigger
     * @throws IllegalArgumentException
     *              if an invalid day string is given
     */
    @Throws(IllegalArgumentException::class)
    fun getAvailableDay(day: String): Day {
        return when(day) {
            // asserting non-null, hopefully that's what it needs
            "Sunday" -> week.value!![0]
            "Monday" -> week.value!![1]
            "Tuesday" -> week.value!![2]
            "Wednesday" -> week.value!![3]
            "Thursday" -> week.value!![4]
            "Friday" -> week.value!![5]
            "Saturday" -> week.value!![6]
            else -> throw IllegalArgumentException("Unknown day")
        }
    }

    /**
     * Asserts that the given day string has a valid time of the HH:mm given
     *
     * @param day       the day of the week
     * @param hours     the hour queried
     * @param minutes   the minute queried
     * @return true iff the time period is valid for a reminder
     */
    fun isAvailableHour(day: String, hours: Int, minutes: Int): Boolean {
        return getAvailableDay(day).isValidTime(hours, minutes)
    }

    /**
     * Sets the next time for a Reminder event to trigger.
     *
     * @param reminder the Reminder to be triggered
     */
    fun setNextReminder(reminder: Reminder) {
        val calendar = Calendar.getInstance()
        val rand = (2 * reminder.variance) * Math.random() - reminder.variance
        calendar.add(Calendar.DAY_OF_WEEK, (reminder.frequency + rand).toInt())
        if(reminders.value!!.contains(reminder)) {
            reminders.value!![reminders.value!!.indexOf(reminder)].nextOccurrence = fromCalendar(calendar)
        } else {
            reminder.nextOccurrence = fromCalendar(calendar)
            reminders.value!!.add(reminder)
        }

        // TODO: insert to database
    }

    /**
     * Inserts a new reminder to the database, and sets a trigger time for it.
     *
     * @param reminder  A new reminder object
     */
    fun addReminder(reminder: Reminder) {
        setNextReminder(reminder)

        // TODO: insert to database
    }

    /**
     * Retrieves the next reminder to be triggered
     *
     * @return the next reminder by calendar date
     */
    fun getNextReminder(): Reminder? {
        return if(reminders.value!!.isEmpty()) {
            null
        } else {
            val sortedList = reminders.value!!.sortedWith(compareBy { it.nextOccurrence })
            return sortedList[0]
        }
    }
}