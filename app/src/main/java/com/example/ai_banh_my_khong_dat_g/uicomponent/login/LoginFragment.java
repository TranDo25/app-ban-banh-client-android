package com.example.ai_banh_my_khong_dat_g.uicomponent.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;


import com.example.ai_banh_my_khong_dat_g.AnimateDuration;
import com.example.ai_banh_my_khong_dat_g.R;
import com.example.ai_banh_my_khong_dat_g.databinding.LoginBinding;
import com.example.ai_banh_my_khong_dat_g.uicomponent.mainui.HomeFragment;
import com.example.ai_banh_my_khong_dat_g.uicomponent.mainui.MainUIFragment;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.Nullable;

import java.util.concurrent.Executor;


public class LoginFragment extends Fragment {

    private LoginBinding binding;
    private EditText emailEdit, passEdit, repeatPassword;
    private Button btnLogin, btnRegis, btnLoginWithGoogle;
    private FirebaseAuth mAuth;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    private boolean check_login_status = true;
//    CallbackManager mCallbackManager;
//    LoginButton btnLoginWithFacebook;
    public LoginFragment(){

    }
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = LoginBinding.inflate(inflater, container, false);
        setupClickableSwitch();
        setupButton();
        //initialize facebook sdk
//        FacebookSdk.sdkInitialize(getActivity());
        //initialize firebase
//        mAuth = FirebaseAuth.getInstance();
        // Initialize Facebook Login button
//        mCallbackManager = CallbackManager.Factory.create();
//        LoginButton loginButton = getView().findViewById(R.id.BL_FacebookLoginButton);
//        loginButton.setReadPermissions("email", "public_profile");
//        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//
//                handleFacebookAccessToken(loginResult.getAccessToken());
//            }
//
//            @Override
//            public void onCancel() {
//
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//
//            }
//        });
        return binding.getRoot();
    }
//    private void handleFacebookAccessToken(AccessToken token) {
//
//        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
//        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            updateUI(user);
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Toast.makeText(getActivity(), "Authentication failed.",Toast.LENGTH_SHORT).show();
//                            updateUI(null);
//                        }
//                    }
//                });
//    }

//    private void updateUI(FirebaseUser user) {
//        if(user!=null){
//            Navigation.findNavController(getView()).navigate(R.id.action_loginFragment_to_mainUIFragment)
//        }else{
//            Toast.makeText(getContext(), "please sign in to continue", Toast.LENGTH_SHORT).show();
//        }
//    }

    @Override
    public void onViewCreated(@NonNull View view, @androidx.annotation.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        btnLogin = binding.MLLoginButton;
        emailEdit = binding.MLUsernameField;
        passEdit = binding.MLPasswordField;
        btnLoginWithGoogle = binding.BLGoogleLoginButton;
        repeatPassword = binding.MLRepeatPasswordField;

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(getContext(), gso);
        btnLoginWithGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignInWithGoogle(view);
            }

            private void SignInWithGoogle(View view) {
                Intent intent = gsc.getSignInIntent();
                startActivityForResult(intent, 100);

            }
        });
        if (btnLogin != null) {
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (check_login_status == true) {
                        login(view);
                    } else if (check_login_status == false) {
                        register();
                    }
                }
            });
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                task.getResult(ApiException.class);
                HomeActivity();
            } catch (ApiException e) {
                Toast.makeText(getContext(), "Lỗi khi đăng nhập với google", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void HomeActivity() {
//        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//        fragmentManager
//                .beginTransaction()
//                .replace(R.id.Root, MainUIFragment.newInstance())
//                .commit();
        View view = this.getView();
        Navigation.findNavController(getView()).navigate(R.id.action_loginFragment_to_mainUIFragment);
    }

    private void login(View view) {
        String email, pass;
        email = emailEdit.getText().toString();
        pass = passEdit.getText().toString();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getContext(), "Vui lòng nhập email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(getContext(), "Vui lòng nhập password", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getContext(), "đăng nhập thành công", Toast.LENGTH_SHORT).show();
                   Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_mainUIFragment);


                    //nhớ đem intent tên đăng nhập và mật khẩu sang

                } else {
                    Toast.makeText(getContext(), "đăng nhập không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void register() {
        String email, pass, repeatPass;
        email = emailEdit.getText().toString();
        pass = passEdit.getText().toString();
        repeatPass = repeatPassword.getText().toString();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getContext(), "Vui lòng nhập email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(getContext(), "Vui lòng nhập password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!pass.equals(repeatPass)) {
            Toast.makeText(getContext(), "mật khẩu không đúng", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getContext(), "tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
                    check_login_status = true;
                    long animateDuration = AnimateDuration.veryFast;
                    int offset = binding.TLSwitchLoginText.getMeasuredWidth() / 2;
                    binding.TLSwitchIndicatorImage
                            .animate()
                            .translationX(-offset)
                            .setDuration(animateDuration)
                            .start();

                    binding.MLForgotPasswordText
                            .animate()
                            .scaleX(1.0f).scaleY(1.0f)
                            .setDuration(animateDuration)
                            .start();

                    binding.MLRepeatPasswordField
                            .animate()
                            .scaleX(0.0f).scaleY(0.0f)
                            .setDuration(animateDuration)
                            .start();

                    binding.BottomLayout
                            .animate()
                            .translationY(0.0f)
                            .setDuration(animateDuration)
                            .start();

                    binding.MLLoginButton.setText(R.string.login);

                } else {
                    Toast.makeText(getContext(), "tạo tài khoản không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setupClickableSwitch() {
        long animateDuration = AnimateDuration.veryFast;

        binding.TLSwitchLoginText.setOnClickListener(view -> {
            check_login_status = true;
            int offset = binding.TLSwitchLoginText.getMeasuredWidth() / 2;
            binding.TLSwitchIndicatorImage
                    .animate()
                    .translationX(-offset)
                    .setDuration(animateDuration)
                    .start();

            binding.MLForgotPasswordText
                    .animate()
                    .scaleX(1.0f).scaleY(1.0f)
                    .setDuration(animateDuration)
                    .start();

            binding.MLRepeatPasswordField
                    .animate()
                    .scaleX(0.0f).scaleY(0.0f)
                    .setDuration(animateDuration)
                    .start();

            binding.BottomLayout
                    .animate()
                    .translationY(0.0f)
                    .setDuration(animateDuration)
                    .start();

            binding.MLLoginButton.setText(R.string.login);
        });

        binding.TLSwitchRegisterText.setOnClickListener(view -> {
            check_login_status = false;
            int offset = binding.TLSwitchLoginText.getMeasuredWidth() / 2;
            binding.TLSwitchIndicatorImage
                    .animate()
                    .translationX(+offset)
                    .setDuration(animateDuration)
                    .start();

            binding.MLForgotPasswordText
                    .animate()
                    .scaleX(0.0f).scaleY(0.0f)
                    .setDuration(animateDuration)
                    .start();

            binding.MLRepeatPasswordField
                    .animate()
                    .scaleX(1.0f).scaleY(1.0f)
                    .setDuration(animateDuration)
                    .start();

            binding.BottomLayout
                    .animate()
                    .translationY(binding.BottomLayout.getMeasuredHeight())
                    .setDuration(animateDuration)
                    .start();

            binding.MLLoginButton.setText(R.string.register);
        });
    }

    public static LoginFragment newInstance(View view) {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
    }
    private void setupButton() {
        View.OnClickListener callback = view -> Toast.makeText(getContext(), "Chưa cài đặt", Toast.LENGTH_SHORT).show();
        binding.MLForgotPasswordText.setOnClickListener(callback);
        binding.BLFacebookLoginButton.setOnClickListener(callback);
        binding.MLLoginButton.setOnClickListener(view-> Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_mainUIFragment));
    }
}