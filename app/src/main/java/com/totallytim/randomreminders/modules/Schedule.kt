package com.totallytim.randomreminders.modules

import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.floor

class Schedule(var week: List<Day>,
               var reminders: MutableMap<Reminder, Date> = mutableMapOf()) {

    data class Day(val name: String?,
                    val availability: String? = "000000") {
        // must be 24H format
        fun isValidTime(hours: Int, minutes: Int): Boolean {
            // 6 slots of 4H sections (0H, 4H ...)
            val hourIndex = floor(hours / 6.0).toInt()
            return if(availability!![hourIndex] == '0') {
                false
            } else {
                // 16 bits of 15M sections (0M, 15M ... 1H 0M, 1H 15M ...)
                val bits = Integer.toBinaryString(availability[hourIndex].toInt())
                val minIndex = (hours % 6) * 4 + floor(minutes / 4.0)
                // return true iff bit is 1 (true)
                bits[minIndex.toInt()] == '1'
            }
        }

        fun isValidDay() = (availability.equals("000000"))
    }

    data class Reminder(val name: String?,
                        // average number of days
                        val frequency: Double,
                        // maximum variance from average (in days)
                        val variance: Double)

    fun getAvailableDays(day: String?): Day {
        return when(day) {
            "Sunday" -> week[0]
            "Monday" -> week[1]
            "Tuesday" -> week[2]
            "Wednesday" -> week[3]
            "Thursday" -> week[4]
            "Friday" -> week[5]
            "Saturday" -> week[6]
            else -> throw UnsupportedOperationException("Unknown day")
        }
    }

    fun isAvailableHour(day: String?, hours: Int, minutes: Int): Boolean {
        return getAvailableDays(day).isValidTime(hours, minutes)
    }

    fun setNextReminder(reminder: Reminder) {
        val calendar = Calendar.getInstance()
        val rand = (2 * reminder.variance) * Math.random() - reminder.variance
        calendar.add(Calendar.DAY_OF_WEEK, (reminder.frequency + rand).toInt())
        reminders[reminder] = calendar.time
    }

    fun addReminder(reminder: Reminder) {
        setNextReminder(reminder)
    }

    fun getNextReminder(): Reminder {
        val sortedMap = reminders.entries.sortedWith(compareBy { it.value })
        return sortedMap[0].key
    }
}