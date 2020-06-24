package com.totallytim.randomreminders.newreminder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.totallytim.randomreminders.R
import com.totallytim.randomreminders.databinding.FragmentNewReminderBinding

/**
 * A fragment representing the creating a new reminder object.
 */
class NewReminderFragment : Fragment() {

    // binding object
    private lateinit var binding: FragmentNewReminderBinding

    // view model objects
    private lateinit var viewModel: NewReminderViewModel
    private lateinit var viewModelFactory: NewReminderViewModelFactory

    override fun onCreateView(
                inflater: LayoutInflater,
                container: ViewGroup?,
                savedInstanceState: Bundle?
            ): View? {

//        viewModel = ViewModelProvider(this).get(NewReminderViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_new_reminder, container, false)

        binding.newReminderConfirmButton.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_newReminderFragment_to_mainFragment)
        }

//        val view: View = inflater.inflate(R.layout.fragment_settings, container, false)
//        val newReminderFragment = view.findViewById(R.id.newReminderFragment) as EditText

        return binding.root
    }
}