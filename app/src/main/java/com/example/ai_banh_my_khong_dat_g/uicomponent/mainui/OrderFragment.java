package com.example.ai_banh_my_khong_dat_g.uicomponent.mainui;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ai_banh_my_khong_dat_g.api.ApiService;
import com.example.ai_banh_my_khong_dat_g.backendmodel.Cart;
import com.example.ai_banh_my_khong_dat_g.backendmodel.Orders;
import com.example.ai_banh_my_khong_dat_g.databinding.OrderBinding;
import com.example.ai_banh_my_khong_dat_g.testmodel.TestOrder;
import com.example.ai_banh_my_khong_dat_g.uicomponent.orderitemrecview.OrderItemRecViewAdapter;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OrderFragment extends Fragment implements IMainUIFragment {
    protected OrderBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = OrderBinding.inflate(inflater, container, false);

        setupDeliveringOrderRecyclerView();
//        setupDeliveredOrderRecyclerView();

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void OnShowed() {}

    protected void setupDeliveringOrderRecyclerView() {
        List<Orders> tmp = new ArrayList<>();
        Orders orderTmp = new Orders(
                4,
                "2023-04-24",
                "created",
                null,
                "0825124160",
                null,
                665000.0,
                "minh quang kiến xương"
        );
        tmp.add(orderTmp);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getContext());

        ApiService.apiService.getAllOrderByIdUser(account.getEmail()).enqueue(new Callback<List<Orders>>() {
            @Override
            public void onResponse(Call<List<Orders>> call, Response<List<Orders>> response) {
                List<Orders> listOrder = response.body();
//                List<Orders> listOrderTmp = new ArrayList<>();
//                Orders orderTmp = new Orders(
//                        4,
//                        "2023-04-24",
//                        "created",
//                        null,
//                        "0825124160",
//                        null,
//                        665000.0,
//                        "minh quang kiến xương"
//                );
//                listOrderTmp.add(orderTmp);
                Log.d("check set adapter", "oke con de");
                OrderItemRecViewAdapter adapter = new OrderItemRecViewAdapter(getContext(), listOrder);
                binding.RecycleViewAllOrder.setAdapter(adapter);

                LinearLayoutManager LLM = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                binding.RecycleViewAllOrder.setLayoutManager(LLM);
                binding.RecycleViewAllOrder.addItemDecoration(new RecyclerView.ItemDecoration() {
                    @Override
                    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                        outRect.set(8, 8, 8, 8);
                    }
                });

            }

            @Override
            public void onFailure(Call<List<Orders>> call, Throwable t) {

            }
        });

    }

    protected void setupDeliveredOrderRecyclerView() {
//        RecyclerView.Adapter adapter = new OrderItemRecViewAdapter(getContext(), TestOrder.getDeliveredList());
//        binding.OLDingRecView.setAdapter(adapter);
//
//        LinearLayoutManager LLM = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
//        binding.OLDingRecView.setLayoutManager(LLM);
//        binding.OLDingRecView.addItemDecoration(new RecyclerView.ItemDecoration() {
//            @Override
//            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
//                outRect.set(8, 8, 8, 8);
//            }
//        });
    }
}