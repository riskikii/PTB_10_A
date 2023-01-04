package com.example.ptb_10_a;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ptb_10_a.Adapter.ListActivityAdapter;
import com.example.ptb_10_a.models.LogbooksItem;
import com.example.ptb_10_a.models.LogbooksResponse;
import com.example.ptb_10_a.models.LogoutResponse;
import com.example.ptb_10_a.retrofit.APIClient;
import com.example.ptb_10_a.retrofit.InterfaceMahasiswa;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogbookActivity extends AppCompatActivity{
    private RecyclerView listLogbook;
    private ListActivityAdapter adapter;
    private ArrayList<ListLbActivity> list = new ArrayList<>();

    SharedPreferences sharedPref;
    InterfaceMahasiswa interfaceMahasiswa;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logbook);

        listLogbook = findViewById(R.id.logbookRcv);
        listLogbook.setHasFixedSize(true);

        list.addAll(getListLogbooks());
        listLogbook.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ListActivityAdapter();
        listLogbook.setAdapter(adapter);

        interfaceMahasiswa = APIClient.getClient().create(InterfaceMahasiswa.class);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String token = sharedPref.getString("TOKEN", "");

        Call<LogbooksResponse> call = interfaceMahasiswa.getLB("Bearer " +token);
        call.enqueue(new Callback<LogbooksResponse>() {
            @Override
            public void onResponse(Call<LogbooksResponse> call, Response<LogbooksResponse> response) {
                Log.d("Logbook_Debug", response.toString());

                LogbooksResponse logbooksResponse = response.body();
                if (logbooksResponse != null){
                    List<LogbooksItem> logbooks = logbooksResponse.getLogbooks();
                    Log.d("Debug2", String.valueOf(logbooks.size()));
                    adapter.setItemList(logbooks);
                }
            }

            @Override
            public void onFailure(Call<LogbooksResponse> call, Throwable t) {

            }
        });

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
                        startActivity(new Intent(getApplicationContext(), Home2Activity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
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

    private void showSelectedLb(ListLbActivity listLbActivity) {
        Intent detailIntent = new Intent(this, EditlogbookActivity.class);
        detailIntent .putExtra("Catatan", listLbActivity.getCatatan());
        detailIntent .putExtra("Tanggal", listLbActivity.getTanggal());
        startActivity(detailIntent);

//        Toast.makeText(this,"kjklkj"+ listLbActivity.getCatatan(), Toast.LENGTH_SHORT).show();
    }





}