package com.totallytim.randomreminders.ui.newreminder

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.totallytim.randomreminders.database.ReminderDatabaseDao

/**
 * Factory for a new reminder view model
 */
class NewReminderViewModelFactory(
        private val dataSource: ReminderDatabaseDao,
        private val application: Application
            ) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewReminderViewModel::class.java)) {
            return NewReminderViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}