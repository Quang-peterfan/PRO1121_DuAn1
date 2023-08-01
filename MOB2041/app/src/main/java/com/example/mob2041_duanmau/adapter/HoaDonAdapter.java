package com.example.mob2041_duanmau.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mob2041_duanmau.DAO.HoaDonDAO;
import com.example.mob2041_duanmau.DAO.HoaDonDAO;
import com.example.mob2041_duanmau.R;
import com.example.mob2041_duanmau.model.HoaDon;
import com.example.mob2041_duanmau.model.HoaDon;

import java.util.ArrayList;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.ViewHolder>{

    private ArrayList<HoaDon> list;
    private Context context;

    public HoaDonAdapter(ArrayList<HoaDon> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { // Quản lí layout
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycler_phieumuon,parent,false);


        return new ViewHolder(view);// Truyền GD
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {// Hiện lên GD
        holder.txtMaPM.setText("Mã HD: "+list.get(position).getMapm());
        holder.txtTenTV.setText("Tên NV: "+list.get(position).getTentv());
//        holder.txtMaTT.setText("Mã TT: "+list.get(position).getMatt());
        holder.txtTenTT.setText("Tên TT: "+list.get(position).getTentt());
        holder.txtMaSach.setText("Mã Sân: "+list.get(position).getMasach());
        holder.txtTenSach.setText("Tên Sân: "+list.get(position).getTensach());
        holder.txtNgay.setText("Ngày: "+list.get(position).getNgay());
        holder.txtNgay.setText("Ngày: "+list.get(position).getNgay());
//        holder.txtTgian.setText("Tgian: "+list.get(position).getTgian());


        String trangthai ="";
        if (list.get(position).getTrasach() == 1){
            trangthai = "Đã trả Sân";
            holder.btnTraSach.setVisibility(View.GONE); // mất Khoảng trống
        }else{
            trangthai = "Chưa trả Sân";
            holder.btnTraSach.setVisibility(View.VISIBLE);
        }

        holder.txtTrangThai.setText("Trạng Thái: "+trangthai);
        holder.txtTien.setText("Số Tiền: "+list.get(position).getTienthue());


        holder.btnTraSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HoaDonDAO HoaDonDAO = new HoaDonDAO(context);
                boolean kiemtra = HoaDonDAO.thayDoiTrangThai(list.get(holder.getAdapterPosition()).getMapm());
                if (kiemtra){
                    list.clear();
                    list = HoaDonDAO.getDSPhieuMuon();
                    notifyDataSetChanged();
                }else {
                    Toast.makeText(context, "Thay đổi trạng thái không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtMaPM, txtMaTV, txtTenTV, txtMaTT,txtTenTT ,txtMaSach ,txtTenSach,txtNgay ,txtTrangThai ,txtTien,txtTgian; // Gọi
        Button btnTraSach;
        public ViewHolder(@NonNull View itemView) { // ÁNh xạ
            super(itemView);
            txtMaPM = itemView.findViewById(R.id.txtMaPM);
            txtMaTV = itemView.findViewById(R.id.txtMaTV);
            txtTenTV = itemView.findViewById(R.id.txtTenTV);
//            txtMaTT = itemView.findViewById(R.id.txtMaTT);
            txtTenTT = itemView.findViewById(R.id.txtTenTT);
            txtMaSach = itemView.findViewById(R.id.txtMaSach);
            txtTenSach = itemView.findViewById(R.id.txtTenSach);
            txtNgay = itemView.findViewById(R.id.txtNgay);
            txtTrangThai = itemView.findViewById(R.id.txtTrangThai);
            txtTien = itemView.findViewById(R.id.txtTien);
//            txtTgian = itemView.findViewById(R.id.txtTgian);
            btnTraSach = itemView.findViewById(R.id.btnTraSach);

        }
    }
}
