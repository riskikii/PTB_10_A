package com.example.ptb_10_a;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SeminarActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seminar);
        

        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.nav_semhas);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.nav_logbook:
                        startActivity(new Intent(getApplicationContext(),LogbookActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nav_semhas:
                        return true;

                    case R.id.nav_sidang:
                        startActivity(new Intent(getApplicationContext(), SidangActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nav_profile:
                        startActivity(new Intent(getApplicationContext(),ProfilActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(),Home2Activity.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });
    }
    public void ajukanSemhas(View view) {
        Intent intent = new Intent(SeminarActivity.this,AddSeminarActivity.class);
        startActivity(intent);
    }
    

    public void kembali(View view) {
        Intent intent = new Intent(SeminarActivity.this,Home2Activity.class);
        startActivity(intent);
    }
}
