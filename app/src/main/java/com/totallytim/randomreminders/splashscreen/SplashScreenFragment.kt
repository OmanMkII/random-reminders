package com.totallytim.randomreminders.splashscreen

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.totallytim.randomreminders.MainActivity

class SplashScreenFragment : Fragment() {
    // Low priority:
    // TODO: make it a little more pretty
    // High priority:
    // TODO: get a proper logo image(s)
    // TODO: logo not visible on intro page
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.splash_screen)
        // delay so people see it
        try {
            println("Skipping splash screen for now..")
//            TimeUnit.MILLISECONDS.sleep(2500);
        } catch (e: Exception) {
            System.err.println("Caught exception when asleep")
            System.err.print(e.message)
        }
        // forward to main activity
        val myIntent = Intent(baseContext, MainActivity::class.java)
        startActivity(myIntent)
    }
}