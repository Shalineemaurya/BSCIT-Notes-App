package com.example.useraccount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class bscIT_first_activity extends AppCompatActivity {
    Button sem1 ,sem2,sem3,sem4,sem5,sem6;
    Button logout;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bsc_i_t_first_activity);

        logout=findViewById(R.id.logout);
        mAuth=FirebaseAuth.getInstance();


        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        getSupportActionBar().hide();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent=new Intent(bscIT_first_activity.this,SignIn_Activity.class);

                startActivity(intent);
                finish();
            }
        });


        sem1 = findViewById(R.id.btnsem1);
        sem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent( bscIT_first_activity.this, Sem_1_Act.class);
                startActivity(intent);
            }
        });
        sem2 = findViewById(R.id.btnsem2);
        sem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(bscIT_first_activity.this, Sem_2.class);
                startActivity(intent);
            }
        });

        sem3 = findViewById(R.id.btnsem3);
        sem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(bscIT_first_activity.this, Sem_3.class);
                startActivity(intent);
            }
        });
        sem4 = findViewById(R.id.btnsem4);
        sem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(bscIT_first_activity.this, Sem_4.class);
                startActivity(intent);
            }
        });

        sem5 = findViewById(R.id.btnsem5);
        sem5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(bscIT_first_activity.this, Sem_5.class);
                startActivity(intent);
            }
        });
        sem6 = findViewById(R.id.btnsem6);
        sem6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(bscIT_first_activity.this, Sem_6.class);
                startActivity(intent);
            }
        });
        }
}