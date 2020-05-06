package com.totallytim.randomreminders.modules;

import java.util.Date;

import kotlin.NotImplementedError;

public class Reminder {

    private String name;
    private float frequency;

    private Date nextOccurence;

    public Reminder(String name, float frequency) {
        this.name = name;
        this.frequency = frequency;
        this.resetReminderTime();
    }

    public Reminder copyReminder() {
        return new Reminder(this.name, this.frequency);
    }

    public void resetReminderTime() {
        throw new NotImplementedError("TODO");
    }

}