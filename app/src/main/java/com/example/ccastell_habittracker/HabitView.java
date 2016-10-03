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
        return habit.getDateCreation();
    }

    public ArrayList<String> datesStringView() {
        return this.habit.getHistory();
    }
}




