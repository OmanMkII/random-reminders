package com.totallytim.randomreminders

import java.util.*

class Reminder(private val name: String, private var frequency: Float) {

    fun copyReminder(): Reminder {
        return Reminder(this.name, this.frequency)
    }

    fun setReminderTime() {
        throw NotImplementedError("TODO")
    }

}