package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity4 extends AppCompatActivity {

    private TextView textUser;
    private ImageButton aboutUS;

    private ImageButton shop;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        aboutUS = findViewById(R.id.aboutUsBut);
        shop = findViewById(R.id.SHOP);
        aboutUS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity4.this, MainActivity9.class);
                startActivity(intent);
            }
        });

        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity4.this, MainActivity10.class);
                startActivity(intent);
            }
        });
        textUser = findViewById(R.id.textView6);
        textUser.setText(getIntent().getStringExtra("gmail"));

    }
}