package com.totallytim.randomreminders.newreminder

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.totallytim.randomreminders.R
import com.totallytim.randomreminders.databinding.FragmentNewReminderBinding

class NewReminderFragment : Fragment() {

//    private lateinit var binding: NewReminderFragmentBinding
    private lateinit var viewModel: NewReminderViewModel

    override fun onCreateView(
                inflater: LayoutInflater,
                container: ViewGroup?,
                savedInstanceState: Bundle?
            ): View? {

        viewModel = ViewModelProvider(this).get(NewReminderViewModel::class.java)
//        binding = DataBindingUtil.inflate<FragmentGameBinding>(this, R.layout.fragment_settings, container, false)

        val binding = DataBindingUtil.inflate<FragmentNewReminderBinding>(
            inflater, R.layout.fragment_new_reminder, container, false)

//        val view: View = inflater.inflate(R.layout.fragment_settings, container, false)
//        val newReminderFragment = view.findViewById(R.id.newReminderFragment) as EditText

        return binding.root
    }
}