package com.example.ai_banh_my_khong_dat_g.testmodel;


import com.example.ai_banh_my_khong_dat_g.model.Order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestOrder {
    public static List<Order> deliveringList;
    public static List<Order> deliveredList;

    public static List<Order> getDeliveringList() {
        if (deliveringList == null) {
            deliveringList = new ArrayList<>();
            deliveringList.add(new Order(1, TestItem.getList(), 100000, Order.State.Delivering, new Date(2023, 3, 12)));
        }
        return deliveringList;
    }

    public static List<Order> getDeliveredList() {
        if (deliveredList == null) {
            deliveredList = new ArrayList<>();
            deliveredList.add(new Order(2, TestItem.getList(), 150000, Order.State.Delivered, new Date(2023, 2, 24)));
        }
        return deliveredList;
    }
}
