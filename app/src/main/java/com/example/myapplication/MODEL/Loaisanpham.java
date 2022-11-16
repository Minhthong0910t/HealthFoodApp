package com.example.myapplication.MODEL;

import java.util.List;
import java.util.Map;

public class Loaisanpham {
    private String maLoai;
    private String name;
    private Map<String, Sanpham> sanphams;

    public Loaisanpham() {
    }

    public Loaisanpham(String maLoai,String name, Map<String, Sanpham> sanphams) {
        this.maLoai = maLoai;
        this.name = name;
        this.sanphams = sanphams;
    }

    public String getName() {
        return name;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Sanpham> getSanphams() {
        return sanphams;
    }

    public void setSanphams(Map<String, Sanpham> sanphams) {
        this.sanphams = sanphams;
    }
}
