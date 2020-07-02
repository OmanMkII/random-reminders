package com.totallytim.randomreminders.ui.newreminder

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.totallytim.randomreminders.R
import com.totallytim.randomreminders.database.ReminderDatabase
import com.totallytim.randomreminders.database.ReminderDatabaseDao
import com.totallytim.randomreminders.databinding.NewReminderFragmentBinding
import kotlinx.coroutines.*

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

        binding = DataBindingUtil.inflate(inflater, R.layout.new_reminder_fragment, container, false)

        viewModelFactory = NewReminderViewModelFactory(dataSource, application, binding)
        viewModel = ViewModelProvider(this, viewModelFactory).get(NewReminderViewModel::class.java)

        // binding
        binding.newReminderConfirmButton.setOnClickListener { view : View ->
            viewModel.onFormCompleted()
            view.findNavController().navigate(R.id.action_newReminderFragment_to_mainFragment)
        }

        // observers
        viewModel.reminderName.observe(viewLifecycleOwner, Observer {
            name -> viewModel.reminderName.value = name
        })

        viewModel.reminderDescription.observe(viewLifecycleOwner, Observer {
            description -> viewModel.reminderDescription.value = description
        })

        viewModel.reminderFrequency.observe(viewLifecycleOwner, Observer {
            frequency -> viewModel.reminderFrequency.value = frequency.toLong()
        })

        viewModel.reminderVariance.observe(viewLifecycleOwner, Observer {
            variance -> viewModel.reminderVariance.value = variance.toLong()
        })

        return binding.root
    }
}