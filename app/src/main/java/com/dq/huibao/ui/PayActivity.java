package com.dq.huibao.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dq.huibao.R;
import com.dq.huibao.base.BaseActivity;

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

    /*接收页面传值*/
    private Intent intent;
    private String ordersn = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ButterKnife.bind(this);
        intent = getIntent();
        ordersn = intent.getStringExtra("ordersn");

        tvPayOrdersn.setText("" + ordersn);
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
}
