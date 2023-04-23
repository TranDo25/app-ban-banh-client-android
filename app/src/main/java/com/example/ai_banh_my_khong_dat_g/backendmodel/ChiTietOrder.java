package com.example.ai_banh_my_khong_dat_g.backendmodel;


public class ChiTietOrder {

    private Integer id;

    private Integer ordersId;

    public ChiTietOrder(Integer id, Integer ordersId, Integer cartId) {
        this.id = id;
        this.ordersId = ordersId;
        this.cartId = cartId;
    }

    private Integer cartId;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrdersId() {
        return this.ordersId;
    }

    public void setOrdersId(Integer ordersId) {
        this.ordersId = ordersId;
    }

    public Integer getCartId() {
        return this.cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }
}
