package com.dq.huibao.ui.memcen;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.dq.huibao.Interface.AddrInterface;
import com.dq.huibao.R;
import com.dq.huibao.adapter.AddressListAdapter;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.bean.account.Login;
import com.dq.huibao.bean.addr.Addr;
import com.dq.huibao.bean.addr.AddrReturn;
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

import javax.net.ssl.SSLContext;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description：收货地址列表
 * Created by jingang on 2017/10/31.
 */

public class AddressListActivity extends BaseActivity
        implements AddrInterface {
    @Bind(R.id.rv_addresslist)
    RecyclerView rvAddresslist;

    /*添加收货地址*/
    @Bind(R.id.but_add_address)
    Button butAddAddress;

    private AddressListActivity TAG = AddressListActivity.this;

    private AddressListAdapter addressListAdapter;
    private List<Addr.DataBean> addrList = new ArrayList<>();

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

        addressListAdapter = new AddressListAdapter(this, addrList);

        rvAddresslist.setLayoutManager(new LinearLayoutManager(this));
        rvAddresslist.setAdapter(addressListAdapter);

        addressListAdapter.setAddrInterface(this);

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
//                intent = new Intent(this, AddAddressActivity.class);
//                startActivityForResult(intent, CodeUtils.ADDR_LIST);

                intent = new Intent(TAG, AddAddressActivity.class);
//                intent.putExtra("addrid", "");
//                intent.putExtra("regionid", "");
//                intent.putExtra("region", "");
//                intent.putExtra("isdefault", "");
//                intent.putExtra("addr", "");
//                intent.putExtra("contact", "");
//                intent.putExtra("mobile", "");
                intent.putExtra("tag", "0");
                startActivityForResult(intent, CodeUtils.ADDR_LIST);
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
                        Addr addr = GsonUtil.gsonIntance().gsonToBean(result, Addr.class);
                        if (addr.getStatus() == 1) {
                            addrList.addAll(addr.getData());
                            addressListAdapter.notifyDataSetChanged();
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
     * 删除收货地址
     *
     * @param position
     * @param id
     * @param phone
     * @param token
     */
    public void addrDel(final int position, String id, String phone, String token) {
        MD5_PATH = "id=" + id + "&phone=" + phone + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token;

        PATH = HttpUtils.PATHS + HttpUtils.MEMBER_DELADDR + MD5_PATH + "&sign=" +
                MD5Util.getMD5String(MD5_PATH + HttpUtils.KEY);

        params = new RequestParams(PATH);
        System.out.println("删除收货地址 = " + PATH);
        x.http().post(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("删除收货地址 = " + result);
                        AddrReturn addrReturn = GsonUtil.gsonIntance().gsonToBean(result, AddrReturn.class);
                        if(addrReturn.getStatus() == 1){
                            addrList.remove(position);
                            addressListAdapter.notifyDataSetChanged();
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

    @Override
    public void checkEdit(int position, String id, String regionid, String region, int isdefault, String addr, String contact, String mobile) {
        intent = new Intent(TAG, AddAddressActivity.class);
        intent.putExtra("addrid", id);
        intent.putExtra("regionid", regionid);
        intent.putExtra("region", region);
        intent.putExtra("isdefault", "" + isdefault);
        intent.putExtra("addr", addr);
        intent.putExtra("contact", contact);
        intent.putExtra("mobile", mobile);
        intent.putExtra("tag", "1");
        startActivityForResult(intent, CodeUtils.ADDR_LIST);
    }

    AlertDialog alert;
    @Override
    public void checkDel(final int position, final String id) {
        alert = new AlertDialog.Builder(this).create();
        alert.setTitle("操作提示");
        alert.setMessage("确定要删除吗？");
        alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
        alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        addrDel(position, id, phone, token);
                        return;
                    }
                });
        alert.show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CodeUtils.ADDR_LIST) {
            if (resultCode == CodeUtils.ADDR_ADD) {
                addrList.clear();
                isLogin();
            }
        }
    }
}
