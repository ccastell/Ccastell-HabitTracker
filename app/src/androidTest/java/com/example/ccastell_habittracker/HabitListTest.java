package com.example.ccastell_habittracker;

import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Created by carlcastello on 30/09/16.
 */
public class HabitListTest extends TestCase {
    private HabitList habitList;
    private Habit habit;
    public void testHabit() {
        this.habitList = new HabitList();
    }
    public void testAddHabit() {
        this.habitList = new HabitList();
        ArrayList<Habit> habitList2 = new ArrayList<Habit>();
        this.habit = new Habit("Apple","Orange");
        this.habitList.addHabit(habit);
    }



}
