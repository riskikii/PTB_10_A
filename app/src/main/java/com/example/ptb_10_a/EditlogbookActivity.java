package com.example.ptb_10_a;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EditlogbookActivity extends AppCompatActivity {

    String catatan, tanggal;
    TextView catatanLogbook, tanggalLB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editlogbook);

        Intent detailIntent = getIntent();
        if (detailIntent != null){
            catatan = detailIntent.getStringExtra("Catatan");
            catatanLogbook = findViewById(R.id.catatanLogbook);
            catatanLogbook.setText(catatan);
            tanggal = detailIntent.getStringExtra("Tanggal");
            tanggalLB = findViewById(R.id.tanggalLB);
            tanggalLB.setText(tanggal);
        }
    }

    public void back(View view) {
        Intent intent = new Intent(EditlogbookActivity.this,LogbookActivity.class);
        startActivity(intent);
    }


}