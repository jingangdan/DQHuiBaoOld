package com.dq.huibao.fragment.memcen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dq.huibao.R;
import com.dq.huibao.adapter.OrderAdapter;
import com.dq.huibao.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description：我的订单（全部）
 * Created by jingang on 2017/11/1.
 */

public class FMOrderAll extends BaseFragment {
    @Bind(R.id.rv_order_all)
    RecyclerView rvOrderAll;
    @Bind(R.id.but_tablayout)

    Button butTablayout;
    private View view;

    private OrderAdapter orderAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fm_tablayout, null);
        ButterKnife.bind(this, view);

        orderAdapter = new OrderAdapter(getActivity());
        rvOrderAll.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvOrderAll.setAdapter(orderAdapter);

        return view;
    }

    public static FMOrderAll newInstance(String order) {
        Bundle bundle = new Bundle();
        bundle.putString("order", order);
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
                butTablayout.setText("随便逛逛");

                break;
            default:
                break;
        }
    }
}
