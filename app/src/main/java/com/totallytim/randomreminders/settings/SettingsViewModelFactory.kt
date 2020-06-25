package com.totallytim.randomreminders.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.totallytim.randomreminders.database.Setting

/**
 * Factory for a new settings view model
 */
class SettingsViewModelFactory(
        private val settings: LiveData<List<Setting>>
            ) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
            return SettingsViewModel(settings) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}