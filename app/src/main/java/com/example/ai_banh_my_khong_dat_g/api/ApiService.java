package com.example.ai_banh_my_khong_dat_g.api;

import com.example.ai_banh_my_khong_dat_g.backendmodel.Cart;
import com.example.ai_banh_my_khong_dat_g.backendmodel.ChiTietOrderDTO;
import com.example.ai_banh_my_khong_dat_g.backendmodel.GioHangModel;
import com.example.ai_banh_my_khong_dat_g.backendmodel.ProductWithImageDTO;
import com.example.ai_banh_my_khong_dat_g.backendmodel.Users;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://192.168.88.102:8080/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);
    @GET("/cart/getCartByIdUser")
    Call<GioHangModel> getListCartByIdUser(@Query("iduser") String iduser);

    //thay doi so luong san pham
    @GET("/cart/changeNumberOfItem")
    Call<String> changeNumberOfItem(@Query("cartId") int cartId, @Query ("soluong") int soluong );

    //xoa cart
    @DELETE("/cart")
    Call<String> deleteCartByID(@Query("id") Long id);

    //lay ra all san pham
    @GET("/api/admin/products")
    Call<List<ProductWithImageDTO>> getallProducts();

    //lay ra san pham theo categoryID
    @GET("/api/admin/productsByCategory/{id}")
    Call<List<ProductWithImageDTO>> getProductsByCategoryId(@Path("id") int id);

    //search ProductByname
    @GET("/api/admin/searchProductByName")
    Call<List<ProductWithImageDTO>> searchProductByName(@Query("searchString") String searchString);

    //get chi tiet Product theo id
    @GET("/api/admin/getDetailProductById")
    Call<ProductWithImageDTO> getDetailProductById(@Query("id") int id);

    //get all Users
    @GET("/users")
    Call<Users> getAllUsers();

    //tao don hang
    @POST("/api/order/createOrder")
    Call<String> createOrder(@Body List<Cart> dsCart);

    //set thong tin giao hang
    @PUT("/api/order/setThongTinGiaoHang")
    Call<String> setThongTinGiaoHang(@Query("idDonHang") int idOrder,@Query("sdt") String sdt
            , @Query("diaChiGiaoHang") String diaChiGiaoHang);


    //them san pham yeu thich
    @GET("/users/like")
    Call<String> likeProduct(@Query("product_id") Integer product_id, @Query("users_id") String user_id);

    // set zalopay token cho don hang
    @PUT("/api/order/setToken")
    Call<String> setToken(@Query("idDonHang") int idOrder, @Query("token") String token );

    //thay doi trang thai don hang
    @PUT("/api/order/changeOrderStatus")
    Call<String> setTrangThaiDonHang(@Query("idDonHang") int idOrder, @Query("codeStatus") Integer codeStatus );

    //get chi tiết order
    @GET("/api/order/getChiTietOrder")
    Call<ChiTietOrderDTO> getChiTietOrder(@Query("idDonHang") int idOrder );

    // get gio hang cua users
    @GET("/cart/getCartByIdUser")
    Call<GioHangModel> getCartByIdUser(@Query("iduser") String iduser);

}
