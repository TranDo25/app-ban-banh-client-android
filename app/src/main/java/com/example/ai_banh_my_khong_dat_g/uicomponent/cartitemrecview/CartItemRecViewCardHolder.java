package com.example.ai_banh_my_khong_dat_g.uicomponent.cartitemrecview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ai_banh_my_khong_dat_g.R;
import com.example.ai_banh_my_khong_dat_g.backendmodel.ProductWithImageWithNumberDTO;
import com.example.ai_banh_my_khong_dat_g.model.ItemInBill;


public class CartItemRecViewCardHolder extends RecyclerView.ViewHolder {
    private final ImageView image;
    private final TextView name;
    private final TextView realPrice;
    private final TextView amount;
    //    private CartItem model;
    private ProductWithImageWithNumberDTO model;
    private View itemView;
    public ConstraintLayout layoutItem;

    public CartItemRecViewCardHolder(@NonNull View itemView) {
        super(itemView);
        this.itemView = itemView;
        image = itemView.findViewById(R.id.ItemImage);
        layoutItem = itemView.findViewById(R.id.cartItemViewHolder);
        name = itemView.findViewById(R.id.TitleText);
        realPrice = itemView.findViewById(R.id.ItemListText);
        amount = itemView.findViewById(R.id.QL_NumberText);

        ImageView decreaseImage = itemView.findViewById(R.id.QL_DecreseImage);
        decreaseImage.setOnClickListener(view -> {

            int soLuong = Integer.parseInt(amount.getText().toString());
            if (soLuong != 0) {
                soLuong = soLuong - 1;
                amount.setText(String.valueOf(soLuong));
            }
        });

        ImageView increaseImage = itemView.findViewById(R.id.QL_IncreaseImage);
        increaseImage.setOnClickListener(view -> {
            int soLuong = Integer.parseInt(amount.getText().toString());
            soLuong = soLuong + 1;
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
    public void setModel(@NonNull ProductWithImageWithNumberDTO model) {
        if (this.model == model)
            return;

        this.model = model;
        validateLayout();
    }

    protected void validateLayout() {
//        ProductWithImageDTO item = model.getItem();
//        image.setImageResource(item.getImageRID());
//        Glide.with(itemView).load("http://192.168.88.102:8080/api/admin/product/image/banh_sn_danh_dau_thang_do.png").into(image);
        Glide.with(itemView).load(model.getProductWithImageDTO().getImageName()).into(image);
        name.setText(model.getProductWithImageDTO().getTenSp());
        realPrice.setText(model.getProductWithImageDTO().getGia() + "đ");
//        model.setTe(1);
        amount.setText(String.valueOf(model.getSoLuongMuonMua()));

    }

    public ItemInBill getInfoItem() {
        ItemInBill tmp = new ItemInBill();
        tmp.setDonGia(model.getProductWithImageDTO().getGia());
        tmp.setTenSp(model.getProductWithImageDTO().getTenSp());
        tmp.setSoLuong(Integer.parseInt(amount.getText().toString()));
        return tmp;
    }
}