package com.totallytim.randomreminders;

import android.os.Bundle;
import android.os.Handler;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private FloatingActionButton fab;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        System.out.println("Showing brand");
        setContentView(R.layout.brand);
        new Handler().postDelayed(
                new Runnable() {
                      @Override
                      public void run() {
                          System.out.println("Moving to main screen");
                          // default
                          setContentView(R.layout.activity_main);
                          toolbar = findViewById(R.id.toolbar);
                          setSupportActionBar(toolbar);
                    }
              }, 2500
        );

        this.fab = findViewById(R.id.fab);
        if(fab == null) {
            System.out.println("fab was null");
            return;
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

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
        // TODO: ensure this works correctly
        switch(item.getItemId()) {
            case R.id.action_settings:
                return true;

            default:
                super.onOptionsItemSelected(item);
                return true;
        }
    }
}
