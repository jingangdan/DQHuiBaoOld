package com.dq.huibao.ui.memcen;

import android.os.Bundle;

import com.dq.huibao.R;
import com.dq.huibao.base.BaseActivity;

/**
 * Description：绑定手机
 * Created by jingang on 2017/11/25.
 */

public class MobileActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile);
    }

    @Override
    protected void initWidght() {
        super.initWidght();
        setTitleName("绑定手机");
    }
}
