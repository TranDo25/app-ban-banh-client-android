package com.example.ai_banh_my_khong_dat_g.uicomponent.cartitemrecview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.ai_banh_my_khong_dat_g.ChiTietSanPhamFromCartActivity;
import com.example.ai_banh_my_khong_dat_g.DetailProductActivity;
import com.example.ai_banh_my_khong_dat_g.R;
import com.example.ai_banh_my_khong_dat_g.backendmodel.ProductWithImageWithNumberDTO;


import java.io.Serializable;
import java.util.List;

public class CartItemRecViewCardAdapter extends RecyclerView.Adapter<CartItemRecViewCardHolder> {
    private final Context context;
    //    private List<CartItem> modelList;
    private List<ProductWithImageWithNumberDTO> modelList;

    //    public CartItemRecViewCardAdapter(Context context, List<CartItem> itemList) {
//        this.context = context;
//        setViewModelList(itemList);
//    }
    public CartItemRecViewCardAdapter(Context context, List<ProductWithImageWithNumberDTO> itemList) {
        this.context = context;
        setViewModelList(itemList);
    }

    //    public void setViewModelList(List<CartItem> modelList) {
//        this.modelList = modelList;
//    }
    public void setViewModelList(List<ProductWithImageWithNumberDTO> modelList) {
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
//        CartItem model = modelList.get(position);
        ProductWithImageWithNumberDTO model = modelList.get(position);
        holder.setModel(model);
        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickGotoDetail(model);
            }
        });
    }
private void onClickGotoDetail(ProductWithImageWithNumberDTO model){
    Intent intent = new Intent(context, ChiTietSanPhamFromCartActivity.class);
    Bundle bundle = new Bundle();
    bundle.putSerializable("object_product_from_cart", model);
    intent.putExtras(bundle);
    context.startActivity(intent);
}
    @Override
    public int getItemCount() {
        return modelList.size();
    }
}