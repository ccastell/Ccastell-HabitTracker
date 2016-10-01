package com.example.ccastell_habittracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<Habit> adapter;
    private ListView listView;
    private ArrayList<Habit> jsonList;
    private HabitList habitList;
    private static final String FILENAME = "file.sav";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addButton = (Button) findViewById(R.id.Main_add_button);
        addButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        Intent intent= new Intent(MainActivity.this, AddHabitActivity.class);
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
                        intent.putExtra("Position", position);
                        startActivityForResult(intent,0);
                    }
                }
        );


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        adapter.notifyDataSetChanged();
    }
    //
    @Override
    protected void onStart() {
        super.onStart();
        loadFromFile();
        this.habitList = new HabitList(jsonList);

        adapter = new ArrayAdapter<Habit>(this, R.layout.list_item,this.habitList.getHabitList());
        listView = (ListView) findViewById(R.id.Main_habit_list);
        listView.setAdapter(adapter);

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
            habitList = new HabitList();
        }
    }
}
