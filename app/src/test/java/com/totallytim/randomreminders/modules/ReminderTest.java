package com.totallytim.randomreminders.modules;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class ReminderTest {

    @Test
    public void testNewReminder() {
        Reminder reminder = new Reminder("Reminder 1", 24, null);

        Assert.assertEquals(reminder.getName(), "Reminder 1");
        Assert.assertEquals(reminder.getFrequency(), 24, 0.1);
        Assert.assertNull(reminder.getNextOccurrence());
    }

    @Test
    public void testCopyReminder() {
        Reminder reminder = new Reminder("Reminder 1", 24, new Date());

        Reminder reminder2 = reminder.copyReminder();

        reminder2.setName("Reminder 2");
        reminder2.setFrequency(48);
        reminder2.setNextOccurrence(new Date(reminder2.getNextOccurrence().getTime() + 1000));

        Assert.assertEquals(reminder.getName(), "Reminder 1");
        Assert.assertEquals(reminder.getFrequency(), 24, 0.1);
        Assert.assertEquals(reminder.getNextOccurrence(), new Date());

        Assert.assertEquals(reminder2.getName(), "Reminder 2");
        Assert.assertEquals(reminder2.getFrequency(), 48, 0.1);
        Assert.assertEquals(reminder2.getNextOccurrence(), new Date(new Date().getTime() + 1000));
    }

    @Test
    public void testBadArguments() {
        try {
            new Reminder(null, 10, null);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            // pass
        }

        try {
            new Reminder("Name", 0, null);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            // pass
        }

        Reminder reminder = new Reminder("Name", 10, null);
        Assert.assertNull(reminder.getNextOccurrence());
    }

    @Test
    public void testSetDate() {
        Reminder reminder = new Reminder("Reminder 1", 24, null);

        Assert.assertNull(reminder.getNextOccurrence());

        reminder.setNextOccurrence(new Date());

        Assert.assertEquals(reminder.getName(), "Reminder 1");
        Assert.assertEquals(reminder.getFrequency(), 24, 0.1);
        Assert.assertEquals(reminder.getNextOccurrence(), new Date());
    }

    @Test
    public void testSetName() {
        Reminder reminder = new Reminder("Reminder 1", 24, null);

        Assert.assertEquals(reminder.getName(), "Reminder 1");

        reminder.setName("Reminder 2");

        Assert.assertEquals(reminder.getName(), "Reminder 2");
        Assert.assertEquals(reminder.getFrequency(), 24, 0.1);
        Assert.assertEquals(reminder.getNextOccurrence(), new Date());
    }

    @Test
    public void testSetFrequency() {
        Reminder reminder = new Reminder("Reminder 1", 24, null);

        Assert.assertEquals(reminder.getFrequency(), 24, 0.1);

        reminder.setFrequency(32);

        Assert.assertEquals(reminder.getName(), "Reminder 1");
        Assert.assertEquals(reminder.getFrequency(), 32, 0.1);
        Assert.assertEquals(reminder.getNextOccurrence(), new Date());
    }

}
