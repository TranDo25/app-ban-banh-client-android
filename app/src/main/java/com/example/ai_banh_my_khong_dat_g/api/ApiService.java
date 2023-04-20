package com.example.ai_banh_my_khong_dat_g.api;

import com.example.ai_banh_my_khong_dat_g.backendmodel.GioHangModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://localhost:8080/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);
    @GET("/cart/getCartByIdUser")
    Call<GioHangModel> getListCartByIdUser(@Query("iduser") String iduser);
//    @GET("api/setfirebaseid")
//    Call<List<CartItem>> setIdFirebaseUser(@Query("iduser")@Query("email") String email);
}
