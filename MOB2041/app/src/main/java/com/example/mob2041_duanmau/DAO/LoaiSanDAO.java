package com.example.mob2041_duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mob2041_duanmau.database.DbHelper;
import com.example.mob2041_duanmau.model.LoaiSan;

import java.util.ArrayList;

public class LoaiSanDAO {
    DbHelper dbHelper;
    public LoaiSanDAO(Context context){
        dbHelper = new DbHelper(context);
    }

    //Lay DS Loai Sach
    public ArrayList<LoaiSan> getDsLoaiSaches(){
        ArrayList<LoaiSan> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM LOAISACH",null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new LoaiSan(cursor.getInt(0), cursor.getString(1)));
            } while (cursor.moveToNext());
        }

        return list;
    }

        //Them Loai Sach
        public boolean ThemLoaiSach(String tenLoai){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("tenLoai",tenLoai);
            long check = sqLiteDatabase.insert("LOAISACH",null,contentValues);
            if (check == -1)
                return false;
            return true;
        }
        //xoa Loai sach
    // 1: xoa thanh cong - 0: xoa that bai - -1: co sach ton tai
    public int xoaLoaiSach(int id){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();


        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SACH WHERE maLoai = ?",new String[]{String.valueOf(id)});
        if (cursor.getCount() != 0 ){
            return -1;
        }
        long check = sqLiteDatabase.delete("LOAISACH","maloai =?", new String[]{String.valueOf(id)});
        if (check == -1)
            return 0;
        return 1;
    }
        public boolean thayDoiLoaiSach(LoaiSan LoaiSan){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenloai", LoaiSan.getTenLoai());
        long check = sqLiteDatabase.update("LOAISACH",contentValues,"maloai =?",new String[]{String.valueOf(LoaiSan.getId())});
        if (check == -1)
            return false;
        return true;


        }


}
