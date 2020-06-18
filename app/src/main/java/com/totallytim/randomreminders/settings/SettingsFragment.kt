package com.totallytim.randomreminders.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.totallytim.randomreminders.R
import com.totallytim.randomreminders.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private lateinit var viewModel: SettingsViewModel

    override fun onCreateView(
                inflater: LayoutInflater,
                container: ViewGroup?,
                savedInstanceState: Bundle?
            ): View? {

//        viewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)

        val binding = DataBindingUtil.inflate<FragmentSettingsBinding>(
            inflater, R.layout.fragment_settings, container, false)

        binding.confirmSettingsButton.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_settingsFragment_to_mainFragment)
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

}