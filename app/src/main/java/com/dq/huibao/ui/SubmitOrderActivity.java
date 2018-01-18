package com.dq.huibao.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dq.huibao.R;
import com.dq.huibao.adapter.SubmitOrderAdapter;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.bean.account.Login;
import com.dq.huibao.bean.addr.Addr;
import com.dq.huibao.bean.cart.CheckOrder;
import com.dq.huibao.ui.addr.AddAddressActivity;
import com.dq.huibao.ui.addr.AddressListActivity;
import com.dq.huibao.utils.CodeUtils;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpUtils;
import com.dq.huibao.utils.MD5Util;
import com.dq.huibao.utils.SPUserInfo;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description：确认提交
 * Created by jingang on 2017/10/30.
 */

public class SubmitOrderActivity extends BaseActivity {
    @Bind(R.id.rv_sibmitorder)
    RecyclerView rvSibmitorder;
    @Bind(R.id.lin_submitorder_address)
    LinearLayout linAddr;
    @Bind(R.id.tv_submitorder_address)
    TextView tvAddr;
    @Bind(R.id.tv_confirm_pay)
    TextView tvConfirmPay;
    @Bind(R.id.but_confirm_pay)
    Button butConfirmPay;

    private SubmitOrderAdapter submitOrderAdapter;
    private LinearLayoutManager mManager;
    private List<CheckOrder.DataBean> shopList = new ArrayList<>();

    /*接收页面传值*/
    private Intent intent;
    private String cartids = "";

    /*接口地址*/
    private String PATH = "", MD5_PATH = "";
    private RequestParams params = null;

    /*本地轻量型缓存*/
    private SPUserInfo spUserInfo;
    private String phone = "", token = "";

    /*收货地址*/
    private List<Addr.DataBean> addrList = new ArrayList<>();
    private String regionid = "";//市级id（省市二级id）

    /*支付价格*/
    private double pay_all = 0.00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submitorder);
        ButterKnife.bind(this);

        intent = getIntent();
        cartids = intent.getStringExtra("cartids");

        mManager = new LinearLayoutManager(this);
        submitOrderAdapter = new SubmitOrderAdapter(this, shopList);

        rvSibmitorder.setLayoutManager(mManager);
        rvSibmitorder.setAdapter(submitOrderAdapter);

        isLogin();

    }

    @Override
    protected void initWidght() {
        super.initWidght();
        setTitleName("提交订单");
    }

    @OnClick({R.id.lin_submitorder_address, R.id.but_confirm_pay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lin_submitorder_address:
                if (regionid.equals("")) {
                    //添加收货地址
                    intent = new Intent(this, AddAddressActivity.class);
                    startActivityForResult(intent, CodeUtils.CONFIRM_ORDER);
                } else {
                    //选择收货地址
                    intent = new Intent(this, AddressListActivity.class);
                    startActivityForResult(intent, CodeUtils.CONFIRM_ORDER);
                }
                break;

            case R.id.but_confirm_pay:
                //提交订单

                orderAdd(phone, token, cartids, "", "");
                break;
            default:
                break;
        }

    }

    @SuppressLint("WrongConstant")
    public void isLogin() {
        spUserInfo = new SPUserInfo(getApplication());

        if (!(spUserInfo.getLoginReturn().equals(""))) {
            Login login = GsonUtil.gsonIntance().gsonToBean(spUserInfo.getLoginReturn(), Login.class);
            phone = login.getData().getPhone();
            token = login.getData().getToken();
            getAddr(phone, token);
        } else {
            toast("请重新登录");
        }

    }

    /**
     * 获取收货地址
     *
     * @param phone
     * @param token
     */
    public void getAddr(final String phone, final String token) {
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
                        Addr addr = GsonUtil.gsonIntance().gsonToBean(result, Addr.class);
                        addrList = addr.getData();
                        if (addr.getStatus() == 1) {

//                            addrList.addAll(addr.getData());
//                            addressListAdapter.notifyDataSetChanged();

                            //确认订单
                            for (int i = 0; i < addrList.size(); i++) {
                                if (addrList.get(i).getIsdefault().equals("1")) {
                                    regionid = addrList.get(i).getRegionid();
                                    tvAddr.setText(addrList.get(i).getContact() + "(" + addrList.get(i).getMobile() + ")\n" +
                                            addrList.get(i).getProvince() + "." + addrList.get(i).getCity() + "." + addrList.get(i).getAddr());

                                    getCheckorder(phone, token, cartids, regionid);

                                } else {
                                    tvAddr.setText("请选择收货地址");
                                }
                            }
                        }

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
     * 提交订单前确认
     *
     * @param phone
     * @param token
     * @param cartids  购物车id 集合 逗号隔开
     * @param regionid 配送地址的市级id
     */
    public void getCheckorder(String phone, String token, String cartids, String regionid) {
        MD5_PATH = "cartids=" + cartids + "&cityid=" + regionid + "&phone=" + phone + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token;

        PATH = HttpUtils.PATHS + HttpUtils.CONFIRM_CHECKORDER + MD5_PATH + "&sign=" +
                MD5Util.getMD5String(MD5_PATH + HttpUtils.KEY);

        params = new RequestParams(PATH);
        System.out.println("确认订单 = " + PATH);
        x.http().post(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("确认订单 = " + result);
                        CheckOrder checkOrder = GsonUtil.gsonIntance().gsonToBean(result, CheckOrder.class);

                        shopList.addAll(checkOrder.getData());

                        submitOrderAdapter.notifyDataSetChanged();

                        for (int i = 0; i < shopList.size(); i++) {
                            pay_all += shopList.get(i).getMoney_all() - shopList.get(i).getDiscount_all() + shopList.get(i).getDispatch_all();
                        }

                        tvConfirmPay.setText("需付：¥" + pay_all);

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
     * 提交订单
     *
     * @param phone
     * @param token
     * @param cartids 购物车id 集合 逗号隔开
     * @param addrid  收货地址的id
     * @param remark  备注[{shopid:remark}]备注
     */
    public void orderAdd(String phone, String token, String cartids, String addrid, String remark) {
        MD5_PATH = "addrid=" + addrid + "&cartids=" + cartids + "&phone=" + phone + "&remark=" + remark + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token;
        PATH = HttpUtils.PATHS + HttpUtils.ORDER_ADD + MD5_PATH + "&sign=" +
                MD5Util.getMD5String(MD5_PATH + HttpUtils.KEY);

        params = new RequestParams(PATH);
        System.out.println("提交订单 = " + PATH);
        x.http().post(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("提交订单 = " + result);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CodeUtils.CONFIRM_ORDER) {
            if (resultCode == CodeUtils.ADDR_ADD && resultCode == CodeUtils.ADDR_LIST) {
                addrList.clear();
                isLogin();
            }
        }
    }
}
