package com.example.ccastell_habittracker;

import java.util.ArrayList;

/**
 * Created by carlcastello on 29/09/16.
 */
public class HabitList {
    private ArrayList<Habit> habitList;

    public HabitList() {
       this.habitList = new ArrayList<Habit>();
    }
    public HabitList(ArrayList<Habit> memory) {
        this.habitList = memory;
    }
    public ArrayList<Habit> getHabitList(){
        return this.habitList;
    }
    public void addHabit(Habit habit) {
        this.habitList.add(habit);
    }
    public Habit getHabit(int index) {
        return this.habitList.get(index);
    }
    public void removeHabit(Habit habit){
        this.habitList.remove(habit);
    }
    public void replaceHabit(Habit habit, int index) {
        this.habitList.set(index,habit);
    }
    public int countHabit() {
        return this.habitList.size();
    }
}
