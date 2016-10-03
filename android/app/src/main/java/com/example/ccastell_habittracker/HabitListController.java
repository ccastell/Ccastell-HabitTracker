package com.example.ccastell_habittracker;

import java.util.ArrayList;

/**
 * Created by carlcastello on 02/10/16.
 */
public class HabitListController {
    private HabitList habitList;

    public HabitListController() {
        habitList = new HabitList();
    }

    public HabitListController(ArrayList<Habit> habitlist) {
        habitList = new HabitList(habitlist);
    }

    public ArrayList<Habit> getHabitList() {
        return habitList.accessHabitList();
    }

    public int getHabitListCount() {
        return habitList.accessHabitSize();
    }

    public Habit getHabit(int index) {
        return habitList.accessHabit(index);
    }

    public void addHabit(Habit habit) {
        habitList.incrementHabit(habit);
    }

    public void removeHabit(Habit habit) {
        habitList.decrementHabit(habit);
    }

    public void replaceHabit(Habit habit, int index) {
        habitList.modifyHabitList(habit,index);
    }

}
