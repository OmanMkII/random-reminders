package com.totallytim.randomreminders.ui.main

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.totallytim.randomreminders.database.Reminder
import com.totallytim.randomreminders.database.ReminderDatabaseDao
import com.totallytim.randomreminders.databinding.MainFragmentBinding
import kotlinx.coroutines.*

class MainViewModel(
    val database: ReminderDatabaseDao,
    val application: Application,
    val binding: MainFragmentBinding
) : ViewModel() {

    private val viewModelJob = Job()
    private val mainScope = CoroutineScope(Dispatchers.Main + viewModelJob)

//    private val queryJob: Job
//    private val insertJob: Job

    var reminders: LiveData<Array<Reminder>> = MutableLiveData()
    var noReminders: Int = 0

    init {
        getAllReminders()
    }

    fun pollReminders() {
        mainScope.launch {
            noReminders = getNoRemindersFromDb()
        }
        // display num
        Toast.makeText(application.baseContext, "%d entries".format(noReminders),
                Toast.LENGTH_SHORT).show()
    }

    private suspend fun getNoRemindersFromDb(): Int {
        return database.getNumReminders()
    }

    private fun getAllReminders() {
        mainScope.launch {
            reminders = getAllRemindersFromDb()
        }
    }

    private suspend fun getAllRemindersFromDb(): LiveData<Array<Reminder>> {
        return database.getAllReminders()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}