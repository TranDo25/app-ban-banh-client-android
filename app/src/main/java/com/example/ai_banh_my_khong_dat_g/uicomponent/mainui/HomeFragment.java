package com.example.ai_banh_my_khong_dat_g.uicomponent.mainui;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.ai_banh_my_khong_dat_g.R;
import com.example.ai_banh_my_khong_dat_g.adapter.DanhSachSanPhamDemoAdapter;
import com.example.ai_banh_my_khong_dat_g.api.ApiService;
import com.example.ai_banh_my_khong_dat_g.backendmodel.DanhSachSPDemo;
import com.example.ai_banh_my_khong_dat_g.backendmodel.ProductWithImageDTO;
import com.example.ai_banh_my_khong_dat_g.databinding.HomeBinding;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment implements IMainUIFragment {
    protected HomeBinding binding;
    private RecyclerView recyclerView;
    private DanhSachSanPhamDemoAdapter adapter;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = HomeBinding.inflate(inflater, container, false);

        setupSideNavigatorOpenButton();
        setupItemRecyclerView();
//        recyclerView = binding.ItemRecView;
//        adapter = new DanhSachSanPhamDemoAdapter();
//        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(staggeredGridLayoutManager);
//        adapter.setData(getListDanhSachSPDemo());
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void OnShowed() {
    }

    protected void setupSideNavigatorOpenButton() {
        binding.OpenSidebarButton.setOnClickListener(MainUIFragment.currentInstance.onOpenSideNavigatorListener());
    }
    private List<DanhSachSPDemo> getListDanhSachSPDemo(){
        List<DanhSachSPDemo> list = new ArrayList<>();
        DanhSachSPDemo tmp = new DanhSachSPDemo("http://192.168.88.102:8080/api/admin/product/image/banh_sn_danh_dau_thang_do.png", "banh chuoi", 10000);
        DanhSachSPDemo tmp2 = new DanhSachSPDemo("http://192.168.88.102:8080/api/admin/product/image/banhmithapcam.png", "banh dau do", 10000);
        list.add(tmp);
        list.add(tmp2);
        return list;
    }
    protected void setupItemRecyclerView() {
        ApiService.apiService.getTenProductWithHighestVote().enqueue(
                new Callback<List<ProductWithImageDTO>>() {
                    @Override
                    public void onResponse(Call<List<ProductWithImageDTO>> call, Response<List<ProductWithImageDTO>> response) {
                        Toast.makeText(getActivity(), "get list successfully", Toast.LENGTH_SHORT).show();
                        List<ProductWithImageDTO> dsResponse = response.body();
//                        ItemCardRecViewAdapter adapter = new ItemCardRecViewAdapter(getContext(), TestItem.getList());
                        DanhSachSanPhamDemoAdapter adapter1 = new DanhSachSanPhamDemoAdapter();
                        adapter1.setData(dsResponse);
                        binding.ItemRecView.setAdapter(adapter1);
//                        ItemCardRecViewAdapter adapter = new ItemCardRecViewAdapter(getContext(), dsResponse);
//                        binding.ItemRecView.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<List<ProductWithImageDTO>> call, Throwable t) {
                        Toast.makeText(getActivity(), "error when get list", Toast.LENGTH_SHORT).show();

                    }
                }
        );


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