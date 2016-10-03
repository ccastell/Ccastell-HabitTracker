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
public class HabitController {
    static private Habit habit;

    public HabitController(Habit newHabit) {
        habit = newHabit;
    }

    public HabitController(String title, String text) {
        habit = new Habit(title,text);
    }

    public String getTitle() {
        return habit.accessTitle();
    }

    public String getText() {
        return habit.accessText();
    }

    public ArrayList<String> getHistory() {
        return habit.accessHistory();
    }

    public void addHistory(Date date) {
        habit.incrementHistory(date);
    }

    public void removeHistory(int index){
        habit.decrementHistory(index);
    }

    public String getHistoryCount() {
        return String.valueOf(habit.accessHistoryCount());
    };

    public void addHistoryCount() {
        habit.incrementHistoryCount();
    }

    public ArrayList<String> getOccurrences(){
        return habit.accessOccurrences();
    }

    public void addOccurrence(ArrayList<String> occurrences) {
        habit.incrementOccurrences(occurrences);
    }

    public String getDate() {
        return habit.accessDateCreation();
    }

    public Habit getThis(){
        return habit;
    }

}




