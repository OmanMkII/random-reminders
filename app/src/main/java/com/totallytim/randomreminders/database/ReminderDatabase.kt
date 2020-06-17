package com.totallytim.randomreminders.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// TODO: documentation, and/or actually understand this a bit more
@Database(entities = [Reminder::class, Day::class], version = 1, exportSchema = false)
abstract class ReminderDatabase : RoomDatabase() {

    abstract val reminderDatabaseDao: ReminderDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: ReminderDatabase? = null

        fun getInstance(context: Context) : ReminderDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    Room.databaseBuilder(
                        context.applicationContext,
                        ReminderDatabase::class.java,
                        "reminder_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance!!
            }
        }
    }
}