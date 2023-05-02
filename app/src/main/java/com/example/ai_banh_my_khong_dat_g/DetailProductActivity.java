package com.example.ai_banh_my_khong_dat_g;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ai_banh_my_khong_dat_g.api.ApiService;
import com.example.ai_banh_my_khong_dat_g.backendmodel.MessageDTO;
import com.example.ai_banh_my_khong_dat_g.backendmodel.ProductAddToCartDTO;
import com.example.ai_banh_my_khong_dat_g.backendmodel.ProductWithImageDTO;
import com.example.ai_banh_my_khong_dat_g.uicomponent.mainui.CartFragment;
import com.example.ai_banh_my_khong_dat_g.uicomponent.mainui.HomeFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailProductActivity extends AppCompatActivity {
    private TextView moTaSanPham, tenSp, giaGoc, giamGia, soLuong;
    private ImageView image;
    private Button btnAddCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        moTaSanPham = findViewById(R.id.Description);
        tenSp = findViewById(R.id.CL_NameText);
        giaGoc = findViewById(R.id.CL_RealPriceText);
        giamGia = findViewById(R.id.CL_OriginalPriceText);
        image = findViewById(R.id.ItemImage);
        soLuong = findViewById(R.id.QL_NumberText);
        btnAddCart = findViewById(R.id.AddToCartButton);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        ProductWithImageDTO product = (ProductWithImageDTO) bundle.get("object_product");
        tenSp.setText(product.getTenSp());
        moTaSanPham.setText(product.getMoTa());
        giaGoc.setText(String.valueOf(product.getGia()));
        giamGia.setText(String.valueOf(product.getGiamGia()));
        Glide.with(getApplicationContext()).load(product.getImageName()).into(image);
        soLuong.setText("1");
        ImageView decreaseImage = findViewById(R.id.decreaseNumberButton);
        decreaseImage.setOnClickListener(view -> {

            int soLuongHienTai = Integer.parseInt(soLuong.getText().toString());
            if (soLuongHienTai != 0) {
                soLuongHienTai = soLuongHienTai - 1;
                soLuong.setText(String.valueOf(soLuongHienTai));
            }
        });

        ImageView increaseImage = findViewById(R.id.increaseNumberButton);
        increaseImage.setOnClickListener(view -> {
            int soLuongHienTai = Integer.parseInt(soLuong.getText().toString());
            soLuongHienTai = soLuongHienTai + 1;
            soLuong.setText(String.valueOf(soLuongHienTai));
        });
        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
                String idUser = account.getEmail();
                int idProduct = product.getId();
                int soLuongDaChon = Integer.parseInt(soLuong.getText().toString());
                ProductAddToCartDTO res = new ProductAddToCartDTO(idUser, idProduct, soLuongDaChon);
                ApiService.apiService.addProductToCart(res).enqueue(new Callback<MessageDTO>() {
                    @Override
                    public void onResponse(Call<MessageDTO> call, Response<MessageDTO> response) {
                        Log.d("check success call api", "success!");
                        MessageDTO ketqua = response.body();
                        if(ketqua != null){
                            Toast.makeText(DetailProductActivity.this, ketqua.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<MessageDTO> call, Throwable t) {
                        Toast.makeText(DetailProductActivity.this, "call api error", Toast.LENGTH_SHORT).show();
                    }
                });
                onBackPressed();
//                CartFragment cartFragment = new CartFragment();
//                getSupportFragmentManager().beginTransaction()
//                        .add(R.id.fragment_gio_hang_tmp, cartFragment).commit();


//                Intent intent = new Intent(DetailProductActivity.this, HomeFragment.class);
//                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}