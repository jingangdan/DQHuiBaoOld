package com.dq.huibao.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dq.huibao.R;
import com.dq.huibao.adapter.SubmitOrderAdapter;
import com.dq.huibao.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Description：提交订单
 * Created by jingang on 2017/10/30.
 */

public class SubmitOrderActivity extends BaseActivity {
    @Bind(R.id.rv_sibmitorder)
    RecyclerView rvSibmitorder;

    private SubmitOrderAdapter submitOrderAdapter;
    private LinearLayoutManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submitorder);
        ButterKnife.bind(this);

        mManager = new LinearLayoutManager(this);
        submitOrderAdapter = new SubmitOrderAdapter(this);

        rvSibmitorder.setLayoutManager(mManager);
        rvSibmitorder.setAdapter(submitOrderAdapter);

    }

    @Override
    protected void initWidght() {
        super.initWidght();
        setTitleName("提交订单");
    }
}
