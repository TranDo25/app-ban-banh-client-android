package com.example.ai_banh_my_khong_dat_g.model;

import java.io.Serializable;

public class CartItem implements Serializable {
    protected int amount;
    protected Item item;




    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Item getItem() {
        return item;
    }

    public CartItem(int amount, Item item) {
        this.amount = amount;
        this.item = item;
    }
}
