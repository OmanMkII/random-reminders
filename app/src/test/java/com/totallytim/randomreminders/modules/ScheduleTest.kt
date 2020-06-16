package com.totallytim.randomreminders.modules

// TODO: proper tests with the new orientation

import com.totallytim.randomreminders.getDayIndex
import com.totallytim.randomreminders.getDayString
import org.junit.Assert
import org.junit.Test
import java.util.*

class ScheduleTest {

    // TODO: trim these tests and have them working with the final writeup of schedule class
    // TODO: also, make them pass

    private val DAYS: Array<String> = arrayOf(
        "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
    )

    private val DAY_SCHEDULE = 0x00FF00

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

    @Test
    fun testGetters() {
        Assert.fail()
    }

    @Test
    fun testScheduleSetter() {
        Assert.fail()
    }

    @Test
    fun testDayArrays() {
        Assert.fail()
    }

    @Test
    fun testValidTimes() {
        // TODO
        Assert.fail()
    }

    // TODO: test added times
    // TODO: test modified times
}