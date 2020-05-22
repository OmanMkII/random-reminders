package com.totallytim.randomreminders.modules

import java.util.*

class Reminder(
    name: String?,
    frequency: Double,
    nextOccurrence: Date = Date()) {

    // TODO: test fails, expects null but got date
    var name: String?

    // TODO: test fails, expects null but got date
    var frequency: Double
    var nextOccurrence: Date

    fun copyReminder(): Reminder {
        return Reminder(
            name.toString(), frequency,
            Date(nextOccurrence.time)
        )
    }

    init {
        require(!(name == null || frequency <= 0))
        this.name = name
        this.frequency = frequency
        this.nextOccurrence = nextOccurrence
    }
}