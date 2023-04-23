package com.example.ai_banh_my_khong_dat_g.model;

import java.io.Serializable;

public class ItemInBill implements Serializable {
    private String tenSp;
    private int soLuong;
    private double donGia;

    public ItemInBill() {
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public ItemInBill(String tenSp, int soLuong, double donGia) {
        this.tenSp = tenSp;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }
}
