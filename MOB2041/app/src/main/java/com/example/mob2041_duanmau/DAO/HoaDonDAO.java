package com.example.mob2041_duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mob2041_duanmau.database.DbHelper;
import com.example.mob2041_duanmau.model.HoaDon;

import java.util.ArrayList;

public class HoaDonDAO {
    DbHelper dbHelper;
    public HoaDonDAO(Context context){
        dbHelper = new DbHelper(context);
    }

    public ArrayList<HoaDon> getDSPhieuMuon(){// Lấy ht toàn bộ HD
        ArrayList<HoaDon> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT pm.mapm, pm.matv, tv.hoten, pm.matt, tt.hoten, pm.masach, sc.tensach, pm.ngay, pm.trasach, pm.tienthue FROM PHIEUMUON pm, THANHVIEN tv, THUTHU tt, SACH sc WHERE pm.matv = tv.matv and pm.matt = tt.matt AND pm.masach = sc.masach ORDER BY pm.mapm DESC",null);
        if (cursor.getCount() != 0 ){
            cursor.moveToFirst();
            do {
                list.add(new HoaDon(cursor.getInt(0),cursor.getInt(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getInt(5),cursor.getString(6),cursor.getString(7), cursor.getInt(8),cursor.getInt(9)));
            }while (cursor.moveToNext());
        }

        return list;
    }
    public boolean thayDoiTrangThai(int mapm){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase(); // (writeble- Contentvalues)
        ContentValues contentValues = new ContentValues();
        contentValues.put("trasach",1);
        long check = sqLiteDatabase.update("PHIEUMUON",contentValues,"mapm = ?",new String[]{String.valueOf(mapm)});
        if (check == -1){
            return false;
        }
        return true;
    }
//    public int xoaPM(int masach){
//        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
//        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM PHIEUMUON WHERE masach = ?",new String[]{String.valueOf(masach)});
//        if (cursor.getCount() != 0){
//            return  -1;
//        }
//        long check = sqLiteDatabase.delete("SACH","masach = ?",new String[]{String.valueOf(masach)});
//        if (check == -1)
//            return 0;
//        return 1;
//    }

    public boolean themPhieuMuon(HoaDon hoaDon){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //mapm integer primary key autoincrement,matv integer references THANHVIEN(matv),matt text references THUTHU(matt),masach integer references SACH(masach),ngay text,trasach integer,tienthue integer
//        contentValues.put("mapm",phieuMuon.getMapm());
        contentValues.put("matv",hoaDon.getMatv());
        contentValues.put("matt",hoaDon.getMatt());
        contentValues.put("masach",hoaDon.getMasach());
        contentValues.put("ngay",hoaDon.getNgay());
        contentValues.put("trasach",hoaDon.getTrasach());
        contentValues.put("tienthue",hoaDon.getTienthue());
//        contentValues.put("tgian",hoaDon.getTgian());

        long check = sqLiteDatabase.insert("PHIEUMUON",null,contentValues);
        if (check == -1){
            return false;
        }
        return true;
    }
}
