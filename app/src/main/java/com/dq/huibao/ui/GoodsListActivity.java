package com.dq.huibao.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
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

    /*标题*/
    @Bind(R.id.include_img)
    ImageView includeImg;
    @Bind(R.id.include_title)
    TextView includeTitle;
    @Bind(R.id.include_sreach)
    ImageView includeSreach;

    @Bind(R.id.lrv_goodslist)
    RecyclerView lrvGoodslist;

    /*筛选条件*/
    @Bind(R.id.tv_sales_top)
    TextView tvSalesTop;
    @Bind(R.id.tv_price_low)
    TextView tvPriceLow;
    @Bind(R.id.tv_price_top)
    TextView tvPriceTop;
    @Bind(R.id.tv_comment_top)
    TextView tvCommentTop;

    private GoodsListActivity TAG = GoodsListActivity.this;
    private GoodsAdapter goodsAdapter;

    /*接收页面传值*/
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodslist);
        ButterKnife.bind(this);

        includeTitle.setText("分类名称");

        lrvGoodslist.setLayoutManager(new GridLayoutManager(this, 2));
        goodsAdapter = new GoodsAdapter(this);
        lrvGoodslist.setAdapter(goodsAdapter);


    }

    @SuppressLint("WrongConstant")
    @OnClick({R.id.include_img, R.id.include_sreach,
            R.id.tv_sales_top, R.id.tv_price_low, R.id.tv_price_top, R.id.tv_comment_top})
    public void onClick(View view) {
        int textColor = getResources().getColor(R.color.tv_color001);
        switch (view.getId()) {
            case R.id.include_img:
                TAG.finish();
                break;
            case R.id.include_sreach:
                Toast.makeText(this, "搜索", Toast.LENGTH_LONG).show();
                break;
            case R.id.tv_sales_top:
                //销量从高到低
                setTextColor();
                tvSalesTop.setTextColor(textColor);
                break;
            case R.id.tv_price_low:
                //价格从低到高
                setTextColor();
                tvPriceLow.setTextColor(textColor);

                break;
            case R.id.tv_price_top:
                //价格从高到底
                setTextColor();
                tvPriceTop.setTextColor(textColor);

                break;
            case R.id.tv_comment_top:
                //评价从高到底
                setTextColor();
                tvCommentTop.setTextColor(textColor);
                break;

        }
    }

    /*还原字体颜色*/
    public void setTextColor() {
        int textColor = getResources().getColor(R.color.tv_color002);
        tvSalesTop.setTextColor(textColor);
        tvPriceLow.setTextColor(textColor);
        tvPriceTop.setTextColor(textColor);
        tvCommentTop.setTextColor(textColor);

    }
}
