package com.example.ccastell_habittracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
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

public class DetailHabitActivity extends AppCompatActivity {

    private TextView titleView;
    private ArrayAdapter<String> adapter;
    private int index;
    private ArrayList<Habit> jsonList;
    private ArrayList<String> occurrences = new ArrayList<String>();

    private HabitListController habitListController;
    private HabitController habitController;

    private static final String FILENAME = "file.sav";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_habit);

    }

    // Read checkbox state for record keeping
    public void DetailsPage_onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();
        String day;
        switch(view.getId()) {
            case R.id.DetailsPage_sunday:
                day = "Sunday";
                if (checked) {
                    this.occurrences.add(day);
                }
                else {
                    if (this.occurrences.contains(day)) {
                        this.occurrences.remove(day);
                    }
                }
                break;

            case R.id.DetailsPage_monday:
                day = "Monday";
                if (checked) {
                    this.occurrences.add(day);
                }
                else {
                    if (this.occurrences.contains(day)) {
                        this.occurrences.remove(day);
                    }
                }
                break;

            case R.id.DetailsPage_tuesday:
                day = "Tuesday";
                if (checked) {
                    this.occurrences.add(day);
                }
                else {
                    if (this.occurrences.contains(day)) {
                        this.occurrences.remove(day);
                    }
                }
                break;

            case R.id.DetailsPage_wednesday:
                day = "Wednesday";
                if (checked) {
                    this.occurrences.add(day);
                }
                else {
                    if (this.occurrences.contains(day)) {
                        this.occurrences.remove(day);
                    }
                }
                break;

            case R.id.DetailsPage_thursday:
                day = "Thursday";
                if (checked) {
                    this.occurrences.add(day);
                }
                else {
                    if (this.occurrences.contains(day)) {
                        this.occurrences.remove(day);
                    }
                }
                break;

            case R.id.DetailsPage_friday:
                day = "Friday";
                if (checked) {
                    this.occurrences.add(day);
                }
                else {
                    if (this.occurrences.contains(day)) {
                        this.occurrences.remove(day);
                    }
                }
                break;

            case R.id.DetailsPage_saturday:
                day = "Saturday";
                if (checked) {
                    this.occurrences.add(day);
                }
                else {
                    if (this.occurrences.contains(day)) {
                        this.occurrences.remove(day);
                    }
                }
                break;

        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        loadFromFile();

        this.habitListController = new HabitListController(this.jsonList);
        //this.habitListController.makeHabitList(this.jsonList);

        this.index = getIntent().getIntExtra("Position", 0);

        Habit habit = this.habitListController.getHabit(this.index);
        this.habitController = new HabitController(habit);

        toogleCheckbox();

        this.occurrences = this.habitController.getOccurrences();

        this.titleView = (TextView) findViewById(R.id.OpenHabit_title);
        this.titleView.setText(this.habitController.getTitle());
        changeAdapter();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Button doneButton2 = (Button) findViewById(R.id.DetailsPage_done_button);
        doneButton2.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        habitController.addOccurrence(occurrences);
                        saveInFile();
                        finish();

                    }
                }
        );

        final ListView habitListView = (ListView) findViewById(R.id.DetailsPage_list);
        habitListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (Integer.valueOf(habitController.getHistoryCount()) > 0) {

                            habitController.removeHistory(position);

                            Habit habit = habitController.getThis();
                            habitListController.replaceHabit(habit, index);

                            changeAdapter();
                            saveInFile();
                        }
                    }
                }
        );
    }

    // Changing checkBox state based on the saved checkbox.
    private void toogleCheckbox() {
        ArrayList<String> days = this.habitController.getOccurrences();
        int range = days.size();
        for (int i=0; i<range;i++) {
            String day = days.get(i);
            CheckBox checkBox1;
            switch (day) {
                case "Monday":
                    checkBox1 = (CheckBox) findViewById(R.id.DetailsPage_monday);
                    checkBox1.setChecked(true);
                    break;
                case "Tuesday":
                    checkBox1 = (CheckBox) findViewById(R.id.DetailsPage_tuesday);
                    checkBox1.setChecked(true);
                    break;
                case "Wednesday":
                    checkBox1 = (CheckBox) findViewById(R.id.DetailsPage_wednesday);
                    checkBox1.setChecked(true);
                    break;
                case "Thursday":
                    checkBox1 = (CheckBox) findViewById(R.id.DetailsPage_thursday);
                    checkBox1.setChecked(true);
                    break;
                case "Friday":
                    checkBox1 = (CheckBox) findViewById(R.id.DetailsPage_friday);
                    checkBox1.setChecked(true);
                    break;
                case "Saturday":
                    checkBox1 = (CheckBox) findViewById(R.id.DetailsPage_saturday);
                    checkBox1.setChecked(true);
                    break;
                case "Sunday":
                    checkBox1 = (CheckBox) findViewById(R.id.DetailsPage_sunday);
                    checkBox1.setChecked(true);
                    break;
            }
        }
    }

    private void changeAdapter() {
        ArrayList<String> dates = this.habitController.getHistory();

        this.adapter = new ArrayAdapter<String>(this, R.layout.list_item, dates);
        ListView listView = (ListView) findViewById(R.id.DetailsPage_list);
        listView.setAdapter(this.adapter);
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

    //This will load the old habitList
    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            // Code from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            Type listType = new TypeToken<ArrayList<Habit>>(){}.getType();
            this.jsonList = gson.fromJson(in,listType);
            //this.habitList = new HabitList(jsonList);

        } catch (FileNotFoundException e) {
			/* Create a brand new tweet list if we can't find the file. */
            this.habitListController = new HabitListController();
        }
    }



}
