package com.totallytim.randomreminders.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.totallytim.randomreminders.R

class SettingsActivity : AppCompatActivity() {
    // Medium:
    // TODO: add settings
    // TODO: add settings tools
    // High:
    // TODO: add hours to trigger alarms
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        val toolbar =
            findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // https://stackoverflow.com/a/16755282
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

//        // Not sure why I need to declare then hide, but here it is
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setVisibility(View.GONE);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val myIntent = Intent(applicationContext, MainActivity::class.java)
        startActivityForResult(myIntent, 0)
        return true
    }

    //    public void clickAnyButton(View view) {
    fun onButtonClick(view: View) {
        println("Logged button click")
        when (view.id) {
            R.id.button2 -> {
                println("Logged confirm prompt")
                confirmDialogDemo()
            }
        }
    }

    private fun confirmDialogDemo() {
        // Popup (Y/N)
        val builder =
            AlertDialog.Builder(this)
        builder.setTitle("Confirm dialog demo!")
        builder.setMessage("Sure you want to go back?")
        builder.setCancelable(false)
        builder.setPositiveButton("Yes") { dialog, which -> // TODO: why won't it go back?
            Toast.makeText(applicationContext, "You went back!", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No") { dialog, which ->
            Toast.makeText(applicationContext, "You stayed here", Toast.LENGTH_SHORT)
                .show()
        }
        builder.show()
    }
}