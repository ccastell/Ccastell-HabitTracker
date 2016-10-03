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

    public HabitList(ArrayList<Habit> habitList) {
        this.habitList = habitList;
    }

    public ArrayList<Habit> accessHabitList(){
        return this.habitList;
    }

    public void incrementHabit(Habit habit) {
        this.habitList.add(habit);
    }
    public Habit accessHabit(int index) {
        return this.habitList.get(index);
    }
    public void decrementHabit(Habit habit){
        this.habitList.remove(habit);
    }
    public void modifyHabitList(Habit habit, int index) {
        this.habitList.set(index,habit);
    }

    public int accessHabitSize() {
        return this.habitList.size();
    }


}
