package com.totallytim.randomreminders.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.totallytim.randomreminders.database.Reminder
import com.totallytim.randomreminders.database.ReminderDatabase
import com.totallytim.randomreminders.database.ReminderDatabaseDao
import com.totallytim.randomreminders.database.Setting
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class MainViewModel(
        val dataSource: ReminderDatabaseDao,
        application: Application
//        val settings: LiveData<List<Setting>>,
//        val reminders: LiveData<List<Reminder>>
) : ViewModel() {

    // TODO: implement view model (refer to tutorials)

//    private lateinit var database: ReminderDatabase

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}