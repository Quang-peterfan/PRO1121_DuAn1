package com.example.mob2041_duanmau.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mob2041_duanmau.DAO.ThongKeDAO;
import com.example.mob2041_duanmau.R;
import com.example.mob2041_duanmau.adapter.Top10_Adapter;
import com.example.mob2041_duanmau.model.San;

import java.util.ArrayList;

public class ThongKeTop10Fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongketop10,container,false) ;

        RecyclerView recyclerTop10 = view.findViewById(R.id.recyclerTop10);
        ThongKeDAO thongKeDAO = new ThongKeDAO(getContext());
        ArrayList<San> list = thongKeDAO.getTop10();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerTop10.setLayoutManager(linearLayoutManager);
        Top10_Adapter adapter = new Top10_Adapter(getContext(),list);
        recyclerTop10.setAdapter(adapter);

        return view;
    }
}
