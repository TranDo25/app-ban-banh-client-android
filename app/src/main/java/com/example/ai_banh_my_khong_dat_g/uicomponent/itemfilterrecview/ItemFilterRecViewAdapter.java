package com.example.ai_banh_my_khong_dat_g.uicomponent.itemfilterrecview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ai_banh_my_khong_dat_g.R;
import com.example.ai_banh_my_khong_dat_g.model.ItemCategory;


import java.util.List;

public class ItemFilterRecViewAdapter extends RecyclerView.Adapter<ItemFilterRecViewViewHolder> {
    private final Context context;
    private final List<ItemCategory> modelList;

    public ItemFilterRecViewAdapter(Context context, List<ItemCategory> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public ItemFilterRecViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.itemmenu_filterrv_element, parent, false);
        return new ItemFilterRecViewViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemFilterRecViewViewHolder holder, int position) {
        ItemCategory model = modelList.get(position);
        holder.set(model);
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}