package com.example.ai_banh_my_khong_dat_g.uicomponent.itemfilterrecview;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.widget.ImageViewCompat;
import androidx.recyclerview.widget.RecyclerView;


import com.example.ai_banh_my_khong_dat_g.GlobalVariable;
import com.example.ai_banh_my_khong_dat_g.R;
import com.example.ai_banh_my_khong_dat_g.model.ItemCategory;


public class ItemFilterRecViewViewHolder extends RecyclerView.ViewHolder {
    @SuppressLint("StaticFieldLeak")
    private static ItemFilterRecViewViewHolder currentSelected = null;
    private final ImageView image;
    private final TextView title;

    public ItemFilterRecViewViewHolder(@NonNull View itemView) {
        super(itemView);

        image = itemView.findViewById(R.id.IconImage);
        title = itemView.findViewById(R.id.Title);
        itemView.setOnClickListener(view -> onSelected());
    }

    public void set(ItemCategory model) {
        image.setImageResource(model.getImageRID());
        title.setText(model.getNameRID());
    }

    public void onSelected() {
        if (currentSelected != null)
            currentSelected.onExit();

        currentSelected = this;
        currentSelected.onEnter();
    }

    private void onEnter() {
        image.setBackgroundResource(R.drawable.rounded_corner_16x16x16x16_dark_pink);
        int color = ContextCompat.getColor(GlobalVariable.currentContext, R.color.color_white_pink);
        ImageViewCompat.setImageTintList(image, ColorStateList.valueOf(color));
    }

    private void onExit() {
        image.setBackgroundResource(R.drawable.rounded_corner_16x16x16x16_white_pink);
        int color = ContextCompat.getColor(GlobalVariable.currentContext, R.color.color_dark_pink);
        ImageViewCompat.setImageTintList(image, ColorStateList.valueOf(color));
    }
}