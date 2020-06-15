package com.totallytim.randomreminders.modules

// TODO: proper tests with the new orientation

//import com.totallytim.randomreminders.getDayIndex
//import com.totallytim.randomreminders.getDayString
//import org.junit.Assert
//import org.junit.Test
//import java.util.*
//
//class ScheduleTest {
//    // TODO: trim these tests and have them working with the final writeup of schedule class
//    // TODO: also, make them pass
//    private fun initTimesSet(): Array<BooleanArray> {
//        val timeSet =
//            Array(7) { BooleanArray(24) }
//        val template = booleanArrayOf(
//            false, false, false, false, false, false,
//            true, true, true, true, true, true,
//            true, true, true, true, true, true,
//            false, false, false, false, false, false
//        )
//        for (i in 0..6) {
//            timeSet[i] = template
//        }
//        return timeSet
//    }
//
//    @Test
//    fun testTimeSet() {
//        val timeSet = initTimesSet()
//        for (i in 0..6) {
//            for (j in 0..23) {
//                if (j < 6 || j >= 18) {
//                    Assert.assertFalse(timeSet[i][j])
//                } else {
//                    Assert.assertTrue(timeSet[i][j])
//                }
//            }
//        }
//    }
//
//    @Test
//    fun testErrorThrows() {
//
////        // TODO: any more error throws
////        try {
////            Schedule(null)
////            Assert.fail()
////        } catch (e: NullPointerException) {
////            // pass
////        }
//        val s = Schedule(initTimesSet())
//        try {
//            s.getNextReminder(Reminder("", 1.0))
//            Assert.fail()
//        } catch (e: NullPointerException) {
//            // pass
//        }
//    }
//
//    @Test
//    fun testGetters() {
//        val rSet = arrayOf(
//            Reminder("r1", 12.0),
//            Reminder("r2", 6.0),
//            Reminder("r3", 24.0),
//            Reminder("r4", 36.0)
//        )
//        val reminders = Arrays.asList(*rSet)
//        val schedule = initTimesSet()
//        val s1 = Schedule(schedule)
//        val s2 = Schedule(schedule, reminders)
//        Assert.assertArrayEquals(schedule, s1.week)
//        Assert.assertArrayEquals(schedule, s2.week)
//        Assert.assertEquals(s1.reminders, ArrayList<Reminder>())
//        Assert.assertEquals(s2.reminders, reminders)
//    }
//
//    @Test
//    fun testScheduleSetter() {
//        val reminders = Arrays.asList(*rSet)
//        val schedule = Schedule(initTimesSet())
//        for (r in reminders) {
//            for (i in 0..9) {
//                // do it 10 times to be safe for Math.rand()
//                schedule.setNextReminder(r)
//                val today = Date()
//                val d = schedule.getNextReminder(r)
////                val after = d?.after(today)
//                // TODO: failed here for some reason
////                Assert.assertTrue(schedule.getNextReminder(r)!!.after(today))
//                Assert.assertTrue(
//                    schedule.getNextReminder(r)!!.before(
//                        Date((today.time + r.frequency).toLong())
//                    )
//                )
//            }
//        }
//    }
//
//    @Test
//    fun testDayArrays() {
//        val days: MutableMap<String, Int> =
//            HashMap()
//        days["Sunday"] = 0
//        days["Monday"] = 1
//        days["Tuesday"] = 2
//        days["Wednesday"] = 3
//        days["Thursday"] = 4
//        days["Friday"] = 5
//        days["Saturday"] = 6
//        for ((key, value) in days) {
//            Assert.assertEquals(
//                getDayIndex(key),
//                value
//            )
//            Assert.assertEquals(getDayString(value), key)
//        }
//    }
//
//    @Test
//    fun testValidTimes() {
//        // TODO
//        Assert.fail()
//    }
//
//    // TODO: test added times
//    // TODO: test modified times
//}