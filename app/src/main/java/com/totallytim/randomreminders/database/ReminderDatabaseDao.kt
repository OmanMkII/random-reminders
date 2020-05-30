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

    /**
     * Inserts a new Reminder object.
     */
    @Insert
    fun insert(reminder: Reminder)

    /**
     * updates an existing reminder object.
     */
    @Update
    fun update(reminder: Reminder)

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
    fun getNext(num: Int): LiveData<Reminder?>
    // TODO: why did the tutorial only take a nullable object instead of a nullable LiveData entry?
//    fun getNext(): Reminder? // might need nullable entry?

}