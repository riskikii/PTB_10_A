package com.example.ptb_10_a;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ptb_10_a.models.TmbhLBResponse;
import com.example.ptb_10_a.retrofit.APIClient;
import com.example.ptb_10_a.retrofit.InterfaceMahasiswa;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddlogbookActivity extends AppCompatActivity {

    EditText setDate, setCatatan;
    SharedPreferences sharedPref;
    InterfaceMahasiswa interfaceMahasiswa;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addlogbook);

        setDate = (EditText) findViewById(R.id.tanggalLB);
        setCatatan = (EditText) findViewById(R.id.catatanLB);

        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        interfaceMahasiswa = APIClient.getClient().create(InterfaceMahasiswa.class);

        setDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(AddlogbookActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                setDate.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
    }





    public void back(View view) {
        Intent intent = new Intent(AddlogbookActivity.this,LogbookActivity.class);
        startActivity(intent);
    }

    public void tambahLogbooks(View view){
        String date = setDate.getText().toString();
        String progress = setCatatan.getText().toString();
        Integer supervisor_id = 1;

        String token = sharedPref.getString("TOKEN", null);
        Call<TmbhLBResponse>call = interfaceMahasiswa.postLB("Bearer " + token.toString(),supervisor_id, date, progress);

        call.enqueue(new Callback<TmbhLBResponse>() {
            @Override
            public void onResponse(Call<TmbhLBResponse> call, Response<TmbhLBResponse> response) {
                Log.d("TesPost", response.body().toString());


            }

            @Override
            public void onFailure(Call<TmbhLBResponse> call, Throwable t) {
                Log.d("TestPost", t.getMessage().toString());
            }
        });
        finish();

    }
}