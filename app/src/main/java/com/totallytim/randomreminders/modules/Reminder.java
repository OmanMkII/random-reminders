package com.totallytim.randomreminders.modules;

import java.util.Date;

public class Reminder {

    private String name;
    private float frequency;
    private Date nextOccurrence;

    public Reminder(String name, float frequency, Date nextOccurrence) {
        if(name == null || frequency <= 0) {
            throw new IllegalArgumentException();
        } else {
            this.name = name;
            this.frequency = frequency;
        }
        this.nextOccurrence = nextOccurrence;
    }

    public Reminder copyReminder() {
        return new Reminder(new String(this.name), this.frequency,
                new Date(this.nextOccurrence.getTime()));
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        // TODO: test fails, expects null but got date
        this.name = name;
    }

    public float getFrequency() {
        return frequency;
    }

    public void setFrequency(float frequency) {
        // TODO: test fails, expects null but got date
        this.frequency = frequency;
    }

    public Date getNextOccurrence() {
        return nextOccurrence;
    }

    public void setNextOccurrence(Date nextOccurrence) {
        this.nextOccurrence = nextOccurrence;
    }
}