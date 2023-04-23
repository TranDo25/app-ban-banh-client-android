package com.example.ai_banh_my_khong_dat_g.uicomponent.mainui;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ai_banh_my_khong_dat_g.adapter.OrderAdapter;
import com.example.ai_banh_my_khong_dat_g.api.ApiService;
import com.example.ai_banh_my_khong_dat_g.backendmodel.GioHangModel;
import com.example.ai_banh_my_khong_dat_g.backendmodel.ProductWithImageDTO;
import com.example.ai_banh_my_khong_dat_g.databinding.CartBinding;
import com.example.ai_banh_my_khong_dat_g.model.CartItem;
import com.example.ai_banh_my_khong_dat_g.model.Item;
import com.example.ai_banh_my_khong_dat_g.model.ItemInBill;
import com.example.ai_banh_my_khong_dat_g.testmodel.TestCartItem;
import com.example.ai_banh_my_khong_dat_g.thanhtoan.ThanhToanActivity;
import com.example.ai_banh_my_khong_dat_g.uicomponent.cartitemrecview.CartItemRecViewCardAdapter;
import com.example.ai_banh_my_khong_dat_g.uicomponent.cartitemrecview.CartItemRecViewCardHolder;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CartFragment extends Fragment implements IMainUIFragment {
    protected com.example.ai_banh_my_khong_dat_g.databinding.CartBinding binding;
    private Button btnThanhToan;
    private RecyclerView recyclerView;
    List<ItemInBill> itemInBills = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = CartBinding.inflate(inflater, container, false);

        setupItemRecyclerView();
        btnThanhToan = binding.PayButton;
        recyclerView = binding.CartItemRecView;
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ThanhToanActivity.class);
                for (int i = 0; i < recyclerView.getChildCount(); i++) {
                    CartItemRecViewCardHolder holder = (CartItemRecViewCardHolder) recyclerView.getChildViewHolder(recyclerView.getChildAt(i));
//                    OrderAdapter.OrderViewHolder holder = (OrderAdapter.OrderViewHolder) recyclerView.getChildViewHolder(recyclerView.getChildAt(i));


                    ItemInBill tmp = holder.getInfoItem();
                    itemInBills.add(tmp);
                }
                intent.putExtra("listItemInBill", (Serializable) itemInBills);
                startActivity(intent);

            }
        });
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

    protected void setupItemRecyclerView() {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getContext());
        System.out.println("id của user là:" + account.getEmail());
        ApiService.apiService.getListCartByIdUser(account.getEmail())
                .enqueue(new Callback<GioHangModel>() {
                    @Override
                    public void onResponse(Call<GioHangModel> call, Response<GioHangModel> response) {
                        Toast.makeText(getActivity(), "call List<CartItem> success", Toast.LENGTH_SHORT).show();
//                        System.out.println();
                        GioHangModel listCartItem = response.body();
                        if (listCartItem != null) {
                            List<ProductWithImageDTO> dsItem = listCartItem.getDsMatHangTrongGio();
                            CartItemRecViewCardAdapter adapter = new CartItemRecViewCardAdapter(getContext(), dsItem);
                            binding.CartItemRecView.setAdapter(adapter);
                            LinearLayoutManager LLM = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                            binding.CartItemRecView.setLayoutManager(LLM);
                            binding.CartItemRecView.addItemDecoration(new RecyclerView.ItemDecoration() {
                                @Override
                                public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                                    outRect.set(8, 8, 8, 8);
                                }
                            });

                        }
                    }

                    @Override
                    public void onFailure(Call<GioHangModel> call, Throwable t) {
                        Toast.makeText(getActivity(), "error call List<CartItem>", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}