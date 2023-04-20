package com.example.ai_banh_my_khong_dat_g.thanhtoan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ai_banh_my_khong_dat_g.R;
import com.example.ai_banh_my_khong_dat_g.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

//import vn.momo.momo_partner.AppMoMoLib;

public class ThanhToanActivity extends AppCompatActivity {
//    Button btnmomo;
//    //variable for momo
//    private String amount = "10000";
//    private String fee = "0";
//    int environment = 0;//developer default
//    private String merchantName = "Apple fake";
//    //cần lấy mã đối tác được lấy trong tài khoản momo của doanh nghiệp
//    private String merchantCode = "SCB01";
//    private String merchantNameLabel = "Apple fake";
//    private String description = "mua hàng online";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);
//        initView();
//        AppMoMoLib.getInstance().setEnvironment(AppMoMoLib.ENVIRONMENT.DEVELOPMENT); // AppMoMoLib.ENVIRONMENT.PRODUCTION
//        initControl();
    }

    private void initControl() {
//        btnmomo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String str_diachi = editdiachi.getText().toString().trim();
//                requestPayment("DH001");
//
//                //cần có hàm update token momo, gửi token và id đơn hàng lên
//                //laays ra id đơn hàng gửi qua intent
//                //cần có message model nếu muốn tạo message notification
//                //trong android cũng có trò subscribe
//                //nhớ dùng hamf finish
//                //update token xong thì chuyển màn hình
//            }
//        });
    }

    //Get token through MoMo app
    private void requestPayment(String iddonhang) {
//        AppMoMoLib.getInstance().setAction(AppMoMoLib.ACTION.PAYMENT);
//        AppMoMoLib.getInstance().setActionType(AppMoMoLib.ACTION_TYPE.GET_TOKEN);
//        //lấy giá trị tiền thông qua ô text
////        if (edAmount.getText().toString() != null && edAmount.getText().toString().trim().length() != 0)
////            amount = edAmount.getText().toString().trim();
////fix cứng bằng 10.000 đ
////        amount = "10000";
//        Map<String, Object> eventValue = new HashMap<>();
//        //client Required
//        eventValue.put("merchantname", merchantName); //Tên đối tác. được đăng ký tại https://business.momo.vn. VD: Google, Apple, Tiki , CGV Cinemas
//        eventValue.put("merchantcode", merchantCode); //Mã đối tác, được cung cấp bởi MoMo tại https://business.momo.vn
//
////        eventValue.put("amount", total_amount); //Kiểu integer
//        eventValue.put("amount", amount); //Kiểu integer
//        eventValue.put("orderId", iddonhang); //uniqueue id cho Bill order, giá trị duy nhất cho mỗi đơn hàng
//        eventValue.put("orderLabel", "Mã đơn hàng: 19002222"); //gán nhãn
//
//        //client Optional - bill info
//        eventValue.put("merchantnamelabel", "Dịch vụ");//gán nhãn
////        eventValue.put("fee", total_fee); //Kiểu integer
//        eventValue.put("fee", "0"); //Kiểu integer, cho phí bằng 0
//        eventValue.put("description", description); //mô tả đơn hàng - short description
//
//        //client extra data
//        eventValue.put("requestId", merchantCode + "merchant_billId_" + System.currentTimeMillis());
//        eventValue.put("partnerCode", merchantCode);
//        //Example extra data
//        JSONObject objExtraData = new JSONObject();
//        //chỗ này cần truyền thoong tin đơn hàng vào
//        try {
//            objExtraData.put("site_code", "008");
//            objExtraData.put("site_name", "CGV Cresent Mall");
//            objExtraData.put("screen_code", 0);
//            objExtraData.put("screen_name", "Special");
//            objExtraData.put("movie_name", "Kẻ Trộm Mặt Trăng 3");
//            objExtraData.put("movie_format", "2D");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        eventValue.put("extraData", objExtraData.toString());
//
//        eventValue.put("extra", "");
//        AppMoMoLib.getInstance().requestMoMoCallBack(this, eventValue);
//

    }

    //Get token callback from MoMo app an submit to server side
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == AppMoMoLib.getInstance().REQUEST_CODE_MOMO && resultCode == -1) {
//            if (data != null) {
//                if (data.getIntExtra("status", -1) == 0) {
//                    //TOKEN IS AVAILABLE
//                    //hiện thông báo thành công, log ra thông báo
//                    Log.d("thanhcong",data.getStringExtra("message"));
////                    tvMessage.setText("message: " + "Get token " + data.getStringExtra("message"));
//                    String token = data.getStringExtra("data"); //Token response
//                    //để hàm update token csdl vào đây, tham khảo cách viết API trong java, gọi là class DAO
//                    String phoneNumber = data.getStringExtra("phonenumber");
//                    String env = data.getStringExtra("env");
//                    if (env == null) {
//                        env = "app";
//                    }
//
//                    if (token != null && !token.equals("")) {
//                        // TODO: send phoneNumber & token to your server side to process payment with MoMo server
//                        // IF Momo topup success, continue to process your order
//                    } else {
//                        Log.d("message",data.getStringExtra("không có thông tin trả về"));
////                        tvMessage.setText("message: " + this.getString(R.string.not_receive_info));
//                    }
//                } else if (data.getIntExtra("status", -1) == 1) {
//                    //TOKEN FAIL
//                    String message = data.getStringExtra("message") != null ? data.getStringExtra("message") : "Thất bại";
//                    Log.d("thanhcong",data.getStringExtra("không thành công"));
//                } else if (data.getIntExtra("status", -1) == 2) {
//                    //TOKEN FAIL
////                    tvMessage.setText("message: " + this.getString(R.string.not_receive_info));
//                    Log.d("thanhcong",data.getStringExtra("không thành công"));
//                } else {
//                    //TOKEN FAIL
////                    tvMessage.setText("message: " + this.getString(R.string.not_receive_info));
//                    Log.d("thanhcong",data.getStringExtra("không thành công"));
//                }
//            } else {
//                Log.d("thanhcong",data.getStringExtra("không thành công"));
////                tvMessage.setText("message: " + this.getString(R.string.not_receive_info));
//            }
//        } else {
//            Log.d("thanhcong",data.getStringExtra("không thành công"));
////            tvMessage.setText("message: " + this.getString(R.string.not_receive_info_err));
//        }
//    }

    private void initView() {
//        btnmomo = (Button) findViewById(R.id.btnmomo);
    }
}