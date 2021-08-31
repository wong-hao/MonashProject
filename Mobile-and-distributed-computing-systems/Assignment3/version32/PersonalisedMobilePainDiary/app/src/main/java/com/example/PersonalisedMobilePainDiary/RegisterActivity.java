package com.example.PersonalisedMobilePainDiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.PersonalisedMobilePainDiary.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    private boolean Hidden;
    private FirebaseAuth auth;
    private static final String TAG = "registertag";

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

            //User.getInstance().register(username,password);
            //Toast.makeText(RegisterActivity.this,"Register Successfully",Toast.LENGTH_SHORT).show();

            auth = FirebaseAuth.getInstance();
            auth.createUserWithEmailAndPassword(username,password).addOnCompleteListener(
                    new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.

                            if(task.isSuccessful()) {
                                // do something, e.g. start the other activity
                                Toast.makeText(RegisterActivity.this,"Register Successfully",Toast.LENGTH_SHORT).show();
                            }else {
                                //do something, e.g. give a message
                                Toast.makeText(RegisterActivity.this,"Register Failed",Toast.LENGTH_SHORT).show();
                                Log.d(TAG, "onComplete: Failed=" + task.getException().getMessage()); //ADD THIS

                            }

                        }
                    });

            finish();
        });
    }
}