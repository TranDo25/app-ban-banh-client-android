package com.example.ai_banh_my_khong_dat_g.backendmodel;

import java.util.ArrayList;
import java.util.List;

public class GioHangModel {
    private List<Products> dsMatHangTrongGio = new ArrayList<>();

    public GioHangModel(List<Products> dsMatHangTrongGio) {
        this.dsMatHangTrongGio = dsMatHangTrongGio;
    }

    public List<Products> getDsMatHangTrongGio() {
        return dsMatHangTrongGio;
    }

    public void setDsMatHangTrongGio(List<Products> dsMatHangTrongGio) {
        this.dsMatHangTrongGio = dsMatHangTrongGio;
    }
}