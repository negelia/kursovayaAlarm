package com.example.kursovayaalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.widget.RadioButton;
import androidx.core.app.NotificationCompat;

public class AlertReceiver extends BroadcastReceiver {

    public static final String APP_PREFERENCES = "mysettings";
    final String KEY_RADIOBUTTON_INDEX = "SAVED_RADIO_BUTTON_INDEX";

    android.media.Ringtone ringTone;
    Uri uriAlarm, uriNotification, uriRingtone;

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
        notificationHelper.getManager().notify(1, nb.build());

        /*Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null)
        {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        Ringtone ringtone = RingtoneManager.getRingtone(context, alarmUri);
        ringtone.play();*/

        /*RingtoneIntent r = new RingtoneIntent();
        android.media.Ringtone newRingtone = r.ringTone;
        newRingtone.play();*/

        SharedPreferences sharedPreferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        int savedRadioIndex = sharedPreferences.getInt(KEY_RADIOBUTTON_INDEX, 0);

        uriAlarm = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        uriNotification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        uriRingtone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);

        if(savedRadioIndex == 0){
            ringTone = RingtoneManager.getRingtone(context, uriAlarm);
        }else if(savedRadioIndex == 1){
            ringTone = RingtoneManager.getRingtone(context, uriNotification);
        }else if(savedRadioIndex == 2){
            ringTone = RingtoneManager.getRingtone(context, uriRingtone);
        }
        ringTone.play();

        //RadioButton savedCheckedRadioButton = (RadioButton) radioGroup.getChildAt(savedRadioIndex);
        //savedCheckedRadioButton.setChecked(true);
    }

}
