package com.totallytim.randomreminders.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "reminders_table")
data class Reminder(
    @PrimaryKey(autoGenerate = true)
    var keyID: Long = 0L,

    @ColumnInfo(name = "reminder_name")
    val name: String = "",

    @ColumnInfo(name = "frequency")
    var frequency: Long = 0L,

    @ColumnInfo(name = "next_occurrence")
    var nextOccurrence: Calendar = Calendar.getInstance())