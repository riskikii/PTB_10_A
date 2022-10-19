package com.example.ptb_10_a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AddtaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addta);
    }

    public void back(View view) {
        Intent intent = new Intent(AddtaActivity.this,HomeActivity.class);
        startActivity(intent);
    }

    public void DaftarTA(View view) {
        Intent intent = new Intent(AddtaActivity.this,Home2Activity.class);
        startActivity(intent);
    }
}