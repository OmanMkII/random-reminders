package com.totallytim.randomreminders.ui.main

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.totallytim.randomreminders.R
import com.totallytim.randomreminders.database.ReminderDatabase
import com.totallytim.randomreminders.database.ReminderDatabaseDao
import com.totallytim.randomreminders.databinding.MainFragmentBinding
import com.totallytim.randomreminders.modules.Schedule
import com.totallytim.randomreminders.populateDatabase
import com.totallytim.randomreminders.ui.reminders.ReminderAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

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

        binding = DataBindingUtil.inflate(inflater,
            R.layout.main_fragment, container, false)

        viewModelFactory = MainViewModelFactory(dataSource, application, binding)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        bindObjects()

        return binding.root
    }

    private fun bindObjects() {
        binding.reminderRecyclerView.adapter = ReminderAdapter()

        binding.newReminderButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_mainFragment_to_newReminderFragment)
        }

        binding.settingsButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_mainFragment_to_settingsFragment)
        }
    }

}