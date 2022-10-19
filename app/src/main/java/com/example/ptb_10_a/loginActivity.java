package com.example.ptb_10_a;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.content.Intent;
import android.widget.Toast;

import com.example.ptb_10_a.databinding.ActivityLoginBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class loginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding =ActivityLoginBinding.inflate((getLayoutInflater()));
        View view = binding.getRoot();
        setContentView(view);

    }


    public void login(View view) {
        String nim = binding.nimInput.getText().toString() ;
        String sandi = binding.sandiInput.getText().toString();

        if(nim.equals("2011527002") && sandi.equals ("riski")){
            Intent intent = new Intent(loginActivity.this,HomeActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "SAlAH WOY", Toast.LENGTH_SHORT).show();
        }
    }
}