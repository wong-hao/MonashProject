package com.example.PersonalisedMobilePainDiary.bean;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class FirebaseWorker extends Worker {

    private DatabaseReference mDatabase;
    int painRecordID = 0;

    private static final String TAG = "worktag";

    public FirebaseWorker(@NonNull @NotNull Context context, @NonNull @NotNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @NotNull
    @Override
    public Result doWork() {

        int intensity = this.getInputData().getInt("intensity",0);
        //mDatabase = FirebaseDatabase.getInstance().getReference();
        //mDatabase.child("painRecord").child(String.valueOf(painRecordID++)).setValue(painRecord);


        Log.d(TAG, "this_doWork"+this.getInputData().getString("h"));
        return Result.success();//结果返回为成功
    }
}
