package com.example.ai_banh_my_khong_dat_g.testmodel;

import com.example.ai_banh_my_khong_dat_g.backendmodel.ProductWithImageDTO;

import java.util.ArrayList;
import java.util.List;

public class TestListProductWithImageDTO {
    private static List<ProductWithImageDTO> list;
    public static List<ProductWithImageDTO> getList(){
        if(list == null){
            list = new ArrayList<>();
            list.add( new ProductWithImageDTO(
                    13, "bánh sinh nhật đánh đâu thắng đó",
                    "Bánh sinh nhật Đánh Đâu Thắng Đó trang trí hài hước với những lá bài là hình ảnh chủ đạo. Chiếc bánh sẽ khiến bữa tiệc trở nên nổi bật hơn bao giờ hết.",
                    560000.0,
                    null,
                    5000,
                    1,
                    "http://192.168.88.102:8080/api/admin/product/image/banh_sn_danh_dau_thang_do.png",
                    50000.0

            ));
            list.add(new ProductWithImageDTO(
                    15,
                    "bánh mì thập cẩm",
                    "bánh mì thập cẩm hồng hoa",
                    35000.0,
                    null,
                    200,
                    2,
                    "http://192.168.88.102:8080/api/admin/product/image/banhmithapcam.png",
                    10000.0
            ));
        }
        return list;
    }
}
