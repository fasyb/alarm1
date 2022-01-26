package com.example.alarm2;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.net.URI;

public class AlarmActivity2 extends AppCompatActivity {
    Ringtone ringtone;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        Log.d("alarm", "alarm3");
        super.onCreate(savedInstanceState, persistentState);
        Log.d("alarm", "alarm3");
        setContentView(R.layout.activity_alarm);
        Log.d("alarm", "alarm3");
        Uri notificationUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        ringtone = RingtoneManager.getRingtone(this, notificationUri);
        if (ringtone == null){
            notificationUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
            ringtone = RingtoneManager.getRingtone(this, notificationUri);
        }
        if(ringtone != null){
            ringtone.play();
        }
    }

    @Override
    protected void onDestroy() {
        if (ringtone != null && ringtone.isPlaying()){
            ringtone.stop();
        }
        super.onDestroy();
    }
}