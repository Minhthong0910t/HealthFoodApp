package com.example.myapplication.MODEL;

import java.util.List;

public class DonHang {
    private String sdt;
    private String name;
    private String diaChi;
    private double tongTien;
    private String trangThai;
    private List<GioHang> sanphams;

    public DonHang() {
    }

    public DonHang(String sdt, String name, String diaChi, double tongTien, String trangThai, List<GioHang> sanphams) {
        this.sdt = sdt;
        this.name = name;
        this.diaChi = diaChi;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
        this.sanphams = sanphams;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public List<GioHang> getSanphams() {
        return sanphams;
    }

    public void setSanphams(List<GioHang> sanphams) {
        this.sanphams = sanphams;
    }
}
