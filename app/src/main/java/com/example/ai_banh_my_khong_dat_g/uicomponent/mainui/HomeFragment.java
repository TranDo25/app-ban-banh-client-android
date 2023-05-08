package com.example.ai_banh_my_khong_dat_g.uicomponent.mainui;

import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.example.ai_banh_my_khong_dat_g.R;
import com.example.ai_banh_my_khong_dat_g.adapter.DanhSachSanPhamDemoAdapter;
import com.example.ai_banh_my_khong_dat_g.api.ApiService;
import com.example.ai_banh_my_khong_dat_g.backendmodel.DanhSachSPDemo;
import com.example.ai_banh_my_khong_dat_g.backendmodel.ProductWithImageDTO;
import com.example.ai_banh_my_khong_dat_g.banner.PhotoBanner;
import com.example.ai_banh_my_khong_dat_g.banner.PhotoViewPagerAdapter;
import com.example.ai_banh_my_khong_dat_g.databinding.HomeBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment implements IMainUIFragment {
    protected HomeBinding binding;
    private RecyclerView recyclerView;
    private DanhSachSanPhamDemoAdapter adapter;
    private ViewPager mViewPager;
    private CircleIndicator mCircleIndicator;
    private List<PhotoBanner> mListPhotoBanner;
    private Handler mHandler = new Handler();
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if(mViewPager.getCurrentItem()==mListPhotoBanner.size()-1){
                mViewPager.setCurrentItem(0);

            }else{
                mViewPager.setCurrentItem(mViewPager.getCurrentItem()+1);

            }

        }
    };
    private FloatingActionButton messengerLink, zaloLink;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = HomeBinding.inflate(inflater, container, false);

        setupSideNavigatorOpenButton();
        setupItemRecyclerView();
        mViewPager = binding.viewPager;
        mCircleIndicator = binding.circleIndicator;

//        recyclerView = binding.ItemRecView;
//        adapter = new DanhSachSanPhamDemoAdapter();
//        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(staggeredGridLayoutManager);
//        adapter.setData(getListDanhSachSPDemo());
        messengerLink = binding.floatingMessengerButton;
        messengerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_VIEW);
//                intent.setPackage("com.facebook.orca");
//                intent.setData(Uri.parse("https://m.me/"+"100032796252474"));
//                startActivity(intent);
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.me/tranglovequyen204"));
                startActivity(i);
            }
        });
        zaloLink = binding.floatingCallButton;
        zaloLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = "0825124160";
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);
            }
        });
        mListPhotoBanner = getListPhotoBanner();
        PhotoViewPagerAdapter adapterBanner = new PhotoViewPagerAdapter(mListPhotoBanner);
        mViewPager.setAdapter(adapterBanner);
        mCircleIndicator.setViewPager(mViewPager);
        mHandler.postDelayed(mRunnable,3000);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mHandler.removeCallbacks(mRunnable);
                mHandler.postDelayed(mRunnable,3000);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return binding.getRoot();
    }

    private List<PhotoBanner> getListPhotoBanner() {
        List<PhotoBanner> list = new ArrayList<>();
        list.add(new PhotoBanner(R.drawable.mainui_logo));
        list.add(new PhotoBanner(R.drawable.banh_sn_banner_1));
        list.add(new PhotoBanner(R.drawable.banh_sn_banner_2));
        list.add(new PhotoBanner(R.drawable.banh_sn_banner_3));
        list.add(new PhotoBanner(R.drawable.banh_sn_banner_4));
        list.add(new PhotoBanner(R.drawable.banh_sn_banner_5));
        return list;
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

    private List<DanhSachSPDemo> getListDanhSachSPDemo() {
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