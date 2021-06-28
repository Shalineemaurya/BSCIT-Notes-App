package com.example.useraccount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.github.barteksc.pdfviewer.PDFView;

public class Sem_1_Act extends AppCompatActivity {

    Button  ip1,os1,cs1,dm1,de1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sem_1_);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        getSupportActionBar().hide();

        ip1 = findViewById(R.id.ip);
        ip1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent( Sem_1_Act.this, IP_act1.class);
                startActivity(intent);
            }
        });

        os1 = findViewById(R.id.os);
        os1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent( Sem_1_Act.this, OS_act1.class);
                startActivity(intent);
            }
        });

        de1 = findViewById(R.id.de);
        de1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent( Sem_1_Act.this, DE_act1.class);
                startActivity(intent);
            }
        });






    }
}