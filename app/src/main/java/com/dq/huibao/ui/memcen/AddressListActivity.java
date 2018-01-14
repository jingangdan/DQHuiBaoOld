package com.dq.huibao.ui.memcen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.dq.huibao.R;
import com.dq.huibao.adapter.AddressListAdapter;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.bean.account.Login;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpUtils;
import com.dq.huibao.utils.MD5Util;
import com.dq.huibao.utils.SPUserInfo;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

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

    private AddressListActivity TAG = AddressListActivity.this;

    private AddressListAdapter addressListAdapter;

    /*接收页面传值*/
    private Intent intent;

    /*接口地址*/
    private String PATH = "";
    private String MD5_PATH = "";
    private RequestParams params = null;

    /*本地轻量型缓存*/
    private SPUserInfo spUserInfo;
    private String phone = "", token = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addresslist);
        ButterKnife.bind(this);

        addressListAdapter = new AddressListAdapter(this);

        rvAddresslist.setLayoutManager(new LinearLayoutManager(this));
        rvAddresslist.setAdapter(addressListAdapter);

        isLogin();
    }

    @SuppressLint("WrongConstant")
    public void isLogin() {
        spUserInfo = new SPUserInfo(TAG.getApplication());

        if (!(spUserInfo.getLoginReturn().equals(""))) {
            Login login = GsonUtil.gsonIntance().gsonToBean(spUserInfo.getLoginReturn(), Login.class);
            phone = login.getData().getPhone();
            token = login.getData().getToken();
            getAddr(phone, token);
        } else {
            toast("请重新登录");
        }

    }

    @Override
    protected void initWidght() {
        super.initWidght();
        setTitleName("管理收货地址");
    }

    @OnClick(R.id.but_add_address)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.but_add_address:
                intent = new Intent(this, AddAddressActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * 获取收货地址
     *
     * @param phone
     * @param token
     */
    public void getAddr(String phone, String token) {
        MD5_PATH = "phone=" + phone + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token;

        PATH = HttpUtils.PATHS + HttpUtils.MEMBER_GETADDR + MD5_PATH + "&sign=" +
                MD5Util.getMD5String(MD5_PATH + "&key=ivKDDIZHF2b0Gjgvv2QpdzfCmhOpya5k");

        params = new RequestParams(PATH);
        System.out.println("获取收货地址 = " + PATH);
        x.http().get(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("获取收货地址 = " + result);
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
}
