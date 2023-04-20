package com.example.ai_banh_my_khong_dat_g.uicomponent.mainui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewpager2.widget.ViewPager2;


import com.example.ai_banh_my_khong_dat_g.AnimateDuration;
import com.example.ai_banh_my_khong_dat_g.R;

import com.example.ai_banh_my_khong_dat_g.databinding.MainuiBinding;
import com.example.ai_banh_my_khong_dat_g.uicomponent.login.LoginFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;


public class MainUIFragment extends Fragment {
    public static MainUIFragment currentInstance;
    TextView name, mail;
    Button logout;
    protected MainuiBinding binding;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = MainuiBinding.inflate(inflater, container, false);
        setupCenterLayout();
        setupSideNavigatorBlocker();
        setupBottomNavigator();


        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(getContext(), gso);
        name = binding.SNUserNameText;
        mail = binding.SNUserEmailText;
        logout = binding.SNLogoutButton;
        name.setFocusable(true);
        mail.setFocusable(true);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getContext());
        if (account != null) {
            String Name = account.getDisplayName();
            String Mail = account.getEmail();
            if (name.isFocusable() == true) {
                name.setText(Name);
                mail.setText(Mail);
            }
        }
        setupSideNavigator();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignOut();
            }
        });
        currentInstance = this;


        return binding.getRoot();
    }

    private void SignOut() {
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            Navigation.findNavController(getView()).navigate(R.id.action_mainUIFragment_to_loginFragment2);
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        currentInstance = null;
    }

    private void setupCenterLayout() {
        binding.CLPagerNavigator.setUserInputEnabled(false);
        binding.CLPagerNavigator.setAdapter(new PagerNavigatorFSA(this));
        binding.CLPagerNavigator.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            private final int[] BOTTOM_NAVIGATOR_MENU_RID = new int[] {
                    R.id.Home,
                    R.id.Item,
                    R.id.Favourite,
                    R.id.Cart,
                    R.id.Order
            };

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                binding.CLBottomNavigator.getMenu().findItem(BOTTOM_NAVIGATOR_MENU_RID[position]).setChecked(true);
            }
        });
    }

    private void setupSideNavigator() {
        binding.SNLogoutButton.setOnClickListener(view -> Navigation.findNavController(view).navigate(R.id.action_mainUIFragment_to_loginFragment2));
    }

    private void setupSideNavigatorBlocker() {
        binding.CenterLayoutBlocker.setOnClickListener(view -> {
            long animateDuration = AnimateDuration.veryFast;
            binding.CenterLayoutBlocker.setClickable(false);
            binding.CenterLayoutBlocker.animate().alpha(0.0f).setDuration(animateDuration).start();

            int sidebarWidth = binding.SideNavigator.getMeasuredWidth();
            binding.SideNavigator.animate().translationX(-sidebarWidth).setDuration(animateDuration).start();
            binding.CenterLayout.animate()
                    .translationX(0.0f).translationY(0.0f)
                    .scaleX(1.0f).scaleY(1.0f)
                    .setDuration(animateDuration)
                    .start();
        });
    }

    private void setupBottomNavigator() {
        binding.CLBottomNavigator.setOnItemSelectedListener(item -> {
            binding.CLPagerNavigator.setCurrentItem(item.getOrder());
            return true;
        });
    }

    public View.OnClickListener onOpenSideNavigatorListener() {
        return view -> {
            long animateDuration = AnimateDuration.veryFast;

            binding.CenterLayoutBlocker.setClickable(true);
            binding.CenterLayoutBlocker.animate().alpha(1.0f).setDuration(animateDuration).start();

            int sidebarWidth = binding.SideNavigator.getMeasuredWidth();
            binding.SideNavigator.animate().translationX(0.0f).setDuration(animateDuration).start();
            binding.CenterLayout.animate()
                    .translationX(sidebarWidth+32.0f).translationY((1.0f-0.65f)*0.5f* binding.CenterLayout.getMeasuredHeight())
                    .scaleX(0.65f).scaleY(0.65f)
                    .setDuration(animateDuration)
                    .start();
        };
    }
}