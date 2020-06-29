package com.totallytim.randomreminders.newreminder

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.totallytim.randomreminders.R
import com.totallytim.randomreminders.database.ReminderDatabase
import com.totallytim.randomreminders.database.ReminderDatabaseDao
import com.totallytim.randomreminders.databinding.NewReminderFragmentBinding
import com.totallytim.randomreminders.settings.SettingsViewModel
import com.totallytim.randomreminders.settings.SettingsViewModelFactory

/**
 * A fragment representing the creating a new reminder object.
 */
class NewReminderFragment : Fragment() {

    // application
    private lateinit var application: Application
    private lateinit var dataSource: ReminderDatabaseDao

    // binding object
    private lateinit var binding: NewReminderFragmentBinding

    // view model objects
    private lateinit var viewModel: NewReminderViewModel
    private lateinit var viewModelFactory: NewReminderViewModelFactory

    override fun onCreateView(
                inflater: LayoutInflater,
                container: ViewGroup?,
                savedInstanceState: Bundle?
            ): View? {

        application = requireNotNull(this.activity).application
        dataSource = ReminderDatabase.getInstance(application).reminderDatabaseDao

        viewModelFactory = NewReminderViewModelFactory(dataSource, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(NewReminderViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.new_reminder_fragment, container, false)

        binding.newReminderConfirmButton.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_newReminderFragment_to_mainFragment)
        }

        return binding.root
    }
}