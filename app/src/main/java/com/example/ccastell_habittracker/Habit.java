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


    public String getTitle() {
        return this.title;
    }

    public String getText() {
        return this.text;
    }

    public void addHistory(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String dateFormatted = dateFormat.format(date);
        System.out.println(dateFormatted);
        this.history.add(dateFormatted);
    }

    public void addHistoryCount() {
        this.count ++;
    }

    public int getHistoryCount() {
        return this.count;
    }

    public ArrayList<String> getHistory() {
        return this.history;
    }

    public void  removeHistory(int index) {
        this.history.remove(index);
        this.count --;
    }

    public String getDateCreation() {
        return this.history.get(0);
    }

    public void addOccurrences(ArrayList<String> occurrences) {
        this.occurrences = occurrences;
    }

    public ArrayList<String> getOccurrences() {
        return this.occurrences;
    }

}
