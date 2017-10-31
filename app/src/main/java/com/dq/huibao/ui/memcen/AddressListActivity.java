package com.dq.huibao.ui.memcen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.dq.huibao.R;
import com.dq.huibao.adapter.AddressListAdapter;
import com.dq.huibao.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description：收货地址列表
 * Created by jingang on 2017/10/31.
 */

public class AddressListActivity extends BaseActivity {
    @Bind(R.id.rv_addresslist)
    RecyclerView rvAddresslist;

    /*添加收货地址*/
    @Bind(R.id.but_add_address)
    Button butAddAddress;

    private AddressListAdapter addressListAdapter;

    /*接收页面传值*/
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addresslist);
        ButterKnife.bind(this);

        addressListAdapter = new AddressListAdapter(this);

        rvAddresslist.setLayoutManager(new LinearLayoutManager(this));
        rvAddresslist.setAdapter(addressListAdapter);
    }

    @Override
    protected void initWidght() {
        super.initWidght();
        setTitleName("管理收货地址");
    }

    @OnClick(R.id.but_add_address)
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.but_add_address:
                intent = new Intent(this, AddAddressActivity.class);
                startActivity(intent);
                break;
        }
    }
}
