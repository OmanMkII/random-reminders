package com.totallytim.randomreminders.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.totallytim.randomreminders.asMutableList
import kotlinx.coroutines.*

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

    /**
     * Selects all setting from the table
     */
    @Query("SELECT * FROM settings_table")
    fun getAllSettings(): LiveData<Array<Setting>>

    /**
     * Selects a specific setting from the data table
     */
    @Query("SELECT * FROM settings_table WHERE field_name = :key")
    fun getSetting(key: String): LiveData<Setting?>


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

    /**
     * Get a specific Day
     */
    @Query("SELECT * FROM daily_schedule WHERE day_name = :key")
    fun getDay(key: String): LiveData<Day>

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
    fun insertNewReminder(reminder: Reminder)

    /**
     * updates an existing reminder object.
     */
    @Update
    fun updateExistingReminder(reminder: Reminder)

    /**
     * Clears an existing entry from the database.
     */
    @Query("DELETE FROM reminders_table WHERE reminder_name = :key")
    fun deleteReminder(key: String)

    /**
     * Clears all existing entries from the database.
     */
    @Query("DELETE FROM reminders_table")
    fun deleteAllReminders()

    /**
     * Selects an existing entry given the name.
     */
    @Query("SELECT * FROM reminders_table WHERE reminder_name = :key")
    fun getReminder(key: String): LiveData<Reminder?>

    /**
     * Selects all existing entries, ordered by the next occurrence.
     */
    @Query("SELECT * FROM reminders_table ORDER BY reminder_name DESC")
    fun getAllReminders(): LiveData<Array<Reminder>>

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