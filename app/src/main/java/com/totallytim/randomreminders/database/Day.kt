package com.totallytim.randomreminders.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.totallytim.randomreminders.HEX_BASE
import com.totallytim.randomreminders.nthDigit
import java.lang.IllegalArgumentException
import kotlin.math.floor

/**
 * The Day data class represents compact data for what time slots are available in each day of
 * the week. It primarily holds a hexdec value of six units representing 15m intervals of the day.
 */
@Entity(tableName = "daily_schedule")
data class Day(
        /**
         * The name of the day (primary key)
         */
        @PrimaryKey
        @ColumnInfo(name = "day_name")
        var name: String = "",

        /**
         * The availability of the day, represented as hex-dec values where 0x1 == 15m (each integer is
         * thus a 4H timeslot)
         */
        @ColumnInfo(name = "day_availability")
        var availability: Int = 0x000000) {

    /**
     * Asserts that the given time (HH:mm) is valid for this day of the week
     *
     * @param hours     the hour specified (HH format)
     * @param minutes   the minute specified (mm format)
     * @return true iff the given time is within a valid range
     * @throws IllegalArgumentException
     *                  when the given arguments exceed time and space
     */
    @Throws(IllegalArgumentException::class)
    fun isValidTime(hours: Int, minutes: Int): Boolean {
        if (hours >= 24 || minutes >= 60) {
            throw IllegalArgumentException()
        }
        // 6 slots of 4H sections (0H, 4H ...)
        val hourIndex = floor(hours / 6.0).toInt()
        val nthDigit = nthDigit(availability, hourIndex, HEX_BASE)
        return if (nthDigit == 0.0) {
            false
        } else {
            val bits = Integer.toBinaryString(nthDigit.toInt())
            val minIndex = (hours % 6) * 4 + floor(minutes / 4.0)
            // return true iff bit is 1 (true)
            bits[minIndex.toInt()] == '1'
        }
    }

    /**
     * Asserts that this day object contains any timeslot that is valid.
     *
     * @return true iff no availability exist for this day
     */
    fun isValidDay() = (availability != 0x000000)
}