package com.example.ptb_10_a;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.content.Intent;

import com.google.android.material.textfield.TextInputLayout;

public class loginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


    }

    public void login(View view) {
        Intent intent = new Intent(loginActivity.this,HomeActivity.class);
        startActivity(intent);

    }
}