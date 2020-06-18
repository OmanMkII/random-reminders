package com.totallytim.randomreminders.splashscreen;

import android.app.Application
import androidx.lifecycle.ViewModel
import com.totallytim.randomreminders.database.ReminderDatabaseDao

class SplashScreenViewModel(
            dataSource: ReminderDatabaseDao,
            application: Application
        ) : ViewModel() {


}
