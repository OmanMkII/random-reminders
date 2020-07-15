package com.totallytim.randomreminders.ui.newreminder

import android.app.Application
import android.app.PendingIntent.getActivity
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.totallytim.randomreminders.database.Reminder
import com.totallytim.randomreminders.database.ReminderDatabaseDao
import com.totallytim.randomreminders.databinding.NewReminderFragmentBinding
import kotlinx.coroutines.*

/**
 * The view model for a new reminder
 */
class NewReminderViewModel(
    private val dataSource: ReminderDatabaseDao,
    val application: Application,
    val binding: NewReminderFragmentBinding
) : ViewModel() {

    var reminderName: MutableLiveData<String?> = MutableLiveData(null)
    var reminderFrequency: MutableLiveData<Long?> = MutableLiveData(null)
    var reminderVariance: MutableLiveData<Long?> = MutableLiveData(null)
    var reminderDescription: MutableLiveData<String?> = MutableLiveData(null)

    private val inputSet: Set<MutableLiveData<out Any?>> = setOf(
        reminderName,
        reminderFrequency,
        reminderVariance,
        reminderDescription
    )

    private var viewModelJob = Job()
    private val newReminderScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun fieldsContainNull() : Boolean {
        for (i in inputSet) {
            if (i.value == null && i != reminderDescription) {
                return true
            }
        }
        return false
    }

    private suspend fun insertNewReminder() {
        return withContext(Dispatchers.IO) {
            val reminder = Reminder()
//            reminder.setNextOccurrence()
            dataSource.insertNewReminder(reminder)
        }
    }

    private fun clearFields() {
        // TODO: clear fields when transitioning to || from
//        binding.fieldReminderName.text = null
//        binding.fieldReminderFrequency.text = null
//        binding.fieldReminderVariance.text = null
//        binding.fieldReminderDescription.text = null
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