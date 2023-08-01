package com.example.mob2041_duanmau.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mob2041_duanmau.DAO.SanDAO;
import com.example.mob2041_duanmau.R;
import com.example.mob2041_duanmau.model.San;


import java.util.ArrayList;
import java.util.HashMap;

public class SanAdapter extends RecyclerView.Adapter<SanAdapter.ViewHolder>{
    private Context context;
    private ArrayList<San> list;
    private ArrayList<HashMap<String, Object>> listHM;
    private SanDAO SanDAO;


    public SanAdapter(Context context, ArrayList<San> list, ArrayList<HashMap<String, Object>> listHM, SanDAO SanDAO) {
        this.context = context;
        this.list = list;
        this.listHM = listHM;
        this.SanDAO = SanDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.item_recycler_san,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.txtMaSach.setText("Mã sân: " +list.get(position).getMasach());
            holder.txtTenSach.setText("Tên sân: " +list.get(position).getTensach());
            holder.txtGiaThue.setText("Giá thuê: " +list.get(position).getGiathue());
            holder.txtMaLoai.setText("Mã Loại " +list.get(position).getMaloai());
            holder.txtTenLoai.setText("Tên Loại: " +list.get(position).getTenloai());

            holder.ivEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDialog(list.get(holder.getAdapterPosition()));
                }
            });

            holder.ivDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int check = SanDAO.xoaSach(list.get(holder.getAdapterPosition()).getMasach());
                    switch (check){
                        case 1:
                            Toast.makeText(context, "Xóa Thành công", Toast.LENGTH_SHORT).show();
                            loadData();
                            break;
                        case 0:
                            Toast.makeText(context, "Xóa không thành công", Toast.LENGTH_SHORT).show();
                            break;
                        case -1:
                            Toast.makeText(context, "không thể xóa vì còn tồn tại sân", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            break;
                    }
                }
            });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
            TextView txtMaSach, txtTenSach,txtGiaThue,txtMaLoai,txtTenLoai;
            ImageView ivEdit,ivDel;
        public ViewHolder(@NonNull View itemView){
            super(itemView);

            txtMaSach = itemView.findViewById(R.id.txtMaSach);
            txtTenSach = itemView.findViewById(R.id.txtTenSach);
            txtGiaThue = itemView.findViewById(R.id.txtGiaThue);
            txtMaLoai = itemView.findViewById(R.id.txtMaLoai);
            txtTenLoai = itemView.findViewById(R.id.txtTenLoai);
            ivDel = itemView.findViewById(R.id.ivDel);
            ivEdit = itemView.findViewById(R.id.ivEdit);
        }
    }

    private void showDialog(San San){
        //alerdialog
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_suasan,null);
        builder.setView(view);

        EditText edtTenSach = view.findViewById(R.id.edtTenSach);
        EditText edtTien = view.findViewById(R.id.edtTien);
        TextView txtMaSach = view.findViewById(R.id.txtMaSach);
        Spinner spnLoaiSach = view.findViewById(R.id.spnLoaiSach);

        txtMaSach.setText("Mã Sân: "+ San.getMasach());
        edtTenSach.setText(San.getTensach());
        edtTien.setText(String.valueOf(San.getGiathue()));

        SimpleAdapter simpleAdapter = new SimpleAdapter(
                context,listHM, android.R.layout.simple_list_item_1,
                new String[]{"tenloai"},new int[]{android.R.id.text1}
        );
        spnLoaiSach.setAdapter(simpleAdapter);

        int index = 0;
        int postion = -1;
        for (HashMap<String, Object> item : listHM){
            if ((int)item.get("maloai") == San.getMaloai()){
                postion = index;
            }
            index++;
        }
        spnLoaiSach.setSelection(postion);

        builder.setNegativeButton("Cập nhật", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String tenSach = edtTenSach.getText().toString();
                int tien = Integer.parseInt(edtTien.getText().toString());
                HashMap<String, Object> hs =(HashMap<String, Object>) spnLoaiSach.getSelectedItem();
                int maloai = (int) hs.get("maloai");

                boolean check = SanDAO.capNhatThongTinSach(San.getMasach(),tenSach,tien,maloai);
                if (check){
                    Toast.makeText(context, "Cập nhật sách thành công", Toast.LENGTH_SHORT).show();
                    //load data
                    loadData();
                }else{
                    Toast.makeText(context, "Cập nhật sách Không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });


        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }
    private void loadData(){
        list.clear();
        list = SanDAO.getDSDauSach();
        notifyDataSetChanged();
    }
}
