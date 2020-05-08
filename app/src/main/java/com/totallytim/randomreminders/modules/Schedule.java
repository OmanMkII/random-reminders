package com.totallytim.randomreminders.modules;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Schedule {

    // TODO: clean up a little

    // format of [days][hours], week starts on Sunday, 24H clock
    // for [hours]: T || F if it can be used (any minute within that hour)
    private boolean[][] week;
    private List<Reminder> reminders;

    // TODO: import JSON as schedule
    public Schedule(Context context) {
        JSONObject json;
        try {
            json = new JSONObject(
                    Schedule.loadJsonAssets(context, "schedule.json"));

            // TODO: convert JSON to valid array (or vice versa)
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }



        throw new UnsupportedOperationException();
    }

    public Schedule(Context context, List<Reminder> reminders) {
        this(context);
        this.reminders = reminders;
    }

    private static String loadJsonAssets(Context context, String jsonPath) throws IOException {
        String json;
        try {
            InputStream stream = context.getAssets().open(jsonPath);

            int size = stream.available();
            byte[] buffer = new byte[size];

            stream.read(buffer);
            stream.close();

            json = new String(buffer, "utf-8");
        } catch(FileNotFoundException e) {
            System.out.println("No existing JSON object");
            return "";
        }
        return json;
    }

    public Schedule(boolean[][] availableTimes) {
        this.week = availableTimes;
        this.reminders = new ArrayList<>();
    }

    public Schedule(boolean[][] availableTimes, List<Reminder> reminders) {
        this(availableTimes);
        this.reminders = reminders;
    }

    public static String getDayString(int day) {
        switch(day) {
            case 0:
                return "Sunday";
            case 1:
                return "Monday";
            case 2:
                return "Tuesday";
            case 3:
                return "Wednesday";
            case 4:
                return "Thursday";
            case 5:
                return "Friday";
            case 6:
                return "Saturday";
            default:
                throw new UnsupportedOperationException(String.format("Index '%d' not recognised", day));
        }
    }

    public static int getDayIndex(String day) {
        switch(day) {
            case "Sunday":
                return 0;
            case "Monday":
                return 1;
            case "Tuesday":
                return 2;
            case "Wednesday":
                return 3;
            case "Thursday":
                return 4;
            case "Friday":
                return 5;
            case "Saturday":
                return 6;
            default:
                throw new UnsupportedOperationException(String.format("Day '%s' not recognised", day));
        }
    }

    public boolean[] getAvailableDay(String day) {
        return this.week[getDayIndex(day)];
    }

    public boolean isAvailableHour(String day, int hour) {
        return this.getAvailableDay(day)[hour];
    }

    public boolean isValidTime(Date time) {
        Calendar c = Calendar.getInstance();
        c.setTime(time);

        int day = c.get(Calendar.DAY_OF_WEEK);
        int hour = c.get(Calendar.HOUR_OF_DAY);

        return isAvailableHour(getDayString(day), hour);
    }

    // TODO: CRITICAL
    // TODO: verify that I have correct formats for times
    public void setNextReminder(Reminder reminder) {
        if(!this.reminders.contains(reminder)) {
            this.reminders.add(reminder);
        }

        long startMillis = System.currentTimeMillis();
        long endMillis = (long)(startMillis + reminder.getFrequency());

        long instanceDate = (long) (Math.random() * (endMillis - startMillis));

        reminder.setNextOccurrence(new Date(startMillis + instanceDate));
    }

    public Date getNextReminder(Reminder reminder) throws NullPointerException {
        if(!this.reminders.contains(reminder)) {
            throw new NullPointerException();
        }
        return this.reminders.get(this.reminders.indexOf(reminder)).getNextOccurrence();
    }

    public List<Reminder> getReminders() {
        return this.reminders;
    }

    public void addReminder(Reminder reminder) {
        setNextReminder(reminder);
    }

    public boolean[][] getWeek() {
        return week;
    }

    public void setWeek(boolean[][] week) {
        this.week = week;
    }

    // TODO: export as JSON to local for later import
    public void exportJson() {
        throw new UnsupportedOperationException();
    }
}