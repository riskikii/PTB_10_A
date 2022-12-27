package com.example.ptb_10_a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ptb_10_a.models.UpdateProfilResponse;
import com.example.ptb_10_a.retrofit.APIClient;
import com.example.ptb_10_a.retrofit.InterfaceMahasiswa;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activity_ubahprofil extends AppCompatActivity {

    TextView NamaBaru, EmailBaru;
    Button UpdateProfil;
    String token;

    InterfaceMahasiswa interfaceMahasiswa;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubahprofil);

        interfaceMahasiswa = APIClient.getClient().create(InterfaceMahasiswa.class);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        token = sharedPref.getString("TOKEN", "");

        NamaBaru= findViewById(R.id.namaBaru);
        EmailBaru = findViewById(R.id.newEmail);
        UpdateProfil = findViewById(R.id.UbahProfile);
    }

    public void UbahProfil(View view) {
        String nama = NamaBaru.getText().toString();
        String email = EmailBaru.getText().toString();

        if (nama.isEmpty()) {
            NamaBaru.setError("fill this field");
            NamaBaru.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            EmailBaru.setError("fill this field");
            EmailBaru.requestFocus();
            return;
        }

        Call<UpdateProfilResponse> call = interfaceMahasiswa.updateProfile("Bearer " + token, email, nama );

        call.enqueue(new Callback <UpdateProfilResponse>() {
            @Override
            public void onResponse(Call<UpdateProfilResponse> call, Response<UpdateProfilResponse> response) {

                if (response.isSuccessful()) {
                    UpdateProfilResponse ganti = response.body();
                    if (ganti != null) {

                        Toast.makeText(activity_ubahprofil.this, ganti.getMessage(), Toast.LENGTH_SHORT).show();
                        Intent mainIntent = new Intent(activity_ubahprofil.this, ProfilActivity.class);
                        startActivity(mainIntent);
                    }
                } else {
                    Toast.makeText(activity_ubahprofil.this, "gagal update email", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<UpdateProfilResponse> call, Throwable t) {

            }
        });
    }
}