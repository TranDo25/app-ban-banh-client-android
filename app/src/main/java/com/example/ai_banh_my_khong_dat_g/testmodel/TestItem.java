package com.example.ai_banh_my_khong_dat_g.testmodel;


import com.example.ai_banh_my_khong_dat_g.R;
import com.example.ai_banh_my_khong_dat_g.model.Item;


import java.util.ArrayList;
import java.util.List;

public class TestItem {
    private static List<Item> list;

    public static List<Item> getList()
    {
        if (list == null) {
            list = new ArrayList<>();
            list.add(new Item(R.drawable.test_image_1, R.string.app_name, 100000, 50, false));
            list.add(new Item(R.drawable.test_image_2, R.string.app_name, 100000, 50, false));
            list.add(new Item(R.drawable.test_image_3, R.string.app_name, 100000, 50, false));
            list.add(new Item(R.drawable.test_image_4, R.string.app_name, 100000, 50, false));
            list.add(new Item(R.drawable.test_image_5, R.string.app_name, 100000, 50, false));
            list.add(new Item(R.drawable.test_image_6, R.string.app_name, 100000, 50, false));
            list.add(new Item(R.drawable.test_image_7, R.string.app_name, 100000, 50, false));
        }

        return list;
    }

    public static Item getItem() {
        return getList().get(0);
    }
}
