package com.dq.huibao.ui.memcen;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.dq.huibao.R;
import com.dq.huibao.adapter.SimpleFragmentPagerAdapter;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.fragment.memcen.FMOrderAll;
import com.dq.huibao.fragment.memcen.FMOrderNoCollect;
import com.dq.huibao.fragment.memcen.FMOrderNoPay;
import com.dq.huibao.fragment.memcen.FMOrderNoSend;
import com.dq.huibao.fragment.memcen.FMorderOK;
import com.dq.huibao.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Description：我的订单
 * Created by jingang on 2017/11/1.
 */

public class OrderActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    @Bind(R.id.tl_mc_order)
    TabLayout tlMcOrder;
    @Bind(R.id.vp_mc_order)
    NoScrollViewPager vpMcOrder;

    private String[] titles = new String[]{"全部", "待付款", "待发货", "待收货", "已完成"};
    private List<Fragment> fragments = new ArrayList<>();

    private SimpleFragmentPagerAdapter sfpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);

        fragments.add(FMOrderAll.newInstance("FMOrderAll"));
        fragments.add(FMOrderNoPay.newInstance("FMOrderNoPay"));
        fragments.add(FMOrderNoSend.newInstance("FMOrderNoSend"));

        fragments.add(FMOrderNoCollect.newInstance("FMOrderNoCollect"));
        fragments.add(FMorderOK.newInstance("FMorderOK"));

        sfpAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), this, fragments, titles);
        vpMcOrder.setAdapter(sfpAdapter);


        vpMcOrder.setOffscreenPageLimit(titles.length);

        vpMcOrder.setOnPageChangeListener(this);
        tlMcOrder.setupWithViewPager(vpMcOrder);
    }

    @Override
    protected void initWidght() {
        super.initWidght();
        setTitleName("我的订单");

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}