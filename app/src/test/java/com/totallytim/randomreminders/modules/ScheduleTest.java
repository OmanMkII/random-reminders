package com.totallytim.randomreminders.modules;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScheduleTest {

    // TODO: trim these tests and have them working with the final writeup of schedule class
    // TODO: also, make them pass

    private boolean[][] initTimesSet() {
        boolean timeSet[][] = new boolean[7][24];

        boolean[] template = {
                false, false, false, false, false, false,
                true, true, true, true, true, true,
                true, true, true, true, true, true,
                false, false, false, false, false, false
        };

        for(int i = 0; i < 7; i++) {
            timeSet[i] = template;
        }

        return timeSet;
    }

    @Test
    public void testTimeSet() {
        boolean[][] timeSet = initTimesSet();

        for(int i = 0; i < 7; i++) {
            for(int j = 0; j < 24; j++) {
                if(j < 6 || j >= 18) {
                    Assert.assertFalse(timeSet[i][j]);
                } else {
                    Assert.assertTrue(timeSet[i][j]);
                }
            }
        }
    }

    @Test
    public void testErrorThrows() {

        // TODO: any more error throws

        try {
            new Schedule(null);
            Assert.fail();
        } catch(NullPointerException e) {
            // pass
        }

        Schedule s = new Schedule(initTimesSet());

        try {
            s.getNextReminder(new Reminder("", 1, null));
            Assert.fail();
        } catch (NullPointerException e) {
            // pass
        }
    }

    @Test
    public void testGetters() {
        Reminder[] rSet = {
                new Reminder("r1", 12, null),
                new Reminder("r2", 6, null),
                new Reminder("r3", 24, null),
                new Reminder("r4", 36, null)
        };

        List<Reminder> reminders = Arrays.asList(rSet);

        boolean[][] schedule = initTimesSet();

        Schedule s1 = new Schedule(schedule);
        Schedule s2 = new Schedule(schedule, reminders);

        Assert.assertArrayEquals(schedule, s1.getWeek());
        Assert.assertArrayEquals(schedule, s2.getWeek());

        Assert.assertEquals(s1.getReminders(), new ArrayList<Reminder>());
        Assert.assertEquals(s2.getReminders(), reminders);
    }

    @Test
    public void testScheduleSetter() {
        Reminder[] rSet = {
                new Reminder("r1", 12, null),
                new Reminder("r2", 6, null),
                new Reminder("r3", 24, null),
                new Reminder("r4", 36, null)
        };

        List<Reminder> reminders = Arrays.asList(rSet);

        Schedule schedule = new Schedule(initTimesSet());

        for(Reminder r : reminders) {
            for(int i = 0; i < 10; i++) {
                // do it 10 times to be safe for Math.rand()
                schedule.setNextReminder(r);
                Date today = new Date();
                Date d = schedule.getNextReminder(r);
                boolean after = d.after(today);
                Assert.assertTrue(schedule.getNextReminder(r).after(today));
                Assert.assertTrue(schedule.getNextReminder(r).before(
                        new Date((int)(today.getTime() + r.getFrequency()))));
            }
        }
    }

    @Test
    public void testDayArrays() {
        Map<String, Integer> days = new HashMap<>();

        days.put("Sunday", 0);
        days.put("Monday", 1);
        days.put("Tuesday", 2);
        days.put("Wednesday", 3);
        days.put("Thursday", 4);
        days.put("Friday", 5);
        days.put("Saturday", 6);

        for(Map.Entry<String, Integer> entry : days.entrySet()) {
            Assert.assertEquals((int)Schedule.getDayIndex(entry.getKey()), (int)entry.getValue());
            Assert.assertEquals(Schedule.getDayString(entry.getValue()), entry.getKey());
        }
    }

    @Test
    public void testValidTimes() {
        Assert.fail();
    }

}
