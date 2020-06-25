package com.totallytim.randomreminders.main

import android.app.Application
import androidx.lifecycle.ViewModel
import com.totallytim.randomreminders.database.ReminderDatabase
import com.totallytim.randomreminders.database.ReminderDatabaseDao

class MainViewModel(
        dataSource: ReminderDatabaseDao
            ) : ViewModel() {

    // TODO: implement view model (refer to tutorials)

    private lateinit var database: ReminderDatabase

}