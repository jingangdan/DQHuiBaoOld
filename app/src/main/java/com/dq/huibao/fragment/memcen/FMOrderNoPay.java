package com.dq.huibao.fragment.memcen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dq.huibao.R;
import com.dq.huibao.base.BaseFragment;

/**
 * Description：我的订单（待付款）
 * Created by jingang on 2017/11/1.
 */

public class FMOrderNoPay extends BaseFragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fm_order_nopay, null);
        return view;
    }

    public static FMOrderNoPay newInstance(String sellerLogin) {
        Bundle bundle = new Bundle();
        bundle.putString("sellerLogin", sellerLogin);
        FMOrderNoPay fragment = new FMOrderNoPay();
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


    @Override
    protected void lazyLoad() {

    }
}
