package com.dq.huibao.ui.addr;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.Interface.OnItemLongClickListener;
import com.dq.huibao.R;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.bean.account.Login;
import com.dq.huibao.bean.addr.Addr;
import com.dq.huibao.bean.addr.AddrReturn;
import com.dq.huibao.utils.BaseRecyclerViewHolder;
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

/**
 * Description：收货地址列表
 * Created by jingang on 2017/10/31.
 */

public class AddrListActivity extends BaseActivity {
    @Bind(R.id.rv_addresslist)
    RecyclerView rvAddresslist;

    /*添加收货地址*/
    @Bind(R.id.but_add_address)
    Button butAddAddress;

    private AddrListActivity TAG = AddrListActivity.this;

    private AddrListAdapter addrListAdapter;
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

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addresslist);
        ButterKnife.bind(this);

        butAddAddress.setVisibility(View.GONE);

        addrListAdapter = new AddrListAdapter(this, addrList);
        rvAddresslist.setLayoutManager(new LinearLayoutManager(this));
        rvAddresslist.setAdapter(addrListAdapter);

        addrListAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //addAddr(regionid, isdefault, addr, UTF_addr, contact, UTF_contact, mobile, phone, token);
            }
        });


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
                            addrListAdapter.notifyDataSetChanged();
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
     * 添加收货地址
     *
     * @param regionid  选择区域id
     * @param isdefault 是否设为默认  0 否 1 是
     * @param addr      详细地址
     * @param contact   联系人
     * @param mobile    联系电话
     * @param phone
     * @param token
     */
    public void addAddr(final String regionid, int isdefault, String addr, String UTF_addr, String contact, String UTF_contact, String mobile, String phone, String token) {
        MD5_PATH = "addr=" + UTF_addr + "&contact=" + UTF_contact + "&isdefault=" + isdefault + "&mobile=" + mobile +
                "&phone=" + phone + "&regionid=" + regionid + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token;

        PATH = HttpUtils.PATHS + HttpUtils.MEMBER_ADDADDR + MD5_PATH + "&sign=" +
                MD5Util.getMD5String("addr=" + addr + "&contact=" + contact + "&isdefault=" + isdefault + "&mobile=" + mobile +
                        "&phone=" + phone + "&regionid=" + regionid + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token + HttpUtils.KEY);

        params = new RequestParams(PATH);
        System.out.println("加密 = " + MD5_PATH);

        System.out.println("添加收货地址 = " + PATH);
        x.http().post(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("添加收货地址 = " + result);
                        AddrReturn addrReturn = GsonUtil.gsonIntance().gsonToBean(result, AddrReturn.class);
                        if (addrReturn.getStatus() == 1) {
                            toast("" + addrReturn.getData());
                            intent = new Intent();
                            setResult(CodeUtils.ADDR_ADD, intent);
                            TAG.finish();
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


    public class AddrListAdapter extends RecyclerView.Adapter<AddrListAdapter.MyViewHolder> {
        private OnItemClickListener onItemClickListener;
        private OnItemLongClickListener onItemLongClickListener;

        private Context mContext;
        private List<Addr.DataBean> addrList;

        public AddrListAdapter(Context mContext, List<Addr.DataBean> addrList) {
            this.mContext = mContext;
            this.addrList = addrList;
        }

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }

        public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
            this.onItemLongClickListener = onItemLongClickListener;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder vh = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_addresslist, parent, false));
            return vh;
        }

        @SuppressLint("WrongConstant")
        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            if (onItemClickListener != null) {
                //
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position = holder.getLayoutPosition(); // 1
                        onItemClickListener.onItemClick(holder.itemView, position); // 2
                    }
                });

            }
            if (onItemLongClickListener != null) {
                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int position = holder.getLayoutPosition();
                        onItemLongClickListener.onItemLongClick(holder.itemView, position);
                        //返回true 表示消耗了事件 事件不会继续传递
                        return true;
                    }
                });
            }
            holder.linearLayout.setVisibility(View.GONE);

            holder.contact.setText("" + addrList.get(position).getContact());
            holder.mobile.setText("" + addrList.get(position).getMobile());
            holder.addr.setText("" + addrList.get(position).getProvince() + addrList.get(position).getCity() + addrList.get(position).getAddr());
            if (addrList.get(position).getIsdefault().equals("1")) {
                holder.checkBox.setChecked(true);
            } else if (addrList.get(position).getIsdefault().equals("0")) {
                holder.checkBox.setChecked(false);
            }

        }

        @Override
        public int getItemCount() {
            return addrList.size();
        }

        public class MyViewHolder extends BaseRecyclerViewHolder {
            private TextView contact, mobile, addr;
            private CheckBox checkBox;
            private LinearLayout linearLayout;

            public MyViewHolder(View view) {
                super(view);
                contact = view.findViewById(R.id.tv_item_addr_contact);
                mobile = view.findViewById(R.id.tv_item_addr_mobile);
                addr = view.findViewById(R.id.tv_item_addr);
                checkBox = view.findViewById(R.id.cb_item_address);
                linearLayout = view.findViewById(R.id.lin_item_addrlist);

            }
        }
    }


}
