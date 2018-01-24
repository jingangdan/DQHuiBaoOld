package com.dq.huibao.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
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

import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.R;
import com.dq.huibao.adapter.GoodsAdapter;
import com.dq.huibao.bean.goods.GoodsList;
import com.dq.huibao.refresh.PullToRefreshView;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description：商品列表
 * Created by jingang on 2017/10/25.
 */
public class GoodsListActivity extends Activity implements
        PullToRefreshView.OnFooterRefreshListener,
        PullToRefreshView.OnHeaderRefreshListener {

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

    @Bind(R.id.ptrv_goodslist)
    PullToRefreshView pullToRefreshView;

    private GoodsListActivity TAG = GoodsListActivity.this;
    private GoodsAdapter goodsAdapter;
    private List<GoodsList.DataBean.ListBean> goodsLists = new ArrayList<>();

    /*接收页面传值*/
    private Intent intent;
    private String content = "", catename = "", keywords = "";
    private String all_content = "";
    private String UTF_keywords = "";

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
        content = intent.getStringExtra("content");
        catename = intent.getStringExtra("catename");
        keywords = intent.getStringExtra("keywords");

        try {
            UTF_keywords = URLEncoder.encode(keywords, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        includeSreach.setVisibility(View.VISIBLE);

        lrvGoodslist.setLayoutManager(new GridLayoutManager(this, 2));

        goodsAdapter = new GoodsAdapter(this, goodsLists);
        lrvGoodslist.setAdapter(goodsAdapter);

        getGoodsList(content, UTF_keywords, index);

        goodsAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                intent = new Intent(TAG, GoodsDetailsActivity.class);
                intent.putExtra("gid", goodsLists.get(position).getId());
                startActivity(intent);
            }
        });

        includeTitle.setText("" + catename);

        pullToRefreshView.setOnHeaderRefreshListener(this);
        pullToRefreshView.setOnFooterRefreshListener(this);
        pullToRefreshView.setLastUpdated(new Date().toLocaleString());

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
                intent = new Intent(TAG, KeywordsActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_sales_top:
                //销量从高到低
                setTextColor();
                tvSalesTop.setTextColor(textColor);
                all_content = content + "&sales=asc";

                getGoodsList(all_content, UTF_keywords, index);
                break;
            case R.id.tv_price_low:
                //价格从低到高
                setTextColor();
                tvPriceLow.setTextColor(textColor);
                all_content = content + "&price=asc";
                getGoodsList(all_content, UTF_keywords, index);


                break;
            case R.id.tv_price_top:
                //价格从高到底
                setTextColor();
                tvPriceTop.setTextColor(textColor);

                all_content = content + "&price=desc";

                getGoodsList(all_content, UTF_keywords, index);

                break;
            case R.id.tv_comment_top:
                //评价从高到底
                setTextColor();
                tvCommentTop.setTextColor(textColor);
                all_content = content + "&comment=asc";
                getGoodsList(all_content, UTF_keywords, index);
                break;

            case R.id.but_gl_first:
                //首页
                index = 1;
                getGoodsList(all_content, UTF_keywords, index);

                break;
            case R.id.but_gl_on:
                //上一页
                if (index > 1) {
                    index--;
                    getGoodsList(all_content, UTF_keywords, index);
                } else {
                    Toast.makeText(TAG, "已经是首页了", Toast.LENGTH_SHORT).show();
                    //toast("已经是首页了");
                }
                break;
            case R.id.but_gl_next:
                //下一页
                if (index < total) {
                    index++;
                    getGoodsList(content, UTF_keywords, index);

                } else {
                    Toast.makeText(TAG, "已经是尾页了", Toast.LENGTH_SHORT).show();
                    //toast("已经是尾页了");
                }
                break;
            case R.id.but_gl_last:
                //尾页
                index = total;
                getGoodsList(content, UTF_keywords, index);
                break;
            case R.id.but_gl_jump:
                //跳到指定页
                if (!etGlPage.getText().toString().equals("")) {
                    if (Integer.parseInt(etGlPage.getText().toString()) > 0 && Integer.parseInt(etGlPage.getText().toString()) <= total) {
                        index = Integer.parseInt(etGlPage.getText().toString());
                        getGoodsList(content, UTF_keywords, index);
                    }

                } else {
                    Toast.makeText(TAG, "请输入跳转的页数", Toast.LENGTH_SHORT).show();
                    //toast("请输入跳转的页数");
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
     * 获取商品列表
     *
     * @param content
     * @param keywords
     * @param page
     */
    public void getGoodsList(String content, String keywords, int page) {

        PATH = HttpUtils.PATHS + HttpUtils.GOODS_SEARCH +
                content + "&keywords=" + keywords + "&page=" + page;

        params = new RequestParams(PATH);

        System.out.println("商品列表 = " + PATH);

        x.http().get(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("商品列表 = " + result);
                        gvGoodslist.scrollTo(0, 0);

                        GoodsList goodsList = GsonUtil.gsonIntance().gsonToBean(result, GoodsList.class);

                        goodsLists.clear();
                        total = 0;

                        goodsLists.addAll(goodsList.getData().getList());
                        goodsAdapter.notifyDataSetChanged();

                        total = (Integer.parseInt(goodsList.getData().getCount()) / 20) + 1;

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

    @Override
    public void onFooterRefresh(PullToRefreshView view) {
        pullToRefreshView.postDelayed(new Runnable() {

            @Override
            public void run() {
                //加载更多数据
                pullToRefreshView.onFooterRefreshComplete();

            }

        }, 1000);
    }

    @Override
    public void onHeaderRefresh(PullToRefreshView view) {
        pullToRefreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                //刷新数据
                pullToRefreshView.onHeaderRefreshComplete("更新于:"
                        + Calendar.getInstance().getTime().toLocaleString());
                pullToRefreshView.onHeaderRefreshComplete();

                getGoodsList(content, UTF_keywords, index);

            }

        }, 1000);
    }
}
