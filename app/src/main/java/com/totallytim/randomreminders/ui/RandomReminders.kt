package com.totallytim.randomreminders.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.totallytim.randomreminders.R
import com.totallytim.randomreminders.databinding.ActivityMainBinding
import timber.log.Timber

/**
 * The main activity of the application.
 */
class RandomReminders : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )
    }
}