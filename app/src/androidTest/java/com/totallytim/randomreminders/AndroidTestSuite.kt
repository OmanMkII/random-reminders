package com.totallytim.randomreminders

import com.totallytim.randomreminders.database.ReminderDatabaseTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(ExampleInstrumentedTest::class, ReminderDatabaseTest::class)
class AndroidTestSuite {
    // TODO: pass all the tests, duh
}