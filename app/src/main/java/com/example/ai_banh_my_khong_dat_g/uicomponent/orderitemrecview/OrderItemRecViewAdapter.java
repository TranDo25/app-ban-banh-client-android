package com.example.ai_banh_my_khong_dat_g.uicomponent.orderitemrecview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.ai_banh_my_khong_dat_g.ChiTietOrderActivity;
import com.example.ai_banh_my_khong_dat_g.R;
import com.example.ai_banh_my_khong_dat_g.backendmodel.ChiTietOrder;
import com.example.ai_banh_my_khong_dat_g.backendmodel.Orders;
import com.example.ai_banh_my_khong_dat_g.model.Order;

import java.util.List;

public class OrderItemRecViewAdapter extends RecyclerView.Adapter<OrderItemRecViewHolder> {
    private final Context context;
    private List<Orders> modelList;

    public OrderItemRecViewAdapter(Context context, List<Orders> itemList) {
        this.context = context;
        setViewModelList(itemList);

    }


    public void setViewModelList(List<Orders> modelList) {
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public OrderItemRecViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.order_item, parent, false);
        return new OrderItemRecViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderItemRecViewHolder holder, int position) {

        Orders model = modelList.get(position);
        holder.setModel(model);
        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickGotoDetail(model);
            }
        });
    }

    private void onClickGotoDetail(Orders model) {
        Intent intent = new Intent(context, ChiTietOrderActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("chi_tiet_order", model);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}