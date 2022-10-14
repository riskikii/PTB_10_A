package com.example.ptb_10_a;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class LogbookActivity extends AppCompatActivity{
    private RecyclerView listLogbook;
    private ArrayList<ListLbActivity> list = new ArrayList<>();
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logbook);

        listLogbook = findViewById(R.id.logbookRcv);
        listLogbook.setHasFixedSize(true);

        list.addAll(getListLogbooks());
        showRecyclerList();



        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.nav_logbook);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.nav_logbook:
                        return true;

                    case R.id.nav_semhas:
                        startActivity(new Intent(getApplicationContext(),SeminarActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nav_sidang:
                        startActivity(new Intent(getApplicationContext(),SidangActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nav_profile:o:
                        startActivity(new Intent(getApplicationContext(), ProfilActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }

    private void showRecyclerList() {
        listLogbook.setLayoutManager(new LinearLayoutManager(this));
        ListActivityAdapter listHeroAdapter = new ListActivityAdapter(list);
        listLogbook.setAdapter(listHeroAdapter);
    }

    public ArrayList<ListLbActivity> getListLogbooks(){
        String[] tanggal_logbook = getResources().getStringArray(R.array.tanggal_logbook);
        String[] catatan_logbook = getResources().getStringArray(R.array.catatan_logbook);
        ArrayList<ListLbActivity> listLbactivity = new ArrayList<>();
        for (int i = 0; i < tanggal_logbook.length; i++) {
            ListLbActivity listLbActivity = new ListLbActivity();
            listLbActivity.setTanggal(tanggal_logbook[i]);
            listLbActivity.setCatatan(catatan_logbook[i]);
            listLbactivity.add(listLbActivity);
        }
        return listLbactivity;
    }

    public void tambahLB(View view) {
        Intent intent = new Intent(LogbookActivity.this,AddlogbookActivity.class);
        startActivity(intent);
    }

    public void detailLogbook(View view) {
        Intent intent = new Intent(LogbookActivity.this,EditlogbookActivity.class);
        startActivity(intent);
    }



}