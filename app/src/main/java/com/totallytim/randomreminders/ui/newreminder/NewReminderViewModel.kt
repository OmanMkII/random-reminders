package com.totallytim.randomreminders.ui.newreminder

import android.app.Application
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.totallytim.randomreminders.database.Reminder
import com.totallytim.randomreminders.database.ReminderDatabaseDao
import com.totallytim.randomreminders.databinding.NewReminderFragmentBinding

/**
 * The view model for a new reminder
 */
class NewReminderViewModel(
    val dataSource: ReminderDatabaseDao,
    val application: Application,
    val binding: NewReminderFragmentBinding
) : ViewModel() {

    var reminderName: MutableLiveData<String> = MutableLiveData("")
    var reminderFrequency: MutableLiveData<Long> = MutableLiveData(0L)
    var reminderVariance: MutableLiveData<Long> = MutableLiveData(0L)
    var reminderDescription: MutableLiveData<String> = MutableLiveData("")

    // TODO: implement view model (refer to tutorials)

    fun insertNewReminder() {
        val reminder = Reminder()

        reminder.name = binding.fieldReminderName.toString()
        reminder.frequency = binding.fieldReminderFrequency.toString().toLong()
        reminder.variance = binding.fieldReminderVariance.toString().toLong()
        reminder.description = binding.fieldReminderDescription.toString()

        reminder.setNextOccurrence()

        dataSource.insertNewReminder(reminder)
    }

    fun clearFields() {
        binding.fieldReminderName.text = null
        binding.fieldReminderFrequency.text = null
        binding.fieldReminderVariance.text = null
        binding.fieldReminderDescription.text = null
    }

    fun onFormCompleted() {
        insertNewReminder()
        clearFields()
    }

}