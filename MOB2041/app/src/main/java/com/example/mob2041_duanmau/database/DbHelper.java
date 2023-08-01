package com.example.mob2041_duanmau.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.FrameLayout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import com.example.mob2041_duanmau.R;
import com.google.android.material.navigation.NavigationView;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper (Context context){
        super(context,"DANGKYMONHOC",null,3);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Bảng thủ thư
        String dbThuThu ="CREATE TABLE THUTHU(matt text primary key,hoten text,matkhau text, loaitaikhoan text)";
        sqLiteDatabase.execSQL(dbThuThu);

        //Bảng thành viên
        String dbThanhVien = "CREATE TABLE THANHVIEN(matv integer primary key autoincrement,hoten text,namsinh text)";
        sqLiteDatabase.execSQL(dbThanhVien);

        //Bảng Loại sách
        String dbLoai = "CREATE TABLE LOAISACH(maloai integer primary key autoincrement,tenloai text)";
        sqLiteDatabase.execSQL(dbLoai);

        //Table Sách
        String dbSach = "CREATE TABLE SACH(masach integer primary key autoincrement,tensach text,giathue integer,maloai integer references LOAISACH(maloai))";
        sqLiteDatabase.execSQL(dbSach);

        //Table Phiếu mượn
        String dbPhieuMuon = "CREATE TABLE PHIEUMUON(mapm integer primary key autoincrement,matv integer references THANHVIEN(matv),matt text references THUTHU(matt),masach integer references SACH(masach),ngay text,trasach integer,tienthue integer)";
        sqLiteDatabase.execSQL(dbPhieuMuon);


//        Data mẫu
        
        sqLiteDatabase.execSQL("INSERT INTO THUTHU VALUES ('admin','ADMIN','admin','Admin'),('nv01','Nguyễn Thế Bảo','123','Nhân viên'),('thuthu02','Nguyễn Viết Tín','123','Nhân viên')");
        sqLiteDatabase.execSQL("INSERT INTO THANHVIEN VALUES (1,'Cao Thu Trang','2000'),(2,'Trần Mỹ Kim','2000')");
        //trả sách: 1: đã trả - 0: chưa trả
        sqLiteDatabase.execSQL("INSERT INTO LOAISACH VALUES (1, 'Sân 3'),(2,'Sân 5'),(3, 'Sân 7')");
//        sqLiteDatabase.execSQL("INSERT INTO PHIEUMUON VALUES (1,1,'nv01', 1, '19/03/2022', 1, 2500),(2,1,'nv02', 3, '19/03/2022', 0, 2000),(3,2,'thuthu02', 1, '19/03/2022', 1, 2000)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i != i1){
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS THUTHU");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS THANHVIEN");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS LOAISACH");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS SACH");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS PHIEUMUON");
            onCreate(sqLiteDatabase);
        }
    }
}
