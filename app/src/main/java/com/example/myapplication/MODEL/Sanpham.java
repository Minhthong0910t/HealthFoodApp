package com.example.myapplication.MODEL;

import java.util.Map;

public class Sanpham {
    private String masp;
    private String name;
    private double price;
    private int time_ship;
    private String describe;//mo ta
    private int luot_mua;//so luong

    private boolean favorite;
    private String imgURL;
    private Map<String, Comment> comments;
    private String ten_loai;

    private int starDanhGia;

    public Sanpham() {
    }

    public Sanpham(String masp, String name, double price, int time_ship, String describe, int amount, boolean favorite, String imgURL, Map<String, Comment> comments,String ten_loai,int starDanhGia) {
        this.masp = masp;
        this.name = name;
        this.price = price;
        this.time_ship = time_ship;
        this.describe = describe;
        this.luot_mua = amount;
        this.favorite = favorite;
        this.imgURL = imgURL;
        this.comments = comments;
        this.ten_loai  = ten_loai;
        this.starDanhGia = starDanhGia;

    }

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTime_ship() {
        return time_ship;
    }

    public void setTime_ship(int time_ship) {
        this.time_ship = time_ship;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getLuot_mua() {
        return luot_mua;
    }

    public void setLuot_mua(int luot_mua) {
        this.luot_mua = luot_mua;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public Map<String, Comment> getComments() {
        return comments;
    }

    public void setComments(Map<String, Comment> comments) {
        this.comments = comments;
    }

    public String getTen_loai() {
        return ten_loai;
    }

    public void setTen_loai(String ten_loai) {
        this.ten_loai = ten_loai;
    }

    public int getStarDanhGia() {
        return starDanhGia;
    }

    public void setStarDanhGia(int starDanhGia) {
        this.starDanhGia = starDanhGia;
    }
}
