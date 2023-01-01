package com.example.ptb_10_a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class PesertaSemhasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpeserta);
    }
    

    public void TambahPeserta(View view) {
        Intent intent = new Intent(PesertaSemhasActivity.this,PesertaActivity.class);
        startActivity(intent);
    }

    
    public void back(View view) {
        Intent intent = new Intent(PesertaSemhasActivity.this,PesertaActivity.class);
        startActivity(intent);
    }
}
