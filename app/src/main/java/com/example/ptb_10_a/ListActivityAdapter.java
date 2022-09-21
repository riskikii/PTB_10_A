package com.example.ptb_10_a;

import android.app.ListActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListActivityAdapter extends RecyclerView.Adapter<ListActivityAdapter.ListViewHolder>{
    private ArrayList<ListLbActivity>listActivity;
    public ListActivityAdapter(ArrayList<ListLbActivity>list){
        this.listActivity = list;
    }


    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_logbook,parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        ListLbActivity listlb = listActivity.get(position);
        holder.tvTanggal_logbook.setText(listlb.getTanggal());
        holder.tvCtt.setText(listlb.getCatatan());
    }

    @Override
    public int getItemCount() {
        return listActivity.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder{
        TextView tvTanggal_logbook, tvCtt;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTanggal_logbook = itemView.findViewById(R.id.tanggalLogbook);
            tvCtt = itemView.findViewById(R.id.CttLogbook);
        }
    }
}
