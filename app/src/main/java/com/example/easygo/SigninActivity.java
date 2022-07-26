package com.example.easygo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SigninActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText email, password;
    private FloatingActionButton btnSignin;
    private TextView textSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_signin);

        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.signin_email);
        password = findViewById(R.id.signin_password);
        btnSignin = findViewById(R.id.signin);
        textSignup = findViewById(R.id.text_signup);

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        textSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SigninActivity.this, com.example.easygo.SignupActivity.class));
            }
        });
    }

    private void login() {
        String user = email.getText().toString().trim();
        String pass = password.getText().toString().trim();
        if (user.isEmpty()) {
            email.setError("Email can not be empty");
        }
        if (pass.isEmpty()) {
            password.setError("Password can not be empty");
        } else {
            mAuth.signInWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(SigninActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SigninActivity.this, com.example.easygo.Dashboard.class));
                    } else {
                        Toast.makeText(SigninActivity.this, "Login Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


    public void OpenSignupPage(View view) {
        startActivity(new Intent(SigninActivity.this, com.example.easygo.SignupActivity.class));
    }

}