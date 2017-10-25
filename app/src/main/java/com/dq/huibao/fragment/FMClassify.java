package com.dq.huibao.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.R;
import com.dq.huibao.adapter.ClassifyAdapter;
import com.dq.huibao.adapter.GoodsAdapter;
import com.dq.huibao.base.BaseFragment;
import com.dq.huibao.ui.GoodsListActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Description：分类
 * Created by jingang on 2017/10/18.
 */

public class FMClassify extends BaseFragment {
    @Bind(R.id.rv_c_classify)
    RecyclerView rvCClassify;
    @Bind(R.id.rv_c_goods)
    RecyclerView rvCGoods;

    private View view;

    private LinearLayoutManager mLayoutManager, mLayoutManager1;
    private GridLayoutManager llmv;
    private ClassifyAdapter classifyAdapter;
    private GoodsAdapter goodsAdapter;

    /*跳转页面*/
    private Intent intent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_classify, null);
        ButterKnife.bind(this, view);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager1 = new LinearLayoutManager(getActivity());
        rvCGoods.setLayoutManager(mLayoutManager1);

        classifyAdapter = new ClassifyAdapter(getActivity());
        rvCClassify.setLayoutManager(mLayoutManager);
        rvCClassify.setAdapter(classifyAdapter);

        goodsAdapter = new GoodsAdapter(getActivity());
        llmv = new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false);
        rvCGoods.setLayoutManager(llmv);

        rvCGoods.setAdapter(goodsAdapter);

        goodsAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                intent = new Intent(getActivity(), GoodsListActivity.class);
                startActivity(intent);
            }
        });


        initData();

        return view;
    }

    /*组建初始化*/
    public void initData(){
        classifyAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                classifyAdapter.changeSelected(position);
            }
        });

    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
