package com.example.ptb_10_a;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.ptb_10_a.Adapter.ListActivityAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Home2Activity extends AppCompatActivity {

    private RecyclerView listLogbook;
    private ArrayList<ListLbActivity> list = new ArrayList<>();

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        listLogbook = findViewById(R.id.listLogbook);
        listLogbook.setHasFixedSize(true);

        list.addAll(getListLogbooks());

        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);

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
                        return true;

                }
                return false;
            }
        });


    }

    public void daftarTA(View view) {
        Intent intent = new Intent(Home2Activity.this,AddtaActivity.class);
        startActivity(intent);
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
        Intent intent = new Intent(Home2Activity.this,AddlogbookActivity.class);
        startActivity(intent);
    }

    public void detailLogbook(View view) {
        Intent intent = new Intent(Home2Activity.this,EditlogbookActivity.class);
        startActivity(intent);
    }

    private void showSelectedLb(ListLbActivity listLbActivity) {
        Intent detailIntent = new Intent(this, EditlogbookActivity.class);
        detailIntent .putExtra("Catatan", listLbActivity.getCatatan());
        detailIntent .putExtra("Tanggal", listLbActivity.getTanggal());
        startActivity(detailIntent);
    }
}