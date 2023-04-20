package com.example.ai_banh_my_khong_dat_g.uicomponent.itemcardrecview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.ai_banh_my_khong_dat_g.R;
import com.example.ai_banh_my_khong_dat_g.model.Item;

import java.util.List;

public class ItemCardRecViewAdapter extends RecyclerView.Adapter<ItemCardRecViewHolder> {
    private final Context context;
    private List<Item> modelList;

    public ItemCardRecViewAdapter(Context context, List<Item> itemList) {
        this.context = context;
        setViewModelList(itemList);
    }

    public void setViewModelList(List<Item> modelList) {
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public ItemCardRecViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.item_card, parent, false);
        return new ItemCardRecViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemCardRecViewHolder holder, int position) {
        Item model = modelList.get(position);
        holder.setModel(model);
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}