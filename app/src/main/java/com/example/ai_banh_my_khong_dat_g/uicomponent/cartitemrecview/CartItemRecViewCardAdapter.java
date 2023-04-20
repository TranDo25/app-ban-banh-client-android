package com.example.ai_banh_my_khong_dat_g.uicomponent.cartitemrecview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.ai_banh_my_khong_dat_g.R;
import com.example.ai_banh_my_khong_dat_g.model.CartItem;


import java.util.List;

public class CartItemRecViewCardAdapter extends RecyclerView.Adapter<CartItemRecViewCardHolder> {
    private final Context context;
    private List<CartItem> modelList;

    public CartItemRecViewCardAdapter(Context context, List<CartItem> itemList) {
        this.context = context;
        setViewModelList(itemList);
    }

    public void setViewModelList(List<CartItem> modelList) {
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public CartItemRecViewCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.cart_item, parent, false);
        return new CartItemRecViewCardHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemRecViewCardHolder holder, int position) {
        CartItem model = modelList.get(position);
        holder.setModel(model);
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}