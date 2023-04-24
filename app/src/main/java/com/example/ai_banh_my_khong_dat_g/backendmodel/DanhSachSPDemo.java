package com.example.ai_banh_my_khong_dat_g.backendmodel;

public class DanhSachSPDemo {
    private String imageName;
    private String tenBanh;
    private double giaTien;

    public DanhSachSPDemo(String imageName, String tenBanh, double giaTien) {
        this.imageName = imageName;
        this.tenBanh = tenBanh;
        this.giaTien = giaTien;
    }

    public DanhSachSPDemo() {
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getTenBanh() {
        return tenBanh;
    }

    public void setTenBanh(String tenBanh) {
        this.tenBanh = tenBanh;
    }

    public double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
    }
}
