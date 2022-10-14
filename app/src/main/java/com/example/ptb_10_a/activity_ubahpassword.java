package com.example.ptb_10_a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class activity_ubahpassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubahpassword);
    }

    public void UbahPasword(View view) {
        Intent intent = new Intent(activity_ubahpassword.this,ProfilActivity.class);
        startActivity(intent);
    }
}