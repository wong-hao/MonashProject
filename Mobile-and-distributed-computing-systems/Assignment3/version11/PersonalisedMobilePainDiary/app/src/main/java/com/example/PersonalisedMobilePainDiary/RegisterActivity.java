package com.example.PersonalisedMobilePainDiary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.PersonalisedMobilePainDiary.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.Button3.setOnClickListener(v->{
            String username= binding.editText3.getText().toString();
            String password= binding.editText4.getText().toString();

            User.getInstance().register(username,password);
            Toast.makeText(RegisterActivity.this,"Register Successfully",Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}