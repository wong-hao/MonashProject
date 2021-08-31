package com.example.PersonalisedMobilePainDiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.PersonalisedMobilePainDiary.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private boolean Hidden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked){
                    Toast.makeText(getApplicationContext(),"The Password is not hidden",Toast.LENGTH_SHORT).show();
                    Hidden = true;

                    if(Hidden){
                        binding.editText2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                    }else{
                        binding.editText2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"The Password is hidden",Toast.LENGTH_SHORT).show();
                    Hidden = false;

                    if(Hidden){
                        binding.editText2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                    }else{
                        binding.editText2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }
                }
            }
        });


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