package com.example.ccastell_habittracker;

import junit.framework.TestCase;

/**
 * Created by carlcastello on 03/10/16.
 */
public class HabitTest extends TestCase {
    Habit habit1;
    Habit habit2;

    public HabitTest() {
        this.habit1 = new Habit("Apple","Orange");
        this.habit2 = new Habit("Apple","Orange");
    }
}
