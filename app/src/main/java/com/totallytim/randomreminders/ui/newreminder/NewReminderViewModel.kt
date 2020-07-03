package com.totallytim.randomreminders.ui.newreminder

import android.app.Application
import android.app.PendingIntent.getActivity
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.totallytim.randomreminders.database.Reminder
import com.totallytim.randomreminders.database.ReminderDatabaseDao
import com.totallytim.randomreminders.databinding.NewReminderFragmentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * The view model for a new reminder
 */
class NewReminderViewModel(
    val dataSource: ReminderDatabaseDao,
    val application: Application,
    val binding: NewReminderFragmentBinding
) : ViewModel() {

    var reminderName: MutableLiveData<String?> = MutableLiveData(null)
    var reminderFrequency: MutableLiveData<Long?> = MutableLiveData(null)
    var reminderVariance: MutableLiveData<Long?> = MutableLiveData(null)
    var reminderDescription: MutableLiveData<String?> = MutableLiveData(null)

    private val inputs: Set<MutableLiveData<out Any?>> = setOf(
        reminderName,
        reminderFrequency,
        reminderVariance,
        reminderDescription
    )

    private fun varsContainNull() : Boolean {
        for (i in inputs) {
            if (i.value == null) {
                return true
            }
        }
        return false
    }

    // scope of view
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private fun insertNewReminder() {
        uiScope.launch {
            val reminder = Reminder()
            reminder.setNextOccurrence()
            dataSource.insertNewReminder(reminder)
        }
    }

    private fun clearFields() {
        binding.fieldReminderName.text = null
        binding.fieldReminderFrequency.text = null
        binding.fieldReminderVariance.text = null
        binding.fieldReminderDescription.text = null
    }

    fun onFormCompleted() {
        if(varsContainNull()) {
            Toast.makeText(application.baseContext, "Please complete all fields", Toast.LENGTH_SHORT)
                .show()
        } else {
            insertNewReminder()
            clearFields()
        }
    }

}