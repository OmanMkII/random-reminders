package com.totallytim.randomreminders.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.totallytim.randomreminders.R;

import java.util.concurrent.TimeUnit;

public class SplashScreen extends AppCompatActivity {

    // Low priority:
    // TODO: make it a little more pretty

    // High priority:
    // TODO: get a proper logo image(s)
    // TODO: logo not visible on intro page

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        // delay so people see it
        try {
            System.out.println("Skipping splash screen for now..");
//            TimeUnit.MILLISECONDS.sleep(2500);
        } catch (Exception e) {
            System.err.println("Caught excpetion when asleep");
            System.err.print(e.getMessage());
        }
        // forward to main activity
        Intent myIntent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(myIntent);
    }
}
