package com.example.testapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.Executor;

public class MainActivity3 extends AppCompatActivity {

    private Button buttonB;
    private ImageButton buttonL;
    private EditText username;
    private EditText password;
    private FirebaseAuth firebase;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        buttonB = findViewById(R.id.buttonBio);
        buttonL = findViewById(R.id.buttonLogin);
        username = findViewById(R.id.editTextLogin1);
        password = findViewById(R.id.editTextLogin);
        firebase = FirebaseAuth.getInstance();
        buttonL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = firebase.getCurrentUser();
                if (username.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity3.this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                } else {
                    firebase.signInWithEmailAndPassword(username.getText().toString(), password.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        if (user.isEmailVerified()) {
                                            Intent intent = new Intent(MainActivity3.this, MainActivity4.class);
                                            intent.putExtra("gmail", username.getText().toString());
                                            startActivity(intent);
                                        }
                                    } else {
                                        Toast.makeText(MainActivity3.this, "You have errors", Toast.LENGTH_SHORT);
                                    }
                                }
                            });
                }

            }
        });

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkDeviceSupportsBiometric()) {
                    showBiometricPrompt();
                } else {
                    Toast.makeText(MainActivity3.this, "Device doesn't support biometric authentication", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    private boolean checkDeviceSupportsBiometric() {
        BiometricManager biometricManager = BiometricManager.from(this);
        return biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_WEAK) == BiometricManager.BIOMETRIC_SUCCESS;
    }


    private void showBiometricPrompt() {
            BiometricPrompt biometricPrompt = new BiometricPrompt(this,
                    new Executor() {
                        @Override
                        public void execute(@NonNull Runnable command) {
                            command.run();
                        }
                    },
                    new BiometricPrompt.AuthenticationCallback() {
                        @Override
                        public void onAuthenticationError(int errorCode,
                                                          @NonNull CharSequence errString) {
                            super.onAuthenticationError(errorCode, errString);
                            Toast.makeText(getApplicationContext(),
                                            "Authentication error: " + errString, Toast.LENGTH_SHORT)
                                    .show();
                        }

                        @Override
                        public void onAuthenticationSucceeded(
                                @NonNull BiometricPrompt.AuthenticationResult result) {
                            super.onAuthenticationSucceeded(result);
                            Toast.makeText(getApplicationContext(),
                                            "Authentication succeeded!", Toast.LENGTH_SHORT)
                                    .show();
                        }

                        @Override
                        public void onAuthenticationFailed() {
                            super.onAuthenticationFailed();
                            Toast.makeText(getApplicationContext(), "Authentication failed",
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                    });

            BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                    .setTitle("Biometric login for my app")
                    .setSubtitle("Log in using your biometric credential")
                    .setNegativeButtonText("Use account password")
                    .build();

            // Display the prompt
            biometricPrompt.authenticate(promptInfo);
        }






    }
