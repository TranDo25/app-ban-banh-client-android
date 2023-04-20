package com.example.ai_banh_my_khong_dat_g.uicomponent.mainui;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ai_banh_my_khong_dat_g.databinding.OrderBinding;
import com.example.ai_banh_my_khong_dat_g.testmodel.TestOrder;
import com.example.ai_banh_my_khong_dat_g.uicomponent.orderitemrecview.OrderItemRecViewAdapter;


public class OrderFragment extends Fragment implements IMainUIFragment {
    protected OrderBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = OrderBinding.inflate(inflater, container, false);

        setupDeliveringOrderRecyclerView();
        setupDeliveredOrderRecyclerView();

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void OnShowed() {}

    protected void setupDeliveringOrderRecyclerView() {
        RecyclerView.Adapter adapter = new OrderItemRecViewAdapter(getContext(), TestOrder.getDeliveringList());
        binding.OLDingRecView.setAdapter(adapter);

        LinearLayoutManager LLM = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.OLDingRecView.setLayoutManager(LLM);
        binding.OLDingRecView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.set(8, 8, 8, 8);
            }
        });
    }

    protected void setupDeliveredOrderRecyclerView() {
        RecyclerView.Adapter adapter = new OrderItemRecViewAdapter(getContext(), TestOrder.getDeliveredList());
        binding.OLDingRecView.setAdapter(adapter);

        LinearLayoutManager LLM = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.OLDingRecView.setLayoutManager(LLM);
        binding.OLDingRecView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.set(8, 8, 8, 8);
            }
        });
    }
}