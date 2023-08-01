package com.example.mob2041_duanmau.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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

import com.example.mob2041_duanmau.DAO.HoaDonDAO;
import com.example.mob2041_duanmau.DAO.SanDAO;
import com.example.mob2041_duanmau.DAO.ThanhVienDAO;
import com.example.mob2041_duanmau.R;
import com.example.mob2041_duanmau.adapter.HoaDonAdapter;
import com.example.mob2041_duanmau.model.HoaDon;
import com.example.mob2041_duanmau.model.San;
import com.example.mob2041_duanmau.model.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class QLHoaDonFragment extends Fragment {
    HoaDonDAO HoaDonDAO;
    RecyclerView recyclerQLPhieuMuon;
    ArrayList<HoaDon> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qlphieumuon,container,false);
        //Giao diện
        recyclerQLPhieuMuon = view.findViewById(R.id.recyclerQLPhieuMuon);
        FloatingActionButton floatAdd = view.findViewById(R.id.floatAdd);


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
        HoaDonDAO = new HoaDonDAO(getContext());
        list = HoaDonDAO.getDSPhieuMuon();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerQLPhieuMuon.setLayoutManager(linearLayoutManager);
        HoaDonAdapter adapter = new HoaDonAdapter(list, getContext());
        recyclerQLPhieuMuon.setAdapter(adapter);
    }
    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_them_phieumuon,null);
        Spinner spnThanhVien = view.findViewById(R.id.spnThanhVien);
        Spinner spnSach = view.findViewById(R.id.spnSach);
//        EditText edtTgian = view.findViewById(R.id.edtTgian);
//        EditText edtTien = view.findViewById(R.id.edtTien);

        getDataThanhVien(spnThanhVien); // truyền spn
        getDataSach(spnSach); // truyền spn
        builder.setView(view);

        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                //Lay ma TV
                HashMap<String, Object> hsTV = (HashMap<String, Object>) spnThanhVien.getSelectedItem();
                int matv = (int) hsTV.get("matv");

                //Lay ma sach
                HashMap<String, Object> hsSach =(HashMap<String, Object>) spnSach.getSelectedItem();
                int masach = (int) hsSach.get("masach");

                int tien = (int) hsSach.get("giathue");
//                int tgian = Integer.parseInt(edtTgian.getText().toString());

                themPhieuMuon(matv, masach, tien);
            }
        });

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void getDataThanhVien(Spinner spnThanhVien){
        ThanhVienDAO thanhVienDAO = new ThanhVienDAO(getContext());
        ArrayList<ThanhVien> list = thanhVienDAO.getDSThanhViens();

        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();
        for (ThanhVien tv : list){
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("matv", tv.getMatv());
            hs.put("hoten", tv.getHoten());
            listHM.add(hs);

        }
            SimpleAdapter simpleAdapter = new SimpleAdapter(
                    getContext(),listHM, android.R.layout.simple_list_item_1,
                    new String[]{"hoten"},
                    new int[]{android.R.id.text1});
        spnThanhVien.setAdapter(simpleAdapter);

        }

    private void getDataSach(Spinner spnSach){
        SanDAO SanDAO = new SanDAO(getContext());
        ArrayList<San> list = SanDAO.getDSDauSach();

        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();
        for (San sc : list){
            HashMap<String,Object> hs = new HashMap<>();
            hs.put("masach", sc.getMasach());
            hs.put("tensach", sc.getTensach());
            hs.put("giathue", sc.getGiathue());

            listHM.add(hs);

        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getContext(),listHM, android.R.layout.simple_list_item_1,
                new String[]{"tensach"},
                new int[]{android.R.id.text1});
        spnSach.setAdapter(simpleAdapter);

    }

    private void themPhieuMuon(int matv,int masach,int tien){
        // Lấy mã nv
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("THONG TIN", Context.MODE_PRIVATE);
        String matt = sharedPreferences.getString("matt","");

        //Lay ngày HT
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String ngay = simpleDateFormat.format(currentTime);

        HoaDon hoaDon = new HoaDon(matv, matt, masach,ngay,0,tien);
        boolean kiemtra = HoaDonDAO.themPhieuMuon(hoaDon);
        if (kiemtra){
            Toast.makeText(getContext(), "Thêm phiếu mươn thành công", Toast.LENGTH_SHORT).show();
            loadData();
        }else{
            Toast.makeText(getContext(), "Thêm phiếu mượn thất bại", Toast.LENGTH_SHORT).show();
        }
    }

}
