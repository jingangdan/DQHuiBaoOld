package com.dq.huibao.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.R;
import com.dq.huibao.adapter.AddressListAdapter;
import com.dq.huibao.adapter.gd.ChooseAdapter;
import com.dq.huibao.adapter.gd.ChooseTwoAdapter;
import com.dq.huibao.adapter.gd.GdCommentAdapter;
import com.dq.huibao.adapter.gd.GdParmasAdapter;
import com.dq.huibao.bean.LoginBean;
import com.dq.huibao.bean.account.Login;
import com.dq.huibao.bean.addr.AddrReturn;
import com.dq.huibao.bean.cart.Cart;
import com.dq.huibao.bean.goods.GoodsDetail;
//import com.dq.huibao.bean.goodsdetail.Comment;
//import com.dq.huibao.bean.goodsdetail.GoodsDetail;
//import com.dq.huibao.bean.goodsdetail.Items;
//import com.dq.huibao.bean.goodsdetail.Options;
//import com.dq.huibao.bean.goodsdetail.Params;
//import com.dq.huibao.bean.goodsdetail.Specs;
import com.dq.huibao.lunbotu.ADInfo;
import com.dq.huibao.lunbotu.CycleViewPager;
import com.dq.huibao.lunbotu.ViewFactory;
import com.dq.huibao.ui.memcen.ShopcarActivity;
import com.dq.huibao.utils.BaseRecyclerViewHolder;
import com.dq.huibao.utils.CodeUtils;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpUtils;
import com.dq.huibao.utils.ImageUtils;
import com.dq.huibao.utils.MD5Util;
import com.dq.huibao.utils.SPUserInfo;
import com.dq.huibao.view.HackyViewPager;
import com.dq.huibao.view.goodsdetails_foot.GradationScrollView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description：商品详情
 * Created by jingang on 2017/10/24.
 */
public class GoodsDetailsActivity extends Activity implements GradationScrollView.ScrollViewListener {
    private GoodsDetailsActivity TAG = GoodsDetailsActivity.this;

    /*返回上层*/
    @Bind(R.id.iv_gd_back)
    ImageView ivGdBack;

    /*标题*/
    @Bind(R.id.tv_gd_title)
    TextView tvGdTitle;

    /*商品名称*/
    @Bind(R.id.tv_gd_name)
    TextView tvGdName;

    /*商品价格*/
    @Bind(R.id.tv_gd_marketprice)
    TextView tvGdMarketprice;

    /*商品库存*/
    @Bind(R.id.tv_gd_total)
    TextView tvGdTotal;

    /*选择商品规格和数量*/
    @Bind(R.id.rel_gd_choose)
    RelativeLayout relGdChoose;

    /*显示商品规格和数量*/
    @Bind(R.id.tv_gd_specification)
    TextView tvGdSpecification;

    CycleViewPager cycleViewPager;

    /*我要分销*/
    @Bind(R.id.lin_gd_distribution)
    LinearLayout linGdDistribution;

    /*加入购物车*/
    @Bind(R.id.but_gd_put_in)
    Button but_gd_put_in;

    /*立即购买*/
    @Bind(R.id.but_gd_bug_new)
    Button but_gd_bug_new;

    /*收藏*/
    @Bind(R.id.lin_gd_collection)
    LinearLayout linGdCollection;
    @Bind(R.id.iv_gd_collection)
    ImageView ivGdCollection;
    @Bind(R.id.tv_gd_collection)
    TextView tvGdCollection;

    /*客服*/
    @Bind(R.id.lin_gd_serice)
    LinearLayout linGdSerice;

    /*购物车*/
    @Bind(R.id.rel_gd_shopcar)
    RelativeLayout relGdShopcar;
    @Bind(R.id.tv_gd_shopcar_num)
    TextView tvShopcarNum;

    /*选择数量和颜色时背景变暗*/
    @Bind(R.id.lin_all_choice)
    LinearLayout all_choice_layout;

    /*查看全部商品*/
    @Bind(R.id.tv_gd_allgoods)
    TextView tvGdAllgoods;

    /*进入我的小店*/
    @Bind(R.id.tv_gd_store)
    TextView tvGdStore;

    /**/
    @Bind(R.id.wv_goodsdetail)
    WebView webView;
    private WebSettings webSettings;

    /*图文详情 产品参数 用户评价 同店推荐*/
    @Bind(R.id.tv_gd_content)
    TextView tvGdContent;
    @Bind(R.id.tv_gd_params)
    TextView tvGdParams;
    @Bind(R.id.tv_gd_comment)
    TextView tvGdComment;
    @Bind(R.id.tv_gd_recommend)
    TextView tvGdRecommend;
    @Bind(R.id.v_gd_content)
    View vGdContent;
    @Bind(R.id.v_gd_params)
    View vGdParams;
    @Bind(R.id.v_gd_comment)
    View vGdComment;
    @Bind(R.id.v_gd_recommend)
    View vGdRecommend;
    @Bind(R.id.lin_gd_content)
    LinearLayout linGdContent;
    @Bind(R.id.rv_gd_params)
    RecyclerView rvGdParams;
    @Bind(R.id.lin_gd_params)
    LinearLayout linGdParams;
    @Bind(R.id.rv_gd_comment)
    RecyclerView rvGdComment;
    @Bind(R.id.lin_gd_comment)
    LinearLayout linGdComment;
    @Bind(R.id.rv_gd_recommend)
    RecyclerView rvGdRecommend;
    @Bind(R.id.lin_gd_recommend)
    LinearLayout linGdRecommend;

    /**
     * 是否添加收藏
     */
    private static boolean isCollection = false;

    @Bind(R.id.scrollview)
    GradationScrollView scrollView;


    /*接收页面传值*/
    private Intent intent;
    private String gid = "";

    /*接口地址*/
    private String PATH = "";
    private String MD5_PATH = "";
    private RequestParams params;

    /*图片*/
    public static List<String> picsList = new ArrayList<>();

    /*UI赋值*/
    private String title = "", marketprice = "", total = "", sales = "";

    /*图文详情*/
    private String content = "";
    /*产品参数*/
    private GdParmasAdapter gdParmasAdapter;
    /*用户评价*/
    private List<GoodsDetail.DataBean.CommentBean> commentList = new ArrayList<>();
    private GdCommentAdapter gdCommentAdapter;

    /*本地轻量型缓存*/
    private SPUserInfo spUserInfo;
    private String token = "", phone = "";

    private GoodsDetail goodsDetail;
    /*选择规格*/
    private List<GoodsDetail.DataBean.SpecBean> specsList = new ArrayList<>();
    /*上传规格*/
    private List<GoodsDetail.DataBean.OptionBean> optionsList = new ArrayList<>();
    /*规格item1*/
    private List<GoodsDetail.DataBean.SpecBean.ItemsBean> itemList = new ArrayList<>();
    /*规格item2*/
    private List<GoodsDetail.DataBean.SpecBean.ItemsBean> itemLists = new ArrayList<>();
    /*参数*/
    private List<GoodsDetail.DataBean.ParamBean> paramsList = new ArrayList<>();

    /*购物车数据*/
    private Cart cart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodsdetails);
        ButterKnife.bind(this);

        gdCommentAdapter = new GdCommentAdapter(TAG, commentList);
        rvGdComment.setLayoutManager(new LinearLayoutManager(TAG));
        rvGdComment.setAdapter(gdCommentAdapter);

        gdParmasAdapter = new GdParmasAdapter(TAG, paramsList);
        rvGdParams.setLayoutManager(new LinearLayoutManager(TAG));
        rvGdParams.setAdapter(gdParmasAdapter);

        intent = getIntent();
        gid = intent.getStringExtra("gid");

        spUserInfo = new SPUserInfo(getApplication());

        initDate();

    }

    @SuppressLint({"WrongConstant", "ResourceAsColor"})
    @OnClick({R.id.rel_gd_choose,
            R.id.tv_gd_allgoods, R.id.tv_gd_store,
            R.id.but_gd_put_in, R.id.but_gd_bug_new, R.id.iv_gd_back, R.id.rel_gd_shopcar, R.id.lin_gd_serice,
            R.id.lin_gd_collection, R.id.lin_gd_distribution,
            R.id.tv_gd_content, R.id.tv_gd_params, R.id.tv_gd_comment, R.id.tv_gd_recommend})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_gd_back:
                //返回上层
                TAG.finish();
                break;

            case R.id.lin_gd_distribution:
                //我要分销
                Toast.makeText(TAG, "我要分销", Toast.LENGTH_SHORT).show();
                break;

            case R.id.rel_gd_choose:
                //选择商品规格和数量
                setPopTest(0);
                setBackgroundBlack(all_choice_layout, 0);
                break;

            case R.id.tv_gd_allgoods:
                //查看全部商品
                intent = new Intent(TAG, GoodsListActivity.class);
                intent.putExtra("content", "cate=");
                intent.putExtra("catename", "所有商品");
                intent.putExtra("keywords", "");
                startActivity(intent);

                break;

            case R.id.tv_gd_store:
                //进店逛逛（进入我的小店）
                Toast.makeText(TAG, "进入我的小店", Toast.LENGTH_SHORT).show();
                break;
            case R.id.lin_gd_collection:
                //收藏
                if (isLogin()) {
                    if (isCollection) {
                        //提示是否取消收藏
                        cancelCollection();
                    } else {
                        setAddRecord("collect", gid, phone, token);
                    }
                } else {
                    dialog();
                }

                break;

            case R.id.lin_gd_serice:
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "0539-7290757"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;

            case R.id.rel_gd_shopcar:
                //购物车
                if (isLogin()) {
                    intent = new Intent(TAG, ShopcarActivity.class);
                    startActivity(intent);
                } else {
                    dialog();
                }


                break;

            case R.id.but_gd_put_in:
                //添加购物车
                if (isLogin()) {
                    setPopTest(1);
                    setBackgroundBlack(all_choice_layout, 0);

                } else {
                    dialog();
                }

                break;
            case R.id.but_gd_bug_new:
                //立即购买
                if (isLogin()) {
                    if (optionid.equals("")) {
                        setPopTest(2);
                        setBackgroundBlack(all_choice_layout, 0);
                    } else {
                        intent = new Intent(TAG, SubmitOrderActivity.class);
                        intent.putExtra("goodsid", gid);
                        intent.putExtra("tag", "1");
                        intent.putExtra("count", num + "");
                        intent.putExtra("optioned", optionid);
                        startActivity(intent);
                    }

                } else {
                    dialog();
                }


                break;

            case R.id.tv_gd_content:
                //图文推荐
                setTabChoose();
                tvGdContent.setTextColor(Color.rgb(241, 83, 83));
                vGdContent.setVisibility(View.VISIBLE);
                linGdContent.setVisibility(View.VISIBLE);

                getWebHTML(content);

                break;

            case R.id.tv_gd_params:
                //产品参数
                setTabChoose();

                tvGdParams.setTextColor(Color.rgb(241, 83, 83));
                vGdParams.setVisibility(View.VISIBLE);
                linGdParams.setVisibility(View.VISIBLE);

                paramsList.clear();
                paramsList.addAll(goodsDetail.getData().getParam());
                gdParmasAdapter.notifyDataSetChanged();


                break;

            case R.id.tv_gd_comment:
                //用户评价
                setTabChoose();
                tvGdComment.setTextColor(Color.rgb(241, 83, 83));
                vGdComment.setVisibility(View.VISIBLE);
                linGdComment.setVisibility(View.VISIBLE);

                commentList.clear();
                commentList.addAll(goodsDetail.getData().getComment());
                gdCommentAdapter.notifyDataSetChanged();

                break;

            case R.id.tv_gd_recommend:
                //同店推荐
                setTabChoose();
                tvGdRecommend.setTextColor(Color.rgb(241, 83, 83));
                vGdRecommend.setVisibility(View.VISIBLE);
                linGdRecommend.setVisibility(View.VISIBLE);
                break;
        }
    }

    /*
    * 获取用户登录信息
    * */
    @SuppressLint("WrongConstant")
    public void initDate() {
        if (!(spUserInfo.getLoginReturn().equals(""))) {
            Login login = GsonUtil.gsonIntance().gsonToBean(spUserInfo.getLoginReturn(), Login.class);
            phone = login.getData().getPhone();
            token = login.getData().getToken();
            getGoodsDetail(gid, token, phone);

        } else {
            //toast("登录状态出错，请重新登录");
            getGoodsDetail(gid, token, phone);
        }

    }

    /*判断登录状态*/
    public boolean isLogin() {
        if (spUserInfo.getLogin().equals("1")) {
            return true;
        } else if (spUserInfo.getLogin().equals("")) {
            return false;
        }
        return false;
    }

    /**
     * 获取商品详情
     *
     * @param id
     * @param token （不用加密，没登陆就不传）
     * @param phone （不用加密，没登陆就不传）
     */
    public void getGoodsDetail(String id, String token, String phone) {
        PATH = HttpUtils.PATHS + HttpUtils.GOODS_DETAIL +
                "id=" + id + "&token=" + token + "&phone=" + phone;
        params = new RequestParams(PATH);
        System.out.println("商品详情 = " + PATH);
        x.http().get(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("商品详情 = " + result);
                        goodsDetail = GsonUtil.gsonIntance().gsonToBean(result, GoodsDetail.class);

                        picsList.clear();
                        specsList.clear();
                        optionsList.clear();
                        commentList.clear();


                        picsList = goodsDetail.getData().getThumb_url();

                        if (!goodsDetail.getData().getSpec().toString().equals("[]")) {
                            specsList = goodsDetail.getData().getSpec();
                        }
                        if (!goodsDetail.getData().getOption().toString().equals("[]")) {
                            optionsList = goodsDetail.getData().getOption();
                        }


                        title = goodsDetail.getData().getGoodsname();
                        marketprice = "" + goodsDetail.getData().getMarketprice();
                        total = "" + goodsDetail.getData().getStock();
                        sales = "" + goodsDetail.getData().getSales();
                        content = goodsDetail.getData().getContent();
                        isCollection = goodsDetail.getData().getCollect();

                        tvGdTitle.setText("" + goodsDetail.getData().getGoodsname());

                        setLunbotu();

                        initData();

                        getWebHTML(content);

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

    /**
     * 添加收藏
     *
     * @param type  收藏类型--- collect收藏商品   collect_shop收藏店铺（暂无）
     * @param id    收藏的商品id 或者店铺id
     * @param phone
     * @param token
     */
    public void setAddRecord(String type, String id, String phone, String token) {
        MD5_PATH = "id=" + id + "&phone=" + phone + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token + "&type=" + type;
        PATH = HttpUtils.PATHS + HttpUtils.MEM_ADDRECORD + MD5_PATH + "&sign=" +
                MD5Util.getMD5String(MD5_PATH + HttpUtils.KEY);
        params = new RequestParams(PATH);
        System.out.println("添加收藏 = " + PATH);
        x.http().post(params,
                new Callback.CommonCallback<String>() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("添加收藏 = " + result);
                        AddrReturn addrReturn = GsonUtil.gsonIntance().gsonToBean(result, AddrReturn.class);
                        if (addrReturn.getStatus() == 1) {
                            Toast.makeText(TAG, "" + addrReturn.getData(), Toast.LENGTH_SHORT).show();
                            isCollection = true;
                            ivGdCollection.setImageResource(R.mipmap.ic_collection002);
                            tvGdCollection.setText("已收藏");
                        }
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

    /**
     * 取消收藏
     *
     * @param type  收藏类型--- collect收藏商品   collect_shop收藏店铺（暂无）
     * @param id    收藏的商品id 或者店铺id
     * @param phone
     * @param token
     */
    public void setDelRecord(String type, String id, String phone, String token) {
        MD5_PATH = "id=" + id + "&phone=" + phone + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token + "&type=" + type;
        PATH = HttpUtils.PATHS + HttpUtils.MEM_DELRECORD + MD5_PATH + "&sign=" +
                MD5Util.getMD5String(MD5_PATH + HttpUtils.KEY);
        params = new RequestParams(PATH);
        System.out.println("取消收藏 = " + PATH);
        x.http().post(params,
                new Callback.CommonCallback<String>() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("取消收藏 = " + result);
                        AddrReturn addrReturn = GsonUtil.gsonIntance().gsonToBean(result, AddrReturn.class);
                        if (addrReturn.getStatus() == 1) {
                            Toast.makeText(TAG, "" + addrReturn.getData(), Toast.LENGTH_SHORT).show();
                            isCollection = false;
                            ivGdCollection.setImageResource(R.mipmap.ic_collection001);
                            tvGdCollection.setText("收藏");
                        }
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


    /*组件赋值*/
    public void initData() {
        tvGdTitle.setText("" + title);
        tvGdName.setText("" + title);
        tvGdMarketprice.setText("¥ " + marketprice);
        tvGdTotal.setText("库存：" + total + " 销量：" + sales);

        if (isCollection) {
            ivGdCollection.setImageResource(R.mipmap.ic_collection002);
            tvGdCollection.setText("已收藏");
        } else {
            ivGdCollection.setImageResource(R.mipmap.ic_collection001);
            tvGdCollection.setText("收藏");
        }
    }

    private List<ImageView> views = new ArrayList<ImageView>();
    private List<ADInfo> infos;
    private ADInfo info;

    /**
     * 设置轮播图
     */
    public void setLunbotu() {
        cycleViewPager = (CycleViewPager) getFragmentManager().findFragmentById(R.id.cycleviewpager);

        infos = new ArrayList<>();
        for (int i = 0; i < picsList.size(); i++) {
            info = new ADInfo();
            info.setUrl(HttpUtils.IMG_HEADER + picsList.get(i).toString());
            info.setContent("");
            info.setImg("");
            infos.add(info);
        }

        // 将最后一个ImageView添加进来
        views.add(ViewFactory.getImageView(TAG, infos.get(infos.size() - 1).getUrl()));
        for (int i = 0; i < infos.size(); i++) {
            views.add(ViewFactory.getImageView(TAG, infos.get(i).getUrl()));
        }
        // 将第一个ImageView添加进来
        views.add(ViewFactory.getImageView(TAG, infos.get(0).getUrl()));

        // 设置循环，在调用setData方法前调用
        cycleViewPager.setCycle(true);

        // 在加载数据前设置是否循环
        cycleViewPager.setData(views, infos, mAdCycleViewListener);
        //设置轮播
        cycleViewPager.setWheel(true);

        // 设置轮播时间，默认5000ms
        cycleViewPager.setTime(3000);
        //设置圆点指示图标组居中显示，默认靠右
        cycleViewPager.setIndicatorCenter();
    }

    /*轮播图点击事件*/
    private CycleViewPager.ImageCycleViewListener mAdCycleViewListener =
            new CycleViewPager.ImageCycleViewListener() {

                @SuppressLint("WrongConstant")
                @Override
                public void onImageClick(ADInfo info, int position, View imageView) {
                    int index = position - 1;
                    if (cycleViewPager.isCycle()) {
                        intent = new Intent(TAG, ShowBigPictrueActivity.class);
                        intent.putExtra("position", index);
                        intent.putExtra("picslist", picsList.toString());
                        startActivity(intent);

                    }

                }

            };


    /*popupwindows*/
    private PopupWindow popWindow;
    private View view;
    private ImageView iv_cancel, iv_thumb;
    private LinearLayout linGdMain, linAdd, linSub;
    private TextView tv_marketprice, tv_total, tv_specification, tv_num, tv_ok;


    /*动态添加UI*/
    private RecyclerView recyclerView;

    private SpecAdapter specAdapter;

    /*数量*/
    private int num = 1;

    /**
     * 选择商品规格和数量
     */
    @SuppressLint("WrongConstant")
    public void setPopTest(final int tag) {

        num = 1;
        view = View.inflate(this, R.layout.pop_gd_choose,
                null);
        // 最后一个参数false 代表：不与其余布局发生交互， true代表：可以与其余布局发生交互事件
        popWindow = new PopupWindow(view,
                ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                false) {

            // 重写popupWindow消失时事件
            @Override
            public void dismiss() {
                super.dismiss();
                setBackgroundBlack(all_choice_layout, 1);

            }
        };
        // 设置Pop入场动画效果
        popWindow.setAnimationStyle(R.style.pop_style);
        // 设置Pop响应内部区域焦点
        popWindow.setFocusable(true);
        // 设置Pop响应外部区域焦点
        popWindow.setOutsideTouchable(true);
        // 设置PopupWindow弹出软键盘模式（此处为覆盖式）
        popWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        // 响应返回键必须的语句
        popWindow.setBackgroundDrawable(new BitmapDrawable());
        // 依附的父布局自己设定，我这里为了方便，这样写的。
        popWindow.showAtLocation(all_choice_layout, Gravity.BOTTOM, 0, 0);

        linGdMain = (LinearLayout) view.findViewById(R.id.lin_pop_gd_main);

        /*图片 取消*/
        iv_cancel = (ImageView) view.findViewById(R.id.iv_pop_gd_back);
        iv_thumb = (ImageView) view.findViewById(R.id.iv_pop_gd_thumb);

        recyclerView = view.findViewById(R.id.rv_goods_spec);
        specAdapter = new SpecAdapter(this, specsList);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(specAdapter);


        Glide.with(TAG)
                .load(HttpUtils.IMG_HEADER + goodsDetail.getData().getThumb())
                .placeholder(R.mipmap.icon_empty002)
                .error(R.mipmap.icon_error002)
                .into(iv_thumb);

        /*价格 库存 规格 数量 确定*/
        tv_marketprice = (TextView) view.findViewById(R.id.tv_pop_gd_marketprice);
        tv_total = (TextView) view.findViewById(R.id.tv_pop_gd_total);
        tv_specification = (TextView) view.findViewById(R.id.tv_pop_gd_specification);
        tv_num = (TextView) view.findViewById(R.id.tv_pop_gd_num);
        tv_ok = (TextView) view.findViewById(R.id.tv_pop_gd_ok);

        /*加减*/
        linAdd = (LinearLayout) view.findViewById(R.id.lin_pop_gd_add);
        linSub = (LinearLayout) view.findViewById(R.id.lin_pop_gd_sub);

        tv_marketprice.setText("￥" + goodsDetail.getData().getMarketprice());
        tv_total.setText("库存：" + goodsDetail.getData().getStock());

        /*取消*/
        iv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popWindow.dismiss();
            }
        });

        /*增加*/
        linAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num = Integer.parseInt(tv_num.getText().toString());
                num++;
                tv_num.setText("" + num);
            }
        });

        /*减少*/
        linSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num = Integer.parseInt(tv_num.getText().toString());
                if (num > 1) {
                    num--;
                    tv_num.setText("" + num);
                } else {
                    Toast.makeText(TAG, "数量需大于0", Toast.LENGTH_SHORT).show();
                }
            }
        });


        /*确定*/
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tag == 0) {
                    //选择规格
                    tvGdSpecification.setText("已选：" + string_name);
                    popWindow.dismiss();
                } else if (tag == 1) {
                    //添加购物车
                    cartAdd(phone, token, gid, optionid, num);

                } else if (tag == 2) {
                    //立即购买
                    if (!optionid.equals("")) {
                        intent = new Intent(TAG, SubmitOrderActivity.class);
                        intent.putExtra("goodsid", gid);
                        intent.putExtra("tag", "1");
                        intent.putExtra("count", num + "");
                        intent.putExtra("optioned", optionid);
                        startActivity(intent);
                    } else {
                        Toast.makeText(TAG, "未选规格", Toast.LENGTH_SHORT).show();
                    }

                }
                popWindow.dismiss();


            }
        });

    }

    /**
     * 添加购物车
     *
     * @param phone
     * @param token
     * @param gid
     * @param optionid
     * @param count
     */
    public void cartAdd(String phone, String token, final String gid, String optionid, int count) {
        MD5_PATH = "count=" + count + "&goodsid=" + gid + "&optionid=" + optionid + "&phone=" + phone + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token;

        PATH = HttpUtils.PATHS + HttpUtils.CART_ADD + MD5_PATH + "&sign=" +
                MD5Util.getMD5String(MD5_PATH + "&key=ivKDDIZHF2b0Gjgvv2QpdzfCmhOpya5k");


        params = new RequestParams(PATH);
        System.out.println("添加购物车 = " + PATH);
        x.http().post(params,
                new Callback.CommonCallback<String>() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("添加购物车 = " + result);
                        cart = GsonUtil.gsonIntance().gsonToBean(result, Cart.class);
                        if (cart.getData().size() > 0) {
                            Toast.makeText(TAG, "添加成功", Toast.LENGTH_SHORT).show();

                            for (int i = 0; i < cart.getData().size(); i++) {
                                for (int j = 0; j < cart.getData().get(i).getGoodslist().size(); j++) {
                                    if (gid.equals(cart.getData().get(i).getGoodslist().get(j).getGoodsid())) {
                                        tvShopcarNum.setText("" + cart.getData().get(i).getGoodslist().get(j).getCount());
                                    }
                                }

                            }
                            popWindow.dismiss();

                        } else {
                            Toast.makeText(TAG, "添加失败", Toast.LENGTH_SHORT).show();
                        }


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

    /**
     * 加载商品图文详情（html）
     *
     * @param html_bady
     */
    @SuppressLint("WrongConstant")
    public void getWebHTML(String html_bady) {
        webView.getSettings().setJavaScriptEnabled(true);
        webSettings = webView.getSettings();
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptEnabled(false);

        String head = "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
                "<style>img{max-width: 100%; width:auto; height:auto;}</style>" +
                "</head>";
        String html = "<html>" + head + "<body>" + html_bady + "</body></html>";

        webView.loadDataWithBaseURL(HttpUtils.HEADER, html, "text/html", "utf-8", null);

    }

    /**
     * 设置文字样式
     */
    @SuppressLint({"ResourceAsColor", "WrongConstant"})
    public void setTabChoose() {
        tvGdContent.setTextColor(Color.rgb(102, 102, 102));
        tvGdParams.setTextColor(Color.rgb(102, 102, 102));
        tvGdComment.setTextColor(Color.rgb(102, 102, 102));
        tvGdRecommend.setTextColor(Color.rgb(102, 102, 102));

        vGdContent.setVisibility(View.INVISIBLE);
        vGdParams.setVisibility(View.INVISIBLE);
        vGdComment.setVisibility(View.INVISIBLE);
        vGdRecommend.setVisibility(View.INVISIBLE);

        linGdContent.setVisibility(View.GONE);
        linGdParams.setVisibility(View.GONE);
        linGdComment.setVisibility(View.GONE);
        linGdRecommend.setVisibility(View.GONE);
    }

    @Override
    public void onScrollChanged(GradationScrollView scrollView, int x, int y, int oldx, int oldy) {

    }

    /**
     * 控制背景变暗 0变暗 1变亮
     */
    @SuppressLint("WrongConstant")
    public void setBackgroundBlack(View view, int what) {
        switch (what) {
            case 0:
                all_choice_layout.setVisibility(View.VISIBLE);
                break;
            case 1:
                all_choice_layout.setVisibility(View.GONE);
                break;
        }
    }

    /**
     * 取消收藏
     */
    private void cancelCollection() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("是否取消收藏");
        dialog.setMessage("提示：");
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
//                //如果取消收藏，则显示取消收藏后的效果
                setDelRecord("collect", gid, phone, token);
//
//                tvGdCollection.setText("收藏");
            }
        });
        dialog.setNegativeButton("取消", null);
        dialog.create().show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CookieSyncManager.createInstance(this);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
        CookieSyncManager.getInstance().sync();

        webView.setWebChromeClient(null);
        webView.setWebViewClient(null);
        webView.getSettings().setJavaScriptEnabled(false);
        webView.clearCache(true);

    }

    /*弹出框*/
    protected void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确定登录？");
        builder.setTitle("提示：未登录");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                intent = new Intent(TAG, LoginActivity.class);
                startActivityForResult(intent, CodeUtils.GDTAILD);


            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        builder.create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CodeUtils.GDTAILD) {
            if (resultCode == CodeUtils.LOGIN) {
                initDate();
            }
        }
    }

    String[] strings;
    String string = "";
    String string_name = "";
    String price = "", stock = "";
    String optionid = "";

    public class SpecAdapter extends RecyclerView.Adapter<SpecAdapter.MyViewHolder> {
        private Context mContext;
        private List<GoodsDetail.DataBean.SpecBean> specBeanList;
        private OnItemClickListener onItemClickListener;

        public SpecAdapter(Context mContext, List<GoodsDetail.DataBean.SpecBean> specBeanList) {
            this.mContext = mContext;
            this.specBeanList = specBeanList;
        }

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
            MyViewHolder vh = new MyViewHolder(
                    LayoutInflater.from(mContext).inflate(R.layout.item_spec, parent, false));
            return vh;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int i) {
            if (onItemClickListener != null) {
                //
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position = holder.getLayoutPosition(); // 1
                        onItemClickListener.onItemClick(holder.itemView, position); // 2
                    }
                });

            }
            holder.title.setText("" + specBeanList.get(i).getTitle());

            final ChooseAdapter chooseAdapter = new ChooseAdapter(mContext, specBeanList.get(i).getItems());
            holder.recyclerView.setLayoutManager(new GridLayoutManager(mContext, 1, GridLayoutManager.HORIZONTAL, false));
            holder.recyclerView.setAdapter(chooseAdapter);

            strings = new String[specBeanList.size()];

            chooseAdapter.setmOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    chooseAdapter.changeSelected(position);

                    if (specBeanList.get(i).getItems().get(position).getThumb().equals("")) {

                    } else {
                        Glide.with(mContext)
                                .load(HttpUtils.IMG_HEADER + specBeanList.get(i).getItems().get(position).getThumb())
                                .placeholder(R.mipmap.icon_empty002)
                                .error(R.mipmap.icon_error002)
                                .into(iv_thumb);
                    }

                    strings[i] = specBeanList.get(i).getItems().get(position).getId();

                    for (int j = 0; j < strings.length; j++) {
                        if (j == 0) {
                            string = strings[j];
                        } else {
                            string = strings[j] + "_" + string;
                        }
                    }

                    for (int k = 0; k < optionsList.size(); k++) {
                        if (string.equals(optionsList.get(k).getSpecs())) {
                            optionid = optionsList.get(k).getId();
                            string_name = optionsList.get(k).getTitle();
                            price = optionsList.get(k).getMarketprice();
                            stock = optionsList.get(k).getStock();
                            tv_specification.setText("已选：" + string_name);
                            tv_marketprice.setText("¥" + price);
                            tv_total.setText("库存：" + stock);
                        }
                    }

                }
            });


        }

        @Override
        public int getItemCount() {
            return specBeanList.size();
        }

        public class MyViewHolder extends BaseRecyclerViewHolder {
            private TextView title;
            private RecyclerView recyclerView;

            public MyViewHolder(View view) {
                super(view);
                title = view.findViewById(R.id.tv_spec_title);
                recyclerView = view.findViewById(R.id.rv_spec);

            }
        }
    }
}