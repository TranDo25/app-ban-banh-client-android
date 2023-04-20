package com.example.ai_banh_my_khong_dat_g.model;

import androidx.lifecycle.ViewModel;
import com.example.ai_banh_my_khong_dat_g.testmodel.TestItemDescription;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Item extends ViewModel implements Serializable {
    //ảnh
    protected int imageRID;
    //tên
    protected int nameRID;
    //giá
    protected int price;
    //giảm giá
    protected int priceDown;
    //trạng thái ưa thích
    protected boolean favourite;

    protected ItemDescription itemDescription;

    public int getImageRID() {
        return imageRID;
    }

    public int getNameRID() {
        return nameRID;
    }

    public int getPrice() {
        return price;
    }

    public int getPriceDown() {
        return priceDown;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public ItemDescription getItemDescription() {
        return itemDescription;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    public Item(int imageRID, int nameRID, int price, int priceDown, boolean favourite) {
        this.imageRID = imageRID;
        this.nameRID = nameRID;
        this.price = price;
        this.priceDown = priceDown;
        this.favourite = favourite;
        this.itemDescription = TestItemDescription.getTestItemDescription();
    }

    public int getRealPrice() {
        return (int)((float)price*priceDown/100.0f);
    }
}
