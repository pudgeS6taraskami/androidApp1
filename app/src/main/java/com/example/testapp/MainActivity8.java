package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity8 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
    }

    public void onClickRegister(View view) {
        Intent intent = new Intent(MainActivity8.this, MainActivity2.class);
        startActivity(intent);
    }

    public void onClickLogin(View view) {
        Intent intent = new Intent(MainActivity8.this, MainActivity3.class);
        startActivity(intent);
    }
}