package com.totallytim.randomreminders.ui.main

import android.app.Application
import androidx.lifecycle.ViewModel
import com.totallytim.randomreminders.database.ReminderDatabaseDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class MainViewModel(
        val dataSource: ReminderDatabaseDao,
        val application: Application
) : ViewModel() {

    // TODO: implement view model (refer to tutorials)

    private var viewModelJob = Job()
    private val mainScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}