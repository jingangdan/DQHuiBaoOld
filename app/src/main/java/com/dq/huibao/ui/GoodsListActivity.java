package com.dq.huibao.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dq.huibao.R;
import com.dq.huibao.adapter.GoodsAdapter;
import com.github.jdsjlzx.recyclerview.LRecyclerView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Description：商品列表
 * Created by jingang on 2017/10/25.
 */

public class GoodsListActivity extends Activity {

    @Bind(R.id.lrv_goodslist)
    RecyclerView lrvGoodslist;
    private GoodsAdapter goodsAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodslist);
        ButterKnife.bind(this);

        lrvGoodslist.setLayoutManager(new GridLayoutManager(this, 2));

        goodsAdapter = new GoodsAdapter(this);
        lrvGoodslist.setAdapter(goodsAdapter);


    }
}
