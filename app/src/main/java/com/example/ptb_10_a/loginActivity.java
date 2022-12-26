package com.example.ptb_10_a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MotionEvent;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ptb_10_a.databinding.ActivityLoginBinding;
import com.example.ptb_10_a.models.LoginResponse;
import com.example.ptb_10_a.retrofit.APIClient;
import com.example.ptb_10_a.retrofit.InterfaceMahasiswa;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class loginActivity extends AppCompatActivity {

    EditText nimInput, sandiInput;
    InterfaceMahasiswa interfaceMahasiswa;
    SharedPreferences sharedPref;

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding =ActivityLoginBinding.inflate((getLayoutInflater()));
        View view = binding.getRoot();
        setContentView(view);

        nimInput = findViewById(R.id.nimInput);
        sandiInput = findViewById(R.id.sandiInput);
        interfaceMahasiswa = APIClient.getClient().create(InterfaceMahasiswa.class);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);


        String token = sharedPref.getString("TOKEN", null);
        if(token !=null){
            startActivity(new Intent(this, HomeActivity.class));
        }
    }

    public void login(View view) {
        String username= nimInput.getText().toString();
        String password = sandiInput.getText().toString();

        Call<LoginResponse> call= interfaceMahasiswa.login(username, password);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                if(loginResponse != null){
                    String token = loginResponse.getAuthorisation().getToken();
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("TOKEN",token);
                    editor.putString("USERNAME",nimInput.getText().toString());
                    editor.putString("PASSWORD",sandiInput.getText().toString());
                    editor.putString("NAME", response.body().getUser().getName());
                    editor.putString("EMAIL", response.body().getUser().getEmail());
                    editor.commit();
                    Toast.makeText(loginActivity.this, "Berhasil Login"+username, Toast.LENGTH_SHORT).show();
                    Intent homeIntent = new Intent(loginActivity.this,HomeActivity.class);
                    startActivity(homeIntent);
                }
                else{
                    Toast.makeText(loginActivity.this, "Username atau pasword salah", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(loginActivity.this, "Gagal menghubungi Server", Toast.LENGTH_SHORT).show();

            }
        });
    }
}