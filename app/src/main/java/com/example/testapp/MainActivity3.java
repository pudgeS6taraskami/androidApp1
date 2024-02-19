package com.example.testapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity3 extends AppCompatActivity {
    private Button buttonL;
    private EditText username;
    private EditText password;
    private FirebaseAuth firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        buttonL = findViewById(R.id.buttonLogin);
        username = findViewById(R.id.editTextLogin1);
        password = findViewById(R.id.editTextLogin);
        firebase = FirebaseAuth.getInstance();
        buttonL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = firebase.getCurrentUser();
                if (username.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity3.this,"Fields cannot be empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    firebase.signInWithEmailAndPassword(username.getText().toString(), password.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        if (user.isEmailVerified()) {
                                            Intent intent = new Intent(MainActivity3.this, MainActivity4.class);
                                            startActivity(intent);
                                        }
                                    }
                                    else {
                                        Toast.makeText(MainActivity3.this, "You have errors", Toast.LENGTH_SHORT);
                                    }
                                }
                            });
                }

            }
        });
    }
}