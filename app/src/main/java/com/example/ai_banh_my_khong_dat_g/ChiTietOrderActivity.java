package com.example.ai_banh_my_khong_dat_g;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ai_banh_my_khong_dat_g.backendmodel.Orders;
import com.example.ai_banh_my_khong_dat_g.googlemapapi.MapsActivity;

public class ChiTietOrderActivity extends AppCompatActivity {
    private TextView txtMaDonHang, txtNgayTaoDon, txtTrangThaiDonHang,txtSdt, txtTongTien, txtDiaChiGiaoHang;
    private Button btnViewDetailOrderOk, btnViewAddressMap;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_order);
        txtMaDonHang = findViewById(R.id.txtMaDonHangDetail);
        btnViewAddressMap = findViewById(R.id.btnViewAddressMap);
        txtNgayTaoDon = findViewById(R.id.txtNgayTaoDonDetail);
        txtSdt = findViewById(R.id.txtSdt);
        txtTrangThaiDonHang = findViewById(R.id.txtMaDonHangDetail);
        txtTongTien = findViewById(R.id.txtTongTienDetail);
        txtDiaChiGiaoHang = findViewById(R.id.txtdiaChiGiaoHangDetail);
        btnViewDetailOrderOk = findViewById(R.id.btnViewDetailOrderOk);
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        Orders order = (Orders) bundle.get("chi_tiet_order");
        txtMaDonHang.setText(order.getId().toString());
        txtNgayTaoDon.setText(order.getNgayTaoDon());
        txtSdt.setText(order.getSdt());
        txtTrangThaiDonHang.setText(order.getTrangThaiDonHang());
        txtTongTien.setText(String.valueOf(order.getTongtien()));
        txtDiaChiGiaoHang.setText(order.getDiachigiaohang());
        btnViewDetailOrderOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btnViewAddressMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChiTietOrderActivity.this, MapsActivity.class);
                intent.putExtra("diaChiGiaoHang", order.getDiachigiaohang());
                startActivity(intent);
            }
        });

    }
}