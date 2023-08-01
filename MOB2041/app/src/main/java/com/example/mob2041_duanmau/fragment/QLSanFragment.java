package com.example.mob2041_duanmau.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mob2041_duanmau.DAO.LoaiSanDAO;
import com.example.mob2041_duanmau.DAO.SanDAO;
import com.example.mob2041_duanmau.R;
import com.example.mob2041_duanmau.adapter.SanAdapter;
import com.example.mob2041_duanmau.model.LoaiSan;
import com.example.mob2041_duanmau.model.San;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

public class QLSanFragment extends Fragment {
    SanDAO SanDAO;
    RecyclerView recyclerSach;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qlsan,container,false);

        recyclerSach = view.findViewById(R.id.recyclerSach);
        FloatingActionButton floatAdd = view.findViewById(R.id.floatAdd);

        SanDAO = new SanDAO(getContext());
        loadData();

        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        return view;
    }
    private void loadData(){
        ArrayList<San> list= SanDAO.getDSDauSach();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerSach.setLayoutManager(linearLayoutManager);
        SanAdapter adapter = new SanAdapter(getContext(), list, getDSLoaiSach(), SanDAO);
        recyclerSach.setAdapter(adapter);
    }

    private void showDialog(){
        AlertDialog.Builder builder= new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_themsan,null);
        builder.setView(view);

        EditText edtTenSach = view.findViewById(R.id.edtTenSach);
        EditText edtTien = view.findViewById(R.id.edtTien);
        Spinner spnLoaiSach = view.findViewById(R.id.spnLoaiSach);

        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getContext(),getDSLoaiSach(), android.R.layout.simple_list_item_1,
                new String[]{"tenloai"},new int[]{android.R.id.text1}
        );
        spnLoaiSach.setAdapter(simpleAdapter);

        builder.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String tenSach = edtTenSach.getText().toString();
                int tien = Integer.parseInt(edtTien.getText().toString());
                HashMap<String, Object> hs =(HashMap<String, Object>) spnLoaiSach.getSelectedItem();
                int maloai = (int) hs.get("maloai");

                boolean check = SanDAO.themSachMoi(tenSach,tien,maloai);
                if (check){
                    Toast.makeText(getContext(), "Thêm sân thành công", Toast.LENGTH_SHORT).show();
                    //load data
                    loadData();
                }else{
                    Toast.makeText(getContext(), "Thêm Không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private ArrayList<HashMap<String, Object>> getDSLoaiSach(){
        LoaiSanDAO LoaiSanDAO = new LoaiSanDAO(getContext());
        ArrayList<LoaiSan> list = LoaiSanDAO.getDsLoaiSaches();
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();

        for (LoaiSan loai: list){
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("maloai", loai.getId());
            hs.put("tenloai", loai.getTenLoai());
            listHM.add(hs);
        }


        return listHM;
    }
}
