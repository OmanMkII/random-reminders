package com.totallytim.randomreminders.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.totallytim.randomreminders.database.Reminder
import com.totallytim.randomreminders.database.ReminderDatabase
import com.totallytim.randomreminders.database.ReminderDatabaseDao
import com.totallytim.randomreminders.database.Setting

class MainViewModel(
        val dataSource: ReminderDatabaseDao,
        application: Application,
        val settings: List<Setting>,
        val reminders: List<Reminder>
) : ViewModel() {

    // TODO: implement view model (refer to tutorials)

//    private lateinit var database: ReminderDatabase



}