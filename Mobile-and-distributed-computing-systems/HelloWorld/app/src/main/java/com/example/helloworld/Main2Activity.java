package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toast.makeText(getApplication(),"Pain intensity level that you choose is ：",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(Main2Activity.this,MainActivity.class);
        startActivity(intent);

    }
}

