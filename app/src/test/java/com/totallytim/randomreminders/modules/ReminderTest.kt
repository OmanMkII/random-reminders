package com.totallytim.randomreminders.modules

import org.junit.Assert
import org.junit.Test
import java.util.*

class ReminderTest {
    @Test
    fun testNewReminder() {
        val reminder = Reminder("Reminder 1", 24, null)
        Assert.assertEquals(reminder.name, "Reminder 1")
        Assert.assertEquals(reminder.frequency.toDouble(), 24.0, 0.1)
        Assert.assertNull(reminder.nextOccurrence)
    }

    @Test
    fun testCopyReminder() {
        val reminder = Reminder("Reminder 1", 24, Date())
        val reminder2 = reminder.copyReminder()
        reminder2.name = "Reminder 2"
        reminder2.frequency = 48f
        reminder2.nextOccurrence = Date(reminder2.nextOccurrence.time + 1000)
        Assert.assertEquals(reminder.name, "Reminder 1")
        Assert.assertEquals(reminder.frequency.toDouble(), 24.0, 0.1)
        Assert.assertEquals(reminder.nextOccurrence, Date())
        Assert.assertEquals(reminder2.name, "Reminder 2")
        Assert.assertEquals(reminder2.frequency.toDouble(), 48.0, 0.1)
        Assert.assertEquals(
            reminder2.nextOccurrence,
            Date(Date().time + 1000)
        )
    }

    @Test
    fun testBadArguments() {
        try {
            Reminder(null, 10, null)
            Assert.fail()
        } catch (e: IllegalArgumentException) {
            // pass
        }
        try {
            Reminder("Name", 0, null)
            Assert.fail()
        } catch (e: IllegalArgumentException) {
            // pass
        }
        val reminder = Reminder("Name", 10, null)
        Assert.assertNull(reminder.nextOccurrence)
    }

    @Test
    fun testSetDate() {
        val reminder = Reminder("Reminder 1", 24, null)
        Assert.assertNull(reminder.nextOccurrence)
        reminder.nextOccurrence = Date()
        Assert.assertEquals(reminder.name, "Reminder 1")
        Assert.assertEquals(reminder.frequency.toDouble(), 24.0, 0.1)
        Assert.assertEquals(reminder.nextOccurrence, Date())
    }

    @Test
    fun testSetName() {
        val reminder = Reminder("Reminder 1", 24, null)
        Assert.assertEquals(reminder.name, "Reminder 1")
        reminder.name = "Reminder 2"
        Assert.assertEquals(reminder.name, "Reminder 2")
        Assert.assertEquals(reminder.frequency.toDouble(), 24.0, 0.1)
        Assert.assertEquals(reminder.nextOccurrence, Date())
    }

    @Test
    fun testSetFrequency() {
        val reminder = Reminder("Reminder 1", 24, null)
        Assert.assertEquals(reminder.frequency.toDouble(), 24.0, 0.1)
        reminder.frequency = 32f
        Assert.assertEquals(reminder.name, "Reminder 1")
        Assert.assertEquals(reminder.frequency.toDouble(), 32.0, 0.1)
        Assert.assertEquals(reminder.nextOccurrence, Date())
    }
}