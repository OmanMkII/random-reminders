package com.totallytim.randomreminders.splashscreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.totallytim.randomreminders.MainActivity
import com.totallytim.randomreminders.R
import com.totallytim.randomreminders.databinding.FragmentSplashScreenBinding

class SplashScreenFragment : Fragment() {
    // Low priority:
    // TODO: make it a little more pretty
    // High priority:
    // TODO: get a proper logo image(s)
    // TODO: logo not visible on intro page
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
////        setContentView(R.layout.fragment_splash_screen)
//        // delay so people see it
//        try {
//            println("Skipping splash screen for now..")
////            TimeUnit.MILLISECONDS.sleep(2500);
//        } catch (e: Exception) {
//            System.err.println("Caught exception when asleep")
//            System.err.print(e.message)
//        }
//        // forward to main activity
//        onSplashScreenProgress(this.context)
//        val myIntent = Intent(this.context, MainActivity::class.java)
//        startActivity(myIntent)
//    }
//
//    fun onSplashScreenProgress(context: Context?) {
//        Toast.makeText(context, "Splash screen", Toast.LENGTH_SHORT).show()
//    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        try {
            Toast.makeText(context, "Skipping splash screen for now..", Toast.LENGTH_SHORT)
                .show()
//            TimeUnit.MILLISECONDS.sleep(2500);
        } catch (e: Exception) {
            Toast.makeText(context, "Caught exception on splash screen", Toast.LENGTH_SHORT)
                    .show()
            System.err.print(e.message)
        }
        // forward to main activity
        Toast.makeText(context, "Splash screen", Toast.LENGTH_SHORT).show()

        val binding = DataBindingUtil.inflate<FragmentSplashScreenBinding>(inflater,
            R.layout.fragment_splash_screen,container,false)
        return binding.root
    }
}