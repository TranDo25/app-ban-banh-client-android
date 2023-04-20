package com.example.ai_banh_my_khong_dat_g.backendmodel;

public class Products {
    private Integer id;
    private String tenSp;
    private String moTa;
    private Double gia;
    private String hinhAnh;
    private Integer soVote;
    private Integer categoryId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Double getGia() {
        return gia;
    }

    public void setGia(Double gia) {
        this.gia = gia;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public Integer getSoVote() {
        return soVote;
    }

    public void setSoVote(Integer soVote) {
        this.soVote = soVote;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Products(Integer id, String tenSp, String moTa, Double gia, String hinhAnh, Integer soVote, Integer categoryId) {
        this.id = id;
        this.tenSp = tenSp;
        this.moTa = moTa;
        this.gia = gia;
        this.hinhAnh = hinhAnh;
        this.soVote = soVote;
        this.categoryId = categoryId;
    }
}
