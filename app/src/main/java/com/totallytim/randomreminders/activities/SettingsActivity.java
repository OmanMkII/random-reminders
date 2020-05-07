package com.totallytim.randomreminders.activities;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.totallytim.randomreminders.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;

import android.view.MenuItem;
import android.view.View;

public class SettingsActivity extends AppCompatActivity {

    // Medium:
    // TODO: add settings
    // TODO: add settings tools

    // High:
    // TODO: add hours to trigger alarms

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // https://stackoverflow.com/a/16755282
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Not sure why I need to declare then hide, but here it is
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setVisibility(View.GONE);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

}
