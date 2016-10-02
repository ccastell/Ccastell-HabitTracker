package com.example.ccastell_habittracker;

//import android.icu.text.DateFormat;
//import android.icu.text.DateFormat;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by carlcastello on 01/10/16.
 */
public class HabitView {
    private Habit habit;

    public HabitView(Habit habit) {
        this.habit = habit;
    }

    public String titleStringView() {
        return this.habit.getTitle();
    }

    public String textStringView() {
        return this.habit.getText();
    }

    public String countStringView() {
        return String.valueOf(this.habit.getHistoryCount());
    }

    public String dateStringView() {
        SimpleDateFormat formatter = new SimpleDateFormat("MMM-d-yyyy, HH:mm");
        return formatter.format(habit.getDateCreation());
    }

    public ArrayList<String> datesStringView() {
        //String date;
        //SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM DD hh:mm:ss yyyy");
        SimpleDateFormat formatter = new SimpleDateFormat("E, MMM-d-yyyy, HH:mm");

        ArrayList<String> dates = new ArrayList<String>();
        ArrayList<Date> habitDates = this.habit.getHistory();
        int range = habitDates.size();
        for (int i = 0; i < range; i++) {
            String date = formatter.format(habitDates.get(i));
            dates.add(date);

        }

        return dates;
    }
}




