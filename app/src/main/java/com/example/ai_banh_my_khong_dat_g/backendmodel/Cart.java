package com.example.ai_banh_my_khong_dat_g.backendmodel;


import java.io.Serializable;


public class Cart implements Serializable {

    private Integer id;


    private Integer soluong;


    private Integer productsId;


    private Boolean isChecked;

    public Cart(Integer id, Integer soluong, Integer productsId, Boolean isChecked, String usersId) {
        this.id = id;
        this.soluong = soluong;
        this.productsId = productsId;
        this.isChecked = isChecked;
        this.usersId = usersId;
    }

    private String usersId;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSoluong() {
        return this.soluong;
    }

    public void setSoluong(Integer soluong) {
        this.soluong = soluong;
    }

    public Integer getProductsId() {
        return this.productsId;
    }

    public void setProductsId(Integer productsId) {
        this.productsId = productsId;
    }

    public Boolean getIsChecked() {
        return this.isChecked;
    }

    public void setIsChecked(Boolean isChecked) {
        this.isChecked = isChecked;
    }

    public String getUsersId() {
        return this.usersId;
    }

    public void setUsersId(String usersId) {
        this.usersId = usersId;
    }
}
