package com.example.ai_banh_my_khong_dat_g;

import android.annotation.SuppressLint;
import android.content.Context;

import java.util.Random;

public class GlobalVariable {
    @SuppressLint("StaticFieldLeak")
    public static Context currentContext;

    public static Random random = new Random(369);
}
