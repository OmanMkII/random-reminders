package com.totallytim.randomreminders.ui.main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.totallytim.randomreminders.database.ReminderDatabaseDao
import com.totallytim.randomreminders.databinding.MainFragmentBinding

/**
 * View model factory of MainView
 */
class MainViewModelFactory(
    private val dataSource: ReminderDatabaseDao,
    private val application: Application,
    private val binding: MainFragmentBinding
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(dataSource, application, binding) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}