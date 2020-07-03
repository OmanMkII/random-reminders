package com.totallytim.randomreminders.ui.main

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.totallytim.randomreminders.database.Reminder
import com.totallytim.randomreminders.database.ReminderDatabaseDao
import com.totallytim.randomreminders.databinding.MainFragmentBinding
import com.totallytim.randomreminders.databinding.NewReminderFragmentBinding
import kotlinx.coroutines.*

class MainViewModel(
        val dataSource: ReminderDatabaseDao,
        val application: Application,
        val binding: MainFragmentBinding
) : ViewModel() {

    // TODO: implement view model (refer to tutorials)

    private val viewModelJob = Job()
    private val mainScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    var reminders: LiveData<Array<Reminder>> = MutableLiveData<Array<Reminder>>()

    init {
        refreshReminderData()
    }

    private suspend fun getAllReminders(): LiveData<Array<Reminder>> {
        return withContext(Dispatchers.IO) {
            dataSource.getAllReminders()
        }
    }

    fun refreshReminderData() {
        mainScope.launch {
            reminders = getAllReminders()
        }
    }

    private suspend fun getOneReminder(name: String): LiveData<Reminder?> {
        return withContext(Dispatchers.IO) {
            dataSource.getReminder(name)
        }
    }

    fun retrieveReminderData(name: String): LiveData<Reminder?> {
        var reminder: LiveData<Reminder?> = MutableLiveData(null)
        mainScope.launch {
            reminder = getOneReminder(name)
        }
        return reminder
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}