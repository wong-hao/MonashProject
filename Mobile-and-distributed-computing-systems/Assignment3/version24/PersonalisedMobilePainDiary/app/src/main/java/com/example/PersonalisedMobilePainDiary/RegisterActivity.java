package com.example.PersonalisedMobilePainDiary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.PersonalisedMobilePainDiary.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    private boolean Hidden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked){
                    Toast.makeText(getApplicationContext(),"The Password is not hidden",Toast.LENGTH_SHORT).show();
                    Hidden = true;

                    if(Hidden){
                        binding.editText4.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                    }else{
                        binding.editText4.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"The Password is hidden",Toast.LENGTH_SHORT).show();
                    Hidden = false;

                    if(Hidden){
                        binding.editText4.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                    }else{
                        binding.editText4.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }
                }
            }
        });

        binding.Button3.setOnClickListener(v->{
            String username= binding.editText3.getText().toString();
            String password= binding.editText4.getText().toString();

            if(username.isEmpty()){
                Toast.makeText(getApplication(),"Email is empty",Toast.LENGTH_SHORT).show();
                return;
            }
            if(password.isEmpty()){
                Toast.makeText(getApplication(),"Password is empty",Toast.LENGTH_SHORT).show();
                return;
            }

            User.getInstance().register(username,password);
            Toast.makeText(RegisterActivity.this,"Register Successfully",Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}