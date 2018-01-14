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
//import com.dq.huibao.bean.classifytest.GoodsList;
import com.dq.huibao.bean.goods.GoodsList;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

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
    //private List<GoodsList.DataBean.GoodsBean> goodsList = new ArrayList<>();
    private List<GoodsList.DataBean.ListBean> goodsLists = new ArrayList<>();

    /*接收页面传值*/
    private Intent intent;
    private String content = "", catename = "", keywords = "";
    private String all_content = "";
    //    private String pcate = "", ccate = "", name = "", keywords = "";
    private String UTF_keywords = "";
//
//    private String cateId = "", cateName = "", context = "";

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
//        pcate = intent.getStringExtra("pcate");
//        ccate = intent.getStringExtra("ccate");
//        name = intent.getStringExtra("name");
//        keywords = intent.getStringExtra("keywords");
//        cateId = intent.getStringExtra("cateid");
//        cateName = intent.getStringExtra("catename");

        try {
            UTF_keywords = URLEncoder.encode(keywords, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        includeSreach.setVisibility(View.VISIBLE);

        lrvGoodslist.setLayoutManager(new GridLayoutManager(this, 2));

        goodsAdapter = new GoodsAdapter(this, goodsLists);
        lrvGoodslist.setAdapter(goodsAdapter);

        //getGoodsListTest(pcate, ccate, "sales", "desc", index, 20, UTF_keywords);

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

//                getGoodsList(cateId, "", UTF_keywords,
//                        "0", "0", "0",
//                        "0", "0", "0",
//                        index, price, sales, comment);
                getGoodsList(all_content, UTF_keywords, index);
                break;
            case R.id.tv_price_low:
                //价格从低到高
                setTextColor();
                tvPriceLow.setTextColor(textColor);
                //getGoodsListTest(pcate, ccate, "marketprice", "asc", index, 20, UTF_keywords);
                all_content = content + "&price=asc";
//                getGoodsList(cateId, "", UTF_keywords,
//                        "0", "0", "0",
//                        "0", "0", "0",
//                        index, price, sales, comment);
                getGoodsList(all_content, UTF_keywords, index);


                break;
            case R.id.tv_price_top:
                //价格从高到底
                setTextColor();
                tvPriceTop.setTextColor(textColor);

                all_content = content + "&price=desc";

                //getGoodsListTest(pcate, ccate, "marketprice", "desc", index, 20, UTF_keywords);
//                getGoodsList(cateId, "", UTF_keywords,
//                        "0", "0", "0",
//                        "0", "0", "0",
//                        index, price, sales, comment);
                getGoodsList(all_content, UTF_keywords, index);

                break;
            case R.id.tv_comment_top:
                //评价从高到底
                setTextColor();
                tvCommentTop.setTextColor(textColor);
                //getGoodsListTest(pcate, ccate, "score", "asc", index, 20, UTF_keywords);
                all_content = content + "&comment=asc";
//                getGoodsList(cateId, "", UTF_keywords,
//                        "0", "0", "0",
//                        "0", "0", "0",
//                        index, price, sales, comment);
                getGoodsList(all_content, UTF_keywords, index);
                break;

            case R.id.but_gl_first:
                //首页
                index = 1;
                //getGoodsListTest(pcate, ccate, "score", "asc", index, 20, UTF_keywords);

//                getGoodsList(cateId, "", UTF_keywords,
//                        "0", "0", "0",
//                        "0", "0", "0",
//                        index, price, sales, comment);
                getGoodsList(all_content, UTF_keywords, index);

                break;
            case R.id.but_gl_on:
                //上一页
                if (index > 1) {
                    index--;
                    //getGoodsListTest(pcate, ccate, "score", "asc", index, 20, UTF_keywords);
//                    getGoodsList(cateId, "", UTF_keywords,
//                            "0", "0", "0",
//                            "0", "0", "0",
//                            index, price, sales, comment);
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
                    //getGoodsListTest(pcate, ccate, "score", "asc", index, 20, UTF_keywords);
//                    getGoodsList(cateId, "", UTF_keywords,
//                            "0", "0", "0",
//                            "0", "0", "0",
//                            index, price, sales, comment);
                    getGoodsList(content, UTF_keywords, index);

                } else {
                    Toast.makeText(TAG, "已经是尾页了", Toast.LENGTH_SHORT).show();
                    //toast("已经是尾页了");
                }
                break;
            case R.id.but_gl_last:
                //尾页
                index = total;
                //getGoodsListTest(pcate, ccate, "score", "asc", index, 20, UTF_keywords);
//                getGoodsList(cateId, "", UTF_keywords,
//                        "0", "0", "0",
//                        "0", "0", "0",
//                        index, price, sales, comment);
                getGoodsList(content, UTF_keywords, index);
                break;
            case R.id.but_gl_jump:
                //跳到指定页
                if (!etGlPage.getText().toString().equals("")) {
                    if (Integer.parseInt(etGlPage.getText().toString()) > 0 && Integer.parseInt(etGlPage.getText().toString()) <= total) {
                        index = Integer.parseInt(etGlPage.getText().toString());
                        //getGoodsListTest(pcate, ccate, "score", "asc", index, 20, UTF_keywords);
//                        getGoodsList(cateId, "", UTF_keywords,
//                                "0", "0", "0",
//                                "0", "0", "0",
//                                index, price, sales, comment);
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
     * 测试商品列表
     *
     * @param pcate
     * @param ccate
     * @param order
     * @param by
     * @param page
     * @param pagesize
     * @param keyword
     */
//    public void getGoodsListTest(String pcate, String ccate, String order, String by, int page, int pagesize, String keyword) {
//        PATH = HttpUtils.PATH + HttpUtils.SHOP_GOODSLIST +
//                pcate + ccate + "&order=" + order + "&by=" + by + "&page=" + page + "&pagesize=" + pagesize + "&keywords=" + keyword;
//        params = new RequestParams(PATH);
//
//        System.out.println("商品列表 = " + PATH);
//
//        x.http().get(params,
//                new Callback.CommonCallback<String>() {
//                    @Override
//                    public void onSuccess(String result) {
//                        System.out.println("商品列表 = " + result);
//                        gvGoodslist.scrollTo(0, 0);
//
//                        GoodsList goodsLists = GsonUtil.gsonIntance().gsonToBean(result, GoodsList.class);
//
//                        total = Integer.parseInt(goodsLists.getData().getAllpage());
//
//                        goodsList.clear();
//
//                        goodsList.addAll(goodsLists.getData().getGoods());
//
//                        goodsAdapter.notifyDataSetChanged();
//
//                        etGlPage.setText("" + index);
//
//                        includeTitle.setText("" + goodsLists.getData().getCurrent_category().getName());
//
//                    }
//
//                    @Override
//                    public void onError(Throwable ex, boolean isOnCallback) {
//
//                    }
//
//                    @Override
//                    public void onCancelled(CancelledException cex) {
//
//                    }
//
//                    @Override
//                    public void onFinished() {
//
//                    }
//                });
//    }

//    /**
//     * 获取商品列表（暂时用搜索滴）
//     *
//     * @param cateid      分类id
//     * @param custom      自定义分类（暂时不用）
//     * @param key         关键词
//     * @param ishot       热销 0/1
//     * @param isrecommand 推荐 0/1
//     * @param isnew       新品 0/1
//     * @param isdiscount  促销 0/1
//     * @param issendfree  包邮 0/1
//     * @param istime      限时0/1
//     * @param page
//     * @param price       价格排序 asc/desc
//     * @param sales       销量排序 asc/desc
//     * @param comment     评价排序 asc/desc
//     */
//    public void getGoodsList(String cateid, String custom, String key,
//                             String ishot, String isrecommand, String isnew,
//                             String isdiscount, String issendfree, String istime,
//                             int page, String price, String sales,
//                             String comment) {
//        PATH = HttpUtils.PATHS + HttpUtils.GOODS_SEARCH +
//                "cate=" + cateid + "&custom=" + custom + "&key=" + key +
//                "&ishot=" + ishot + "&isrecomand=" + isrecommand + "&isnew=" + isnew +
//                "&isdiscount=" + isdiscount + "&issendfree=" + issendfree + "&istime=" + istime +
//                "&page=" + page + "&price=" + price + "&sales=" + sales +
//                "&comment=" + comment;

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

                        goodsLists.addAll(goodsList.getData().getList());
                        goodsAdapter.notifyDataSetChanged();

                        total = (Integer.parseInt(goodsList.getData().getCount()) / 20) + 1;

                        etGlPage.setText("" + index);

//                        GoodsList goodsLists = GsonUtil.gsonIntance().gsonToBean(result, GoodsList.class);
//
//                        total = Integer.parseInt(goodsLists.getData().getAllpage());
//
//                        goodsList.clear();
//
//                        goodsList.addAll(goodsLists.getData().getGoods());
//
//                        goodsAdapter.notifyDataSetChanged();
//
//                        etGlPage.setText("" + index);

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


//    private AutoCompleteTextView autoCompleteTextView;
//    private TextView tv_cancel;
//    private ImageView iv_pop;
//    private RecyclerView rv_pop;
//
//    private String UTF_search = "";
//
//    /**
//     * 搜索
//     */
//    public void setDialog() {
//        pop = new PopupWindow(TAG);
//        view_pop = getLayoutInflater().inflate(R.layout.pop_search, null);
//        view_pop.setAnimation(AnimationUtils.loadAnimation(
//                TAG, R.anim.slide_bottom_to_top));
//        ll_popup = (LinearLayout) view_pop.findViewById(R.id.lin_pop);
//
//        pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
//        pop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
//        pop.setBackgroundDrawable(new BitmapDrawable());
//        pop.setFocusable(true);
//        pop.setOutsideTouchable(true);
//        pop.setContentView(view_pop);
//        pop.showAsDropDown(view_pop);
//
//        //final LinearLayout parent = (LinearLayout) view_pop.findViewById(R.id.lin_pop);
//
//        autoCompleteTextView = (AutoCompleteTextView) view_pop.findViewById(R.id.autoCompleteTextView);
//        iv_pop = (ImageView) view_pop.findViewById(R.id.iv_pop_search);
//        tv_cancel = (TextView) view_pop.findViewById(R.id.tv_pop_cancel);
//
//        rv_pop = (RecyclerView) view_pop.findViewById(R.id.rv_pop);
//        rv_pop.setLayoutManager(new LinearLayoutManager(this));
//
//        //取消
//        tv_cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                pop.dismiss();
//                ll_popup.clearAnimation();
//            }
//        });
//
//        //搜索
//        iv_pop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                try {
//                    UTF_search = URLEncoder.encode(autoCompleteTextView.getText().toString(), "UTF-8");
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
//                getSearch(UTF_search);
//            }
//        });
//
//    }
//
//    /**
//     * 获取搜索数据
//     *
//     * @param keywords
//     */
//    public void getSearch(String keywords) {
//        PATH = HttpUtils.PATH + HttpUtils.SHOP_SEARCH + "keywords=" + keywords;
//
//        params = new RequestParams(PATH);
//        System.out.println("搜索 = " + PATH);
//        x.http().get(params,
//                new Callback.CommonCallback<String>() {
//                    @Override
//                    public void onSuccess(String result) {
//                        System.out.println("搜索 = " + result);
//
//                    }
//
//                    @Override
//                    public void onError(Throwable ex, boolean isOnCallback) {
//
//                    }
//
//                    @Override
//                    public void onCancelled(CancelledException cex) {
//
//                    }
//
//                    @Override
//                    public void onFinished() {
//
//                    }
//                });
//
//    }


}
