package com.example.ccastell_habittracker;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by carlcastello on 29/09/16.
 */
public class Habit implements Serializable{
    private int count;
    private String title;
    private String text;
    private ArrayList<String> history;
    private ArrayList<String> occurrences;

    public Habit(String title, String text){
        //Date date = date;
        this.count = 0;
        this.title = title;
        this.text = text;
        this.history = new ArrayList<String>();
        this.occurrences = new ArrayList<String>();
    }

    public String accessTitle() {
        return this.title;
    }

    public String accessText() {
        return this.text;
    }

    public void incrementHistory(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String dateFormatted = dateFormat.format(date);
        System.out.println("Habit " + dateFormatted);

        this.history.add(dateFormatted);
    }

    public void incrementHistoryCount() {
        this.count ++;
    }

    public int accessHistoryCount() {
        return this.count;
    }

    public ArrayList<String> accessHistory() {
        return this.history;
    }

    public void  decrementHistory(int index) {
        this.history.remove(index);
        this.count --;
    }

    public String accessDateCreation() {
        return this.history.get(0);
    }

    public void incrementOccurrences(ArrayList<String> occurrences) {
        this.occurrences = occurrences;
    }

    public ArrayList<String> accessOccurrences() {
        return this.occurrences;
    }

}
