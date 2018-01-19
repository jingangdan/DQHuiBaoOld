package com.dq.huibao.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.dq.huibao.R;
import com.dq.huibao.base.BaseActivity;

/**
 * 支付
 * Created by jingang on 2018/1/19.
 */

public class PayActivity extends BaseActivity{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_pay);

    }

    @Override
    protected void initWidght() {
        super.initWidght();
        setTitleName("支付");
    }
}
