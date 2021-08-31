package com.example.twoactivitiesjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.twoactivitiesjava.databinding.ActivitySecondBinding;

/*
public class SecondActivity extends AppCompatActivity {
    private ActivitySecondBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        final Intent intent=getIntent();
        String msg = intent.getStringExtra("message");
        binding.textView.setText(msg);
        binding.startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            } });
    }
}

 */
public class SecondActivity extends AppCompatActivity {
    private ActivitySecondBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = getIntent();

                String message= binding.editText.getText().toString();
                returnIntent.putExtra("message",message);

                String message1= binding.editText1.getText().toString();
                returnIntent.putExtra("message1",message1);

                String message2= binding.editText2.getText().toString();
                returnIntent.putExtra("message2",message2);

                String message3= binding.editText3.getText().toString();
                returnIntent.putExtra("message3",message3);

                setResult(RESULT_OK,returnIntent);
                finish();
            } });
    } }