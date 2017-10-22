package com.dq.huibao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dq.huibao.R;
import com.dq.huibao.base.BaseFragment;

/**
 * Description：小店
 * Created by jingang on 2017/10/20.
 */

public class FMStore extends BaseFragment{
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_store, null);

        return view;
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void onClick(View view) {

    }
}
