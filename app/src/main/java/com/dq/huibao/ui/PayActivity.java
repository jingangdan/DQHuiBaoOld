package com.dq.huibao.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dq.huibao.R;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.utils.HttpUtils;
import com.dq.huibao.utils.MD5Util;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 支付界面
 * Created by jingang on 2018/1/23.
 */

public class PayActivity extends BaseActivity {

    @Bind(R.id.tv_pay_ordersn)
    TextView tvPayOrdersn;
    @Bind(R.id.tv_pay_price)
    TextView tvPayPrice;
    @Bind(R.id.rel_pay_weixin)
    RelativeLayout relPayWeixin;
    @Bind(R.id.rel_pay_yue)
    RelativeLayout relPayYue;
    @Bind(R.id.rel_pay_zhifubao)
    RelativeLayout relPayZhifubao;

    /*接收页面传值*/
    private Intent intent;
    private String ordersn = "", price = "", phone = "", token = "";

    /*接口地址*/
    private String MD5_PATH = "", PATH = "";
    private RequestParams params = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ButterKnife.bind(this);
        intent = getIntent();
        ordersn = intent.getStringExtra("ordersn");
        price = intent.getStringExtra("price");
        phone = intent.getStringExtra("phone");
        token = intent.getStringExtra("token");

        getPayType(ordersn, phone, token);

        tvPayOrdersn.setText("" + ordersn);
        tvPayPrice.setText("¥" + price);
    }

    @Override
    protected void initWidght() {
        super.initWidght();
        setTitleName("支付");
    }

    @OnClick({R.id.rel_pay_weixin, R.id.rel_pay_yue})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rel_pay_weixin:
                break;
            case R.id.rel_pay_yue:
                break;
        }
    }

    /**
     * 选择支付方式
     *
     * @param ordersn
     * @param phone
     * @param token
     */
    public void getPayType(String ordersn, String phone, String token) {
        MD5_PATH = "ordersn=" + ordersn + "&phone=" + phone + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token;
        PATH = HttpUtils.PATHS + HttpUtils.PAY_PAYTYPE + MD5_PATH + "&sign=" +
                MD5Util.getMD5String(MD5_PATH + HttpUtils.KEY);
        params = new RequestParams(PATH);
        System.out.println("选择支付方式 = " + PATH);
        x.http().get(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("选择支付方式 = " + result);

                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {

                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });
    }

    /**
     * 调用第三方支付前，在第三方下单
     *
     * @param ordersn
     * @param paytype
     * @param phone
     * @param token
     */
    public void setPayOrder(String ordersn, String paytype, String phone, String token) {
        MD5_PATH = "ordersn=" + ordersn + "&paytype=" + paytype + "&phone=" + phone + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token;
        PATH = HttpUtils.PATHS + HttpUtils.PAY_PAYTYPE + MD5_PATH + "&sign=" +
                MD5Util.getMD5String(MD5_PATH + HttpUtils.KEY);

        params = new RequestParams(PATH);
        System.out.println("第三方下单 = " + PATH);
        x.http().post(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("第三方下单 = " + result);
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {

                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });
    }

    @SuppressLint("WrongConstant")
    public void setPayType() {
        relPayWeixin.setVisibility(View.GONE);
        relPayZhifubao.setVisibility(View.GONE);
        relPayYue.setVisibility(View.GONE);
    }
}
