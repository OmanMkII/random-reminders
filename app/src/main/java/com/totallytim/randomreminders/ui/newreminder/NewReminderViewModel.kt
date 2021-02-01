package com.totallytim.randomreminders.ui.newreminder

import android.app.Application
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.totallytim.randomreminders.database.Reminder
import com.totallytim.randomreminders.database.ReminderDatabaseDao
import com.totallytim.randomreminders.databinding.NewReminderFragmentBinding
import kotlinx.coroutines.*

/**
 * The view model for a new reminder
 */
class NewReminderViewModel(
    private val database: ReminderDatabaseDao,
    val application: Application,
    val binding: NewReminderFragmentBinding
) : ViewModel() {

    private var viewModelJob = Job()
    private val newReminderScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    // TODO: does it already exist?
    init {
    clearFields()
//        newReminderScope.launch {
//            val reminder = database.getReminder()
//        }
    }

    fun fieldsContainNull(): Boolean {
        // return true is a required field is empty
        return TextUtils.isEmpty(binding.fieldReminderName.text) ||
                TextUtils.isEmpty(binding.fieldReminderFrequency.text) ||
                TextUtils.isEmpty(binding.fieldReminderVariance.text)
    }

    private suspend fun insertNewReminder() {
        val reminder = Reminder()
        reminder.name = binding.fieldReminderName.text.toString()
        reminder.frequency = binding.fieldReminderFrequency.text.toString().toLong()
        reminder.variance = binding.fieldReminderVariance.text.toString().toFloat()
        reminder.description = binding.fieldReminderDescription.text.toString()
        // insert or throw
        if (fieldsContainNull())
            Toast.makeText(application.baseContext, "Please enter a Name, Frequency, and Variance.",
                Toast.LENGTH_SHORT).show()
        else
            database.insertNewReminder(reminder)
    }

    private fun clearFields() {
        binding.fieldReminderName.text = null
        binding.fieldReminderFrequency.text = null
        binding.fieldReminderVariance.text = null
        binding.fieldReminderDescription.text = null
    }

    fun onFormCompleted() {
        newReminderScope.launch {
            insertNewReminder()
            clearFields()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}