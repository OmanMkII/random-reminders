package com.totallytim.randomreminders.modules

import android.content.Context
import org.json.JSONException
import org.json.JSONObject
import java.io.FileNotFoundException
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.util.*

class Schedule {
    // TODO: clean up a little
    // format of [days][hours], week starts on Sunday, 24H clock
    // for [hours]: T | F if it can be used (any minute within that hour)
    var week: Array<BooleanArray>
    private var reminders: MutableList<Reminder>? = null

    // TODO: import JSON as schedule
    constructor(context: Context) {
        val json: JSONObject
        try {
            json = JSONObject(
                loadJsonAssets(context, "schedule.json")
            )

            // TODO: convert JSON to valid array (or vice versa)
        } catch (e: JSONException) {
            // TODO: catch format(?) exception
            e.printStackTrace()
        } catch (e: IOException) {
            // TODO: catch local exception
            e.printStackTrace()
        }
        throw UnsupportedOperationException()
    }

    constructor(
        context: Context,
        reminders: MutableList<Reminder>?
    ) : this(context) {
        this.reminders = reminders
    }

    constructor(availableTimes: Array<BooleanArray>) {
        week = availableTimes
        reminders = ArrayList()
    }

    constructor(
        availableTimes: Array<BooleanArray>,
        reminders: MutableList<Reminder>?
    ) : this(availableTimes) {
        this.reminders = reminders
    }

    fun getAvailableDay(day: String?): BooleanArray {
        return week[getDayIndex(day)]
    }

    fun isAvailableHour(day: String?, hour: Int): Boolean {
        return getAvailableDay(day)[hour]
    }

    fun isValidTime(time: Date?): Boolean {
        val c = Calendar.getInstance()
        c.time = time
        val day = c[Calendar.DAY_OF_WEEK]
        val hour = c[Calendar.HOUR_OF_DAY]
        return isAvailableHour(getDayString(day), hour)
    }

    // TODO: CRITICAL
    // TODO: verify that I have correct formats for times
    fun setNextReminder(reminder: Reminder) {
        if (!reminders!!.contains(reminder)) {
            reminders!!.add(reminder)
        }
        val startMillis = System.currentTimeMillis()
        val endMillis = (startMillis + reminder.frequency) as Long
        val instanceDate =
            (Math.random() * (endMillis - startMillis)).toLong()
        reminder.nextOccurrence = Date(startMillis + instanceDate)
    }

    @Throws(NullPointerException::class)
    fun getNextReminder(reminder: Reminder): Date? {
        if (!reminders!!.contains(reminder)) {
            throw NullPointerException()
        }
        return reminders!![reminders!!.indexOf(reminder)].nextOccurrence
    }

    fun getReminders(): List<Reminder>? {
        return reminders
    }

    fun addReminder(reminder: Reminder) {
        setNextReminder(reminder)
    }

    // TODO: export as JSON to local for later import
    fun exportJson() {
        throw UnsupportedOperationException()
    }

    companion object {
        @Throws(IOException::class)
        private fun loadJsonAssets(
            context: Context,
            jsonPath: String
        ): String {
            val json: String
            json = try {
                val stream = context.assets.open(jsonPath)
                val size = stream.available()
                val buffer = ByteArray(size)
                stream.read(buffer)
                stream.close()
                String(buffer, StandardCharsets.UTF_8)
            } catch (e: FileNotFoundException) {
                println("No existing JSON object")
                return ""
            }
            return json
        }

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
    }
}