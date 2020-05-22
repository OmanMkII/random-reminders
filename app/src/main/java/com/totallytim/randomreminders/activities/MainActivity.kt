package com.totallytim.randomreminders.activities

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.NotificationCompat
import androidx.fragment.app.ListFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.totallytim.randomreminders.R
import com.totallytim.randomreminders.modules.Schedule

class MainActivity : AppCompatActivity() {

    private lateinit var schedule: Schedule
    private lateinit var toolbar: Toolbar
    private lateinit var fab: FloatingActionButton

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // TODO: how to fix a snackbar:
        // https://stackoverflow.com/a/44842735
        fab = findViewById(R.id.fab)
        fab.setOnClickListener(View.OnClickListener { view ->
            val snackbar =
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Make Notification") {
                        println("Registered click")
                        addNotification()
                    }
            snackbar.show()
        })

        // TODO: prompt to make schedule if null
        // TODO: import local JSON as new schedule (if it exists)
//        schedule = null

        // TODO: display all existing reminders
        val listDemo = arrayOf(
            "Visual Basic .NET",
            "Java",
            "Android",
            "C# .NET",
            "PHP",
            "C++",
            "Scala",
            "Ruby on Rails",
            "Javascript",
            "HTML",
            "Python",
            "Swift"
        )
        val listView =
            findViewById<View>(R.id.listView1) as ListView
        val arrayAdapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, listDemo)
//        ListFragment.setListAdapter(
//            ArrayAdapter<String>(
//                this,
//                android.R.layout.simple_list_item_1,
//                this.directoryEntries
//            )
//        )
        listView.adapter = arrayAdapter
    }

    // TODO: proper logo for notifications
    private fun addNotification() {
        val builder = NotificationCompat.Builder(this)
            .setSmallIcon(R.drawable.random_icon)
            .setContentTitle("Notif title")
            .setContentText("Notif content")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

//        Intent notificationIntent = new Intent(this, MainActivity.class);
        val notificationIntent = Intent(this, SettingsActivity::class.java)
        val contentIntent = PendingIntent.getActivity(
            this, 0, notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        builder.setContentIntent(contentIntent)

        // Add as notification
        val manager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(0, builder.build())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        val menuInflater = this.menuInflater
        menuInflater.inflate(R.menu.menu_main, menu)
        //        return true;
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> {
                println("Registered 'Settings' click")
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
                true
            }
        }
    }

    fun onButtonClick(v: View?) {
        val myIntent = Intent(baseContext, SettingsActivity::class.java)
        startActivity(myIntent)
    }
}