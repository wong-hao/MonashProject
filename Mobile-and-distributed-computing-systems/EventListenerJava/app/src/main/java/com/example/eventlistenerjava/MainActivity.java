package com.example.eventlistenerjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.eventlistenerjava.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    Button reverseButton;
    Button clearButton;
    EditText editText;

    private ActivityMainBinding binding;//this variable has not been initialised yet
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        reverseButton = findViewById(R.id.reverseBtn);
        clearButton= findViewById(R.id.clearBtn);
        editText = findViewById(R.id.editMessage);

        reverseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String builder= new StringBuilder(editText.getText()).reverse().toString();
                editText.setText(builder);
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });

/*
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        // Get a reference to the root view
        View view = binding.getRoot();

        // The root view needs to be passed as an input to setContentView()
        setContentView(view);

        binding.reverseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String builder= new StringBuilder(binding.editMessage.getText()).reverse().toString();
                binding.editMessage.setText(builder);
            }
        });

        binding.clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.editMessage.setText("");
            }
        });
*/
    }
    }
