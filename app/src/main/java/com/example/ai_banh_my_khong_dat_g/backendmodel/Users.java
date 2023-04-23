package com.example.ai_banh_my_khong_dat_g.backendmodel;



public class Users {

    private String id;

    private String hoTen;

    private String passWord;

    private String email;

    public Users(String id, String hoTen, String passWord, String email) {
        this.id = id;
        this.hoTen = hoTen;
        this.passWord = passWord;
        this.email = email;
    }


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHoTen() {
        return this.hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getPassWord() {
        return this.passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    }
