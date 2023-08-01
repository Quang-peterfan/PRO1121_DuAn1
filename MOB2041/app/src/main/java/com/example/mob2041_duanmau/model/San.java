package com.example.mob2041_duanmau.model;

public class San {
    private int masach;
    private String tensach;
    private int giathue;
    private int maloai;
    private int soLuongDaMuon;
    private int Tgian;
    private String tenloai;

    public San(int tgian) {
        Tgian = tgian;
    }

    public int getTgian() {
        return Tgian;
    }

    public void setTgian(int tgian) {
        Tgian = tgian;
    }

    public San(int masach, String tensach, int giathue, int maloai) {
        this.masach = masach;
        this.tensach = tensach;
        this.giathue = giathue;
        this.maloai = maloai;
    }

    public San(int masach, String tensach, int soLuongDaMuon) {
        this.masach = masach;
        this.tensach = tensach;
        this.soLuongDaMuon = soLuongDaMuon;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }

    public San(int masach, String tensach, int giathue, int maloai, String tenloai) {
        this.masach = masach;
        this.tensach = tensach;
        this.giathue = giathue;
        this.maloai = maloai;
        this.tenloai = tenloai;
    }

    public int getSoLuongDaMuon() {
        return soLuongDaMuon;
    }

    public void setSoLuongDaMuon(int soLuongDaMuon) {
        this.soLuongDaMuon = soLuongDaMuon;
    }

    public int getMasach() {
        return masach;
    }

    public void setMasach(int masach) {
        this.masach = masach;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public int getGiathue() {
        return giathue;
    }

    public void setGiathue(int giathue) {
        this.giathue = giathue;
    }

    public int getMaloai() {
        return maloai;
    }

    public void setMaloai(int maloai) {
        this.maloai = maloai;
    }
}
