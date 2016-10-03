package com.example.ccastell_habittracker;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private SimpleAdapter adapter;
    private ArrayList<Habit> jsonList;
    private int range;
    private Date currentDate;
    private HabitListController habitListController;
    private static final String FILENAME = "file.sav";
    private List<Map<String,String>> Data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String dateFormatted = dateFormat.format(currentDate);
        System.out.println("Main Activity " + dateFormatted);

    }

    //
    @Override
    protected void onStart() {
        super.onStart();
        loadFromFile();
        changeAdapter();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Button addButton = (Button) findViewById(R.id.Main_add_button);
        addButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        Intent intent= new Intent(MainActivity.this, AddHabitActivity.class);
                        intent.putExtra("Date",currentDate.getTime());
                        startActivityForResult(intent,0);
                    }
                }
        );

        ListView habitListView = (ListView) findViewById(R.id.Main_habit_list);
        habitListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(MainActivity.this, OpenHabitActivity.class);
                        /*http://stackoverflow.com/questions/7074097/how-to-pass-integer-from-one-activity-to-another*/
                        intent.putExtra("Date",currentDate.getTime());
                        intent.putExtra("Position", position);
                        startActivityForResult(intent,0);
                    }
                }
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.adapter.notifyDataSetChanged();
        changeAdapter();

    }

    private void changeAdapter() {
        ListView listView = (ListView) findViewById(R.id.Main_habit_list);

        this.range = this.habitListController.getHabitListCount();
        this.Data = new  ArrayList<Map<String,String>>();
        for (int i=0; i<range; i++) {

            Habit habit = this.habitListController.getHabit(i);
            HabitController habitController = new HabitController(habit);

            Map<String,String> datum = new HashMap<String,String>(2);
            datum.put("title",habitController.getTitle());
            datum.put("date",habitController.getDate());
            this.Data.add(datum);
        }

        this.adapter = new SimpleAdapter(
                this,
                this.Data,
                android.R.layout.simple_list_item_2,
                new String[] {"title","date","occurrences"},
                new int[] {android.R.id.text1,android.R.id.text2}
        ) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View row = super.getView(position, convertView, parent);
                TextView text = (TextView) row.findViewById(android.R.id.text1);

                SimpleDateFormat daysFormat = new SimpleDateFormat("EEEE");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
                String day = daysFormat.format(currentDate);
                String date = dateFormat.format(currentDate);

                Habit habit = habitListController.getHabit(position);
                HabitController habitController = new HabitController(habit);

                if (habitController.getOccurrences().contains(day)){
                    if (habitController.getHistory().contains(date) && Integer.valueOf(habitController.getHistoryCount()) == 0) {
                        text.setTextColor(Color.RED);
                    }
                    if (!habitController.getHistory().contains(date)) {
                        text.setTextColor(Color.RED);
                    }
                }
                else {
                    text.setTextColor(Color.BLACK);
                }

                return  row;
            }
        };
        listView.setAdapter(this.adapter);
    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            // Code from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            Type listType = new TypeToken<ArrayList<Habit>>(){}.getType();
            this.jsonList = gson.fromJson(in,listType);
            this.habitListController = new HabitListController(this.jsonList);
            //System.out.println(this.jsonList.size());

        } catch (FileNotFoundException e) {
			/* Create a brand new tweet list if we can't find the file. */
            this.habitListController = new HabitListController();
        }
    }
}
