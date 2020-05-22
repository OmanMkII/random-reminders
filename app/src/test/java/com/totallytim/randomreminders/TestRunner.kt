package com.totallytim.randomreminders

import com.totallytim.randomreminders.modules.ReminderTest
import com.totallytim.randomreminders.modules.ScheduleTest
import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.junit.runners.Suite.SuiteClasses

@RunWith(Suite::class)
@SuiteClasses(ReminderTest::class, ScheduleTest::class)
class TestRunner {
    // TODO: pass all the tests, duh
}