package com.example.ccastell_habittracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class OpenHabitActivity extends AppCompatActivity {

    private String titleText;
    private String bodyText;
    private Habit habit;
    private ArrayList<Habit> jsonList;
    private HabitList habitList;
    private static final String FILENAME = "file.sav";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_habit);

        Button doneButton = (Button) findViewById(R.id.OpenHabit_done_button);
        doneButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        saveInFile();
                        finish();

                    }
                }
        );

        Button deleteButton = (Button) findViewById(R.id.OpenHabit_delete_button);
        deleteButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        habitList.removeHabit(habit);
                        saveInFile();
                        finish();
                    }
                }
        );

        Button completeButton = (Button) findViewById(R.id.OpenHabit_mark_complete_button);
        completeButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        habit.addHistory();
                        saveInFile();
                    }
                }
        );

    }

    @Override
    protected void onStart() {
        super.onStart();
        loadFromFile();
        this.habitList = new HabitList(jsonList);
        int index = getIntent().getIntExtra("Position", 0);
        this.habit = this.habitList.getHabit(index);
    }

    //This will save the data into a file
    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(habitList.getHabitList(), out);
            out.flush();

            fos.close();
        } catch (FileNotFoundException e) {
			/* Rethrow. */
            throw new RuntimeException(e);
        } catch (IOException e) {
			/* Rethrow. */
            throw new RuntimeException(e);
        }
    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            // Code from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            Type listType = new TypeToken<ArrayList<Habit>>(){}.getType();
            this.jsonList = gson.fromJson(in,listType);


        } catch (FileNotFoundException e) {
			/* Create a brand new tweet list if we can't find the file. */
            this.habitList = new HabitList();
        }
    }
}
