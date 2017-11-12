package com.dq.huibao.ui;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.R;
import com.dq.huibao.adapter.ChooseAdapter;
import com.dq.huibao.bean.classify.GoodsDetail;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpUtils;
import com.dq.huibao.utils.ImageUtils;
import com.dq.huibao.view.HackyViewPager;
import com.dq.huibao.view.goodsdetails_foot.GradationScrollView;
import com.dq.huibao.view.goodsdetails_foot.ScrollViewContainer;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.BindInt;
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

    /*显示图片控件*/
    @Bind(R.id.iv_baby)
    HackyViewPager ivBaby;

    /*我要分销*/
    @Bind(R.id.lin_gd_distribution)
    LinearLayout linGdDistribution;

    /*加入购物车*/
    @Bind(R.id.but_gd_put_in)
    Button but_gd_put_in;
    //ImageView putIn;

    /*立即购买*/
    @Bind(R.id.but_gd_bug_new)
    Button but_gd_bug_new;
    //ImageView buyNow;

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

    NfcAdapter nfcAdapter;

    private HackyViewPager viewPager;
    private ArrayList<View> allListView;

//    private int[] resId = {R.mipmap.detail_show_1, R.mipmap.detail_show_2, R.mipmap.detail_show_3,
//            R.mipmap.detail_show_4, R.mipmap.detail_show_5, R.mipmap.detail_show_6};
    /**
     * 弹出商品订单信息详情
     */
    //private BabyPopWindow popWindow;

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

    private int width;
    private int height;

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
    private List<GoodsDetail.DataBean.SpecsBean> specsList = new ArrayList<>();

    /*商品规格items*/
    private List<GoodsDetail.DataBean.SpecsBean.ItemsBean> itemList = new ArrayList<>();
    private List<GoodsDetail.DataBean.SpecsBean.ItemsBean> itemLists = new ArrayList<>();

    /*UI赋值*/
    private String title = "", marketprice = "", total = "", sales = "";

    /*图文详情*/
    private String content = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodsdetails);
        ButterKnife.bind(this);

        intent = getIntent();
        gid = intent.getStringExtra("gid");

        getGoodsDetail(gid);

        getSaveCollection();

        initListeners();

    }

    @SuppressLint("WrongConstant")
    @OnClick({R.id.iv_baby, R.id.rel_gd_choose,
            R.id.tv_gd_allgoods, R.id.tv_gd_store,
            R.id.but_gd_put_in, R.id.but_gd_bug_new, R.id.iv_gd_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_gd_back:
                TAG.finish();
                break;

            case R.id.lin_gd_distribution:
                //我要分销
                Toast.makeText(TAG, "我要分销", Toast.LENGTH_SHORT).show();
                break;

            case R.id.rel_gd_choose:
                //选择商品规格和数量
                setPopTest();
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
//            case R.id.iv_baby_collection:
//                //收藏
//                if (isCollection) {
//                    //提示是否取消收藏
//                    cancelCollection();
//                } else {
//                    isCollection = true;
//                    setSaveCollection();
//                    //如果已经收藏，则显示收藏后的效果
//                    iv_baby_collection.setImageResource(R.drawable.second_2_collection);
//                    Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show();
//                }
//                break;

            case R.id.but_gd_put_in:
                //添加购物车
                setPopTest();
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
                break;
        }
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
                        specsList = goodsDetail.getData().getSpecs();

                        System.out.println("555555 = " + specsList.get(0).getTitle());

                        title = goodsDetail.getData().getGoods().getTitle();
                        marketprice = goodsDetail.getData().getGoods().getMarketprice();
                        total = goodsDetail.getData().getGoods().getTotal();
                        sales = goodsDetail.getData().getGoods().getSales();
                        content = goodsDetail.getData().getGoods().getContent();

                        tvGdTitle.setText("" + goodsDetail.getData().getGoods().getTitle());

                        initView();

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

    /*组件赋值*/
    public void initData() {
        tvGdTitle.setText("" + title);
        tvGdName.setText("" + title);
        tvGdMarketprice.setText("¥ " + marketprice);
        tvGdTotal.setText("库存：" + total + " 销量：" + sales);
    }


    /*popupwindows*/
    private PopupWindow popWindow;
    private View view;
    private ImageView iv_cancel, iv_thumb;
    private LinearLayout linGdMain;
    private TextView tv_marketprice, tv_total, tv_specification, tv_num, tv_ok;


    /*动态添加UI*/
    private TextView textView;
    private RecyclerView recyclerView;
    private ChooseAdapter chooseAdapter;

    /**
     * 选择商品规格和数量
     */
    @SuppressLint("WrongConstant")
    public void setPopTest() {
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

        /*价格 库存 规格 数量 确定*/
        tv_marketprice = (TextView) view.findViewById(R.id.tv_pop_gd_marketprice);
        tv_total = (TextView) view.findViewById(R.id.tv_pop_gd_total);
        tv_specification = (TextView) view.findViewById(R.id.tv_pop_gd_specification);
        tv_num = (TextView) view.findViewById(R.id.tv_pop_gd_num);
        tv_ok = (TextView) view.findViewById(R.id.tv_pop_gd_ok);

        tv_marketprice.setText("￥" + goodsDetail.getData().getGoods().getMarketprice());
        tv_total.setText("库存：" + goodsDetail.getData().getGoods().getTotal());

        /*取消*/
        iv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popWindow.dismiss();
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

                //itemList.clear();
                itemList = specsList.get(i).getItems();

                textView.setText("" + specsList.get(i).getTitle());

                chooseAdapter = new ChooseAdapter(TAG, itemList);

                recyclerView.setLayoutManager(new GridLayoutManager(TAG, 1, GridLayoutManager.HORIZONTAL, false));

                recyclerView.setAdapter(chooseAdapter);

                chooseAdapter.setmOnItemClickListener(new OnItemClickListener() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onItemClick(View view, int position) {
                        //chooseAdapter.changeSelected(position);
                        Toast.makeText(TAG, "" + itemList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                        tv_specification.setText("已选：" + itemList.get(position).getTitle());
                    }
                });
                break;

            case "1":
                lin_choose = (LinearLayout) TAG.getLayoutInflater().inflate(R.layout.include_gd_choose, null);
                linGdMain.addView(lin_choose);

                textView = (TextView) lin_choose.findViewById(R.id.tv_gd_choose);
                recyclerView = (RecyclerView) lin_choose.findViewById(R.id.rv_gd_choose);

                //itemList.clear();
                itemLists = specsList.get(i).getItems();

                textView.setText("" + specsList.get(i).getTitle());


                chooseAdapter = new ChooseAdapter(TAG, itemLists);

                recyclerView.setLayoutManager(new GridLayoutManager(TAG, 1, GridLayoutManager.HORIZONTAL, false));

                recyclerView.setAdapter(chooseAdapter);

                chooseAdapter.setmOnItemClickListener(new OnItemClickListener() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onItemClick(View view, int position) {
                        //Toast.makeText(TAG, "" + itemLists.get(position).getTitle(), Toast.LENGTH_SHORT).show();

                        chooseAdapter.changeSelected(position);

                        System.out.println("3333 = "+itemLists.get(position).getThumb());

                        ImageUtils.loadIntoUseFitWidth(TAG,
                                itemLists.get(position).getThumb(),
                                R.mipmap.icon_empty002,
                                R.mipmap.icon_error002,
                                iv_thumb);
                    }
                });
                break;
            default:
                break;
        }

    }

    /**
     * @param html_bady
     */
    @SuppressLint("WrongConstant")
    public void getWebHTML(String html_bady) {
        //webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webSettings = webView.getSettings();
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptEnabled(false);

        webView.getSettings().setBlockNetworkImage(false);
        webView.getSettings().setSupportMultipleWindows(false);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(false);
        webView.getSettings().setDefaultTextEncodingName("utf-8");
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.setWebChromeClient(new WebChromeClient());


//        String head = "<head>" +
//                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
//                "<style>*{margin:0;padding:0;}img{max-width: 100%; width:auto; height:auto;}</style>" +
//                "</head>";
//        //return "<html>" + head + "<body>" + bodyHTML + "</body></html>";
//        String html = "<html>" + head + "<body>" + html_bady + "</body></html>";
//
//        webView.loadDataWithBaseURL(HttpUtils.HEADER, html, "text/html", "utf-8", null);

        //拼接HTML
        String css = "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "</style>";
        String html = "<html><header>" + css + "</header><body>" + html_bady + "</body></html>";
        webView.loadDataWithBaseURL(HttpUtils.HEADER, html, "text/html", "utf-8", null);

    }


    private void initListeners() {

        ViewTreeObserver vto = ivBaby.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //llTitle.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                height = ivBaby.getHeight();

                scrollView.setScrollViewListener(GoodsDetailsActivity.this);
            }
        });
    }


    @SuppressLint({"NewApi", "WrongConstant"})
    private void initView() {
        // 获取默认的NFC控制器
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter == null) {
            //Toast.makeText(this, "该设备不支持NFC", Toast.LENGTH_SHORT).show();
        }
        initViewPager();

        if (isCollection) {
            //如果已经收藏，则显示收藏后的效果
            //iv_baby_collection.setImageResource(R.drawable.second_2_collection);
        }
    }

    /*点击查看大图*/
    private void initViewPager() {

        if (allListView != null) {
            allListView.clear();
            allListView = null;
        }
        allListView = new ArrayList<View>();
        for (int i = 0; i < picsList.size(); i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.pic_item, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.pic_item);

            //System.out.println("图片地址 = " + HttpUtils.HEADER + picsList.get(i).toString());

            ImageUtils.loadIntoUseFitWidth(this,
                    HttpUtils.HEADER + picsList.get(i).toString(),
                    R.mipmap.icon_empty002,
                    R.mipmap.icon_error002,
                    imageView);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intent = new Intent(GoodsDetailsActivity.this, ShowBigPictrueActivity.class);
                    intent.putExtra("position", position);
                    intent.putExtra("picslist", picsList.toString());
                    startActivity(intent);
                }
            });

            allListView.add(view);

        }

        viewPager = (HackyViewPager) findViewById(R.id.iv_baby);
        ViewPagerAdapter adapter = new ViewPagerAdapter();
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                position = arg0;
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
        viewPager.setAdapter(adapter);

    }

    @Override
    public void onScrollChanged(GradationScrollView scrollView, int x, int y, int oldx, int oldy) {

    }

    /*显示图片适配器*/
    private class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return allListView.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == (View) arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = allListView.get(position);
            container.addView(view);
            return view;
        }

    }

    //点击弹窗的确认按钮的响应
    //@Override
    @SuppressLint("WrongConstant")
    public void onClickOKPop() {
        setBackgroundBlack(all_choice_layout, 1);

        if (isClickBuy) {
            //如果之前是点击的立即购买，那么就跳转到立即购物界面
//            Intent intent = new Intent(GoodsDetailsActivity.this, BuynowActivity.class);
//            startActivity(intent);
        } else {
            Toast.makeText(this, "添加到购物车成功", Toast.LENGTH_SHORT).show();
        }
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
                //iv_baby_collection.setImageResource(R.drawable.second_2);
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
