package com.example.ai_banh_my_khong_dat_g;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ai_banh_my_khong_dat_g.api.ApiService;
import com.example.ai_banh_my_khong_dat_g.backendmodel.MessageDTO;
import com.example.ai_banh_my_khong_dat_g.backendmodel.ProductWithImageDTO;
import com.example.ai_banh_my_khong_dat_g.backendmodel.ProductWithImageWithNumberDTO;
import com.example.ai_banh_my_khong_dat_g.uicomponent.mainui.CartFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietSanPhamFromCartActivity extends AppCompatActivity {
    private TextView moTaSanPham, tenSp, giaGoc, giamGia, soLuong;
    private ImageView image;
    private Button btnCancel, btnOk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham_from_cart);
        moTaSanPham = findViewById(R.id.Description);
        tenSp = findViewById(R.id.CL_NameText);
        giaGoc = findViewById(R.id.CL_RealPriceText);
        giamGia = findViewById(R.id.CL_OriginalPriceText);
        image = findViewById(R.id.ItemImage);
        soLuong = findViewById(R.id.QL_NumberText);
        btnCancel = findViewById(R.id.btnCancel);
        btnOk = findViewById(R.id.btnOk);
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        ProductWithImageWithNumberDTO product = (ProductWithImageWithNumberDTO) bundle.get("object_product_from_cart");
        tenSp.setText(product.getProductWithImageDTO().getTenSp());
        moTaSanPham.setText(product.getProductWithImageDTO().getMoTa());
        giaGoc.setText(String.valueOf(product.getProductWithImageDTO().getGia()));
        giamGia.setText(String.valueOf(product.getProductWithImageDTO().getGiamGia()));
        Glide.with(getApplicationContext()).load(product.getProductWithImageDTO().getImageName()).into(image);
        soLuong.setText(String.valueOf(product.getSoLuongMuonMua()));
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
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ApiService.apiService.changeNumberOfItem(product.getCartId(),Integer.parseInt(soLuong.getText().toString())).enqueue(new Callback<MessageDTO>() {
                    @Override
                    public void onResponse(Call<MessageDTO> call, Response<MessageDTO> response) {
                        MessageDTO ketqua = response.body();
                        if(ketqua != null){
                            Toast.makeText(ChiTietSanPhamFromCartActivity.this, ketqua.getMessage(), Toast.LENGTH_SHORT).show();
                            onBackPressed();

                        }
                    }

                    @Override
                    public void onFailure(Call<MessageDTO> call, Throwable t) {
                        Toast.makeText(ChiTietSanPhamFromCartActivity.this, "error update number", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
    @Override
    public void onBackPressed() {
        finish();

    }


}