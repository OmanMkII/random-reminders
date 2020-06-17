package com.totallytim.randomreminders.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
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
    var keyID: Long = 0L,

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
     * The next time this reminder instance will occur.
     */
    @ColumnInfo(name = "next_occurrence")
    var nextOccurrence: String = Calendar.getInstance().toString(),

    /**
     * Additional information about this reminder that will be presented when triggered.
     */
    @ColumnInfo(name = "description")
    var description: String = "")