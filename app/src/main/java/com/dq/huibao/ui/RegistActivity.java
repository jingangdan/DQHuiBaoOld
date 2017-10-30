package com.dq.huibao.ui;

import android.os.Bundle;
import android.view.View;

import com.dq.huibao.R;
import com.dq.huibao.base.BaseActivity;

/**
 * Description：
 * Created by jingang on 2017/10/27.
 */
public class RegistActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
    }

    @Override
    protected void initWidght() {
        super.initWidght();
        setTitleName("会员注册");
       // toast("");
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
    }
}
