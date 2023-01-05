package com.example.ptb_10_a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.ptb_10_a.retrofit.APIClient;
import com.example.ptb_10_a.retrofit.InterfaceMahasiswa;
import com.example.ptb_10_a.retrofit.Listpesertasemhas.ListpesertasemhasResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PesertaSemhasActivity extends AppCompatActivity {

    String gettoken,token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpeserta);

        InterfaceMahasiswa interfaceMahasiswa = APIClient.getClient().create(InterfaceMahasiswa.class);

      SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.kelompok_15.tb_ptb.SHARED_KEY", MODE_PRIVATE);
        gettoken = sharedPreferences.getString("token", "");
        token = "Bearer " + gettoken;
        Toast.makeText(PesertaSemhasActivity.this, token, Toast.LENGTH_SHORT).show();
        Log.e("Token", token);

        Call<ListpesertasemhasResponse> call = interfaceMahasiswa.getPesertasemhas(token);
        call.enqueue(new Callback<ListpesertasemhasResponse>() {
            @Override
            public void onResponse(Call<ListpesertasemhasResponse> call, Response<ListpesertasemhasResponse> response) {
                Log.e("Suc", response.toString());
            }

            @Override
            public void onFailure(Call<ListpesertasemhasResponse> call, Throwable t) {
                Log.e("Suc", t.getLocalizedMessage());
            }
        });
    }
    

    public void TambahPeserta(View view) {
        Intent intent = new Intent(PesertaSemhasActivity.this,PesertaActivity.class);
        startActivity(intent);
    }

    
    public void back(View view) {
        Intent intent = new Intent(PesertaSemhasActivity.this,PesertaActivity.class);
        startActivity(intent);
    }
}
