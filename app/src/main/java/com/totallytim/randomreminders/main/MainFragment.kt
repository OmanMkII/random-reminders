package com.totallytim.randomreminders.main

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.totallytim.randomreminders.R
import com.totallytim.randomreminders.database.ReminderDatabase
import com.totallytim.randomreminders.database.ReminderDatabaseDao
import com.totallytim.randomreminders.databinding.MainFragmentBinding
import com.totallytim.randomreminders.reminders.ReminderAdapter

/**
 * The primary fragment of the activity that displays the central data.
 */
class MainFragment : Fragment() {

    // application
    private lateinit var application: Application
    private lateinit var dataSource: ReminderDatabaseDao

    // binding object
    private lateinit var binding: MainFragmentBinding

    // view models
    private lateinit var viewModel: MainViewModel
    private lateinit var viewModelFactory: MainViewModelFactory

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        // Show action bar
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()

        application = requireNotNull(this.activity).application
        dataSource = ReminderDatabase.getInstance(application).reminderDatabaseDao

        viewModelFactory = MainViewModelFactory(dataSource, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater,
            R.layout.main_fragment, container, false)

        binding.reminderRecyclerView.adapter = ReminderAdapter()

        binding.newReminderButton.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_mainFragment_to_newReminderFragment)
        }

        binding.settingsButton.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_mainFragment_to_settingsFragment)
        }

        return binding.root
    }
}