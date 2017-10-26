package com.dq.huibao.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dq.huibao.R;
import com.dq.huibao.base.BaseFragment;
import com.dq.huibao.ui.LoginActivity;
import com.dq.huibao.view.RoundImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description：
 * Created by jingang on 2017/10/18.
 */

public class FMMemCen extends BaseFragment {
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

    @OnClick(R.id.riv_memcen)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.riv_memcen:
                intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

}
