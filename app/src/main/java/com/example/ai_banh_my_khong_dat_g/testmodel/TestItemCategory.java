package com.example.ai_banh_my_khong_dat_g.testmodel;


import com.example.ai_banh_my_khong_dat_g.R;
import com.example.ai_banh_my_khong_dat_g.model.ItemCategory;


import java.util.ArrayList;
import java.util.List;

public class TestItemCategory {
    private static List<ItemCategory> list;

    public static List<ItemCategory> getList()
    {
        if (list == null) {
            list = new ArrayList<>();
            list.add(new ItemCategory(R.drawable.icon_coffee, R.string.coffee));
            list.add(new ItemCategory(R.drawable.icon_cake, R.string.cake));
            list.add(new ItemCategory(R.drawable.icon_fruit, R.string.fruit));
            list.add(new ItemCategory(R.drawable.icon_hamburger, R.string.hamburger));
            list.add(new ItemCategory(R.drawable.icon_cake, R.string.cake));
        }
        return list;
    }
}
