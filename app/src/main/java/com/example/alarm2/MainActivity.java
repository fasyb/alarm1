package com.example.alarm2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Button setAlarm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());


        setAlarm = findViewById(R.id.button);
        setAlarm.setOnClickListener(v -> {
            MaterialTimePicker materialTimePicker = new MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_24H)
                    .setHour(12)
                    .setMinute(0)
                    .setTitleText("Set your alarm")
                    .build();
            materialTimePicker.addOnPositiveButtonClickListener(view -> {
                Calendar calendar = Calendar.getInstance();
                calendar.set(calendar.SECOND, 0);
                calendar.set(calendar.MILLISECOND, 0);
                calendar.set(calendar.MINUTE, materialTimePicker.getMinute());
                calendar.set(calendar.HOUR_OF_DAY, materialTimePicker.getHour());

                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

                AlarmManager.AlarmClockInfo alarmClockInfo = new AlarmManager.AlarmClockInfo(calendar.getTimeInMillis(), getAlarmInfoPendingIntent());

                alarmManager.setAlarmClock(alarmClockInfo, getAlarmActionPendingIntent());
                Toast.makeText(this, "Alarm set on " + sdf.format(calendar.getTime()), Toast.LENGTH_SHORT).show();
            });

            materialTimePicker.show(getSupportFragmentManager(), "tag_picker");
        });
    }
    private PendingIntent getAlarmInfoPendingIntent(){
        Log.d("alarm", "alarm1");
        Intent alarmInfoIntent = new Intent(this, MainActivity.class);
        alarmInfoIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_NEW_TASK );
        return PendingIntent.getActivity(this, 0, alarmInfoIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    }
    private  PendingIntent getAlarmActionPendingIntent(){
        Log.d("alarm", "alarm2");

        Intent intent = new Intent(this, AlarmActivity2.class );
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_NEW_TASK );
        return PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }
}