package com.example.ai_banh_my_khong_dat_g.uicomponent.orderitemrecview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.ai_banh_my_khong_dat_g.R;
import com.example.ai_banh_my_khong_dat_g.model.Order;

import java.util.List;

public class OrderItemRecViewAdapter extends RecyclerView.Adapter<OrderItemRecViewHolder> {
    private final Context context;
    private List<Order> modelList;

    public OrderItemRecViewAdapter(Context context, List<Order> itemList) {
        this.context = context;
        setViewModelList(itemList);
    }



    public void setViewModelList(List<Order> modelList) {
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
        Order model = modelList.get(position);
        holder.setModel(model);
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}