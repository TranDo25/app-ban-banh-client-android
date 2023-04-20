package com.example.ai_banh_my_khong_dat_g.uicomponent.orderitemrecview;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ai_banh_my_khong_dat_g.R;
import com.example.ai_banh_my_khong_dat_g.Utils;
import com.example.ai_banh_my_khong_dat_g.model.Item;
import com.example.ai_banh_my_khong_dat_g.model.Order;


public class OrderItemRecViewHolder extends RecyclerView.ViewHolder {
    private final ImageView image;
    private final TextView titleText;
    private final TextView itemListText;
    private final TextView orderID;
    private final TextView totalPriceText;
    private final TextView stateText;
    private final TextView dateTitleText;
    private final TextView dateText;
    private Order model;

    public OrderItemRecViewHolder(@NonNull View itemView) {
        super(itemView);

        image = itemView.findViewById(R.id.ItemImage);
        titleText = itemView.findViewById(R.id.TitleText);
        itemListText = itemView.findViewById(R.id.ItemListText);
        orderID = itemView.findViewById(R.id.OrderIDText);
        totalPriceText = itemView.findViewById(R.id.IL_TotalPriceTitleText);
        stateText = itemView.findViewById(R.id.StateText);
        dateTitleText = itemView.findViewById(R.id.DateTitleText);
        dateText = itemView.findViewById(R.id.DateText);
    }

    public void setModel(@NonNull Order model) {
        if (this.model == model)
            return;

        this.model = model;
        validateLayout();
    }

    @SuppressLint("SetTextI18n")
    private void validateLayout() {
        int itemCount = model.getItemList().size();
        Item firstItem = model.getItemList().get(0);

        image.setImageResource(firstItem.getImageRID());
        titleText.setText(firstItem.getNameRID());
        itemListText.setText(itemCount > 1 ? "Và " + (itemCount-1) + " sản phẩm khác." : "");
        orderID.setText("#" + model.getOrderID());
        totalPriceText.setText(Utils.formatPrice(model.getTotalPrice()));

        String stateTextValue = "";
        switch (model.getState()) {
            case Preparing:     stateTextValue = "Đang chuẩn bị hàng"; break;
            case Delivering:    stateTextValue = "Đang giao hàng"; break;
            case Delivered:     stateTextValue = "Đã giao hàng"; break;
            default: break;
        }
        stateText.setText(stateTextValue);
        dateTitleText.setText("Ngày giao" + (model.getState() != Order.State.Delivered ? " (dự kiến)" : ""));
        dateText.setText(model.getDate().toString());
    }
}