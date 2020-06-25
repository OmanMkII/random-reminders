package com.totallytim.randomreminders.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

/**
 * The main database of all reminder objects.
 */
@Dao
interface ReminderDatabaseDao {

    // TODO: ensure I can grab all the proper fields
    // TODO: make queries to grab all settings, certain setting
    // TODO: make method to set all setting fields to a default value

    /**
     * Inserts a new Reminder object.
     */
    @Insert
    fun insertDay(day: Day)

    /**
     * updates an existing reminder object.
     */
    @Update
    fun updateDay(day: Day)

    /**
     * Inserts a new Reminder object.
     */
    @Insert
    fun insertReminder(reminder: Reminder)

    /**
     * updates an existing reminder object.
     */
    @Update
    fun updateReminder(reminder: Reminder)

    /**
     * Clears an existing entry from the database.
     */
    @Query("DELETE FROM reminders_table WHERE reminder_name = :key")
    fun clearEntry(key: String)

    /**
     * Clears all existing entries from the database.
     */
    @Query("DELETE FROM reminders_table")
    fun clearAll()

    /**
     * Selects an existing entry given the name.
     */
    @Query("SELECT * FROM reminders_table WHERE reminder_name = :key")
    fun get(key: String): LiveData<Reminder>

    /**
     * Selects all existing entries, ordered by the next occurrence.
     */
    @Query("SELECT * FROM reminders_table ORDER BY next_occurrence DESC")
    fun getAll(): LiveData<List<Reminder>>

    /**
     * Selects all existing extries and orders by next occurring, with a variable limit on volume.
     */
    @Query("SELECT * FROM reminders_table ORDER BY next_occurrence DESC LIMIT :num")
    fun getNext(num: Int): LiveData<List<Reminder>?>
}