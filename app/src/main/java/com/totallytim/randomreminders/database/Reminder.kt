package com.totallytim.randomreminders.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.totallytim.randomreminders.fromCalendar
import java.util.*

/**
 * The Reminder database: a collection of the user's reminders that hold information about what the
 * reminder is, as well as the next occurrence of it and the frequency of it.
 */
@Entity(tableName = "reminders_table")
data class Reminder(
        /**
         * The generated primary key.
         */
        @PrimaryKey(autoGenerate = true)
        var keyID: Long,

        /**
         * The name of this reminder (non-unique).
         */
        @ColumnInfo(name = "reminder_name")
        val name: String = "",

        /**
         * The frequency with which this reminder will recur.
         */
        @ColumnInfo(name = "frequency")
        var frequency: Long = 0L,

        /**
         * The variance from the mean at which this occurs; that is:
         *
         * NEXT_OCCURRENCE = NOW + T(frequency +/- Range(variance))
         */
        @ColumnInfo(name = "variance")
        var variance: Long = 0L,

        /**
         * The next time this reminder instance will occur.
         */
        @ColumnInfo(name = "next_occurrence")
        var nextOccurrence: Long? = fromCalendar(Calendar.getInstance()),

        /**
         * Additional information about this reminder that will be presented when triggered.
         */
        @ColumnInfo(name = "description")
        var description: String? = null)