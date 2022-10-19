package com.example.ptb_10_a.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ptb_10_a.ListPeserta;
import com.example.ptb_10_a.R;

import java.util.ArrayList;

public class ListPesertaAdapter extends RecyclerView.Adapter<ListPesertaAdapter.ListViewHolder>{
    private ArrayList<ListPeserta>listPeserta;
    public ListPesertaAdapter(ArrayList<ListPeserta>list){
        this.listPeserta = list;
    }


    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_peserta,parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        ListPeserta listpeserta = listPeserta.get(position);
        holder.imgFoto.setImageResource(listpeserta.getFoto());
        holder.tvNamaPeserta.setText(listpeserta.getNama_peserta());
        holder.tvNim.setText(listpeserta.getNim_peserta());
    }

    @Override
    public int getItemCount() {
        return listPeserta.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder{
        TextView tvNamaPeserta, tvNim;
        ImageView imgFoto;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNamaPeserta = itemView.findViewById(R.id.namaPeserta);
            tvNim = itemView.findViewById(R.id.nimPeserta);
            imgFoto = itemView.findViewById(R.id.fotoPeserta);
        }
    }
}
