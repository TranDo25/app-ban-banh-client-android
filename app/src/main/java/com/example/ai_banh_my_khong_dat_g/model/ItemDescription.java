package com.example.ai_banh_my_khong_dat_g.model;

import androidx.lifecycle.ViewModel;

import java.io.Serializable;

public class ItemDescription extends ViewModel implements Serializable {
    protected int descriptionRID;

    public int getDescriptionRID() {
        return descriptionRID;
    }

    public ItemDescription(int descriptionRID) {
        this.descriptionRID = descriptionRID;
    }
}
