package com.example.ptb_10_a;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class EditlogbookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editlogbook);
    }

    public void back(View view) {
        Intent intent = new Intent(EditlogbookActivity.this,LogbookActivity.class);
        startActivity(intent);
    }
}