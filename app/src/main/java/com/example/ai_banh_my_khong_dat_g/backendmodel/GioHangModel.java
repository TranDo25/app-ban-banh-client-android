package com.example.ai_banh_my_khong_dat_g.backendmodel;

import java.util.ArrayList;
import java.util.List;

public class GioHangModel {
    private List<ProductWithImageDTO> dsMatHangTrongGio = new ArrayList<>();

    public GioHangModel(List<ProductWithImageDTO> dsMatHangTrongGio) {
        this.dsMatHangTrongGio = dsMatHangTrongGio;
    }

    public List<ProductWithImageDTO> getDsMatHangTrongGio() {
        return dsMatHangTrongGio;
    }

    public void setDsMatHangTrongGio(List<ProductWithImageDTO> dsMatHangTrongGio) {
        this.dsMatHangTrongGio = dsMatHangTrongGio;
    }

    public GioHangModel() {
    }
}