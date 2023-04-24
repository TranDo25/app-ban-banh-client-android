package com.example.ai_banh_my_khong_dat_g.backendmodel;

import java.io.Serializable;

public class ProductWithImageWithNumberDTO implements Serializable {

    private ProductWithImageDTO productWithImageDTO;
    private int soLuongMuonMua;
    private boolean isCheck = false;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
    private int cartId;

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public ProductWithImageWithNumberDTO(ProductWithImageDTO productWithImageDTO, int soLuongMuonMua) {
        this.productWithImageDTO = productWithImageDTO;
        this.soLuongMuonMua = soLuongMuonMua;
    }

    public ProductWithImageWithNumberDTO() {
    }

    public ProductWithImageDTO getProductWithImageDTO() {
        return productWithImageDTO;
    }

    public void setProductWithImageDTO(ProductWithImageDTO productWithImageDTO) {
        this.productWithImageDTO = productWithImageDTO;
    }

    public int getSoLuongMuonMua() {
        return soLuongMuonMua;
    }

    public void setSoLuongMuonMua(int soLuongMuonMua) {
        this.soLuongMuonMua = soLuongMuonMua;
    }
}
