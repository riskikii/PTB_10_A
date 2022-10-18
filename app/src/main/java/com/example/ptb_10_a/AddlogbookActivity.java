package com.example.ptb_10_a;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class AddlogbookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addlogbook);
    }

    public void back(View view) {
        Intent intent = new Intent(AddlogbookActivity.this,LogbookActivity.class);
        startActivity(intent);
    }
}