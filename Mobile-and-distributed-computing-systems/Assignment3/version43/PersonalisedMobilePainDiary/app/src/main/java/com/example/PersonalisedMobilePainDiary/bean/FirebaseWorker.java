package com.example.PersonalisedMobilePainDiary.bean;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.PersonalisedMobilePainDiary.entity.PainRecord;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class FirebaseWorker extends Worker {

    private DatabaseReference mDatabase;
    int painRecordID = 2;

    private static final String TAG = "worktag";

    public FirebaseWorker(@NonNull @NotNull Context context, @NonNull @NotNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @NotNull
    @Override
    public Result doWork() {

        int intensity = getInputData().getInt("intensity",0);
        String location = getInputData().getString("location");
        String mood = getInputData().getString("mood");
        int steps = getInputData().getInt("steps",0);
        String temperature = getInputData().getString("temperature");
        String humidity = getInputData().getString("humidity");
        String pressure = getInputData().getString("pressure");
        String date = getInputData().getString("date");
        String email = getInputData().getString("email");

        System.out.println("Intensity: " + intensity);
        //System.out.println("Location: " + location);
        //System.out.println("Mood: " + mood);
        //System.out.println("Steps: " + steps);
        //System.out.println("Temperature: " + temperature);
        //System.out.println("Humidity: " +humidity);
        //System.out.println("Pressure: " +pressure);
        System.out.println("Date: " +date);
        //System.out.println("Email: " +email);

        PainRecord painRecord = new PainRecord(intensity,location,mood,steps,date,temperature,humidity,pressure,email);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("painRecord").child(String.valueOf(painRecordID++)).setValue(painRecord);


        Log.d(TAG, "Object" + painRecordID++ + " is uploaded to firebase\n" + "The daily record details->" + " intensity: " + intensity + ", location: " + location + ",  mood: " + mood + ", steps: " + steps + ", temperature" + temperature + ", humidity: " + humidity + ", pressure: " + pressure + ", date: " + date + ", email: " + email);
        return Result.success();//结果返回为成功
    }
}
