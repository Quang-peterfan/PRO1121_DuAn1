package com.example.mob2041_duanmau.model;

public class HoaDon {
    private int mapm;
    private int matv;
    private String matt;
    private int masach;
    private String ngay;
    private int trasach;
    private int tienthue;
    private String tentv;
    private String tentt;
    private String tensach;
//    private String tgian;


    //pm.mapm, pm.matv, tv.hoten, pm.matt, tt.hoten, pm.masach, sc.tensach, pm.ngay, pm.trasach, pm.tienthue FROM PHIEUMUON pm, THANHVIEN tv, THUTHU tt, SACH sc WHERE pm.matv = tv.matv and pm.matt = tt.matt AND pm.masach = sc.masach
    public HoaDon(int mapm, int matv, String tentv, String matt, String tentt, int masach, String tensach, String ngay, int trasach, int tienthue) {
        this.mapm = mapm;
        this.matv = matv;
        this.matt = matt;
        this.masach = masach;
        this.ngay = ngay;
        this.trasach = trasach;
        this.tienthue = tienthue;
        this.tentv = tentv;
        this.tentt = tentt;
        this.tensach = tensach;
    }

    public HoaDon(int matv, String matt, int masach, String ngay, int trasach, int tienthue) {
        this.matv = matv;
        this.matt = matt;
        this.masach = masach;
        this.ngay = ngay;
        this.trasach = trasach;
        this.tienthue = tienthue;

    }
//
//    public String getTgian() {
//        return tgian;
//    }
//
//    public void setTgian(String tgian) {
//        this.tgian = tgian;
//    }

    public int getMapm() {
        return mapm;
    }

    public void setMapm(int mapm) {
        this.mapm = mapm;
    }

    public int getMatv() {
        return matv;
    }

    public void setMatv(int matv) {
        this.matv = matv;
    }

    public String getMatt() {
        return matt;
    }

    public void setMatt(String matt) {
        this.matt = matt;
    }

    public int getMasach() {
        return masach;
    }

    public void setMasach(int masach) {
        this.masach = masach;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public int getTrasach() {
        return trasach;
    }

    public void setTrasach(int trasach) {
        this.trasach = trasach;
    }

    public int getTienthue() {
        return tienthue;
    }

    public void setTienthue(int tienthue) {
        this.tienthue = tienthue;
    }

    public String getTentv() {
        return tentv;
    }

    public void setTentv(String tentv) {
        this.tentv = tentv;
    }

    public String getTentt() {
        return tentt;
    }

    public void setTentt(String tentt) {
        this.tentt = tentt;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }
}