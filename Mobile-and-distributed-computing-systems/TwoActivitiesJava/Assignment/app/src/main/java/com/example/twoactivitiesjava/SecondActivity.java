package com.example.twoactivitiesjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.twoactivitiesjava.databinding.ActivitySecondBinding;

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

                String message1= binding.editText1.getText().toString();
                String message2= binding.editText2.getText().toString();
                String message3= binding.editText3.getText().toString();

                Student student = new Student(message1,message2,Integer.parseInt(message3));
                Bundle bundle = new Bundle();
                bundle.putParcelable("student",student);
                returnIntent.putExtra("bundle",bundle);

                setResult(RESULT_OK,returnIntent);
                finish();

            } });
    } }