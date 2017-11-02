package com.dq.huibao.fragment.memcen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dq.huibao.R;
import com.dq.huibao.base.BaseFragment;

/**
 * Description：
 * Created by jingang on 2017/11/1.
 */

public class FMCouponsNoUse extends BaseFragment{
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fm_order_all, null);
        return view;
    }

    public static FMCouponsNoUse newInstance(String coupons) {
        Bundle bundle = new Bundle();
        bundle.putString("coupons", coupons);
        FMCouponsNoUse fragment = new FMCouponsNoUse();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            System.out.println("in");

        } else {
            System.out.println("move");

        }

    }

    @Override
    protected void lazyLoad() {

    }
}
