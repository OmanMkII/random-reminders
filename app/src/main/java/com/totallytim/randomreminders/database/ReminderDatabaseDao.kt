package com.totallytim.randomreminders.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.totallytim.randomreminders.asMutableList
import kotlinx.coroutines.*

// TODO :: All queries that return a single instance should be called using a suspend fun
// TODO :: All queries that return a list should return LiveData<List> and NOT use a suspend fun

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
    suspend fun insertNewSetting(setting: Setting)

    /**
     * Updates an existing setting
     */
    @Update
    suspend fun updateExistingSetting(setting: Setting)

    /**
     * Selects all setting from the table
     */
    @Query("SELECT * FROM settings_table")
    fun getAllSettings(): LiveData<Array<Setting>>

    /**
     * Selects a specific setting from the data table
     */
    @Query("SELECT * FROM settings_table WHERE field_name = :key")
    suspend fun getSetting(key: String): Setting?


    /** Day Queries */

    /**
     * Inserts a new Reminder object.
     */
    @Insert
    suspend fun insertNewDay(day: Day)

    /**
     * updates an existing reminder object.
     */
    @Update
    suspend fun updateExistingDay(day: Day)

    /**
     * Get a specific Day
     */
    @Query("SELECT * FROM daily_schedule WHERE day_name = :key")
    suspend fun getDay(key: String): Day

    /**
     * Gets all Days from the database
     */
    @Query("SELECT * FROM daily_schedule")
    fun getAllDays(): LiveData<Array<Day>>


    /** Reminder Queries */

    /**
     * Inserts a new Reminder object.
     */
    @Insert
    suspend fun insertNewReminder(reminder: Reminder)

    /**
     * updates an existing reminder object.
     */
    @Update
    suspend fun updateExistingReminder(reminder: Reminder)

    /**
     * Clears an existing entry from the database.
     */
    @Query("DELETE FROM reminders_table WHERE reminder_name = :key")
    suspend fun deleteReminder(key: String)

    /**
     * Clears all existing entries from the database.
     */
    @Query("DELETE FROM reminders_table")
    suspend fun deleteAllReminders()

    /**
     * Selects an existing entry given the name.
     */
    @Query("SELECT * FROM reminders_table WHERE reminder_name = :key")
    suspend fun getReminder(key: String): Reminder?

    /**
     * Selects all existing entries, ordered by the next occurrence.
     */
    @Query("SELECT * FROM reminders_table ORDER BY reminder_name DESC")
    fun getAllReminders(): LiveData<Array<Reminder>>

    @Query("SELECT COUNT(*) FROM reminders_table")
    suspend fun getNumReminders(): Int

    /**
     * Selects all existing entries, ordered by the next occurrence.
     */
    @Query("SELECT * FROM reminders_table WHERE next_occurrence IS NOT NULL ORDER BY reminder_name DESC")
    fun getAllRemindersWithEvents(): LiveData<Array<Reminder>>

    /**
     * Selects all existing entries and orders by next occurring, with a variable limit on volume.
     */
    @Query("SELECT * FROM reminders_table WHERE next_occurrence IS NOT NULL ORDER BY -next_occurrence DESC LIMIT :lim")
    fun getNextReminder(lim: Int): LiveData<Array<Reminder>?>
}

/**
 * Inserts a set of Reminder objects to the database.
 *
 * @param database  The local database instance
 * @param reminders The set of reminders to insert
 * @return a list of deferred jobs to await for all items to be inserted
 */
suspend fun insertSetOfNewReminders(database: ReminderDatabase, reminders: Set<Reminder>):
        List<Deferred<Unit>> {
    return withContext(Dispatchers.IO) {
        val asyncReminders: MutableList<Deferred<Unit>> = mutableListOf()
        for (rem in reminders) {
            val job = GlobalScope.async { database.reminderDatabaseDao.insertNewReminder(rem) }
            asyncReminders.add(job)
        }
        asyncReminders
    }
}

/**
 * Updates a set of Reminders.
 *
 * @param database  The local database instance
 * @param reminders The set of reminders to insert
 * @return a list of deferred jobs to await for all items to be updated
 */
suspend fun updateSetOfReminders(database: ReminderDatabase, reminders: Set<Reminder>):
        List<Deferred<Unit>> {
    return withContext(Dispatchers.IO) {
        val asyncReminders: MutableList<Deferred<Unit>> = mutableListOf()
        for (rem in reminders) {
            val job = GlobalScope.async { database.reminderDatabaseDao.updateExistingReminder(rem) }
            asyncReminders.add(job)
        }
        asyncReminders
    }
}