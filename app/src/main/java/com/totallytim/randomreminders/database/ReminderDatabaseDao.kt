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
    // TODO: make method to set all setting fields to a default value

    /** Settings Queries */

    /**
     * Inserts a new Setting to the data table
     */
    @Insert
    fun insertNewSetting(setting: Setting)

    /**
     * Updates an existing setting
     */
    @Update
    fun updateExistingSetting(setting: Setting)

//    /**
//     * Selects all setting from the table
//     */
    @Query("SELECT * FROM settings_table")
    fun getAllSettings(): LiveData<List<Setting>>
//
//    /**
//     * Selects a specific setting from the data table
//     */
//    @Query("SELECT * FROM settings_table WHERE field_name = :key")
//    fun getSetting(key: String): LiveData<Setting?>


    /** Day Queries */

    /**
     * Inserts a new Reminder object.
     */
    @Insert
    fun insertNewDay(day: Day)

    /**
     * updates an existing reminder object.
     */
    @Update
    fun updateExistingDay(day: Day)

//    /**
//     * Get a specific Day
//     */
//    @Query("SELECT * FROM daily_schedule WHERE day_name = :key")
//    fun getDay(key: String): LiveData<Day>
//
//    /**
//     * Gets all Days from the database
//     */
//    @Query("SELECT * FROM daily_schedule")
//    fun getAllDays(): LiveData<List<Day>>


    /** Reminder Queries */

    /**
     * Inserts a new Reminder object.
     */
    @Insert
    fun insertNewReminder(reminder: Reminder)

    /**
     * updates an existing reminder object.
     */
    @Update
    fun updateExistingReminder(reminder: Reminder)

//    /**
//     * Clears an existing entry from the database.
//     */
//    @Query("DELETE FROM reminders_table WHERE reminder_name = :key")
//    fun deleteReminder(key: String)
//
//    /**
//     * Clears all existing entries from the database.
//     */
//    @Query("DELETE FROM reminders_table")
//    fun deleteAllReminders()
//
//    /**
//     * Selects an existing entry given the name.
//     */
//    @Query("SELECT * FROM reminders_table WHERE reminder_name = :key")
//    fun getReminder(key: String): LiveData<Reminder?>
//
//    /**
//     * Selects all existing entries, ordered by the next occurrence.
//     */
    @Query("SELECT * FROM reminders_table ORDER BY next_occurrence DESC")
    fun getAllReminders(): LiveData<List<Reminder>>
//
//    /**
//     * Selects all existing extries and orders by next occurring, with a variable limit on volume.
//     */
//    @Query("SELECT * FROM reminders_table ORDER BY next_occurrence DESC LIMIT :num")
//    fun getNextReminder(num: Int): LiveData<List<Reminder>?>
}