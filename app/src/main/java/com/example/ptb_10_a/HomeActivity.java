package com.example.ptb_10_a;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.ptb_10_a.Adapter.ListActivityAdapter;
import com.example.ptb_10_a.models.LogbooksItem;
import com.example.ptb_10_a.models.LogbooksResponse;
import com.example.ptb_10_a.models.LogoutResponse;
import com.example.ptb_10_a.retrofit.APIClient;
import com.example.ptb_10_a.retrofit.InterfaceMahasiswa;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView listLogbook;
    private ListActivityAdapter adapter;
    private ArrayList<ListLbActivity> list = new ArrayList<>();

    BottomNavigationView bottomNavigationView;

    SharedPreferences sharedPref;
    InterfaceMahasiswa interfaceMahasiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        listLogbook = findViewById(R.id.listLogbook);
        listLogbook.setHasFixedSize(true);

        list.addAll(getListLogbooks());
        listLogbook.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ListActivityAdapter();
        listLogbook.setAdapter(adapter);
        adapter.setOnItemClickCallback(data -> showSelectedLb(data));

        interfaceMahasiswa = APIClient.getClient().create(InterfaceMahasiswa.class);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

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

    @Override
    protected void onResume() {
        super.onResume();
        getAllLb();

    }

    private void getAllLb() {
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
    }

    public void daftarTA(View view) {
        Intent intent = new Intent(HomeActivity.this,AddtaActivity.class);
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
        Intent intent = new Intent(HomeActivity.this,AddlogbookActivity.class);
        startActivity(intent);
    }

    public void detailLogbook(View view) {
        Intent intent = new Intent(HomeActivity.this,EditlogbookActivity.class);
        startActivity(intent);
    }

    private void showSelectedLb(LogbooksItem logbooksItem) {
        Intent detailIntent = new Intent(this, EditlogbookActivity.class);
        detailIntent .putExtra("Catatan", logbooksItem.getProgress());
        detailIntent .putExtra("Tanggal", logbooksItem.getDate());
        startActivity(detailIntent);
    }
}