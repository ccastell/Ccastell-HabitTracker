package com.example.ccastell_habittracker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by carlcastello on 29/09/16.
 */
public class Habit {
    private int count;
    private String title;
    private String text;
    private ArrayList<Date> history;
    private ArrayList<String> occurrences;

    public Habit(String title, String text){
        //Date date = date;
        this.count = 0;
        this.title = title;
        this.text = text;
        this.history = new ArrayList<Date>();
        this.occurrences = new ArrayList<String>();
    }


    public String getTitle() {
        return this.title;
    }

    public String getText() {
        return this.text;
    }

    public void addHistory() {
        Date date = new Date();
        this.history.add(date);
        this.count ++;
    }

    public void addOccurrences(ArrayList<String> occurrences) {
        this.occurrences = occurrences;
    }

    @Override
    public String toString() {
        String habitTitle = this.getTitle();
        //String creationDate = this.getCreationDate();
        return habitTitle;
    }

}
