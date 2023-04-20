package com.example.ai_banh_my_khong_dat_g.model;

import androidx.lifecycle.ViewModel;

import java.io.Serializable;

public class ItemCategory extends ViewModel implements Serializable {
    protected int imageRID;
    protected int nameRID;

    public int getImageRID() {
        return imageRID;
    }

    public int getNameRID() {
        return nameRID;
    }

    public ItemCategory(int imageRID, int nameRID) {
        this.imageRID = imageRID;
        this.nameRID = nameRID;
    }
}
