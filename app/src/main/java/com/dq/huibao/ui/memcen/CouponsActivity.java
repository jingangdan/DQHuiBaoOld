package com.dq.huibao.ui.memcen;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.dq.huibao.R;
import com.dq.huibao.adapter.SimpleFragmentPagerAdapter;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.fragment.memcen.FMCouponsNoUse;
import com.dq.huibao.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Description：优惠券
 * Created by jingang on 2017/11/1.
 */

public class CouponsActivity extends BaseActivity implements ViewPager.OnPageChangeListener{
    @Bind(R.id.tl_mc_coupons)
    TabLayout tlMcCoupons;
    @Bind(R.id.vp_mc_coupons)
    NoScrollViewPager vpMcCoupons;

    private String[] titles = new String[]{"未使用", "已使用", "已过期"};
    private List<Fragment> fragments = new ArrayList<>();

    private SimpleFragmentPagerAdapter sfpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupons);
        ButterKnife.bind(this);

        fragments.add(FMCouponsNoUse.newInstance("FMCouponsNoUse"));
        fragments.add(FMCouponsNoUse.newInstance("FMCouponsNoUse"));
        fragments.add(FMCouponsNoUse.newInstance("FMCouponsNoUse"));

        sfpAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), this, fragments, titles);
        vpMcCoupons.setAdapter(sfpAdapter);


        vpMcCoupons.setOffscreenPageLimit(titles.length);

        vpMcCoupons.setOnPageChangeListener(this);
        tlMcCoupons.setupWithViewPager(vpMcCoupons);

    }

    @Override
    protected void initWidght() {
        super.initWidght();
        setTitleName("我的优惠券");
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