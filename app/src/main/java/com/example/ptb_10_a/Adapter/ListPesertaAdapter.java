package com.example.ptb_10_a.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ptb_10_a.ListPeserta;
import com.example.ptb_10_a.retrofit.Listpesertasemhas.AudiencesItem;
import com.example.ptb_10_a.R;

import java.util.ArrayList;
import java.util.List;

public class ListPesertaAdapter extends RecyclerView.Adapter<ListPesertaAdapter.PesertaViewHolder>{

    private List<AudiencesItem> itemAudiences = new ArrayList<>();

    public void setItemAudiences(List<AudiencesItem> itemAudiences){
        this.itemAudiences = itemAudiences;
        notifyDataSetChanged();
    }

//    ItemPesertaClickListener listener;

    //    public void setListener(ItemPesertaClickListener listener) {
//        this.listener = listener;
//    }
    @NonNull
    @Override
    public PesertaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row_peserta, parent, false);
        return new ListPesertaAdapter.PesertaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PesertaViewHolder holder, int position) {
        AudiencesItem peserta= itemAudiences.get(position);
        holder.imagePeserta.setImageResource(R.drawable.ic_baseline_person_24);
        String str1 = peserta.getName();
        String str2 = str1.toLowerCase();
        holder.textNamaPeserta.setText(StringFormatter.capitalizeWord(str2));
        holder.textNimPeserta.setText(peserta.getNim());
    }

//    public interface ItemPesertaClickListener{
//        void onItemPesertaClick(AudiencesItem Peserta);
//    }

    @Override
    public int getItemCount() {
        return itemAudiences.size();
    }

    public class PesertaViewHolder extends RecyclerView.ViewHolder{

        public ImageView imagePeserta;
        public TextView textNamaPeserta, textNimPeserta;

        public PesertaViewHolder(@NonNull View itemView)
        {
            super(itemView);
            imagePeserta = itemView.findViewById(R.id.fotoPeserta);
            textNamaPeserta = itemView.findViewById(R.id.namaPeserta);
            textNimPeserta = itemView.findViewById(R.id.nimPeserta);

        }

//        @Override
//        public void onClick(View v) {
//            AudiencesItem peserta = itemAudiences.get(getAdapterPosition());
//            listener.onItemPesertaClick(peserta);
//        }
    }
    public static class StringFormatter {
        public static String capitalizeWord(String str){
            String words[]=str.split("\\s");
            String capitalizeWord="";
            for(String w:words){
                String first=w.substring(0,1);
                String afterfirst=w.substring(1);
                capitalizeWord+=first.toUpperCase()+afterfirst+" ";
            }
            return capitalizeWord.trim();
        }
    }
}
