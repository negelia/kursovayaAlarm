package com.example.kursovayaalarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class todoList extends AppCompatActivity {
    ArrayList<String> items = new ArrayList<String>();
    ArrayList<String> selectedItem = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ListView list;
    String newNameAlarm;

    public static final String APP_PREFERENCES = "data";
    SharedPreferences mSettings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(todoList.this);

        list = findViewById(R.id.listView);

        readItems();

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, items);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                String item = adapter.getItem(position);
                if(list.isItemChecked(position))
                    selectedItem.add(item);
                else
                    selectedItem.remove(item);

                writeItems();
            }
        });

        newNameAlarm = pref.getString("alarm name", "");
        if (!newNameAlarm.equals("")) {
            adapter.add(newNameAlarm);
            adapter.notifyDataSetChanged();
        }

        writeItems();
    }

    public void add(View view){
        EditText txt = findViewById(R.id.txt);
        String entry = txt.getText().toString();
        if(!entry.isEmpty()){
            adapter.add(entry);
            txt.setText("");
            adapter.notifyDataSetChanged();

            writeItems();
        }
    }
    public void remove(View view){
        for(int i=0; i< selectedItem.size();i++){
            adapter.remove(selectedItem.get(i));
        }
        list.clearChoices();
        selectedItem.clear();
        adapter.notifyDataSetChanged();

        writeItems();
    }

    private void readItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            items = new ArrayList<String>(FileUtils.readLines(todoFile));
        } catch (IOException e) {
            items = new ArrayList<String>();
        }
    }

    private void writeItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            FileUtils.writeLines(todoFile, items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void list(View view) {
        Intent intent = new Intent(todoList.this, todoList.class);
        startActivity(intent);
    }

    public void alarmList(View view) {
        Intent intent = new Intent(todoList.this, alarmchik.class);
        startActivity(intent);
    }

    public void home(View view) {
        Intent intent = new Intent(todoList.this, MainActivity.class);
        startActivity(intent);
    }
}