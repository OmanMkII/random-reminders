package com.totallytim.randomreminders.activities;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.totallytim.randomreminders.R;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;

import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

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

//        // Not sure why I need to declare then hide, but here it is
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setVisibility(View.GONE);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }


//    public void clickAnyButton(View view) {
    public void onButtonClick(View view) {
        System.out.println("Logged button click");
        switch (view.getId()) {
//            case R.id.btnConfirmDialog:
            case R.id.button2:
                System.out.println("Logged confirm prompt");
                confirmDialogDemo();
                break;

        }
    }

    private void confirmDialogDemo() {
        // Popup (Y/N)
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm dialog demo!");
        builder.setMessage("Sure you want to go back?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO: why won't it go back?
                Toast.makeText(getApplicationContext(), "You went back!", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "You stayed here", Toast.LENGTH_SHORT).show();
            }
        });

        builder.show();
    }

}
