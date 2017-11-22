package com.dq.huibao.fragment.memcen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dq.huibao.R;
import com.dq.huibao.base.BaseFragment;

/**
 * Description：我的订单（待发货）
 * Created by jingang on 2017/11/1.
 */

public class FMOrderNoSend extends BaseFragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fm_tablayout, null);
        return view;
    }

    public static FMOrderNoSend newInstance(String order) {
        Bundle bundle = new Bundle();
        bundle.putString("order", order);
        FMOrderNoSend fragment = new FMOrderNoSend();
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
