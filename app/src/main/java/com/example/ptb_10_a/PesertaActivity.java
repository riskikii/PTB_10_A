package com.example.ptb_10_a;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class PesertaActivity extends AppCompatActivity {
    private RecyclerView listPeserta;
    private ArrayList<ListPeserta> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peserta);

        listPeserta = findViewById(R.id.daftarPeserta);
        listPeserta.setHasFixedSize(true);

        list.addAll(getListLogbooks());
        showRecyclerList();

    }

    private void showRecyclerList() {
        listPeserta.setLayoutManager(new LinearLayoutManager(this));
        ListPesertaAdapter listPesertaAdapter = new ListPesertaAdapter(list);
        listPeserta.setAdapter(listPesertaAdapter);
    }

    public ArrayList<ListPeserta> getListLogbooks(){
        String[] nama_peserta = getResources().getStringArray(R.array.nama_peserta);
        String[] nim_peseerta = getResources().getStringArray(R.array.nim_peserta);
        ArrayList <ListPeserta> listPesertaSd = new ArrayList<>();
        for (int i = 0; i < nama_peserta.length; i++) {
            ListPeserta listPeserta = new ListPeserta();
            listPeserta.setNama_peserta(nama_peserta[i]);
            listPeserta.setNim_peserta(nim_peseerta[i]);
            listPesertaSd.add(listPeserta);
        }
        return listPesertaSd;
    }

    public void TambahPesertaSemhas(View view) {
        Intent intent = new Intent(PesertaActivity.this,PesertaSemhasActivity.class);
        startActivity(intent);
    }

    public void back(View view) {
        Intent intent = new Intent(PesertaActivity.this,TambahPesertaActivity.class);
        startActivity(intent);
    }
}