package com.totallytim.randomreminders.ui.settings

import android.app.Application
import android.util.Log
import android.util.Log.INFO
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.totallytim.randomreminders.database.ReminderDatabaseDao
import com.totallytim.randomreminders.database.Setting
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.logging.Level.INFO

/**
 * Setting view model
 */
class SettingsViewModel(
    private val database: ReminderDatabaseDao,
    private val application: Application
) : ViewModel() {

    private var viewModelJob = Job()
    private val settingsScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var testMode: Setting? = null
    private var allowNotifications: Setting? = null

    init {
        getTestMode()
        getNotificationsAllowed()
    }

    fun getTestMode(): Boolean {
        var result = false
        settingsScope.launch {
            testMode = database.getSetting("test_mode");
            if (testMode == null) {
                Timber.e("Field not found!")
                testMode = Setting("test_mode", false, "")
//                database.insertNewSetting(testMode!!)
                result = false
            } else {
                result = testMode!!.dataType
            }
        }
        return result
    }

    fun setTestMode() {
        settingsScope.launch {
            testMode?.let { database.updateExistingSetting(it) }
        }
    }

    fun getNotificationsAllowed(): Boolean {
        var result = false
        settingsScope.launch {
            allowNotifications = database.getSetting("allow_notifications");
            if (allowNotifications == null) {
                Timber.e("Field not found!")
                allowNotifications = Setting("allow_notifications", false, "")
//                database.insertNewSetting(allowNotifications!!)
                result = false
            } else {
                result = allowNotifications!!.dataType
            }
        }
        return result
    }

    fun setNotificationsAllowed() {
        settingsScope.launch {
            allowNotifications?.let { database.updateExistingSetting(it) }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}