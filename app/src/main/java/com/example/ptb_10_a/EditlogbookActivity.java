package com.example.ptb_10_a;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ptb_10_a.models.EdtLBResponse;
import com.example.ptb_10_a.models.TmbhLBResponse;
import com.example.ptb_10_a.retrofit.APIClient;
import com.example.ptb_10_a.retrofit.InterfaceMahasiswa;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditlogbookActivity extends AppCompatActivity {

    String catatan, tanggal;
    TextView catatanLogbook, tanggalLB;
    DatePickerDialog datePickerDialog;
    SharedPreferences sharedPref;
    InterfaceMahasiswa interfaceMahasiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editlogbook);

        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        interfaceMahasiswa = APIClient.getClient().create(InterfaceMahasiswa.class);

        Intent detailIntent = getIntent();
        if (detailIntent != null){
            catatan = detailIntent.getStringExtra("Catatan");
            catatanLogbook = findViewById(R.id.catatanLogbook);
            catatanLogbook.setText(catatan);
            tanggal = detailIntent.getStringExtra("Tanggal");
            tanggalLB = findViewById(R.id.tanggalLB);
            tanggalLB.setText(tanggal);
        }

        tanggalLB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(EditlogbookActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                tanggalLB.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
    }



    public void back(View view) {
        Intent intent = new Intent(EditlogbookActivity.this,LogbookActivity.class);
        startActivity(intent);
    }


    public void EditLogbook(View view) {
        String date = tanggalLB.getText().toString();
        String progress = catatanLogbook.getText().toString();
        String token = sharedPref.getString("TOKEN", null);
        Call<EdtLBResponse> call = interfaceMahasiswa.edtLB("Bearer " + token.toString(), date, progress);

        call.enqueue(new Callback<EdtLBResponse>() {
            @Override
            public void onResponse(Call<EdtLBResponse> call, Response<EdtLBResponse> response) {
                Log.d("TesPost", response.body().toString());

            }

            @Override
            public void onFailure(Call<EdtLBResponse> call, Throwable t) {
                Log.d("TestPost", t.getMessage().toString());
            }
        });
        finish();

    }
}