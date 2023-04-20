package com.example.ai_banh_my_khong_dat_g.model;

import androidx.lifecycle.ViewModel;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Order extends ViewModel implements Serializable {
    public enum State {
        Preparing, Delivering, Delivered
    }

    protected int orderID;
    protected List<Item> itemList;
    protected int totalPrice;
    protected State state;

    public int getOrderID() {
        return orderID;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public State getState() {
        return state;
    }

    public Date getDate() {
        return date;
    }

    protected Date date;

    public Order(int orderID, List<Item> itemList, int totalPrice, State state, Date date) {
        this.orderID = orderID;
        this.itemList = itemList;
        this.totalPrice = totalPrice;
        this.state = state;
        this.date = date;
    }
}
