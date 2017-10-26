package com.dq.huibao.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.dq.huibao.R;
import com.dq.huibao.adapter.GoodsAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description：商品列表
 * Created by jingang on 2017/10/25.
 */

public class GoodsListActivity extends Activity {

    @Bind(R.id.lrv_goodslist)
    RecyclerView lrvGoodslist;

    /*返回上一层*/
    @Bind(R.id.iv_gl_back)
    ImageView ivGlBack;

    /*搜索*/
    @Bind(R.id.iv_gl_sreach)
    ImageView ivGlSreach;

    private GoodsListActivity TAG = GoodsListActivity.this;

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

    @SuppressLint("WrongConstant")
    @OnClick({R.id.iv_gl_back, R.id.iv_gl_sreach})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_gl_back:
                TAG.finish();
                break;
            case R.id.iv_gl_sreach:
                Toast.makeText(this, "搜索", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
