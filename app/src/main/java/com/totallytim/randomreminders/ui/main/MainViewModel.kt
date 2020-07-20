package com.totallytim.randomreminders.ui.main

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.totallytim.randomreminders.database.Reminder
import com.totallytim.randomreminders.database.ReminderDatabase
import com.totallytim.randomreminders.database.ReminderDatabaseDao
import com.totallytim.randomreminders.databinding.MainFragmentBinding
import com.totallytim.randomreminders.populateDatabase
import kotlinx.coroutines.*

class MainViewModel(
    val dataSource: ReminderDatabaseDao,
    val application: Application,
    val binding: MainFragmentBinding
) : ViewModel() {

    private val viewModelJob = Job()
    private val mainScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    var reminders: LiveData<Array<Reminder>> = MutableLiveData<Array<Reminder>>()

    init {
        // launch the local job
        mainScope.launch {
            // TODO: populate database for now
            populateDatabase(ReminderDatabase.getInstance(application))

            // run all relevant functions (including async/suspended ones)
            reminders = getAllReminders()

            delay(2000)
            if (reminders.value == null) {
                Toast.makeText(application.baseContext, "Null entry!",
                    Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(application.baseContext, "Have " + reminders.value!!.size + " entries",
                    Toast.LENGTH_LONG).show()
            }
        }
    }

    private suspend fun getAllReminders(): LiveData<Array<Reminder>> {
        // run a function with the context of Dispatchers.IO
        // it launches a new coroutine (thread) for you!
        return withContext(Dispatchers.IO) {
            dataSource.getAllReminders()
        }
    }

    fun getReminder(name: String) {
        mainScope.launch {
            // return once completed (I think)
            return@launch getOneReminder(name)
        }
    }

    private suspend fun getOneReminder(name: String) {
        withContext(Dispatchers.IO) {
            // alternate way to return from within the function
            // it has no specific type, from what I understand
            return@withContext dataSource.getReminder(name)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}