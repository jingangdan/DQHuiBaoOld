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
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
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
import com.dq.huibao.adapter.gd.ChooseAdapter;
import com.dq.huibao.adapter.gd.ChooseTwoAdapter;
import com.dq.huibao.adapter.gd.GdCommentAdapter;
import com.dq.huibao.adapter.gd.GdParmasAdapter;
import com.dq.huibao.bean.LoginBean;
import com.dq.huibao.bean.goodsdetail.Comment;
import com.dq.huibao.bean.goodsdetail.GoodsDetail;
import com.dq.huibao.bean.goodsdetail.Items;
import com.dq.huibao.bean.goodsdetail.Options;
import com.dq.huibao.bean.goodsdetail.Params;
import com.dq.huibao.bean.goodsdetail.Specs;
import com.dq.huibao.lunbotu.ADInfo;
import com.dq.huibao.lunbotu.CycleViewPager;
import com.dq.huibao.lunbotu.ViewFactory;
import com.dq.huibao.ui.memcen.ShopcarActivity;
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

    NfcAdapter nfcAdapter;

    private HackyViewPager viewPager;
    private ArrayList<View> allListView;

    /**
     * 判断是否点击的立即购买按钮
     */
    boolean isClickBuy = false;

    /**
     * 是否添加收藏
     */
    private static boolean isCollection = false;

    /**
     * ViewPager当前显示页的下标
     */
    private int position = 0;

    @Bind(R.id.scrollview)
    GradationScrollView scrollView;


    /*接收页面传值*/
    private Intent intent;
    private String gid = "";

    /*接口地址*/
    private String PATH = "";
    private RequestParams params;

    /**
     *
     */
    private GoodsDetail goodsDetail;

    /*图片*/
    public static List<String> picsList = new ArrayList<>();

    /*商品规格*/
    private List<Specs> specsList = new ArrayList<>();

    /*商品规格items*/
    private List<Items> itemList = new ArrayList<>();
    private List<Items> itemLists = new ArrayList<>();
    private List<Options> optionsList = new ArrayList<>();

    /*UI赋值*/
    private String title = "", marketprice = "", total = "", sales = "";

    /*图文详情*/
    private String content = "";
    /*产品参数*/
    private List<Params> paramsList = new ArrayList<>();
    private GdParmasAdapter gdParmasAdapter;
    /*用户评价*/
    private List<Comment> commentList = new ArrayList<>();
    private GdCommentAdapter gdCommentAdapter;

    /*规格id*/
    private String specsItemId1 = "", specsItemId2 = "";

    /*本地轻量型缓存*/
    private SPUserInfo spUserInfo;
    private String unionid = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodsdetails);
        ButterKnife.bind(this);

        intent = getIntent();
        gid = intent.getStringExtra("gid");

        getGoodsDetail(gid);

        getSaveCollection();

        initDate();

    }

    @SuppressLint({"WrongConstant", "ResourceAsColor"})
    @OnClick({R.id.rel_gd_choose,
            R.id.tv_gd_allgoods, R.id.tv_gd_store,
            R.id.but_gd_put_in, R.id.but_gd_bug_new, R.id.iv_gd_back, R.id.rel_gd_shopcar,
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
                //specsList = goodsDetail.getData().getSpecs();
                setPopTest(0);

                setBackgroundBlack(all_choice_layout, 0);
                break;

            case R.id.tv_gd_allgoods:
                //查看全部商品
                intent = new Intent(TAG, GoodsListActivity.class);
                intent.putExtra("pcate", "");
                intent.putExtra("ccate", "");
                intent.putExtra("name", "");
                intent.putExtra("keywords", "");
                startActivity(intent);

                break;

            case R.id.tv_gd_store:
                //进店逛逛（进入我的小店）
                Toast.makeText(TAG, "进入我的小店", Toast.LENGTH_SHORT).show();
                break;
            case R.id.lin_gd_collection:
                //收藏
                if (isCollection) {
                    //提示是否取消收藏
                    cancelCollection();
                } else {
                    isCollection = true;
                    setSaveCollection();
                    //如果已经收藏，则显示收藏后的效果
                    ivGdCollection.setImageResource(R.mipmap.ic_collection002);
                    tvGdCollection.setText("已收藏");
                    //butGdCollection.setBackgroundResource(R.mipmap.ic_collection002);
                    Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.rel_gd_shopcar:
                //购物车
                intent = new Intent(TAG, ShopcarActivity.class);
                startActivity(intent);

                break;

            case R.id.but_gd_put_in:
                //添加购物车
                setPopTest(1);
                setBackgroundBlack(all_choice_layout, 0);
//                isClickBuy = false;
//                setBackgroundBlack(all_choice_layout, 0);
//                popWindow.showAsDropDown(view);
                break;
            case R.id.but_gd_bug_new:
                //立即购买
//                isClickBuy = true;
//                setBackgroundBlack(all_choice_layout, 0);
//                popWindow.showAsDropDown(view);
                setPopTest(2);
                setBackgroundBlack(all_choice_layout, 0);


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

                paramsList = goodsDetail.getData().getParams();
                gdParmasAdapter = new GdParmasAdapter(TAG, paramsList);
                rvGdParams.setLayoutManager(new LinearLayoutManager(TAG));
                rvGdParams.setAdapter(gdParmasAdapter);


                break;

            case R.id.tv_gd_comment:
                //用户评价
                setTabChoose();
                tvGdComment.setTextColor(Color.rgb(241, 83, 83));
                vGdComment.setVisibility(View.VISIBLE);
                linGdComment.setVisibility(View.VISIBLE);

                commentList = goodsDetail.getData().getComment();
                gdCommentAdapter = new GdCommentAdapter(TAG, commentList);
                rvGdComment.setLayoutManager(new LinearLayoutManager(TAG));
                rvGdComment.setAdapter(gdCommentAdapter);

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
        if (isLogin()) {
            if (!(spUserInfo.getLoginReturn().equals(""))) {
                LoginBean loginBean = GsonUtil.gsonIntance().gsonToBean(spUserInfo.getLoginReturn(), LoginBean.class);
                unionid = loginBean.getData().getUnionid();

            } else {
                //toast("登录状态出错，请重新登录");
                Toast.makeText(TAG, "登录状态出错，请重新登录", Toast.LENGTH_SHORT).show();
            }
        }

    }

    /*判断登录状态*/
    public boolean isLogin() {
        spUserInfo = new SPUserInfo(getApplication());
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
     * @param gid
     */
    public void getGoodsDetail(String gid) {
        PATH = HttpUtils.PATH + HttpUtils.SHOP_GOODS_DETAIL + gid;
        params = new RequestParams(PATH);

        System.out.println("商品详情 = " + PATH);

        x.http().get(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("商品详情 = " + result);
                        goodsDetail = GsonUtil.gsonIntance().gsonToBean(result, GoodsDetail.class);

                        picsList = goodsDetail.getData().getPics();

                        if (!goodsDetail.getData().getSpecs().toString().equals("[]")) {
                            specsList = goodsDetail.getData().getSpecs();
                        }
                        if (!goodsDetail.getData().getOptions().toString().equals("[]")) {
                            optionsList = goodsDetail.getData().getOptions();
                        }

                        title = goodsDetail.getData().getGoods().getTitle();
                        marketprice = goodsDetail.getData().getGoods().getMarketprice();
                        total = goodsDetail.getData().getGoods().getTotal();
                        sales = goodsDetail.getData().getGoods().getSales();
                        content = goodsDetail.getData().getGoods().getContent();

                        tvGdTitle.setText("" + goodsDetail.getData().getGoods().getTitle());

                        //initView();

                        initData();

                        getWebHTML(content);

                        setLunbotu();


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
            info.setUrl(HttpUtils.HEADER + picsList.get(i).toString());
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
    private TextView textView;
    private RecyclerView recyclerView;
    private ChooseAdapter chooseAdapter;
    private ChooseTwoAdapter chooseTwoAdapter;

    /*规格*/
    private String specifications1 = "", specifications2 = "";

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
                // 在pop消失之前，给咱们加的view设置背景渐变出场动画（Android3.0以上的开发环境，这里建议使用属性动画，那样很柔和，视觉效果更棒！）
//                viewBg.startAnimation(AnimationUtils.loadAnimation(context,
//                        R.anim.anim_bookshelf_folder_editer_exit));
//                viewBg.setVisibility(View.GONE);
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
        // 在显示pop之前，给咱们加的view设置背景渐变入场动画（Android3.0以上的开发环境，这里建议使用属性动画，那样很柔和，视觉效果更棒！）
//        viewBg.setVisibility(View.VISIBLE);
//        viewBg.startAnimation(AnimationUtils.loadAnimation(context,
//                R.anim.anim_bookshelf_folder_editer_enter));
        // 依附的父布局自己设定，我这里为了方便，这样写的。
        popWindow.showAtLocation(all_choice_layout, Gravity.BOTTOM, 0, 0);

        linGdMain = (LinearLayout) view.findViewById(R.id.lin_pop_gd_main);

        /*图片 取消*/
        iv_cancel = (ImageView) view.findViewById(R.id.iv_pop_gd_back);
        iv_thumb = (ImageView) view.findViewById(R.id.iv_pop_gd_thumb);

        Glide.with(TAG)
                .load(HttpUtils.HEADER + goodsDetail.getData().getPics().get(0))
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

        tv_marketprice.setText("￥" + goodsDetail.getData().getGoods().getMarketprice());
        tv_total.setText("库存：" + goodsDetail.getData().getGoods().getTotal());

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
                String options_specs = "", options_title = "", options_id = "";
                if (specsItemId2.equals("")) {
                    options_specs = specsItemId1 + specsItemId2;
                } else {
                    options_specs = specsItemId1 + "_" + specsItemId2;
                }

//                System.out.println("111 = " + specsItemId1);
//                System.out.println("222 = " + specsItemId2);
//                System.out.println("sss = " + options_specs);

                for (int i = 0; i < optionsList.size(); i++) {
                    if (options_specs.equals(optionsList.get(i).getSpecs())) {
//                        System.out.println(optionsList.get(i).getTitle());
                        options_id = optionsList.get(i).getId();
                        options_title = optionsList.get(i).getTitle();

                    } else {
                        System.out.println("有规格木有选择");
                    }
                }

                if (tag == 0) {
                    //tvGdSpecification.setText("已选：" + specifications1 + "   " + specifications2 + "   数量：" + num);
                    tvGdSpecification.setText("已选：" + options_title);
                    popWindow.dismiss();
                } else if (tag == 1) {
                    //添加购物车
                    setAddCart(unionid, gid, options_id, "" + num);

                    //判断规格选择情况

                } else if (tag == 2) {
                    intent = new Intent(TAG, SubmitOrderActivity.class);
                    startActivity(intent);
                }

            }
        });

        /*根据数据结构动态生成UI*/
        for (int i = 0; i < specsList.size(); i++) {
            setChooseLayout(i, specsList.get(i).getDisplayorder());
        }

    }

    /**/
    private LinearLayout lin_choose;

    /**
     * 选择商品规格和数量时根据json数据动态创建布局
     *
     * @param i
     * @param displayorder
     */
    public void setChooseLayout(int i, String displayorder) {
        switch (displayorder) {
            case "0":
                lin_choose = (LinearLayout) TAG.getLayoutInflater().inflate(R.layout.include_gd_choose, null);
                linGdMain.addView(lin_choose);

                textView = (TextView) lin_choose.findViewById(R.id.tv_gd_choose);
                recyclerView = (RecyclerView) lin_choose.findViewById(R.id.rv_gd_choose);

                itemList = specsList.get(i).getItems();

                textView.setText("" + specsList.get(i).getTitle());

                chooseTwoAdapter = new ChooseTwoAdapter(TAG, itemList);

                recyclerView.setLayoutManager(new GridLayoutManager(TAG, 1, GridLayoutManager.HORIZONTAL, false));

                recyclerView.setAdapter(chooseTwoAdapter);

                chooseTwoAdapter.setmOnItemClickListener(new OnItemClickListener() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onItemClick(View view, int position) {
                        chooseTwoAdapter.changeSelected(position);
                        //Toast.makeText(TAG, "000 = " + itemList.get(position).getTitle(), Toast.LENGTH_SHORT).show();

                        specifications1 = itemList.get(position).getTitle();
                        tv_specification.setText("已选：" + specifications1 + "   " + specifications2);

                        specsItemId1 = itemList.get(position).getId();
                    }
                });
                break;

            case "1":
                lin_choose = (LinearLayout) TAG.getLayoutInflater().inflate(R.layout.include_gd_choose, null);
                linGdMain.addView(lin_choose);

                textView = (TextView) lin_choose.findViewById(R.id.tv_gd_choose);
                recyclerView = (RecyclerView) lin_choose.findViewById(R.id.rv_gd_choose);

                itemLists = specsList.get(i).getItems();

                textView.setText("" + specsList.get(i).getTitle());

                chooseAdapter = new ChooseAdapter(TAG, itemLists);

                recyclerView.setLayoutManager(new GridLayoutManager(TAG, 1, GridLayoutManager.HORIZONTAL, false));

                recyclerView.setAdapter(chooseAdapter);

                chooseAdapter.setmOnItemClickListener(new OnItemClickListener() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onItemClick(View view, int position) {

                        chooseAdapter.changeSelected(position);

                        ImageUtils.loadIntoUseFitWidth(TAG,
                                HttpUtils.HEADER + itemLists.get(position).getThumb(),
                                R.mipmap.icon_empty002,
                                R.mipmap.icon_error002,
                                iv_thumb);

                        specifications2 = itemLists.get(position).getTitle();

                        tv_specification.setText("已选：" + specifications1 + "   " + specifications2);

                        specsItemId2 = itemLists.get(position).getId();

                    }
                });
                break;
            default:
                break;
        }

    }


    /**
     * 添加购物车
     *
     * @param unionid
     * @param id       商品id
     * @param optionid 规格id
     * @param total    商品数量
     */
    public void setAddCart(String unionid, String id, String optionid, String total) {
        PATH = HttpUtils.PATH + HttpUtils.SHOP_CART_ADD +
                "unionid=" + unionid + "&stamp=" + (System.currentTimeMillis() / 1000) + "&doc=" +
                MD5Util.getMD5String(HttpUtils.SHOP_CART_ADD + "unionid=" + unionid +
                        "&stamp=" + (System.currentTimeMillis() / 1000) + "&dequanhuibaocom") +
                "&id=" + id + "&optionid=" + optionid + "&total=" + total;

        params = new RequestParams(PATH);

        System.out.println("添加购物车（商品详情） = " + PATH);

        x.http().post(params,
                new Callback.CommonCallback<String>() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("添加购物车（商品详情） = " + result);
                        Toast.makeText(TAG, "添加成功", Toast.LENGTH_SHORT).show();
                        popWindow.dismiss();
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
     * 保存是否添加收藏
     */
    private void setSaveCollection() {
        SharedPreferences sp = getSharedPreferences("SAVECOLLECTION", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("isCollection", isCollection);
        editor.commit();
    }

    /**
     * 得到保存的是否添加收藏标记
     */
    private void getSaveCollection() {
        SharedPreferences sp = getSharedPreferences("SAVECOLLECTION", Context.MODE_PRIVATE);
        isCollection = sp.getBoolean("isCollection", false);

    }

    /**
     * 取消收藏
     */
    private void cancelCollection() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("是否取消收藏");
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                isCollection = false;
                //如果取消收藏，则显示取消收藏后的效果

                ivGdCollection.setImageResource(R.mipmap.ic_collection001);
                tvGdCollection.setText("收藏");
                //butGdCollection.setBackgroundResource(R.mipmap.ic_collection001);
                setSaveCollection();
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
}