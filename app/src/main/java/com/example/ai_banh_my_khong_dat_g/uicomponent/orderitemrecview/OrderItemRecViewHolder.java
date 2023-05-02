package com.example.ai_banh_my_khong_dat_g.uicomponent.orderitemrecview;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ai_banh_my_khong_dat_g.R;
import com.example.ai_banh_my_khong_dat_g.Utils;
import com.example.ai_banh_my_khong_dat_g.backendmodel.Orders;
import com.example.ai_banh_my_khong_dat_g.model.Item;
import com.example.ai_banh_my_khong_dat_g.model.Order;


public class OrderItemRecViewHolder extends RecyclerView.ViewHolder {
    private TextView maDonHang, soTien, trangThaiDonHang, ngayTaoDon;
    private Orders model;

    public ConstraintLayout layoutItem;

    public OrderItemRecViewHolder(@NonNull View itemView) {
        super(itemView);
        maDonHang = itemView.findViewById(R.id.maDonHang);
        soTien = itemView.findViewById(R.id.soTien);
        trangThaiDonHang = itemView.findViewById(R.id.trangThaiDonHang);
        ngayTaoDon = itemView.findViewById(R.id.ngayTaoDon);
        layoutItem = itemView.findViewById(R.id.OrderItemViewHolder);
    }

    public void setModel(@NonNull Orders model) {
        if (this.model == model)
            return;

        this.model = model;
        validateLayout();
    }

    @SuppressLint("SetTextI18n")
    private void validateLayout() {
        maDonHang.setText(model.getId().toString());
        soTien.setText(String.valueOf(model.getTongtien()));
        trangThaiDonHang.setText(model.getTrangThaiDonHang());
        ngayTaoDon.setText(model.getNgayTaoDon());

    }
}