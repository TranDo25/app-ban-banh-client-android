package com.example.ai_banh_my_khong_dat_g.backendmodel;


import java.io.Serializable;

public class Orders implements Serializable {


    private Integer id;


    private String ngayTaoDon;



    private String trangThaiDonHang;


    private String momoToken;


    private String sdt;


    private Integer soluong;


    private Double tongtien;


    private String diachigiaohang;

    public Orders(int id,String ngayTaoDon, String trangThaiDonHang, String momoToken, String sdt, Integer soluong, Double tongtien, String diachigiaohang) {
        this.id = id;
        this.ngayTaoDon = ngayTaoDon;
        this.trangThaiDonHang = trangThaiDonHang;
        this.momoToken = momoToken;
        this.sdt = sdt;
        this.soluong = soluong;
        this.tongtien = tongtien;
        this.diachigiaohang = diachigiaohang;
    }

    public Orders() {

    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNgayTaoDon() {
        return this.ngayTaoDon;
    }

    public void setNgayTaoDon(String ngayTaoDon) {
        this.ngayTaoDon = ngayTaoDon;
    }



    public String getTrangThaiDonHang() {
        return this.trangThaiDonHang;
    }

    public void setTrangThaiDonHang(String trangThaiDonHang) {
        this.trangThaiDonHang = trangThaiDonHang;
    }

    public String getMomoToken() {
        return this.momoToken;
    }

    public void setMomoToken(String momoToken) {
        this.momoToken = momoToken;
    }

    public String getSdt() {
        return this.sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public Integer getSoluong() {
        return this.soluong;
    }

    public void setSoluong(Integer soluong) {
        this.soluong = soluong;
    }

    public Double getTongtien() {
        return this.tongtien;
    }

    public void setTongtien(Double tongtien) {
        this.tongtien = tongtien;
    }

    public String getDiachigiaohang() {
        return this.diachigiaohang;
    }

    public void setDiachigiaohang(String diachigiaohang) {
        this.diachigiaohang = diachigiaohang;
    }
}
