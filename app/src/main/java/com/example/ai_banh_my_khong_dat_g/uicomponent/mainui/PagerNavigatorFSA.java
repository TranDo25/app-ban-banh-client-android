package com.example.ai_banh_my_khong_dat_g.uicomponent.mainui;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;


import com.example.ai_banh_my_khong_dat_g.uicomponent.EmptyFragment;

import java.util.ArrayList;
import java.util.List;

public class PagerNavigatorFSA extends FragmentStateAdapter {
    private final List<Fragment> fragmentList = new ArrayList<>();

    public PagerNavigatorFSA(@NonNull Fragment fragment) {
        super(fragment);

        fragmentList.add(fragment.getParentFragmentManager().getFragmentFactory().instantiate(ClassLoader.getSystemClassLoader(), HomeFragment.class.getName()));
        fragmentList.add(fragment.getParentFragmentManager().getFragmentFactory().instantiate(ClassLoader.getSystemClassLoader(), ItemMenuFragment.class.getName()));
        fragmentList.add(fragment.getParentFragmentManager().getFragmentFactory().instantiate(ClassLoader.getSystemClassLoader(), YeuThichFragment.class.getName()));
        fragmentList.add(fragment.getParentFragmentManager().getFragmentFactory().instantiate(ClassLoader.getSystemClassLoader(), CartFragment.class.getName()));
        fragmentList.add(fragment.getParentFragmentManager().getFragmentFactory().instantiate(ClassLoader.getSystemClassLoader(), OrderFragment.class.getName()));
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }
}
