package com.dq.huibao.fragment.memcen;

import android.annotation.SuppressLint;
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
import com.dq.huibao.R;
import com.dq.huibao.adapter.memcen.CouponsAdapter;
import com.dq.huibao.base.BaseFragment;
import com.dq.huibao.bean.LoginBean;
import com.dq.huibao.bean.memcen.Coupons;
import com.dq.huibao.ui.memcen.CouponsDetailActivity;
import com.dq.huibao.ui.memcen.CouponsListActivity;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpPath;
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
 * Description：优惠券（已使用）
 * Created by jingang on 2017/11/1.
 */

public class FMCouponsUsed extends BaseFragment {
    @Bind(R.id.lin_coupons_null)
    LinearLayout linCouponsNull;
    @Bind(R.id.but_tablayout)
    Button butTablayout;
    private View view;

    /*接口地址*/
    private String PATH = "";
    private RequestParams params = null;

    /*接收页面传值*/
    private Intent intent;

    /*本地轻量型缓存*/
    private SPUserInfo spUserInfo;
    private String unionid = "";

    @Bind(R.id.rv_order_all)
    RecyclerView rvOrderAll;
    private CouponsAdapter couponsAdapter;
    private List<Coupons.DataBean.ListBean> couponsList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fm_tablayout, null);
        ButterKnife.bind(this, view);

        couponsAdapter = new CouponsAdapter(getActivity(), couponsList);
        rvOrderAll.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvOrderAll.setAdapter(couponsAdapter);

        couponsAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                intent = new Intent(getActivity(), CouponsDetailActivity.class);
                intent.putExtra("couponsid", couponsList.get(position).getCouponid());
                intent.putExtra("index", "0");//0代表用户的优惠券
                startActivity(intent);
            }
        });


        initData();
        return view;
    }

    public static FMCouponsUsed newInstance(String coupons) {
        Bundle bundle = new Bundle();
        bundle.putString("coupons", coupons);
        FMCouponsUsed fragment = new FMCouponsUsed();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            System.out.println("in 2");
            if (!unionid.equals("")) {
                getCoupons(unionid, "1", "", 1);
            }

        } else {
            System.out.println("move 2");

        }

    }

    public void initData() {
        spUserInfo = new SPUserInfo(getActivity().getApplication());

        if (!(spUserInfo.getLoginReturn().equals(""))) {
            LoginBean loginBean = GsonUtil.gsonIntance().gsonToBean(spUserInfo.getLoginReturn(), LoginBean.class);
            unionid = loginBean.getData().getUnionid() + "";
            //getCoupons(unionid, "1", "", 1);
        } else {
            toast("登录状态出错，请重新登录");
        }
    }

    /**
     * 优惠券列表
     *
     * @param unionid
     * @param used    默认空  1已使用
     * @param past    默认空  1已过期
     *                <p>
     *                used 和past 都为空  未使用
     */
    public void getCoupons(String unionid, String used, String past, int page) {
        PATH = HttpPath.PATH + HttpPath.SHOP_MEMBER_COUPON +
                "unionid=" + unionid + "&stamp=" + (System.currentTimeMillis() / 1000) + "&doc=" +
                MD5Util.getMD5String(HttpPath.SHOP_MEMBER_COUPON + "unionid=" + unionid + "&stamp=" + (System.currentTimeMillis() / 1000) + "&dequanhuibaocom") +
                "&used=" + used + "&past=" + past + "&page=" + page;

        params = new RequestParams(PATH);
        System.out.println("优惠券 已使用 = " + PATH);

        x.http().get(params,
                new Callback.CommonCallback<String>() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("优惠券 已使用= " + result);
                        Coupons coupons = GsonUtil.gsonIntance().gsonToBean(result, Coupons.class);

                        if (coupons.getData().getList().size() > 0) {
                            linCouponsNull.setVisibility(View.GONE);
                            couponsList.clear();
                            couponsList.addAll(coupons.getData().getList());
                            couponsAdapter.notifyDataSetChanged();

                        } else {
                            linCouponsNull.setVisibility(View.VISIBLE);

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

    @OnClick(R.id.but_tablayout)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.but_tablayout:
                //领取优惠券
                intent = new Intent(getActivity(), CouponsListActivity.class);
                startActivity(intent);

                break;
            default:
                break;
        }
    }
}
