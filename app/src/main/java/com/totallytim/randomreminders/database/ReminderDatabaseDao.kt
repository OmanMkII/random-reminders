package com.totallytim.randomreminders.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ReminderDatabaseDao {

    @Insert
    fun insert(reminder: Reminder)

    @Update
    fun update(reminder: Reminder)

    @Query("DELETE FROM reminders_table WHERE reminder_name = :key")
    fun clearEntry(key: String)

    @Query("DELETE FROM reminders_table")
    fun clearAll()

    @Query("SELECT * FROM reminders_table WHERE reminder_name = :key")
    fun get(key: String): LiveData<Reminder>

    @Query("SELECT * FROM reminders_table ORDER BY next_occurrence DESC")
    fun getAll(): LiveData<List<Reminder>>

    @Query("SELECT * FROM reminders_table ORDER BY next_occurrence LIMIT :num")
    fun getNext(num: Int): LiveData<Reminder?>
//    fun getNext(): Reminder? // might need nullable entry?

}