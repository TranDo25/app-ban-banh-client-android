package com.example.ai_banh_my_khong_dat_g.backendmodel;



import java.io.Serializable;


public class ProductWithImageDTO implements Serializable {
    private Integer id;
    private String tenSp;
    private String moTa;
    private Double gia;
    private String hinhAnh;
    private Integer soVote;
    private Integer categoryId;
    private String imageName;
    private Double giamGia;

    public ProductWithImageDTO(Integer id, String tenSp, String moTa, Double gia, String hinhAnh, Integer soVote, Integer categoryId, String imageName, Double giamGia) {
        this.id = id;
        this.tenSp = tenSp;
        this.moTa = moTa;
        this.gia = gia;
        this.hinhAnh = hinhAnh;
        this.soVote = soVote;
        this.categoryId = categoryId;
        this.imageName = imageName;
        this.giamGia = giamGia;
    }

    public ProductWithImageDTO() {
    }

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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Double getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(Double giamGia) {
        this.giamGia = giamGia;
    }
}
