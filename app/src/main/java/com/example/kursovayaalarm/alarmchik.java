package com.example.kursovayaalarm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;

import com.google.gson.Gson;

import java.util.ArrayList;

import java.lang.reflect.Type;

public class alarmchik extends AppCompatActivity {
    ArrayList<Model> movies = new ArrayList<Model>();

    public static final String APP_PREFERENCES = "data";

    SharedPreferences mSettings;

    String newAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarmchik);

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        loadData();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerAdapter adapter = new RecyclerAdapter(this, movies);
        recyclerView.setAdapter(adapter);

        SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(alarmchik.this);
        newAlarm = pref.getString("time", "");
        if (!newAlarm.equals("")){
            movies.add(new Model (newAlarm));
            adapter.notifyItemInserted(movies.size());
        }

        saveData();

    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(movies);
        editor.putString("courses", json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("courses", null);
        Type type = new TypeToken<ArrayList<Model>>() {}.getType();
        movies = gson.fromJson(json, type);
        if (movies == null) {
            movies = new ArrayList<>();
        }
    }

    public void list(View view) {
        Intent intent = new Intent(alarmchik.this, todoList.class);
        startActivity(intent);
    }

    public void alarmList(View view) {
        Intent intent = new Intent(alarmchik.this, alarmchik.class);
        startActivity(intent);
    }

    public void home(View view) {
        Intent intent = new Intent(alarmchik.this, MainActivity.class);
        startActivity(intent);
    }
}