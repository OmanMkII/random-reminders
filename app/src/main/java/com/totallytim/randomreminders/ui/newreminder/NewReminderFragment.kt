package com.totallytim.randomreminders.ui.newreminder

import android.app.Application
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
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
        viewModel = ViewModelProvider(this, viewModelFactory)
                .get(NewReminderViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        // binding
        binding.newReminderConfirmButton.setOnClickListener { view : View ->
            viewModel.onFormCompleted()
//            view.findNavController().navigate(R.id.action_newReminderFragment_to_mainFragment)
        }

        bindTextFields()

        return binding.root
    }

    private fun bindTextFields() {
        binding.fieldReminderName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.reminderName.value = s.toString()
            }

            // Do nothing for these?
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.fieldReminderDescription.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.reminderDescription.value = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.fieldReminderVariance.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.reminderVariance.value = s.toString().toLong()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.fieldReminderFrequency.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.reminderFrequency.value = s.toString().toLong()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }
}