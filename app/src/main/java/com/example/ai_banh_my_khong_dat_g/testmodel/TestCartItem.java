package com.example.ai_banh_my_khong_dat_g.testmodel;


import com.example.ai_banh_my_khong_dat_g.model.CartItem;
import com.example.ai_banh_my_khong_dat_g.model.Item;

import java.util.ArrayList;
import java.util.List;

public class TestCartItem {
    private static List<CartItem> list;

    public static List<CartItem> getList()
    {
        if (list == null) {
            List<Item> itemList = TestItem.getList();

            list = new ArrayList<>();
            list.add(new CartItem(1, itemList.get(0)));
            list.add(new CartItem(1, itemList.get(1)));
            list.add(new CartItem(1, itemList.get(2)));
        }

        return list;
    }
}
