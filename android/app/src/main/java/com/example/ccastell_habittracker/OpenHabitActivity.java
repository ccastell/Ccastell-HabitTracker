package com.example.ccastell_habittracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import java.util.Date;

public class OpenHabitActivity extends AppCompatActivity {

    //private Habit habit;
    private int index;
    //private HabitView habitView;
    private TextView titleView;
    private TextView textView;
    private Date currentDate;
    private TextView countView;
    private ArrayList<Habit> jsonList;
    //private HabitList habitList;
    private HabitListController habitListController;
    private HabitController habitController;
    private static final String FILENAME = "file.sav";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_habit);

    }

    @Override
    protected void onStart() {
        super.onStart();
        loadFromFile();

        this.habitListController = new HabitListController(this.jsonList);
        //this.habitListController.makeHabitList(this.jsonList);

        this.currentDate = new Date(getIntent().getLongExtra("Date",-1));
        this.index = getIntent().getIntExtra("Position", 0);

        Habit habit = this.habitListController.getHabit(this.index);
        System.out.println("Open Habit "+habit.accessTitle());
        System.out.println("Open Habit "+habit.accessHistoryCount());
        System.out.println("Open Habit "+habit.accessText());
        this.habitController = new HabitController(habit);

        this.titleView = (TextView) findViewById(R.id.OpenHabit_title);
        this.titleView.setText(this.habitController.getTitle());

        this.textView = (TextView) findViewById(R.id.OpenHabit_description);
        this.textView.setText(this.habitController.getText());

        this.countView = (TextView) findViewById(R.id.OpenHabit_Counter);
        this.countView.setText(this.habitController.getHistoryCount());

    }

    @Override
    protected void onResume() {
        super.onResume();
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
                        Habit habit = habitController.getThis();
                        habitListController.removeHabit(habit);
                        saveInFile();
                        finish();
                    }
                }
        );
        Button completeButton = (Button) findViewById(R.id.OpenHabit_mark_complete_button);
        completeButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        habitController.addHistory(currentDate);
                        habitController.addHistoryCount();
                        countView = (TextView) findViewById(R.id.OpenHabit_Counter);
                        countView.setText(habitController.getHistoryCount());
                        saveInFile();
                    }
                }
        );
        Button moreButton = (Button) findViewById(R.id.OpenHabit_more_button);
        moreButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        Intent intent= new Intent(OpenHabitActivity.this, DetailHabitActivity.class);
                        //putParcelable

                        intent.putExtra("Position", index);
                        startActivityForResult(intent,0);

                        //finish();
                        //habit.addHistory();
                        //saveInFile();
                    }
                }
        );
    }

    //This will save the data into a file
    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(this.habitListController.getHabitList(), out);
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
            this.habitListController = new HabitListController();
        }
    }
}
