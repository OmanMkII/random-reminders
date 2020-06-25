package com.totallytim.randomreminders.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.totallytim.randomreminders.R
import com.totallytim.randomreminders.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding

    private lateinit var viewModelFactory: SettingsViewModelFactory
    private lateinit var viewModel: SettingsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
                ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)

//        binding.confirmSettingsButton.setOnClickListener { view : View ->
//            view.findNavController().navigate(R.id.action_settingsFragment_to_mainFragment)
//        }

        // ScoreFragmentArgs.fromBundle(arguments!!).score
//        viewModelFactory = SettingsViewModelFactory(SettingsFragmentArgs.fromBundle(requireArguments()))
//        viewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)



        return binding.root
    }
}