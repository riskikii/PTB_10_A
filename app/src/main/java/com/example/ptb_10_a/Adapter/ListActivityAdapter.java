package com.example.ptb_10_a.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ptb_10_a.ListLbActivity;
import com.example.ptb_10_a.R;
import com.example.ptb_10_a.models.LogbooksItem;

import java.util.ArrayList;
import java.util.List;

public class ListActivityAdapter extends RecyclerView.Adapter<ListActivityAdapter.ListViewHolder>{
    private List<LogbooksItem> itemList = new ArrayList<>();

    public void setItemList(List<LogbooksItem>itemList){
        this.itemList = itemList;
        notifyDataSetChanged();
    }

    private OnItemClickCallback onItemClickCallback;

    public ListActivityAdapter() {

    }


    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }
    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_logbook,parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        final LogbooksItem listlb = itemList.get(position);
        holder.tvTanggal_logbook.setText(listlb.getDate());
        holder.tvCtt.setText(listlb.getProgress());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder{
        TextView tvTanggal_logbook, tvCtt;
        Button editlogbook;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            editlogbook = itemView.findViewById(R.id.EditLogbook);
            tvTanggal_logbook = itemView.findViewById(R.id.tanggalLogbook);
            tvCtt = itemView.findViewById(R.id.CttLogbook);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(ListLbActivity data);
    }
}
