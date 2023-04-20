package com.example.ai_banh_my_khong_dat_g.uicomponent.cartitemrecview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ai_banh_my_khong_dat_g.GlobalVariable;
import com.example.ai_banh_my_khong_dat_g.R;
import com.example.ai_banh_my_khong_dat_g.Utils;
import com.example.ai_banh_my_khong_dat_g.model.CartItem;
import com.example.ai_banh_my_khong_dat_g.model.Item;


public class CartItemRecViewCardHolder extends RecyclerView.ViewHolder {
    private final ImageView image;
    private final TextView name;
    private final TextView realPrice;
    private final TextView amount;
    private CartItem model;

    public CartItemRecViewCardHolder(@NonNull View itemView) {
        super(itemView);

        image = itemView.findViewById(R.id.ItemImage);
        name = itemView.findViewById(R.id.TitleText);
        realPrice = itemView.findViewById(R.id.ItemListText);
        amount = itemView.findViewById(R.id.QL_NumberText);

        ImageView decreaseImage = itemView.findViewById(R.id.QL_DecreseImage);
        decreaseImage.setOnClickListener(view -> {});

        ImageView increaseImage = itemView.findViewById(R.id.QL_IncreaseImage);
        increaseImage.setOnClickListener(view -> {});
    }

    public void setModel(@NonNull CartItem model) {
        if (this.model == model)
            return;

        this.model = model;
        validateLayout();
    }

    protected void validateLayout() {
        Item item = model.getItem();
        image.setImageResource(item.getImageRID());
        name.setText("Tên sản phẩm : " + GlobalVariable.currentContext.getString(item.getNameRID()));
        realPrice.setText("Đơn giá : " + Utils.formatPrice(item.getRealPrice()));
        model.setAmount(1);
        amount.setText(String.valueOf(model.getAmount()));
    }
}