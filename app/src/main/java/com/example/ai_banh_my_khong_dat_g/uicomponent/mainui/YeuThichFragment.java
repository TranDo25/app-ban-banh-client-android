package com.example.ai_banh_my_khong_dat_g.uicomponent.mainui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.ai_banh_my_khong_dat_g.R;
import com.example.ai_banh_my_khong_dat_g.api.ApiService;
import com.example.ai_banh_my_khong_dat_g.backendmodel.ProductWithImageDTO;
import com.example.ai_banh_my_khong_dat_g.databinding.YeuthichBinding;

import com.example.ai_banh_my_khong_dat_g.uicomponent.yeuthich_view.Cake;
import com.example.ai_banh_my_khong_dat_g.uicomponent.yeuthich_view.YeuThichViewAdapter;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class YeuThichFragment extends Fragment {
    private YeuthichBinding binding;
    //    private Button btnBack;
    private RecyclerView recyclerView;
    List<ProductWithImageDTO> listProductWithImageDTO = new ArrayList<ProductWithImageDTO>();
    private YeuThichViewAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = YeuthichBinding.inflate(inflater, container, false);
        recyclerView = binding.YeuThichRecycleView;
        adapter = new YeuThichViewAdapter(getContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        setupItemRecyclerView();

//        btnBack = binding.btnYeuThichBack;
//        btnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
        return binding.getRoot();
    }

    private void setupItemRecyclerView() {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getContext());

        ApiService.apiService.getListSanPhamYeuThichByIdUser(account.getEmail())
                .enqueue(new Callback<List<ProductWithImageDTO>>() {
                    @Override
                    public void onResponse(Call<List<ProductWithImageDTO>> call, Response<List<ProductWithImageDTO>> response) {
                        listProductWithImageDTO = response.body();
                        adapter.setData(listProductWithImageDTO);
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<List<ProductWithImageDTO>> call, Throwable t) {
                        Toast.makeText(getActivity(), "lỗi khi lấy sản phẩm yêu thích", Toast.LENGTH_SHORT).show();

                    }
                });


    }

    private List<Cake> getListCake() {
        List<Cake> cakeList = new ArrayList<>();
        cakeList.add(new Cake(R.drawable.tuananh_banh1, "banh1"));
        cakeList.add(new Cake(R.drawable.tuananh_banh2, "banh2"));
        cakeList.add(new Cake(R.drawable.tuananh_banh3, "banh3"));
        cakeList.add(new Cake(R.drawable.tuananh_banh4, "banh4"));

        cakeList.add(new Cake(R.drawable.tuananh_banh1, "banh1"));
        cakeList.add(new Cake(R.drawable.tuananh_banh2, "banh2"));
        cakeList.add(new Cake(R.drawable.tuananh_banh3, "banh3"));
        cakeList.add(new Cake(R.drawable.tuananh_banh4, "banh4"));

        cakeList.add(new Cake(R.drawable.tuananh_banh1, "banh1"));
        cakeList.add(new Cake(R.drawable.tuananh_banh2, "banh2"));
        cakeList.add(new Cake(R.drawable.tuananh_banh3, "banh3"));
        cakeList.add(new Cake(R.drawable.tuananh_banh4, "banh4"));

        return cakeList;


    }
    @Override
    public void onResume() {
        super.onResume();
        // Load lại Fragment
        listProductWithImageDTO.clear();
        setupItemRecyclerView();
    }
}