package com.example.useraccount;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {
    private EditText emailEditText;
    private Button resetButton;
    String email;


    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        getSupportActionBar().hide();

        emailEditText= (EditText)findViewById(R.id.editTextTextEmailAddress2);
        resetButton=(Button)findViewById(R.id.resetbtn);


        mAuth = FirebaseAuth.getInstance();

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });
    }
    private  void resetPassword(){
        email = emailEditText.getText().toString();

        if(email.isEmpty()){
            emailEditText.setError("Email is required");

        }
        else{
            forgotPass();
        }

    }
    private  void forgotPass(){
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ForgotPassword.this, "Check your email", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ForgotPassword.this,SignIn_Activity.class));
                    finish();
                }else{
                    Toast.makeText(ForgotPassword.this, "Error:"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}