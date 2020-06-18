package com.totallytim.randomreminders.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.totallytim.randomreminders.R
import com.totallytim.randomreminders.databinding.FragmentMainBinding
import com.totallytim.randomreminders.newreminder.NewReminderViewModel

class MainFragment : Fragment() {

//    private lateinit var binding: NewReminderFragmentBinding
//    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
                inflater: LayoutInflater,
                container: ViewGroup?,
                savedInstanceState: Bundle?
            ): View? {

//        viewModel = ViewModelProvider(this).get(NewReminderViewModel::class.java)
//        binding = DataBindingUtil.inflate<FragmentGameBinding>(this, R.layout.fragment_settings, container, false)

        val binding = DataBindingUtil.inflate<FragmentMainBinding>(
            inflater, R.layout.fragment_new_reminder, container, false)

//        val view: View = inflater.inflate(R.layout.fragment_settings, container, false)
//        val newReminderFragment = view.findViewById(R.id.newReminderFragment) as EditText

        return binding.root
    }
}