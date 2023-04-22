package com.example.ai_banh_my_khong_dat_g.uicomponent.cartitemrecview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ai_banh_my_khong_dat_g.GlobalVariable;
import com.example.ai_banh_my_khong_dat_g.R;
import com.example.ai_banh_my_khong_dat_g.Utils;
import com.example.ai_banh_my_khong_dat_g.backendmodel.ProductWithImageDTO;
import com.example.ai_banh_my_khong_dat_g.model.CartItem;
import com.example.ai_banh_my_khong_dat_g.model.Item;


public class CartItemRecViewCardHolder extends RecyclerView.ViewHolder {
    private final ImageView image;
    private final TextView name;
    private final TextView realPrice;
    private final TextView amount;
    //    private CartItem model;
    private ProductWithImageDTO model;
    private View itemView;

    public CartItemRecViewCardHolder(@NonNull View itemView) {
        super(itemView);
        this.itemView = itemView;
        image = itemView.findViewById(R.id.ItemImage);
        name = itemView.findViewById(R.id.TitleText);
        realPrice = itemView.findViewById(R.id.ItemListText);
        amount = itemView.findViewById(R.id.QL_NumberText);

        ImageView decreaseImage = itemView.findViewById(R.id.QL_DecreseImage);
        decreaseImage.setOnClickListener(view -> {

            int soLuong = Integer.parseInt(amount.getText().toString());
            if(soLuong != 0){
                soLuong = soLuong - 1;
                amount.setText(String.valueOf(soLuong));
            }
        });

        ImageView increaseImage = itemView.findViewById(R.id.QL_IncreaseImage);
        increaseImage.setOnClickListener(view -> {
            int soLuong = Integer.parseInt(amount.getText().toString());
                soLuong = soLuong +1;
                amount.setText(String.valueOf(soLuong));



        });
    }

    //    public void setModel(@NonNull CartItem model) {
//        if (this.model == model)
//            return;
//
//        this.model = model;
//        validateLayout();
//    }
    public void setModel(@NonNull ProductWithImageDTO model) {
        if (this.model == model)
            return;

        this.model = model;
        validateLayout();
    }

    protected void validateLayout() {
//        ProductWithImageDTO item = model.getItem();
//        image.setImageResource(item.getImageRID());
//        Glide.with(itemView).load("http://192.168.88.102:8080/api/admin/product/image/banh_sn_danh_dau_thang_do.png").into(image);
        Glide.with(itemView).load(model.getImageName()).into(image);
        name.setText("Tên sản phẩm : "+model.getTenSp());
        realPrice.setText("Đơn giá : " +model.getGia());
//        model.set(1);
        amount.setText("1");
    }
}