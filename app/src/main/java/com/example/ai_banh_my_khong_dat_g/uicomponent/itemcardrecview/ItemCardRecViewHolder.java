package com.example.ai_banh_my_khong_dat_g.uicomponent.itemcardrecview;

import android.annotation.SuppressLint;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


import com.example.ai_banh_my_khong_dat_g.GlobalVariable;
import com.example.ai_banh_my_khong_dat_g.R;
import com.example.ai_banh_my_khong_dat_g.Utils;
import com.example.ai_banh_my_khong_dat_g.model.Item;

import android.widget.Toast;







public class ItemCardRecViewHolder extends RecyclerView.ViewHolder {
    private final ImageView image;
    private final TextView name;
    private final TextView priceDownText;
    private final TextView price1;
    private final TextView price2;
    private final ImageView favouriteImage;
    private Item model;

    public ItemCardRecViewHolder(@NonNull View itemView) {
        super(itemView);

        image = itemView.findViewById(R.id.IL_B_Image);
        name = itemView.findViewById(R.id.IL_B_NameText);
        priceDownText = itemView.findViewById(R.id.IL_PriceDownText);
        price1 = itemView.findViewById(R.id.IL_B_RealPriceText);
        price2 = itemView.findViewById(R.id.IL_B_OriginalPriceText);
        favouriteImage = itemView.findViewById(R.id.IL_B_FavouriteButton);

        image.setClickable(true);
        image.setOnClickListener(view -> {
            Toast.makeText(GlobalVariable.currentContext, "CC", Toast.LENGTH_LONG);
        });

        favouriteImage.setOnClickListener(view -> {
            model.setFavourite(!model.isFavourite());
            validateFavourite();
        });
    }

    public void setModel(@NonNull Item model) {
        if (this.model == model)
            return;

        this.model = model;
        validateLayout();
    }

    @SuppressLint("SetTextI18n")
    @SuppressWarnings("deprecation")
    private void validateLayout() {
        image.setImageResource(model.getImageRID());
        ConstraintLayout.LayoutParams LP = (ConstraintLayout.LayoutParams) image.getLayoutParams();
        float randomRatio = 1.05f + GlobalVariable.random.nextFloat()*0.30f; // [0.75, 1.35]
        String randomRatioAsString = String.valueOf(randomRatio).replace(',', '.');
        LP.dimensionRatio = "H," + randomRatioAsString;

        name.setText(model.getNameRID());

        int textColor1 = GlobalVariable.currentContext.getResources().getColor(R.color.text_dark_pink);
        int textColor2 = GlobalVariable.currentContext.getResources().getColor(R.color.text_brown);

        int price = model.getPrice();
        if (model.getPriceDown() == 0) {

            priceDownText.setAlpha(0.0f);
            price1.setText(price);
            price1.setTextColor(textColor1);
            price2.setAlpha(0.0f);
        }
        else {
            int realPrice = model.getRealPrice();

            priceDownText.setAlpha(1.0f);
            priceDownText.setText(String.valueOf(model.getPriceDown()) + '%');
            price1.setText(Utils.formatPrice(price));
            price1.setTextColor(textColor1);
            price1.setPaintFlags(price1.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            price2.setAlpha(1.0f);
            price2.setText(Utils.formatPrice(realPrice));
            price2.setTextColor(textColor2);
        }
        validateFavourite();
    }

    @SuppressWarnings("deprecation")
    private void validateFavourite() {
        int color1 = GlobalVariable.currentContext.getResources().getColor(R.color.color_dark_pink);
        int color2 = GlobalVariable.currentContext.getResources().getColor(R.color.color_brown);
        favouriteImage.setColorFilter(model.isFavourite() ? color1 : color2);
    }
}