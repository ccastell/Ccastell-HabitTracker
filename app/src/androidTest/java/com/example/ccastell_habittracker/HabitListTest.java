package com.example.ccastell_habittracker;

import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Created by carlcastello on 30/09/16.
 */
public class HabitListTest extends TestCase {
    private HabitList habitList;
    private ArrayList<Habit> habitList2;
    private Habit habit1;
    private Habit habit2;

    public HabitListTest() {
        this.habitList = new HabitList();
        this.habit1 = new Habit("Apple","Orange");
        this.habit2 = new Habit("Apple","Orange");
    }

    public void testCountHabit() {
        assertTrue("Count is not equal", this.habitList.accessHabitSize() == 0);
    }

    public void testAddHabit() {
        this.habitList = new HabitList();
        this.habitList2 = new ArrayList<Habit>();

        this.habitList.incrementHabit(habit1);
        this.habitList2.add(this.habit1);
        assertTrue("Increment not Succesful",this.habitList.accessHabitSize() == 1);

        this.habitList.incrementHabit(habit2);
        this.habitList2.add(habit2);
        assertTrue("Increment not Succesful",this.habitList.accessHabitSize() == 2);
    }

    public void testRemoveHabit() {
        testAddHabit();
        this.habitList.decrementHabit(habit1);
        assertTrue("Properly removed", this.habitList.accessHabitSize() == 1);
        assertFalse("Properly removed", this.habitList.accessHabitSize() == 2);
    }

    public void testAccessList() {
        testAddHabit();
        assertTrue("Proper Size ", this.habitList.accessHabitList().size() == 2);
        assertFalse("imProper Size", this.habitList.accessHabitList().size() == 1);
        assertTrue("Match", this.habitList.accessHabitList().contains(habit1));
        assertTrue("Match", this.habitList.accessHabitList().contains(habit2));
    }

    public void testAccessHabit(){
        testAddHabit();
        assertTrue("Equal", this.habitList.accessHabit(0).equals(habit1));
        assertTrue("Equal", this.habitList.accessHabit(1).equals(habit2));
        assertFalse("Equal", this.habitList.accessHabit(1).equals(habit1));
        assertFalse("Equal", this.habitList.accessHabit(0).equals(habit2));
    }

    public void testModify() {
        testAddHabit();
        this.habitList.modifyHabitList(habit2,0);
        this.habitList.modifyHabitList(habit1,1);
        testAccessHabit();
    }


}
