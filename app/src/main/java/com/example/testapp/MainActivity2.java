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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2 extends AppCompatActivity {
    private EditText usernameReg;
    private EditText passwordReg;
    private Button buttonR;
    private FirebaseAuth firebaseR;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        firebaseR = FirebaseAuth.getInstance();
        usernameReg = findViewById(R.id.editTextReg);
        passwordReg = findViewById(R.id.editTextReg1);
        buttonR = findViewById(R.id.buttonReg);

        buttonR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (usernameReg.getText().toString().isEmpty() || passwordReg.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity2.this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    firebaseR.createUserWithEmailAndPassword(usernameReg.getText().toString(), passwordReg.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Intent intent =  new Intent(MainActivity2.this, MainActivity4.class);
                                        startActivity(intent);
                                    }
                                    else {
                                        Toast.makeText(MainActivity2.this, "Something  get wrong!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }
            }
        });
    }

}