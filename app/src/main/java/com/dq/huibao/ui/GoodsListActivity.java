package com.dq.huibao.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.dq.huibao.R;
import com.dq.huibao.adapter.GoodsAdapter;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.bean.classify.GoodsList;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description：商品列表
 * Created by jingang on 2017/10/25.
 */
public class GoodsListActivity extends BaseActivity {

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

    /*选择页数*/
    @Bind(R.id.but_gl_first)
    Button butGlFirst;
    @Bind(R.id.but_gl_on)
    Button butGlOn;
    @Bind(R.id.but_gl_next)
    Button butGlNext;
    @Bind(R.id.but_gl_last)
    Button butGlLast;
    @Bind(R.id.but_gl_jump)
    Button butGlJump;
    @Bind(R.id.et_gl_page)
    EditText etGlPage;

    @Bind(R.id.gv_goodslist)
    ScrollView gvGoodslist;

    private GoodsListActivity TAG = GoodsListActivity.this;
    private GoodsAdapter goodsAdapter;
    private List<GoodsList.DataBean.GoodsBean> goodsList = new ArrayList<>();

    /*接收页面传值*/
    private Intent intent;
    private String pcate = "", ccate = "", name = "";

    /*接口地址*/
    private String PATH = "";
    RequestParams params;
    private int index = 1;//页数
    private int total = 0;//总页数

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodslist);
        ButterKnife.bind(this);

        /*接收页面传值*/
        intent = getIntent();
        pcate = intent.getStringExtra("pcate");
        ccate = intent.getStringExtra("ccate");
        name = intent.getStringExtra("name");

        includeTitle.setText("" + name);
        includeSreach.setVisibility(View.VISIBLE);

        lrvGoodslist.setLayoutManager(new GridLayoutManager(this, 2));

        goodsAdapter = new GoodsAdapter(this, goodsList);
        lrvGoodslist.setAdapter(goodsAdapter);

        getGoodsList(pcate, ccate, "sales", "desc", index, 20);

    }

    @SuppressLint("WrongConstant")
    @OnClick({R.id.include_img, R.id.include_sreach,
            R.id.tv_sales_top, R.id.tv_price_low, R.id.tv_price_top, R.id.tv_comment_top,
            R.id.but_gl_first, R.id.but_gl_on, R.id.but_gl_next, R.id.but_gl_last, R.id.but_gl_jump})

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

                getGoodsList(pcate, ccate, "sales", "desc", index, 20);
                break;
            case R.id.tv_price_low:
                //价格从低到高
                setTextColor();
                tvPriceLow.setTextColor(textColor);
                getGoodsList(pcate, ccate, "marketprice", "asc", index, 20);


                break;
            case R.id.tv_price_top:
                //价格从高到底
                setTextColor();
                tvPriceTop.setTextColor(textColor);

                getGoodsList(pcate, ccate, "marketprice", "desc", index, 20);

                break;
            case R.id.tv_comment_top:
                //评价从高到底
                setTextColor();
                tvCommentTop.setTextColor(textColor);
                getGoodsList(pcate, ccate, "score", "asc", index, 20);
                break;

            case R.id.but_gl_first:
                //首页
                index = 1;
                getGoodsList(pcate, ccate, "score", "asc", index, 20);
                break;
            case R.id.but_gl_on:
                //上一页
                if (index > 1) {
                    index--;
                    getGoodsList(pcate, ccate, "score", "asc", index, 20);
                } else {
                    toast("已经是首页了");
                }
                break;
            case R.id.but_gl_next:
                //下一页
                if (index < total) {
                    index++;
                    getGoodsList(pcate, ccate, "score", "asc", index, 20);
                } else {
                    toast("已经是尾页了");
                }
                break;
            case R.id.but_gl_last:
                //尾页
                index = total;
                getGoodsList(pcate, ccate, "score", "asc", index, 20);
                break;
            case R.id.but_gl_jump:
                //跳到指定页
                if (!etGlPage.getText().toString().equals("")) {
                    if (Integer.parseInt(etGlPage.getText().toString()) > 0 && Integer.parseInt(etGlPage.getText().toString()) <= total) {
                        index = Integer.parseInt(etGlPage.getText().toString());
                        getGoodsList(pcate, ccate, "score", "asc", index, 20);
                    }

                } else {
                    toast("请输入跳转的页数");
                }

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

    /**
     * 商品列表
     *
     * @param pcate 一级分类id
     * @param ccate 二级分类id
     * @param order
     * @param by
     */
    public void getGoodsList(String pcate, String ccate, String order, String by, int page, int pagesize) {
        PATH = HttpUtils.PATH + HttpUtils.SHOP_GOODSLIST +
                "pcate=" + pcate + "&ccate=" + ccate + "&order="
                + order + "&by=" + by + "&page=" + page + "&pagesize=" + pagesize;
        params = new RequestParams(PATH);

        System.out.println("商品列表 = " + PATH);

        x.http().get(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("商品列表 = " + result);
                        gvGoodslist.scrollTo(0, 0);

                        GoodsList goodsLists = GsonUtil.gsonIntance().gsonToBean(result, GoodsList.class);

                        total = Integer.parseInt(goodsLists.getData().getAllpage());

                        goodsList.clear();

                        goodsList.addAll(goodsLists.getData().getGoods());

                        goodsAdapter.notifyDataSetChanged();

                        etGlPage.setText("" + index);

                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {

                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });
    }

}
