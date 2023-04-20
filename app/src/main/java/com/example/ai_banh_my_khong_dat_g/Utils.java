package com.example.ai_banh_my_khong_dat_g;

import java.util.Locale;

public class Utils {
    public static String formatPrice(int value) {
        return String.format(Locale.ENGLISH, "%,d", value) + " đ";
    }
}
