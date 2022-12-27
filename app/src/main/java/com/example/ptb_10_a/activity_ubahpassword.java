package com.example.ptb_10_a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ptb_10_a.models.UbahPassword;
import com.example.ptb_10_a.retrofit.APIClient;
import com.example.ptb_10_a.retrofit.InterfaceMahasiswa;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activity_ubahpassword extends AppCompatActivity {
    TextInputEditText PwLama, PwBaru, ConfrmPwBaru;
    Button update;
    String token;

    InterfaceMahasiswa interfaceMahasiswa;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubahpassword);

        interfaceMahasiswa = APIClient.getClient().create(InterfaceMahasiswa.class);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        token = sharedPref.getString("TOKEN", "");

        PwLama = findViewById(R.id.PwLama);
        PwBaru = findViewById(R.id.PwBaru);
        ConfrmPwBaru = findViewById(R.id.KonfrmPw);
        update = findViewById(R.id.btnUbahPW);
    }

    public void UbahPasword(View view) {
        String old_password = PwLama.getText().toString();
        String new_password = PwBaru.getText().toString();
        String confirm_password = ConfrmPwBaru.getText().toString();

        if (old_password.isEmpty()) {
            PwLama.setError("fill this field");
            PwBaru.requestFocus();
            return;
        }
        if (new_password.isEmpty()) {
            PwBaru.setError("fill this field");
            PwBaru.requestFocus();
            return;
        }
        if (confirm_password.isEmpty()) {
            ConfrmPwBaru.setError("fill this field");
            ConfrmPwBaru.requestFocus();
            return;
        }

        Call<UbahPassword> call = interfaceMahasiswa.UbahPassword("Bearer " + token, old_password, new_password, confirm_password);

        call.enqueue(new Callback<UbahPassword>() {
            @Override
            public void onResponse(Call<UbahPassword> call, Response<UbahPassword> response) {
                if (response.isSuccessful()) {
                    UbahPassword ganti = response.body();
                    if (ganti != null) {

                        Toast.makeText(activity_ubahpassword.this, ganti.getMessage(), Toast.LENGTH_SHORT).show();
                        Intent mainIntent = new Intent(activity_ubahpassword.this, ProfilActivity.class);
                        startActivity(mainIntent);
                    }
                } else {
                    Toast.makeText(activity_ubahpassword.this, "Gagal ganti password", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<UbahPassword> call, Throwable t) {
                Toast.makeText(activity_ubahpassword.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}