package com.totallytim.randomreminders.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.totallytim.randomreminders.database.Reminder
import com.totallytim.randomreminders.database.ReminderDatabaseDao
import com.totallytim.randomreminders.database.Setting

/**
 * View model factory of MainView
 */
class MainViewModelFactory(
            private val dataSource: ReminderDatabaseDao,
            private val application: Application
                ) : ViewModelProvider.Factory {

//    private lateinit var settings: LiveData<List<Setting>>
//    private lateinit var reminders: LiveData<List<Reminder>>

    private lateinit var settings: List<Setting>
    private lateinit var reminders: List<Reminder>

//    init {
//        settings = dataSource.getAllSettings()
//        reminders = dataSource.getAllReminders()
//    }
//    private var settings: List<Setting> = dataSource.getAllSettings()
//    private var reminders: List<Reminder> = dataSource.getAllReminders()

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {

            settings = dataSource.getAllSettings()
            reminders = dataSource.getAllReminders()

//            return MainViewModel(dataSource) as T
            return MainViewModel(dataSource, application, settings, reminders) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}