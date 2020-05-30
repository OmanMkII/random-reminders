package com.totallytim.randomreminders.activities

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment


class NewReminderFragment : Fragment() {
    override fun onCreateView(
                inflater: LayoutInflater,
                container: ViewGroup?,
                savedInstanceState: Bundle?
            ): View? {
        val view: View = inflater.inflate(R.layout.fragment_layout, container, false)
        NewReminderFragment = view.findViewById(R.id.NewReminderFragment) as EditText
        return view
    }
}