package com.example.ai_banh_my_khong_dat_g.uicomponent.splash;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.ai_banh_my_khong_dat_g.R;
import com.example.ai_banh_my_khong_dat_g.databinding.Splash1Binding;


public class Splash1Fragment extends Fragment {
    private Splash1Binding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = Splash1Binding.inflate(inflater, container, false);

        setupLoadingIcon();

        final Handler handler= new Handler(Looper.getMainLooper());
        handler.postDelayed(
                () -> Navigation.findNavController(container).navigate(R.id.action_splash1Fragment_to_loginFragment), 3000);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setupLoadingIcon() {
        Animation rotation = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_360);
        rotation.setRepeatCount(Animation.INFINITE);
        binding.LoadingCircleImage.startAnimation(rotation);
    }
}