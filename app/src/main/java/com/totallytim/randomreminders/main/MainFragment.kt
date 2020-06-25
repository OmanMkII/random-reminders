package com.totallytim.randomreminders.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.totallytim.randomreminders.R
import com.totallytim.randomreminders.databinding.MainFragmentBinding

/**
 * The primary fragment of the activity that displays the central data.
 */
class MainFragment : Fragment() {

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

//        viewModelFactory = MainViewModelFactory()
//        viewModelFactory = SettingsViewModelFactory(SettingsFragmentArgs.fromBundle)
//        viewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)


        binding = DataBindingUtil.inflate<MainFragmentBinding>(inflater,
                R.layout.main_fragment, container, false)

        binding.newReminderButton.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_mainFragment_to_newReminderFragment)
        }

        binding.settingsButton.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_mainFragment_to_settingsFragment)
        }

        return binding.root
    }
}