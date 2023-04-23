package com.example.ai_banh_my_khong_dat_g.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ai_banh_my_khong_dat_g.R;
import com.example.ai_banh_my_khong_dat_g.model.ItemInBill;

import org.w3c.dom.Text;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    private final List<ItemInBill> dsItem;

    public OrderAdapter(List<ItemInBill> dsItem) {
        this.dsItem = dsItem;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sanpham_soluong_dongia, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        ItemInBill item = dsItem.get(position);
        if(item == null){
            return;
        }
        holder.tenSp.setText(item.getTenSp());
        holder.soLuong.setText(String.valueOf(item.getSoLuong()));
        holder.donGia.setText(String.valueOf(item.getDonGia()));
    }

    @Override
    public int getItemCount() {
        if(dsItem!=null){
            return dsItem.size();
        }
        return 0;
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder{
        private TextView tenSp, soLuong, donGia;
        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            tenSp = itemView.findViewById(R.id.orderTenSp);
            soLuong = itemView.findViewById(R.id.orderSoLuong);
            donGia = itemView.findViewById(R.id.orderDonGia);
        }

        public TextView getTenSp() {
            return tenSp;
        }

        public void setTenSp(TextView tenSp) {
            this.tenSp = tenSp;
        }

        public TextView getSoLuong() {
            return soLuong;
        }

        public void setSoLuong(TextView soLuong) {
            this.soLuong = soLuong;
        }

        public TextView getDonGia() {
            return donGia;
        }

        public void setDonGia(TextView donGia) {
            this.donGia = donGia;
        }
    }
}
