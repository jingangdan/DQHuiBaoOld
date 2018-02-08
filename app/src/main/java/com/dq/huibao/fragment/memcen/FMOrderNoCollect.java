package com.dq.huibao.fragment.memcen;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.Interface.OrderInterface;
import com.dq.huibao.R;
import com.dq.huibao.adapter.order.OrderAdapter;
import com.dq.huibao.base.BaseFragment;
import com.dq.huibao.bean.addr.AddrReturn;
import com.dq.huibao.bean.order.Order;
import com.dq.huibao.ui.order.OrderDettailActivity;
import com.dq.huibao.ui.order.OrderKuaiDiActivity;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.MD5Util;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Description：我的订单（待收货）
 * Created by jingang on 2017/11/1.
 */

public class FMOrderNoCollect extends BaseFragment implements OrderInterface {
    @Bind(R.id.rv_order_all)
    RecyclerView recyclerView;
    @Bind(R.id.lin_coupons_null)
    LinearLayout linCouponsNull;
    @Bind(R.id.but_tablayout)
    Button butTablayout;
    private View view;

    /*接收页面传值*/
    private Intent intent;

    /*接口地址*/
    private String PATH = "", MD5_PATH = "";
    private RequestParams params = null;

    private List<Order.DataBean> orderList = new ArrayList<>();
    private OrderAdapter orderAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fm_tablayout, null);
        ButterKnife.bind(this, view);
        orderAdapter = new OrderAdapter(getActivity(), orderList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(orderAdapter);
        orderAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                intent = new Intent(getActivity(), OrderDettailActivity.class);
                intent.putExtra("orderid", orderList.get(position).getId());
                intent.putExtra("phone", getArguments().getString("phone"));
                intent.putExtra("token", getArguments().getString("token"));
                startActivity(intent);
            }
        });
        orderAdapter.setOrderInterface(this);
        return view;
    }

    public static FMOrderNoCollect newInstance(String phone, String token) {
        Bundle bundle = new Bundle();
        bundle.putString("phone", phone);
        bundle.putString("token", token);
        FMOrderNoCollect fragment = new FMOrderNoCollect();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            orderGetList("2", getArguments().getString("phone"), getArguments().getString("token"));

        } else {
            System.out.println("move");
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

        PATH = HttpPath.PATHS + HttpPath.ORDER_GETIST + MD5_PATH + "&sign=" +
                MD5Util.getMD5String(MD5_PATH + HttpPath.KEY);
        params = new RequestParams(PATH);
        System.out.println("全部订单列表 = " + PATH);
        x.http().get(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("全部订单列表 = " + result);
                        Order order = GsonUtil.gsonIntance().gsonToBean(result, Order.class);

                        orderList.clear();
                        orderList.addAll(order.getData());

                        orderAdapter.notifyDataSetChanged();

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
     * 订单状态修改
     *
     * @param id
     * @param type 'del删除','close关闭','finish确认收货
     */
    public void orderEdit(String id, String type, String phone, String token) {
        MD5_PATH = "id=" + id + "&phone=" + phone + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token + "&type=" + type;
        PATH = HttpPath.PATHS + HttpPath.ORDER_EDIT + MD5_PATH + "&sign=" +
                MD5Util.getMD5String(MD5_PATH + HttpPath.KEY);

        params = new RequestParams(PATH);
        System.out.println("订单状态修改 = " + PATH);
        x.http().post(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("订单状态修改 = " + result);
                        AddrReturn addrReturn = GsonUtil.gsonIntance().gsonToBean(result, AddrReturn.class);
                        if (addrReturn.getStatus() == 1) {
                            toast("" + addrReturn.getData());

                            orderGetList("2", getArguments().getString("phone"), getArguments().getString("token"));

                        } else {
                            toast("操作失败");
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
    protected void lazyLoad() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void doOrderPay(String ordersn) {

    }

    @Override
    public void doOrderComment(String id, int position) {

    }

    @Override
    public void doOrderKuaidi(String type, String postid) {
        intent = new Intent(getActivity(), OrderKuaiDiActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("postid", postid);
        startActivity(intent);
    }

    @Override
    public void doOrderEdit(String id, String type, int position) {
        dialog(id, type);
    }

    @Override
    public void deRefund(String id, String price, int position) {

    }

    /*弹出框*/
    protected void dialog(final String id, final String type) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("确定操作该订单吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                orderEdit(id, type, getArguments().getString("phone"), getArguments().getString("token"));
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        builder.create().show();
    }


}
