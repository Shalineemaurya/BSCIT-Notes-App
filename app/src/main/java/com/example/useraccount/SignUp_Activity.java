package com.example.useraccount;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp_Activity extends AppCompatActivity {
    TextView btn;
    private EditText inputUsername,inputContactNo,inputEmail,inputPassword;
    Button btnSignUp;
    private FirebaseAuth mAuth;
    private ProgressDialog mLoadingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btn= findViewById(R.id.textview02);
        inputUsername = findViewById(R.id.editTextTextPersonName);
        inputContactNo = findViewById(R.id.editTextPhone);
        inputEmail = findViewById(R.id.editTextTextEmailAddress);
        inputPassword = findViewById(R.id.editTextTextPassword2);
        mAuth=FirebaseAuth.getInstance();
        mLoadingBar=new ProgressDialog(SignUp_Activity.this);

        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCredential();
            }
        });

        checkCredential();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp_Activity.this,SignIn_Activity.class));
            }
        });

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        getSupportActionBar().hide();




    }
    private void checkCredential(){
        String username = inputUsername.getText().toString();
        String contactNo = inputContactNo.getText().toString();
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (username.isEmpty()  || username.length()<7 )
        {
            showError(inputUsername,"Username is not valid");
        }
        else if (contactNo.isEmpty()){
            showError(inputContactNo, "invalid  contact number");
        }

        else if (email.isEmpty()  || (!email.matches(emailPattern)))
        {
            showError(inputEmail, "Email is not valid");
        }
        else if (password.isEmpty()  || password.length()<7)
        {
            showError(inputPassword, "Password must have 7 character");
        }
        else
        {
            mLoadingBar.setTitle("Registration");
            mLoadingBar.setMessage("Please wait,while check your credentials");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(SignUp_Activity.this, "Successfully Registration", Toast.LENGTH_SHORT).show();
                        mLoadingBar.dismiss();
                        Intent intent= new Intent(SignUp_Activity.this,bscIT_first_activity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(SignUp_Activity.this,task.getException().toString(), Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

    }

    private void showError(EditText input, String s) {

        input.setError(s);
        input.requestFocus();
    }

}