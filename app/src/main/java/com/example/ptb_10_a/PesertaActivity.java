package com.example.ptb_10_a;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;

import com.example.ptb_10_a.Adapter.ListPesertaAdapter;

import java.lang.reflect.Type;
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
        TypedArray foto_peserta = getResources().obtainTypedArray(R.array.foto_peserta);

        ArrayList <ListPeserta> listPesertaSd = new ArrayList<>();
        for (int i = 0; i < nama_peserta.length; i++) {
            ListPeserta listPeserta = new ListPeserta();
            listPeserta.setNama_peserta(nama_peserta[i]);
            listPeserta.setNim_peserta(nim_peseerta[i]);
            listPeserta.setFoto(foto_peserta.getResourceId(i, -1));

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