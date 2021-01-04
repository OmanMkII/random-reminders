package com.totallytim.randomreminders.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// TODO: documentation, and/or actually understand this a bit more
@Database(entities = [Reminder::class, Day::class, Setting::class], version = 5, exportSchema = false)
abstract class ReminderDatabase : RoomDatabase() {

    // the database instance
    abstract val reminderDatabaseDao: ReminderDatabaseDao

    /**
     * The database companion object: returns the existing object or instantiates when called upon.
     */
    companion object {

        var TEST_MODE = false

        @Volatile
        private var INSTANCE: ReminderDatabase? = null

        /**
         * Gets the instance of the database that exists internally and assigns it to the local
         * instance variable for simpler retrieval in future.
         *
         * @return the instance database
         */
        fun getInstance(context: Context) : ReminderDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    if (TEST_MODE) {
                        instance = Room.inMemoryDatabaseBuilder(
                            context.applicationContext,
                            ReminderDatabase::class.java
                        )
                            .allowMainThreadQueries()
                            .build()
                    } else {
                        instance = Room.databaseBuilder(
                            context.applicationContext,
                            ReminderDatabase::class.java,
                            "reminder_database"
                        )
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}