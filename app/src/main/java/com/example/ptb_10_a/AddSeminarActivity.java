package com.example.ptb_10_a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AddSeminarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addseminar);
    }

    public void AjukanSeminar(View view) {
        Intent intent = new Intent(AddSeminarActivity.this,TambahPesertaActivity.class);
        startActivity(intent);
    }
}