package com.dq.huibao.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.dq.huibao.R;
import com.dq.huibao.base.BaseFragment;
import com.dq.huibao.ui.LoginActivity;
import com.dq.huibao.ui.memcen.AddressListActivity;
import com.dq.huibao.ui.memcen.CollectActivity;
import com.dq.huibao.ui.memcen.CouponsActivity;
import com.dq.huibao.ui.memcen.FootprintActivity;
import com.dq.huibao.ui.memcen.OrderActivity;
import com.dq.huibao.ui.memcen.ShopcarActivity;
import com.dq.huibao.view.RoundImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description：
 * Created by jingang on 2017/10/18.
 */

public class FMMemCen extends BaseFragment {
    /*收货地址*/
    @Bind(R.id.but_mc_address)
    Button butMcAddress;

    /*我的订单*/
    @Bind(R.id.rel_mc_orders)
    RelativeLayout relMcOrders;

    /*购物车*/
    @Bind(R.id.but_mc_shopcar)
    Button butMcShopcar;

    /*我的收藏*/
    @Bind(R.id.but_mc_collect)
    Button butMcCollect;

    /*我的足迹*/
    @Bind(R.id.but_mc_footprint)
    Button butMcFootprint;

    /*优惠券*/
    @Bind(R.id.but_mc_coupons)
    Button butMcCoupons;

    /*积分商城*/
    @Bind(R.id.but_mc_integral)
    Button butMcIntegral;

    private View view;

    /*用户头像（圆形图片）*/
    @Bind(R.id.riv_memcen)
    RoundImageView rivMemcen;

    /*页面跳转*/
    private Intent intent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_memcen, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.riv_memcen, R.id.but_mc_address, R.id.rel_mc_orders,
            R.id.but_mc_shopcar, R.id.but_mc_collect, R.id.but_mc_footprint, R.id.but_mc_coupons, R.id.but_mc_integral})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.riv_memcen:
                //登录
                intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.but_mc_address:
                //收货地址
                intent = new Intent(getActivity(), AddressListActivity.class);
                startActivity(intent);
                break;

            case R.id.rel_mc_orders:
                //我的订单
                intent = new Intent(getActivity(), OrderActivity.class);
                startActivity(intent);
                break;

            case R.id.but_mc_shopcar:
                //购物车
                intent = new Intent(getActivity(), ShopcarActivity.class);
                startActivity(intent);

                break;
            case R.id.but_mc_collect:
                //我的收藏
                intent = new Intent(getActivity(), CollectActivity.class);
                startActivity(intent);

                break;
            case R.id.but_mc_footprint:
                //我的足迹
                intent = new Intent(getActivity(), FootprintActivity.class);
                startActivity(intent);

                break;

            case R.id.but_mc_coupons:
                //优惠券
                intent = new Intent(getActivity(), CouponsActivity.class);
                startActivity(intent);

                break;

            case R.id.but_mc_integral:
                //积分商城

                break;

            default:
                break;


        }
    }

}
