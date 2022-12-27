package com.example.ptb_10_a;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ptb_10_a.databinding.ActivityProfilBinding;
import com.example.ptb_10_a.models.LogoutResponse;
import com.example.ptb_10_a.retrofit.APIClient;
import com.example.ptb_10_a.retrofit.InterfaceMahasiswa;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfilActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ImageView BtnLogout;
    InterfaceMahasiswa interfaceMahasiswa;

    private ActivityProfilBinding binding;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        binding = ActivityProfilBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        interfaceMahasiswa = APIClient.getClient().create(InterfaceMahasiswa.class);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        BtnLogout = findViewById(R.id.btnLogout);

        String token = sharedPref.getString("TOKEN", "");
        String username = sharedPref.getString("USERNAME", "");
        String name = sharedPref.getString("NAME", "");
        String email = sharedPref.getString("EMAIL", "");

        Intent intent = getIntent();
        binding.viewNama.setText(name);
        binding.textNim.setText(username);
        binding.textEmail.setText(email);

        BtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<LogoutResponse> call = interfaceMahasiswa.logout("Bearer " + token);

                call.enqueue(new Callback<LogoutResponse>() {
                    @Override
                    public void onResponse(Call<LogoutResponse> call, Response<LogoutResponse> response) {
                        LogoutResponse logoutResponse = response.body();
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.remove("TOKEN");
                        editor.apply();
                        finish();
                        Toast.makeText(ProfilActivity.this,"Successfully Log Out", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ProfilActivity.this, loginActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<LogoutResponse> call, Throwable t) {

                    }
                });
            }
        });

        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.nav_profile);

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
                        startActivity(new Intent(getApplicationContext(), SidangActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nav_profile:
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

    public void ubahProfil(View view) {
        Intent intent = new Intent(ProfilActivity.this,activity_ubahprofil.class);
        startActivity(intent);
    }
    public void ubahPasword(View view) {
        Intent intent = new Intent(ProfilActivity.this,activity_ubahpassword.class);
        startActivity(intent);
    }
}