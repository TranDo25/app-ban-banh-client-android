package com.example.ai_banh_my_khong_dat_g.uicomponent.mainui;

import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;


import com.example.ai_banh_my_khong_dat_g.AnimateDuration;
import com.example.ai_banh_my_khong_dat_g.databinding.ItemmenuBinding;
import com.example.ai_banh_my_khong_dat_g.testmodel.TestItem;
import com.example.ai_banh_my_khong_dat_g.testmodel.TestItemCategory;
import com.example.ai_banh_my_khong_dat_g.testmodel.TestListProductWithImageDTO;
import com.example.ai_banh_my_khong_dat_g.uicomponent.itemcardrecview.ItemCardRecViewAdapter;
import com.example.ai_banh_my_khong_dat_g.uicomponent.itemfilterrecview.ItemFilterRecViewAdapter;


public class ItemMenuFragment extends Fragment implements IMainUIFragment {
    protected ItemmenuBinding binding;
    protected int originalFilterHeight;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = ItemmenuBinding.inflate(inflater, container, false);
        setupFilterRV();
        setupFilterImage();
        setupItemRecyclerView();
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void OnShowed() {}

    protected  void setupFilterRV() {
        ItemFilterRecViewAdapter adapter = new ItemFilterRecViewAdapter(getContext(), TestItemCategory.getList());
        binding.ItemFilterRecView.setAdapter(adapter);

        LinearLayoutManager LLM = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.ItemFilterRecView.setLayoutManager(LLM);
        binding.ItemFilterRecView.setPadding(16, 16, 16, 16);

        originalFilterHeight = binding.ItemFilterRecView.getLayoutParams().height;
    }

    protected boolean _filterExpanded = true;
    protected void setupFilterImage() {
        binding.FilterImage.setOnClickListener(view -> {
            _filterExpanded = !_filterExpanded;
            changeFilterRVHeight(_filterExpanded);
        });
    }

    protected void setupItemRecyclerView() {
        ItemCardRecViewAdapter adapter = new ItemCardRecViewAdapter(getContext(), TestListProductWithImageDTO.getList());
        binding.ItemRecView.setAdapter(adapter);

        int itemSpacing = 16;
        StaggeredGridLayoutManager SGLM = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        SGLM.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        binding.ItemRecView.setLayoutManager(SGLM);
        binding.ItemRecView.setPadding(itemSpacing, itemSpacing, itemSpacing, itemSpacing);
        binding.ItemRecView.setClipToPadding(false);
        binding.ItemRecView.setClipChildren(false);
        binding.ItemRecView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.set(itemSpacing, itemSpacing, itemSpacing, itemSpacing);
            }
        });
    }

    protected void changeFilterRVHeight(boolean expanded)
    {
        long animateDuration = AnimateDuration.veryFast;

        ValueAnimator VA = ValueAnimator.ofInt(expanded ? 0 : originalFilterHeight, expanded ? originalFilterHeight : 0);
        VA.setDuration(animateDuration);
        VA.addUpdateListener(animation -> {
            binding.ItemFilterRecView.getLayoutParams().height = (Integer) animation.getAnimatedValue();
            binding.ItemFilterRecView.requestLayout();
        });
        VA.start();
    }
}