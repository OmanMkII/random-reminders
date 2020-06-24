package com.totallytim.randomreminders.main

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
import com.totallytim.randomreminders.databinding.FragmentMainBinding
import com.totallytim.randomreminders.newreminder.NewReminderViewModel

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
//    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
                inflater: LayoutInflater,
                container: ViewGroup?,
                savedInstanceState: Bundle?
            ): View? {

        (activity as AppCompatActivity?)!!.supportActionBar!!.show()

//        viewModel = ViewModelProvider(this).get(NewReminderViewModel::class.java)
        binding = DataBindingUtil.inflate<FragmentMainBinding>(inflater,
                R.layout.fragment_main, container, false)

//        val view: View = inflater.inflate(R.layout.fragment_settings, container, false)
//        val newReminderFragment = view.findViewById(R.id.newReminderFragment) as EditText

        binding.newReminderButton.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_mainFragment_to_newReminderFragment)
        }

        binding.settingsButton.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_mainFragment_to_settingsFragment)
        }

        return binding.root
    }
}