package com.example.PersonalisedMobilePainDiary.bean;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Time to enter daily record",
                Toast.LENGTH_SHORT).show();
    }
}
