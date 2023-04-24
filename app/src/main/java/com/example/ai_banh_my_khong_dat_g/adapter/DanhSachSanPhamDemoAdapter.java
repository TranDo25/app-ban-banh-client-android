package com.example.ai_banh_my_khong_dat_g.adapter;

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
import com.example.ai_banh_my_khong_dat_g.backendmodel.DanhSachSPDemo;
import com.example.ai_banh_my_khong_dat_g.backendmodel.ProductWithImageDTO;

import org.w3c.dom.Text;

import java.util.List;

public class DanhSachSanPhamDemoAdapter extends RecyclerView.Adapter<DanhSachSanPhamDemoAdapter.DanhSachSanPhamDemoHolder> {
    //    private List<DanhSachSPDemo> listSpDemo;
    private List<ProductWithImageDTO> listSpDemo;

    private Context context;

    public void setData(List<ProductWithImageDTO> list) {
//        this.context2 = context;
        this.listSpDemo = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DanhSachSanPhamDemoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_demo, parent, false);
        this.context = parent.getContext();
        return new DanhSachSanPhamDemoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DanhSachSanPhamDemoHolder holder, int position) {
        ProductWithImageDTO tmp = listSpDemo.get(position);
        if (tmp == null) {
            return;
        }
//        Glide.with(context).load("http://192.168.88.102:8080/api/admin/product/image/banh_sn_danh_dau_thang_do.png").into(holder.imgSp);
        Glide.with(context).load(tmp.getImageName()).into(holder.imgSp);

        holder.tenSp.setText(tmp.getTenSp());
        holder.giaGoc.setText(String.valueOf(tmp.getGia()) + "đ");
        if (tmp.getGiamGia() != 0) {
            holder.giaKhuyenMai.setText(String.valueOf("-" + tmp.getGiamGia()) + "đ");
        }
        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickGotoDetail(tmp);
            }
        });
    }

    private void onClickGotoDetail(ProductWithImageDTO product) {
        Intent intent = new Intent(context, DetailProductActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_product", product);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        if (listSpDemo != null) {
            return listSpDemo.size();
        }
        return 0;
    }

    public class DanhSachSanPhamDemoHolder extends RecyclerView.ViewHolder {
        private ImageView imgSp;
        private TextView tenSp, giaKhuyenMai, giaGoc;

        private CardView layoutItem;

        public DanhSachSanPhamDemoHolder(@NonNull View itemView) {
            super(itemView);
            layoutItem = itemView.findViewById(R.id.layout_item_card_demo);
            tenSp = itemView.findViewById(R.id.cardTenSp);
            imgSp = itemView.findViewById(R.id.img_product);
            giaGoc = itemView.findViewById(R.id.textViewGiaGoc);
            giaKhuyenMai = itemView.findViewById(R.id.textViewGiamGia);

        }
    }
}
