package com.totallytim.randomreminders.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.totallytim.randomreminders.database.Setting

/**
 * Factory for a new settings view model
 */
class SettingsViewModelFactory(
        private val currentSettings: Array<String>
            ) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
            return SettingsViewModel(currentSettings) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}