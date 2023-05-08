package com.example.ai_banh_my_khong_dat_g.uicomponent.yeuthich_view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ai_banh_my_khong_dat_g.DetailProductActivity;
import com.example.ai_banh_my_khong_dat_g.R;
import com.example.ai_banh_my_khong_dat_g.backendmodel.ProductWithImageDTO;

import java.util.List;

public class YeuThichViewAdapter extends RecyclerView.Adapter<YeuThichViewAdapter.YeuThichViewHolder>{
    private Context mConText;
    private List<ProductWithImageDTO> mListCake;
    public YeuThichViewAdapter(Context mConText) {
        this.mConText = mConText;
    }
    public void setData(List<ProductWithImageDTO> list){
        this.mListCake = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public YeuThichViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.yeuthich_item_cake,parent,false);
        this.mConText = parent.getContext();
        return new YeuThichViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull YeuThichViewHolder holder, int position) {
        ProductWithImageDTO cake = mListCake.get(position);
        if(cake == null) return;
        Glide.with(mConText).load(cake.getImageName()).into(holder.imgCake);
        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickGotoDetail(cake);
            }


        });
        holder.txtName.setText(cake.getTenSp());
    }
    private void onClickGotoDetail(ProductWithImageDTO cake) {
        Intent intent = new Intent(mConText, DetailProductActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_product", cake);
        intent.putExtras(bundle);
        mConText.startActivity(intent);
    }
    @Override
    public int getItemCount() {
        if(mListCake!=null) return mListCake.size();
        return 0;
    }
    public class YeuThichViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgCake;
        private TextView txtName;
        private CardView layoutItem;
        public YeuThichViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutItem = itemView.findViewById(R.id.carditemyeuthich);
            imgCake = itemView.findViewById(R.id.img_cake);
//            txtName = itemView.findViewById(R.id.rcv_cake);
            txtName = itemView.findViewById(R.id.tenbanhyeuthich);
        }
    }

}
