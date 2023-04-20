package com.example.ai_banh_my_khong_dat_g.uicomponent.mainui;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.ai_banh_my_khong_dat_g.databinding.HomeBinding;
import com.example.ai_banh_my_khong_dat_g.testmodel.TestItem;
import com.example.ai_banh_my_khong_dat_g.uicomponent.itemcardrecview.ItemCardRecViewAdapter;


public class HomeFragment extends Fragment implements IMainUIFragment {
    protected HomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = HomeBinding.inflate(inflater, container, false);

        setupSideNavigatorOpenButton();
        setupItemRecyclerView();

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void OnShowed() {}

    protected void setupSideNavigatorOpenButton() {
        binding.OpenSidebarButton.setOnClickListener(MainUIFragment.currentInstance.onOpenSideNavigatorListener());
    }

    protected void setupItemRecyclerView() {
        ItemCardRecViewAdapter adapter = new ItemCardRecViewAdapter(getContext(), TestItem.getList());
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
}