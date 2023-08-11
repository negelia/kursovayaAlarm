package com.example.kursovayaalarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RingtoneIntent extends AppCompatActivity {

    RadioButton optAlarm, optNotification, optRingtone;
    Button btnStart, btnStop, btnBack;
    android.media.Ringtone ringTone;

    Uri uriAlarm, uriNotification, uriRingtone;

    RadioGroup radioGroup;
    public static final String APP_PREFERENCES = "mysettings";
    final String KEY_RADIOBUTTON_INDEX = "SAVED_RADIO_BUTTON_INDEX";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ringtone_intent);
        optAlarm = (RadioButton)findViewById(R.id.optAlarm);
        optNotification = (RadioButton)findViewById(R.id.optNotification);
        optRingtone = (RadioButton)findViewById(R.id.optRingtone);
        btnStart = (Button)findViewById(R.id.start);
        btnStop = (Button)findViewById(R.id.stop);
        btnBack = (Button)findViewById(R.id.back);

        radioGroup = findViewById(R.id.radiogroup);
        radioGroup.setOnCheckedChangeListener( new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton checkedRadioButton = (RadioButton) radioGroup.findViewById(checkedId);
                int checkedIndex = radioGroup.indexOfChild(checkedRadioButton);

                SavePreferences(KEY_RADIOBUTTON_INDEX, checkedIndex);
            }
        });
        //LoadPreferences();

        uriAlarm = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        uriNotification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        uriRingtone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);

        /*optAlarm.setText("uriAlarm: " + uriAlarm);
        optNotification.setText("uriNotification: " + uriNotification);
        optRingtone.setText("uriRingtone: " + uriRingtone);*/

        btnStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(ringTone!=null){
                    ringTone.stop();
                }

                if(optAlarm.isChecked()){
                    ringTone = RingtoneManager.getRingtone(getApplicationContext(), uriAlarm);
                }else if(optNotification.isChecked()){
                    ringTone = RingtoneManager.getRingtone(getApplicationContext(), uriNotification);
                }else if(optRingtone.isChecked()){
                    ringTone = RingtoneManager.getRingtone(getApplicationContext(), uriRingtone);
                }

                Toast.makeText(RingtoneIntent.this,
                        ringTone.getTitle(RingtoneIntent.this),
                        Toast.LENGTH_LONG).show();
                ringTone.play();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(ringTone!=null){
                    ringTone.stop();
                    ringTone = null;
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RingtoneIntent.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void SavePreferences(String key, int value) {
        SharedPreferences sharedPreferences = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    /*private void LoadPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
        int savedRadioIndex = sharedPreferences.getInt(KEY_RADIOBUTTON_INDEX, 0);
        RadioButton savedCheckedRadioButton = (RadioButton) radioGroup.getChildAt(savedRadioIndex);
        savedCheckedRadioButton.setChecked(true);
    }*/

    public void list(View view) {
        Intent intent = new Intent(RingtoneIntent.this, todoList.class);
        startActivity(intent);
    }

    public void alarmList(View view) {
        Intent intent = new Intent(RingtoneIntent.this, alarmchik.class);
        startActivity(intent);
    }

    public void home(View view) {
        Intent intent = new Intent(RingtoneIntent.this, MainActivity.class);
        startActivity(intent);
    }
}