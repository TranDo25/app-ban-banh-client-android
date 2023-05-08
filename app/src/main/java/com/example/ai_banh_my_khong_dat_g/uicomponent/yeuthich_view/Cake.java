package com.example.ai_banh_my_khong_dat_g.uicomponent.yeuthich_view;

public class Cake {
    private int resourceImage;
    private String name;

    public Cake(int resourceImage, String name) {
        this.resourceImage = resourceImage;
        this.name = name;
    }

    public int getResourceImage() {
        return resourceImage;
    }

    public void setResourceImage(int resourceImage) {
        this.resourceImage = resourceImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
