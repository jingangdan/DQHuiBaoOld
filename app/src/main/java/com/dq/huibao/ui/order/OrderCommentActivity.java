package com.dq.huibao.ui.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.View;
import android.widget.Button;

import com.dq.huibao.R;
import com.dq.huibao.adapter.order.OrderCommentAdapter;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.bean.order.OrderDetail;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.MD5Util;

import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 订单评价
 * Created by jingang on 2018/1/26.
 */

public class OrderCommentActivity extends BaseActivity {
    @Bind(R.id.rv_order_comment)
    RecyclerView recyclerView;
    @Bind(R.id.but_order_comment)
    Button butOrderComment;
    /*接收页面传值*/
    private Intent intent;
    private String orderid = "", phone = "", token = "";

    /*接口地址*/
    private String MD5_PATH = "", PATH = "";
    private RequestParams params = null;

    private List<OrderDetail.DataBean.GoodslistBean> goodsList = new ArrayList<>();
    private OrderCommentAdapter orderCommentAdapter;
    private String remark = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_comment);
        ButterKnife.bind(this);

        orderCommentAdapter = new OrderCommentAdapter(this, goodsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(orderCommentAdapter);


        intent = getIntent();
        orderid = intent.getStringExtra("orderid");
        phone = intent.getStringExtra("phone");
        token = intent.getStringExtra("token");

        getOrderDetail(orderid, phone, token);
    }

    @Override
    protected void initWidght() {
        super.initWidght();
        setTitleName("评价");
    }

    /**
     * 获取订单详情 (商品列表)
     *
     * @param orderid
     * @param phone
     * @param token
     */
    public void getOrderDetail(final String orderid, String phone, String token) {
        MD5_PATH = "id=" + orderid + "&phone=" + phone + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token;
        PATH = HttpPath.PATHS + HttpPath.ORDER_DETAIL + MD5_PATH + "&sign=" +
                MD5Util.getMD5String(MD5_PATH + HttpPath.KEY);
        params = new RequestParams(PATH);
        System.out.println("订单详情 = " + PATH);
        x.http().get(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("订单详情 = " + result);
                        OrderDetail orderDetail = GsonUtil.gsonIntance().gsonToBean(result, OrderDetail.class);

                        if (orderDetail.getStatus() == 1) {
                            goodsList.clear();
                            goodsList.addAll(orderDetail.getData().getGoodslist());
                            orderCommentAdapter.notifyDataSetChanged();
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
     * 评价订单
     *
     * @param orderid
     * @param remark
     * @param phone
     * @param token
     */
    public void setOrderComment(String orderid, String remark, String phone, String token) {
        MD5_PATH = "orderid=" + orderid + "&phone=" + phone + "&remark=" + remark + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token;
        PATH = HttpPath.PATHS + HttpPath.ORDER_COMMENT + MD5_PATH + "&sign=" +
                MD5Util.getMD5String(MD5_PATH + HttpPath.KEY);

        params = new RequestParams(PATH);
        System.out.println("评价订单 = " + PATH);
        x.http().post(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("评价订单 = " + result);
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

    @OnClick(R.id.but_order_comment)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.but_order_comment:
                //提价评价
                JSONObject obj = new JSONObject();
                JSONObject object = new JSONObject();
                try {
                    for (int i = 0; i < goodsList.size(); i++) {
                        obj.put("score", goodsList.get(i).getXing());
                        obj.put("comment", goodsList.get(i).getContent());
                        object.put(goodsList.get(i).getId(), obj);
                    }

                } catch (Exception e) {

                }
                System.out.println("111 = " + object.toString());

                try {
                    String s = URLEncoder.encode(object.toString(), "UTF-8");
                    String ss = Base64.encodeToString(s.getBytes(), Base64.DEFAULT);
                    ss = ss.replaceAll("[\\s*\t\n\r]", "");

                    setOrderComment(orderid, ss, phone, token);

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                break;
            default:
                break;
        }
    }
}
