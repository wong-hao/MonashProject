package com.example.PersonalisedMobilePainDiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.PersonalisedMobilePainDiary.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.Button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent, 1);
            } });

        binding.Button1.setOnClickListener(v->{
            String username= binding.editText1.getText().toString();
            String password= binding.editText2.getText().toString();

            if(User.getInstance().login(username,password)){
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivityForResult(intent, 1);
                Toast.makeText(LoginActivity.this,"Login Successfully",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(LoginActivity.this,"Login failed",Toast.LENGTH_SHORT).show();
            }
        });

    }
}