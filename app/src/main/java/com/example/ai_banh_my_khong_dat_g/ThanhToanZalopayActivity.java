package com.example.ai_banh_my_khong_dat_g;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;


import com.example.ai_banh_my_khong_dat_g.api.ApiService;
import com.example.ai_banh_my_khong_dat_g.backendmodel.Cart;
import com.example.ai_banh_my_khong_dat_g.backendmodel.MessageDTO;
import com.example.ai_banh_my_khong_dat_g.zalo.Api.CreateOrder;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;

import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.zalopay.sdk.ZaloPayError;
import vn.zalopay.sdk.ZaloPaySDK;
import vn.zalopay.sdk.Environment;
import vn.zalopay.sdk.listeners.PayOrderListener;

public class ThanhToanZalopayActivity extends AppCompatActivity {
    TextView lblZpTransToken, txtToken;
    Button btnCreateOrder, btnPay;
    EditText txtAmount;

    private void BindView() {
        txtToken = findViewById(R.id.txtToken);
        lblZpTransToken = findViewById(R.id.lblZpTransToken);
        btnCreateOrder = findViewById(R.id.btnCreateOrder);
        txtAmount = findViewById(R.id.txtAmount);
        btnPay = findViewById(R.id.btnPay);
        IsLoading();
    }

    private void IsLoading() {
        lblZpTransToken.setVisibility(View.INVISIBLE);
        txtToken.setVisibility(View.INVISIBLE);
        btnPay.setVisibility(View.INVISIBLE);
    }

    private void IsDone() {
        lblZpTransToken.setVisibility(View.VISIBLE);
        txtToken.setVisibility(View.VISIBLE);
        btnPay.setVisibility(View.VISIBLE);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan_with_zalopay);

        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // ZaloPay SDK Init
        ZaloPaySDK.init(2553, Environment.SANDBOX);
        // bind components with ids
        BindView();
        String tmp = getIntent().getStringExtra("tongTien");
        double tongTien = Double.parseDouble(tmp);
        int tongTienInt = (int) tongTien;
        txtAmount.setText(String.valueOf(tongTienInt));
        // handle CreateOrder
        btnCreateOrder.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                CreateOrder orderApi = new CreateOrder();
//                List<Cart> danhSachCart = new ArrayList<>();
//
//                String sdt = getIntent().getStringExtra("sdt");
//                String diaChiGiaoHang = getIntent().getStringExtra("diaChiGiaoHang");
//                ApiService.apiService.setThongTinGiaoHang().enqueue(
//
//                );
                try {
                    JSONObject data = orderApi.createOrder(txtAmount.getText().toString());
                    Log.d("Amount", txtAmount.getText().toString());
                    lblZpTransToken.setVisibility(View.VISIBLE);
                    String code = data.getString("return_code");
                    Toast.makeText(getApplicationContext(), "return_code: " + code, Toast.LENGTH_LONG).show();

                    if (code.equals("1")) {
                        lblZpTransToken.setText("zptranstoken");
                        String tokenZalopay = data.getString("zp_trans_token");
                        int idNewestOrder = getIntent().getIntExtra("idNewestOrder",0);
                        ApiService.apiService.setToken(idNewestOrder, tokenZalopay).enqueue(new Callback<MessageDTO>() {
                            @Override
                            public void onResponse(Call<MessageDTO> call, Response<MessageDTO> response) {
                                String responseMessage = response.body().getMessage();
                                Toast.makeText(getApplicationContext(), responseMessage, Toast.LENGTH_LONG).show();

                            }

                            @Override
                            public void onFailure(Call<MessageDTO> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), "error save zalopay token", Toast.LENGTH_LONG).show();
                            }
                        });
                        txtToken.setText(data.getString("zp_trans_token"));
                        IsDone();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().subscribeToTopic("thanh_toan_don_hang")
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                String message = "Thanh toán thành công";
                                if(!task.isSuccessful()){
                                    message = "Thanh toán thất bại";
                                }
                            }
                        });
                String token = txtToken.getText().toString();
                ZaloPaySDK.getInstance().payOrder(ThanhToanZalopayActivity.this, token, "demozpdk://app", new PayOrderListener() {
                    @Override
                    public void onPaymentSucceeded(final String transactionId, final String transToken, final String appTransID) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                new AlertDialog.Builder(ThanhToanZalopayActivity.this)
                                        .setTitle("Payment Success")
                                        .setMessage(String.format("TransactionId: %s - TransToken: %s", transactionId, transToken))
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                            }
                                        })
                                        .setNegativeButton("Cancel", null).show();
                            }

                        });
                        IsLoading();
                    }

                    @Override
                    public void onPaymentCanceled(String zpTransToken, String appTransID) {
                        new AlertDialog.Builder(ThanhToanZalopayActivity.this)
                                .setTitle("User Cancel Payment")
                                .setMessage(String.format("zpTransToken: %s \n", zpTransToken))
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                })
                                .setNegativeButton("Cancel", null).show();
                    }

                    @Override
                    public void onPaymentError(ZaloPayError zaloPayError, String zpTransToken, String appTransID) {
                        new AlertDialog.Builder(ThanhToanZalopayActivity.this)
                                .setTitle("Payment Fail")
                                .setMessage(String.format("ZaloPayErrorCode: %s \nTransToken: %s", zaloPayError.toString(), zpTransToken))
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                })
                                .setNegativeButton("Cancel", null).show();
                    }
                });
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ZaloPaySDK.getInstance().onResult(intent);
    }
}
