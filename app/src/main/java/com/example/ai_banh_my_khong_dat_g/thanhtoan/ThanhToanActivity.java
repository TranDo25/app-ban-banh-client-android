package com.example.ai_banh_my_khong_dat_g.thanhtoan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ai_banh_my_khong_dat_g.R;
import com.example.ai_banh_my_khong_dat_g.ThanhToanZalopayActivity;
import com.example.ai_banh_my_khong_dat_g.Utils;
import com.example.ai_banh_my_khong_dat_g.adapter.OrderAdapter;
import com.example.ai_banh_my_khong_dat_g.api.ApiService;
import com.example.ai_banh_my_khong_dat_g.backendmodel.Cart;
import com.example.ai_banh_my_khong_dat_g.backendmodel.MessageDTO;
import com.example.ai_banh_my_khong_dat_g.model.ItemInBill;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import vn.momo.momo_partner.AppMoMoLib;

public class ThanhToanActivity extends AppCompatActivity {
    //    Button btnmomo;
//    //variable for momo
//    private String amount = "10000";
//    private String fee = "0";
//    int environment = 0;//developer default
//    private String merchantName = "Apple fake";
//    //cần lấy mã đối tác được lấy trong tài khoản momo của doanh nghiệp
//    private String merchantCode = "SCB01";
//    private String merchantNameLabel = "Apple fake";
//    private String description = "mua hàng online";
    private RecyclerView recyclerView;
    private TextView lblTongTien;
    EditText diaChiGiaoHang, sdt, email;
    private List<ItemInBill> dsItem = new ArrayList<>();
    private Button btnThanhToan;
    private double tongTien;
    private OrderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);
        recyclerView = findViewById(R.id.recycleViewBillItem);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        diaChiGiaoHang = findViewById(R.id.txtDiaChi);
        sdt = findViewById(R.id.txtSdt);
//        email = findViewById(R.id.txtEmail);
        lblTongTien = findViewById(R.id.lblTongTien);
        recyclerView.addItemDecoration(itemDecoration);
        dsItem = new ArrayList<>();
        dsItem = (List<ItemInBill>) getIntent().getSerializableExtra("listItemInBill");
        for (ItemInBill i : dsItem) {
            double tongTienTungLoai = i.getDonGia() * i.getSoLuong();
            tongTien += tongTienTungLoai;
        }
        lblTongTien.setText(" " + String.valueOf(tongTien) + " đ");
        btnThanhToan = findViewById(R.id.btnmomo);
        adapter = new OrderAdapter(dsItem);
        recyclerView.setAdapter(adapter);
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String diaChiGiaoHangData = diaChiGiaoHang.getText().toString();
                String soDienThoaiData = sdt.getText().toString();
                if (TextUtils.isEmpty(diaChiGiaoHangData)) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập địa chỉ giao hàng", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(soDienThoaiData)) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập số điện thoại", Toast.LENGTH_SHORT).show();
                    return;
                }
                GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
                ApiService.apiService.getListCartByIdUserNew(account.getEmail()).enqueue(new Callback<List<Cart>>() {
                    @Override
                    public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                        List<Cart> userCart = response.body();
                        if (userCart.size() > 0) {
                            ApiService.apiService.createOrder(userCart).enqueue(
                                    new Callback<String>() {
                                        @Override
                                        public void onResponse(Call<String> call, Response<String> response) {
                                            String messageReturn = response.body();
                                            if (messageReturn.equals("error")) {
                                                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();

                                            } else {
                                                ApiService.apiService.setThongTinGiaoHang(
                                                        Integer.parseInt(messageReturn),
                                                        sdt.getText().toString(),
                                                        diaChiGiaoHang.getText().toString()).enqueue(new Callback<MessageDTO>() {
                                                    @Override
                                                    public void onResponse(Call<MessageDTO> call, Response<MessageDTO> response) {
                                                        String messageFinal = response.body().getMessage();
                                                        Toast.makeText(getApplicationContext(), messageFinal, Toast.LENGTH_SHORT).show();

                                                    }

                                                    @Override
                                                    public void onFailure(Call<MessageDTO> call, Throwable t) {
                                                        Toast.makeText(getApplicationContext(), "error when set order info", Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                            }

                                        }

                                        @Override
                                        public void onFailure(Call<String> call, Throwable t) {
                                            Toast.makeText(getApplicationContext(), "error in create order", Toast.LENGTH_SHORT).show();

                                        }
                                    }
                            );
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Cart>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "error in create order", Toast.LENGTH_SHORT).show();

                    }
                });

                ApiService.apiService.getTheNewestOrderId().enqueue(new Callback<MessageDTO>() {
                    @Override
                    public void onResponse(Call<MessageDTO> call, Response<MessageDTO> response) {
                        int idNewestOrder = Integer.parseInt(response.body().getMessage());
                        Intent intent = new Intent(ThanhToanActivity.this, ThanhToanZalopayActivity.class);
                        intent.putExtra("tongTien", String.valueOf(tongTien));
                        intent.putExtra("diaChiGiaoHang", diaChiGiaoHang.getText().toString());
//                intent.putExtra("email", email.getText().toString());
                        intent.putExtra("sdt", sdt.getText().toString());
                        intent.putExtra("idNewestOrder", idNewestOrder);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<MessageDTO> call, Throwable t) {

                    }
                });

            }
        });
        Log.d("ds item length", String.valueOf(dsItem.size()));


    }

    // Ghi đè phương thức onBackPressed() để xử lý sự kiện khi người dùng nhấn nút back
    @Override
    public void onBackPressed() {
        // Xoá tất cả các phần tử trong RecyclerView
        dsItem.clear();
        Log.d("ds item length", String.valueOf(dsItem.size()));
        adapter.notifyDataSetChanged();
        finish();
        // Gọi phương thức onBackPressed() của lớp cha để hoàn thành việc thoát Activity
        super.onBackPressed();

    }

}


