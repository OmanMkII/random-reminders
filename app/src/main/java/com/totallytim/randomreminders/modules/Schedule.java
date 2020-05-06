package com.totallytim.randomreminders.modules;

import java.util.ArrayList;
import java.util.List;

public class Schedule {

    // format of [days][hours], week starts on Sunday, 24H clock
    // for [hours]:
    //      N hours after the hour, 0 <= N <= 1
    //      N hours before next hour for -1 < N <= 0
    private int[][] week;
    private List<Reminder> reminders;

    public Schedule(int[][] availableTimes) {
        this.week = availableTimes;
        this.reminders = new ArrayList<>();
    }

    public Schedule(int[][] availableTimes, List<Reminder> reminders) {
        this(availableTimes);
        this.reminders = reminders;
    }

    public int[] getAvailableDay(String day) {
        switch(day) {
            case "Sunday":
                return this.week[0];
            case "Monday":
                return this.week[1];
            case "Tuesday":
                return this.week[2];
            case "Wednesday":
                return this.week[3];
            case "Thursday":
                return this.week[4];
            case "Friday":
                return this.week[5];
            case "Saturday":
                return this.week[6];
            default:
                throw new UnsupportedOperationException(String.format("Day '%s' not recognised", day));
        }
    }

//    public int getAvailableHour(String day, int hour) {
//        return this.getAvailableDay(day)[hour];
//    }
//
//    public boolean isValidTime(Date time) {
//        Calendar c = Calendar.getInstance();
//        c.setTime(time);
//
//        int day = c.get(Calendar.DAY_OF_WEEK);
//        int hour = c.get(Cal)
//
////        SimpleDateFormat
//        int exactHour = this.[day - 1];
//    }

}