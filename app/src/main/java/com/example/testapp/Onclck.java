package com.example.testapp;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testapp.data.model.LoggedInUser;
import com.example.testapp.ui.login.LoginActivity;
import com.example.testapp.ui.login.LoginViewModel;

public class Onclck extends AppCompatActivity{


    public void onClck(View view) {
    Intent intent = new Intent(this, LoginActivity.class);
    startActivity(intent);

    }

}