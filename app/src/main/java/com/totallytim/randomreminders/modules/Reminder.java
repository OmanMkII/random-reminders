package com.totallytim.randomreminders.modules;

import java.util.Calendar;
import java.util.Date;

import kotlin.NotImplementedError;

public class Reminder {

    private String name;
    private float frequency;
    private Date nextOccurence;

    public Reminder(String name, float frequency, Date nextOccurence) {
        this.name = name;
        this.frequency = frequency;
        this.nextOccurence = nextOccurence;
    }

    public Reminder copyReminder() {
        return new Reminder(this.name, this.frequency, this.nextOccurence);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getFrequency() {
        return frequency;
    }

    public void setFrequency(float frequency) {
        this.frequency = frequency;
    }

    public Date getNextOccurence() {
        return nextOccurence;
    }

    public void setNextOccurence(Date nextOccurence) {
        this.nextOccurence = nextOccurence;
    }
}