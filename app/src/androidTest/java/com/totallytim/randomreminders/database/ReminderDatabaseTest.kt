package com.totallytim.randomreminders.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.*
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ReminderDatabaseTest {

    private val testJob = Job()
    private val testScope = CoroutineScope(Dispatchers.Main + testJob)

    private lateinit var context: Context
    private lateinit var database: ReminderDatabase

    private lateinit var staticReminders: Array<Reminder?>

    @Before
    fun initTest() {
        ReminderDatabase.TEST_MODE = true
        context = InstrumentationRegistry.getInstrumentation().targetContext
        database = ReminderDatabase.getInstance(context)
    }

    private suspend fun populateDatabase() {
        return withContext(Dispatchers.IO) {
            // clear all data to prevent test conflicts
            database.reminderDatabaseDao.deleteAllReminders()

            // Raw data
            val names = arrayOf(
                "Reminder 1",
                "Reminder 2",
                "Reminder 3",
                "Reminder 4",
                "Reminder 5"
            )
            val frequency = arrayOf(1L, 2L, 3L, 4L, 5L)
            val description = arrayOf(
                "First reminder.",
                "",
                "Third",
                "Fourth",
                ""
            )
            // Reminders
            staticReminders = arrayOf(null, null, null, null, null)
            for (i in 0..4) {
                staticReminders[i] = Reminder(names[i], frequency[i], (1 / frequency[i]).toFloat(),
                    null, description[i])
                database.reminderDatabaseDao.updateExistingReminder(staticReminders[i]!!)
            }
        }
    }

    private fun assertPopulatedDataCorrect(data: LiveData<Array<Reminder>>, canExceed: Boolean) {
        // Note: only name reminders as >5
        for ((i, reminder) in data.value!!.asIterable().withIndex()) {
            // ensure that the first 5 are the inserted values
            if (i >= 5) {
                // if not the first test, allow it to exceed 5 values
                if (canExceed) {
                    break
                } else {
                    // else fail
                    Assert.fail()
                }
            } else {
                // check values
                Assert.assertTrue(reminder == staticReminders[i])
            }
        }
    }

    @Test
    fun testInsertReminder() {
        testScope.launch {
//            populateDatabase()
            val job1 = GlobalScope.async { populateDatabase() }
            job1.await()
            // get initial data
            var data = database.reminderDatabaseDao.getAllReminders()

            // assert initial data correct
            assertPopulatedDataCorrect(data, false)

            // add new reminders
            val r6 = Reminder("Reminder 6",
                1L, 1f, null, null)
            val r7 = Reminder("Reminder 7",
                2L, 0.5f, null, null)
            val r8 = Reminder("Reminder 8",
                3L, 0.25f, null, null)

            database.reminderDatabaseDao.insertNewReminder(r6)
            database.reminderDatabaseDao.insertNewReminder(r7)
            database.reminderDatabaseDao.insertNewReminder(r7)

            // assert they're correct
            data = database.reminderDatabaseDao.getAllReminders()
            assertPopulatedDataCorrect(data, true)
            Assert.assertTrue(data.value!![5] == r6)
            Assert.assertTrue(data.value!![6] == r7)
            Assert.assertTrue(data.value!![7] == r8)

            // clear all data to prevent test conflicts
            database.reminderDatabaseDao.deleteAllReminders()
        }
    }

    @Test
    fun testUpdateReminder() {
        testScope.launch {
            populateDatabase()
            val populateJob = GlobalScope.async { populateDatabase() }
            populateJob.await()

            // get initial data
            val callJob1 = GlobalScope.async { database.reminderDatabaseDao.getAllReminders() }
            var data = callJob1.await()
            // assert initial data correct
            assertPopulatedDataCorrect(data, false)

            // keep these reminders
            val r1 = Reminder("Reminder 1",
                1L, 1f, null, "First reminder.")
            val r3 = Reminder("Reminder 3",
                3L, (1 / 3).toFloat(), null, "Third")

            // update existing reminders
            val r2 = Reminder("Reminder 2",
                2L, 0.5f, null, "Updated description")
            val r4 = Reminder("Reminder 4",
                8L, 1f, null, "Fourth")
            val r5 = Reminder("Reminder 5",
                1L, 1f, null, "Another description")

            val inputSet = mutableSetOf(r2, r4, r5)

            val insertJob = GlobalScope.async { updateSetOfReminders(database, inputSet) }
            insertJob.await()

            // assert they're correct
            val callJob2 = GlobalScope.async { database.reminderDatabaseDao.getAllReminders() }
            data = callJob2.await()

            val dataSet = arrayOf(r1, r2, r3, r4, r5)
            for ((i, d) in data.value!!.withIndex()) {
                Assert.assertEquals(d, dataSet[i])
            }

            // clear all data to prevent test conflicts
            database.reminderDatabaseDao.deleteAllReminders()
        }
    }

    @Test
    fun testDeleteReminder() {
        testScope.launch {
            // TODO: properly await the next time it fails
            populateDatabase()
            // get initial data
            val data = database.reminderDatabaseDao.getAllReminders()
            // assert initial data correct
            assertPopulatedDataCorrect(data, false)

            // keep these reminders
            val r1 = Reminder("Reminder 1",
                1L, (1 / 1).toFloat(), null, "First reminder.")
            val r2 = Reminder("Reminder 1",
                2L, (1 / 2).toFloat(), null, "")
            val r3 = Reminder("Reminder 3",
                3L, (1 / 3).toFloat(), null, "Third")
            val r4 = Reminder("Reminder 4",
                4L, (1 / 4).toFloat(), null, "Fourth")
            val r5 = Reminder("Reminder 5",
                5L, (1 / 5).toFloat(), null, "")

            // assert they're correct
            val nameSet = arrayOf("Reminder 1", "Reminder 2", "Reminder 3", "Reminder 4", "Reminder 5")
            val dataSet = arrayOf(r1, r2, r3, r4, r5)
            for ((i, d) in data.value!!.withIndex()) {
                Assert.assertEquals(
                    database.reminderDatabaseDao.getReminder(nameSet[i]),
                    dataSet[i]
                )
            }

            // clear all data to prevent test conflicts
            database.reminderDatabaseDao.deleteAllReminders()
        }
    }

    @Test
    fun testGetNextReminder() {
        testScope.launch {
            // clear all data to prevent test conflicts
            val deleteJob = GlobalScope.async { database.reminderDatabaseDao.deleteAllReminders() }

            // set of new reminders
            val r1 = Reminder("Reminder 1",
                1L, (1 / 1).toFloat(), 20L, "Longest Period")
            val r2 = Reminder("Reminder 2",
                2L, (1 / 2).toFloat(), 5L, "Shortest period")
            val r3 = Reminder("Reminder 3",
                3L, (1 / 3).toFloat(), 15L, "Middle")

            val reminderList = mutableListOf(r1, r2, r3).toSet()

            // insert all events
            deleteJob.await()

            val insertAllJob = GlobalScope.async {
                insertSetOfNewReminders(
                    database,
                    reminderList
                )
            }

            insertAllJob.await()

            val getJob = GlobalScope.async {
                database.reminderDatabaseDao.getNextReminder(3)
            }

            val nextReminder: LiveData<Array<Reminder>?> = getJob.await()

            // assert correct
            Assert.assertNotNull(nextReminder)
            Assert.assertNotNull(nextReminder.value)
            Assert.assertNotNull(nextReminder.value!![0])

            // assert r2 is the next calendar event
            Assert.assertEquals(r2, nextReminder.value!![0])

            // clear all data to prevent test conflicts
            database.reminderDatabaseDao.deleteAllReminders()
        }
    }

}