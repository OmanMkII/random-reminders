package com.totallytim.randomreminders.modules

import java.util.*

class Reminder(
    name: String?,
    frequency: Float,
    nextOccurrence: Date
) {
    // TODO: test fails, expects null but got date
    var name: String? = null

    // TODO: test fails, expects null but got date
    var frequency = 0f
    var nextOccurrence: Date
    fun copyReminder(): Reminder {
        return Reminder(
            String(name), frequency,
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