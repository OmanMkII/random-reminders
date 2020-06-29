package com.totallytim.randomreminders.ui.settings

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
import com.totallytim.randomreminders.databinding.SettingsFragmentBinding

class SettingsFragment : Fragment() {

    // application
    private lateinit var application: Application
    private lateinit var dataSource: ReminderDatabaseDao

    // binding object
    private lateinit var binding: SettingsFragmentBinding

    // view objects
    private lateinit var viewModelFactory: SettingsViewModelFactory
    private lateinit var viewModel: SettingsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        application = requireNotNull(this.activity).application
        dataSource = ReminderDatabase.getInstance(application).reminderDatabaseDao

        viewModelFactory = SettingsViewModelFactory(dataSource, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SettingsViewModel::class.java)

        // Inflate view and obtain an instance of the binding class.
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.settings_fragment,
            container,
            false
        )

        binding.confirmSettingsButton.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_settingsFragment_to_mainFragment)
        }

        // ScoreFragmentArgs.fromBundle(arguments!!).score



        return binding.root
    }
}