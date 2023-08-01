package com.example.mob2041_duanmau.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mob2041_duanmau.database.DbHelper;
import com.example.mob2041_duanmau.model.San;

import java.util.ArrayList;

public class ThongKeDAO {
    DbHelper dbHelper;

    public ThongKeDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    public ArrayList<San> getTop10() {
        ArrayList<San> list = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT pm.masach, sc.tensach, COUNT(pm.masach) FROM PHIEUMUON pm, SACH sc WHERE pm.masach = sc.masach GROUP BY pm.masach, sc.tensach ORDER BY COUNT(pm.masach) DESC LIMIT 10", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new San(cursor.getInt(0), cursor.getString(1), cursor.getInt(2)));
            } while (cursor.moveToNext());
        }

        return list;
    }

    public int getDoanhThu(String ngaybatdau, String ngayketthuc) {
        ngaybatdau = ngaybatdau.replace("/", "");
        ngayketthuc = ngayketthuc.replace("/", "");

        //Doi lai dd/MM/yyyy
        String bdNew = "", ktNew = "";
        bdNew = ngaybatdau.substring(4) + ngaybatdau.substring(2,4) + ngaybatdau.substring(0,2);
        ktNew = ngayketthuc.substring(4) + ngayketthuc.substring(2,4) + ngayketthuc.substring(0,2);

        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT SUM(tienthue) FROM PHIEUMUON WHERE substr(ngay,7)||substr(ngay,4,2)||substr(ngay,1,2) between ? and ?",
                new String[]{bdNew, ktNew});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            return cursor.getInt(0);
        }
        return 0;
    }
}