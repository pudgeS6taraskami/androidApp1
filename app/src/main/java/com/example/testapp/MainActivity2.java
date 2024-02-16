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
import com.google.firebase.auth.FirebaseUser;

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
                } else {
                    firebaseR.createUserWithEmailAndPassword(usernameReg.getText().toString(), passwordReg.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        verifyEmail();

                                    }
                                    redirectToMainActivity4();
                                }
                            });
                }
            }
        });
    }

    private void redirectToMainActivity4() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        user.reload();
        if (user.isEmailVerified()) {
            Intent intent = new Intent(MainActivity2.this, MainActivity4.class);
            startActivity(intent);
            Toast.makeText(this, "Ваша почта верифицирована", Toast.LENGTH_SHORT).show();
        }
    }

    private void verifyEmail() {
        assert usernameReg != null;
        FirebaseUser user = firebaseR.getCurrentUser();
        assert user != null;
        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity2.this, "На вашу электронную почту отправлено письмо для верификации", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity2.this, "Ошибка: Письмо не отправлено", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void ver() {
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    if(user !=null) {

        String name = user.getDisplayName();
        String email = user.getEmail();

        boolean emailVerified = user.isEmailVerified();
        Toast.makeText(this, " " + emailVerified, Toast.LENGTH_SHORT).show();
        }
    }
}