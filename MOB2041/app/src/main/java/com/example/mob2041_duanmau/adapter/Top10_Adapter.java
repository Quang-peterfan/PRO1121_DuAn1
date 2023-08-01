package com.example.mob2041_duanmau.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mob2041_duanmau.R;
import com.example.mob2041_duanmau.model.San;

import java.util.ArrayList;

public class Top10_Adapter extends RecyclerView.Adapter<Top10_Adapter.ViewHolder> {
    private Context context;
    private ArrayList<San> list;

    public Top10_Adapter(Context context, ArrayList<San> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycler_top10,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.txtMaSach.setText("Mã thành viên: "+String.valueOf(list.get(position).getMasach()));
        holder.txtMaSach.setText("Mã Sân "+String.valueOf(list.get(position).getMasach()));
        holder.txtTenSach.setText("Tên: "+list.get(position).getTensach());
        holder.txtSoLuongSach.setText("Số lần: "+String.valueOf(list.get(position).getSoLuongDaMuon()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtMaSach, txtTenSach,txtSoLuongSach;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaSach = itemView.findViewById(R.id.txtMaSach);
            txtTenSach = itemView.findViewById(R.id.txtTenSach);
            txtSoLuongSach = itemView.findViewById(R.id.txtSoLuongMuon);

        }
    }
}
