package com.example.ai_banh_my_khong_dat_g;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import com.example.ai_banh_my_khong_dat_g.databinding.RootBinding;
import com.example.ai_banh_my_khong_dat_g.uicomponent.login.LoginFragment;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    protected RootBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GlobalVariable.currentContext = this;
        Objects.requireNonNull(getSupportActionBar()).hide();
        binding = RootBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}