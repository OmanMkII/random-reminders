package com.totallytim.randomreminders.splashscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.totallytim.randomreminders.R
import com.totallytim.randomreminders.databinding.FragmentSplashScreenBinding
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit


class SplashScreenFragment : Fragment() {

    // Low priority:
    // TODO: make it an actual logo

    private val splashViewJob = Job()
    private val splashScope = CoroutineScope(Dispatchers.Main + splashViewJob)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Hide action bar for splash screen
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

        val binding = DataBindingUtil.inflate<FragmentSplashScreenBinding>(inflater,
            R.layout.fragment_splash_screen,container,false)

        splashScreenDelay()

        return binding.root
    }

    private fun splashScreenDelay() {
        splashScope.launch {
            try {
                Toast.makeText(context, "Splash screen", Toast.LENGTH_SHORT)
                    .show()
                delay(2500)
                findNavController().navigate(R.id.action_splashScreenFragment_to_mainFragment)
            } catch (e: Exception) {
                Toast.makeText(context, "Caught exception on splash screen", Toast.LENGTH_SHORT)
                    .show()
                System.err.print(e.message)
            }
        }
    }
}