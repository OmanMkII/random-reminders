package com.totallytim.randomreminders.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.totallytim.randomreminders.database.Setting

/**
 * Setting view model
 */
class SettingsViewModel(
    _settings: LiveData<List<Setting>>) : ViewModel() {

    // TODO: implement view model (refer to tutorials)

    /**
     * The list of settings within this program; stored locally for use while running and backed up
     * to the database for retrieval when the app closes and re-launches.
     */
    private lateinit var _settings: MutableLiveData<List<Setting>>
    val settings: LiveData<List<Setting>>
        get() = _settings

    /**
     * Stores the current state of the view
     */
    private var _settingsComplete: Boolean = false

    /**
     * Executed when the 'confirm' button is tapped to leave the settings page.
     */
    fun onConfirmSettings() {
        // TODO: set database settings
        backupAllSettings()
        // TODO: save settings locally
        saveAllSettings()
        // TODO: navigate (up one level)
        _settingsComplete = true
    }

    fun restoreAllSettings() {
        // TODO loop through all settings, set all
        // TODO do this at start?
    }

    fun saveAllSettings() {
        // TODO save all to local
    }

    fun backupAllSettings() {
        // TODO back up to db
    }

    fun onSettingsComplete() {
        // TODO: is this necessary?
        _settingsComplete = false
    }
}