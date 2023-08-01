package com.example.mob2041_duanmau.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mob2041_duanmau.DAO.LoaiSanDAO;
import com.example.mob2041_duanmau.R;
import com.example.mob2041_duanmau.adapter.LoaiSanAdapter;
import com.example.mob2041_duanmau.model.ItemClick;
import com.example.mob2041_duanmau.model.LoaiSan;

import java.util.ArrayList;

public class QLLoaiSanFragment extends Fragment {
    RecyclerView recyclerLoaiSach;
    LoaiSanDAO dao;
    EditText edtLoaiSach;
    int maloai;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qlloaisan,container,false);

        recyclerLoaiSach = view.findViewById(R.id.recyclerLoaiSan);
//        edtLoaiSach = view.findViewById(R.id.edtLoaiSach);
//        Button btnThem = view.findViewById(R.id.btnThem);
//        Button btnSua = view.findViewById(R.id.btnSua);

        dao = new LoaiSanDAO(getContext());
        loadData();

//        btnThem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String tenloai = edtLoaiSach.getText().toString();
//
//                if (dao.ThemLoaiSach(tenloai)){
//                    //TB + load DS
//                    loadData();
//                    edtLoaiSach.setText("");
//                }else {
//                    Toast.makeText(getContext(),"Thêm loại sách không thành công", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//        btnSua.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String tenloai = edtLoaiSach.getText().toString();
//                LoaiSan loaiSach = new LoaiSan(maloai,tenloai);
//                if (dao.thayDoiLoaiSach(loaiSach)){
//                    loadData();
//                    edtLoaiSach.setText("");
//                }else {
//                    Toast.makeText(getContext(), "Thay đổi thông tin không thành công", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        return view;
    }
    private void loadData(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerLoaiSach.setLayoutManager(linearLayoutManager);

        ArrayList<LoaiSan> list = dao.getDsLoaiSaches();
        LoaiSanAdapter adapter = new LoaiSanAdapter(getContext(), list, new ItemClick() {
            @Override
            public void onClickLoaiSach(LoaiSan LoaiSan) {
//                edtLoaiSach.setText(loaiSach.getTenLoai());
                maloai = LoaiSan.getId();
            }
        });
        recyclerLoaiSach.setAdapter(adapter);
    }
}
