package com.totallytim.randomreminders;

import com.totallytim.randomreminders.modules.ReminderTest;
import com.totallytim.randomreminders.modules.ScheduleTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        ReminderTest.class,
        ScheduleTest.class
})
public class TestRunner {
    // TODO: pass all the tests, duh
}
