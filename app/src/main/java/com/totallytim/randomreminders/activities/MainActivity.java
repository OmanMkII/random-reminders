package com.totallytim.randomreminders.activities;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.totallytim.randomreminders.R;
import com.totallytim.randomreminders.modules.Schedule;

public class MainActivity extends AppCompatActivity {

    private Schedule schedule;

    private Toolbar toolbar;
    private FloatingActionButton fab;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // TODO: how to fix a snackbar:
        // https://stackoverflow.com/a/44842735
        this.fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Snackbar snackbar = Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_INDEFINITE)
                .setAction("Make Notification", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println("Registered click");
                        addNotification();
                    }
            });
            snackbar.show();
            }
        });

        this.schedule = null;
    }

    private void addNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.random_icon)
                .setContentTitle("Notif title")
                .setContentText("Notif content")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

//        Intent notificationIntent = new Intent(this, MainActivity.class);
        Intent notificationIntent = new Intent(this, SettingsActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater menuInflater = this.getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
//        return true;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()) {
            case R.id.action_settings:
                System.out.println("Registered 'Settings' click");
                return true;

            default:
                super.onOptionsItemSelected(item);
                return true;
        }
    }

    public void onButtonClick(View v){
        Intent myIntent = new Intent(getBaseContext(), SettingsActivity.class);
        startActivity(myIntent);
    }
}
