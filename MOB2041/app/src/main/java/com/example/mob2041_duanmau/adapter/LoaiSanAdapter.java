package com.example.mob2041_duanmau.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mob2041_duanmau.DAO.LoaiSanDAO;
import com.example.mob2041_duanmau.R;
import com.example.mob2041_duanmau.model.ItemClick;
import com.example.mob2041_duanmau.model.LoaiSan;

import java.util.ArrayList;

public class LoaiSanAdapter extends RecyclerView.Adapter<LoaiSanAdapter.ViewHolder>{
   private Context context;
   private ArrayList<LoaiSan> list;
   private ItemClick itemClick;

    public LoaiSanAdapter(Context context, ArrayList<LoaiSan> list, ItemClick itemClick) {
        this.context = context;
        this.list = list;
        this.itemClick = itemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycler_loaisan,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTenLoai.setText( "Tên Loại: " + list.get(position).getTenLoai());
        holder.txtMaLoai.setText( "Mã Loại: " + String.valueOf(list.get(position).getId()));

//
//        holder.ivDel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LoaiSanDAO loaiSachDAO = new LoaiSanDAO(context);
//                int check = loaiSachDAO.xoaLoaiSach(list.get(holder.getAdapterPosition()).getId());
//                switch (check){
//                    case 1: //Load data
//                        list.clear();
//                        list = loaiSachDAO.getDsLoaiSaches();
//                        notifyDataSetChanged();
//                        break;
//                    case -1:
//                        Toast.makeText(context, "Không thể xóa loại sách này", Toast.LENGTH_SHORT).show();
//                        break;
//                    case 0:
//                        Toast.makeText(context, "Xóa loại sách không thành công", Toast.LENGTH_SHORT).show();
//                        break;
//                    default:
//                        break;
//                }
//            }
//        });
//
//        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                LoaiSan loaiSach = list.get(holder.getAdapterPosition());
//                itemClick.onClickLoaiSach(loaiSach);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtMaLoai, txtTenLoai;
        ImageView ivDel,ivEdit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaLoai = itemView.findViewById(R.id.txtMaloai);
            txtTenLoai = itemView.findViewById(R.id.txtTenLoai);
            ivDel = itemView.findViewById(R.id.ivDel);
            ivEdit = itemView.findViewById(R.id.ivEdit);
        }
    }

}
