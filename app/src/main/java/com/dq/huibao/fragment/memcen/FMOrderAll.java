package com.dq.huibao.fragment.memcen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dq.huibao.Interface.OrderInterface;
import com.dq.huibao.R;
import com.dq.huibao.adapter.OrderAdapter;
import com.dq.huibao.base.BaseFragment;
import com.dq.huibao.bean.order.Order;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpUtils;
import com.dq.huibao.utils.MD5Util;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description：我的订单（全部）
 * Created by jingang on 2017/11/1.
 */

public class FMOrderAll extends BaseFragment implements OrderInterface{
    @Bind(R.id.rv_order_all)
    RecyclerView recyclerView;
    @Bind(R.id.but_tablayout)
    Button butTablayout;

    private View view;

    private List<Order.DataBean> orderList = new ArrayList<>();
    private OrderAdapter orderAdapters;

    /*接收页面传值*/
    private Intent intent;

    /*接口地址*/
    private String PATH = "", MD5_PATH = "";
    private RequestParams params = null;

    Bundle bundle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fm_tablayout, null);
        ButterKnife.bind(this, view);

        orderAdapters = new OrderAdapter(getActivity(), orderList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(orderAdapters);

        orderGetList("", getArguments().getString("phone"), getArguments().getString("token"));

        return view;
    }

    public FMOrderAll newInstance(String phone, String token) {
        bundle = new Bundle();
        bundle.putString("phone", phone);
        bundle.putString("token", token);
        FMOrderAll fragment = new FMOrderAll();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            System.out.println("in FMSellerOK");

        } else {
            System.out.println("move FMSellerOK");

        }

    }

    @OnClick(R.id.but_tablayout)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.but_tablayout:
                butTablayout.setText("随便逛逛");

                break;
            default:
                break;
        }
    }

    /**
     * 获取订单列表
     *
     * @param status
     * @param phone
     * @param token
     */
    public void orderGetList(String status, String phone, String token) {
        MD5_PATH = "phone=" + phone + "&status=" + status + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token;

        PATH = HttpUtils.PATHS + HttpUtils.ORDER_GETIST + MD5_PATH + "&sign=" +
                MD5Util.getMD5String(MD5_PATH + HttpUtils.KEY);
        params = new RequestParams("http://new.dequanhuibao.com/Api/Order/getlist?phone=17865069350&status=&timestamp=1516418845&token=dcb252e7fc72e2ff493511c4bf5616d8&sign=5f0cc942a0f9cecd57fe4bf0946071bd");
        System.out.println("全部订单列表 = " + PATH);
        x.http().get(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("全部订单列表 = " + result);
                        Order order = GsonUtil.gsonIntance().gsonToBean(result, Order.class);

                        orderList.clear();
                        orderList.addAll(order.getData());

                        orderAdapters.notifyDataSetChanged();

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
    protected void lazyLoad() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @Override
    public void doOrderKuaidi(String type, String postid) {

    }

    @Override
    public void doOrderEdit(String id, String type) {

    }

    /**
     * 快递100快递查询接口
     * @param type 快递公司编号订单详情提供
     * @param postid 快递单号
     */
    public void getOrderKuaidi(String type, String postid){

    }

}
