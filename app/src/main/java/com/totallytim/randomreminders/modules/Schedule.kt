package com.totallytim.randomreminders.modules

import com.totallytim.randomreminders.HEX_BASE
import com.totallytim.randomreminders.nthDigit
import java.lang.IllegalArgumentException
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.floor

/**
 * The schedule for when all reminders that exist will trigger events in the main application, stores
 * values as compact hexdec time values and caches them locally, before storing them according to the
 * DAO.
 */
class Schedule(var week: List<Day>,
               var reminders: MutableMap<Reminder, Date> = mutableMapOf()) {

    /**
     * The Day data class represents compact data for what time slots are available in each day of
     * the week. It primarily holds a hexdec value of six units representing 15m intervals of the day.
     *
     * @param name          The day of the week this represents
     * @param availability  A hexdec representation of what timeslots are available
     */
    data class Day(val name: String,
                    val availability: Int = 0x000000) {
        /**
         * Asserts that the given time (HH:mm) is valid for this day of the week
         *
         * @param hours     the hour specified (HH format)
         * @param minutes   the minute specified (mm format)
         * @return true iff the given time is within a valid range
         * @throws IllegalArgumentException
         *                  when the given arguments exceed time and space
         */
        @Throws(IllegalArgumentException::class)
        fun isValidTime(hours: Int, minutes: Int): Boolean {
            if (hours >= 24 || minutes >= 60) {
                throw IllegalArgumentException()
            }
            // 6 slots of 4H sections (0H, 4H ...)
            val hourIndex = floor(hours / 6.0).toInt()
            val nthDigit = nthDigit(availability, hourIndex, HEX_BASE)
            return if (nthDigit == 0.0) {
                false
            } else {
                val bits = Integer.toBinaryString(nthDigit.toInt())
                val minIndex = (hours % 6) * 4 + floor(minutes / 4.0)
                // return true iff bit is 1 (true)
                bits[minIndex.toInt()] == '1'
            }
        }

        /**
         * Asserts that this day object contains any timeslot that is valid.
         *
         * @return true iff no availability exist for this day
         */
        fun isValidDay() = availability != 0x000000
    }

    /**
     * The Reminder data class contains information of a specific reminder, and specifies the frequency
     * it will trigger at, as well as the allowed variance from the absolute time difference.
     *
     * Format: triggers at NOW + T[Frequency +/- Range(Variance)]
     *
     * TODO: insert to database
     */
    data class Reminder(val name: String,
                        // average number of days
                        val frequency: Double,
                        // maximum variance from average (in days)
                        val variance: Double)

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
            "Sunday" -> week[0]
            "Monday" -> week[1]
            "Tuesday" -> week[2]
            "Wednesday" -> week[3]
            "Thursday" -> week[4]
            "Friday" -> week[5]
            "Saturday" -> week[6]
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
        reminders[reminder] = calendar.time

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
        val sortedMap = reminders.entries.sortedWith(compareBy { it.value })
        return sortedMap[0].key
    }
}