package com.dq.huibao.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.dq.huibao.R;
import com.dq.huibao.view.goodsdetails.BabyPopWindow;
import com.dq.huibao.view.HackyViewPager;
import com.dq.huibao.view.goodsdetails_foot.GradationScrollView;
import com.dq.huibao.view.goodsdetails_foot.ScrollViewContainer;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Description：商品详情
 * Created by jingang on 2017/10/24.
 */
public class GoodsDetailsActivity extends Activity implements GradationScrollView.ScrollViewListener{
    /*显示图片控件*/
    @Bind(R.id.iv_baby)
    HackyViewPager ivBaby;

    /*我要分销*/
    @Bind(R.id.iv_baby_collection)
    ImageView iv_baby_collection;

    /*加入购物车*/
    @Bind(R.id.but_gd_put_in)
    Button but_gd_put_in;
    //ImageView putIn;

    /*立即购买*/
    @Bind(R.id.but_gd_bug_new)
    Button but_gd_bug_new;
    //ImageView buyNow;

    /*选择数量和颜色时背景变暗*/
    @Bind(R.id.all_choice_layout)
    LinearLayout all_choice_layout;


    NfcAdapter nfcAdapter;
    private HackyViewPager viewPager;
    private ArrayList<View> allListView;

    private int[] resId = {R.mipmap.detail_show_1, R.mipmap.detail_show_2, R.mipmap.detail_show_3,
            R.mipmap.detail_show_4, R.mipmap.detail_show_5, R.mipmap.detail_show_6};
    /**
     * 弹出商品订单信息详情
     */
    private BabyPopWindow popWindow;

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


    @Bind(R.id.sv_container)
    ScrollViewContainer container;

    @Bind(R.id.scrollview)
    GradationScrollView scrollView;

    private int width;
    private int height;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodsdetails);
        ButterKnife.bind(this);

        getSaveCollection();
        initView();
        popWindow = new BabyPopWindow(this);

        container = new ScrollViewContainer(getApplicationContext());

        //initImgDatas();

        initListeners();

    }

    public  int getScreenHeight(Context context) {
        @SuppressLint("WrongConstant")
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    public static int getScreenWidth(Context context) {
        @SuppressLint("WrongConstant")
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    // TODO: 16/8/21 模拟图片假数据
//    private void initImgDatas(){
//        width = getScreenWidth(getApplicationContext());
//        imgsUrl = new ArrayList<>();
//        imgsUrl.add("https://img.alicdn.com/imgextra/i4/714288429/TB2dLhGaVXXXXbNXXXXXXXXXXXX-714288429.jpg");
//        imgsUrl.add("https://img.alicdn.com/imgextra/i3/726966853/TB2vhJ6lXXXXXbJXXXXXXXXXXXX_!!726966853.jpg");
//        imgsUrl.add("https://img.alicdn.com/imgextra/i4/2081314055/TB2FoTQbVXXXXbuXpXXXXXXXXXX-2081314055.png");
//        imgAdapter = new QuickAdapter<String>(this,R.layout.adapter_good_detail_imgs) {
//            @Override
//            protected void convert(BaseAdapterHelper helper, String item) {
//                ImageView iv = helper.getView(R.id.iv_adapter_good_detail_img);
//                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) iv.getLayoutParams();
//                params.width = width;
//                params.height = width/2;
//                iv.setLayoutParams(params);
//                MyImageLoader.getInstance().displayImageCen(getApplicationContext(),item,iv,width,width/2);
//            }
//        };
//        imgAdapter.addAll(imgsUrl);
//        nlvImgs.setAdapter(imgAdapter);
//    }

    private void initListeners() {

        ViewTreeObserver vto = ivBaby.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout()  {
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

        for (int i = 0; i < resId.length; i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.pic_item, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.pic_item);
            imageView.setImageResource(resId[i]);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    //挑战到查看大图界面
                    Intent intent = new Intent(GoodsDetailsActivity.this, ShowBigPictrueActivity.class);
                    intent.putExtra("position", position);
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

    @OnClick({R.id.iv_baby, R.id.but_gd_put_in, R.id.but_gd_bug_new})
    public void onClick(View view) {
        switch (view.getId()) {
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
                isClickBuy = false;
                setBackgroundBlack(all_choice_layout, 0);
                popWindow.showAsDropDown(view);
                break;
            case R.id.but_gd_bug_new:
                //立即购买
                isClickBuy = true;
                setBackgroundBlack(all_choice_layout, 0);
                popWindow.showAsDropDown(view);
                break;
        }
    }

    @Override
    public void onScrollChanged(GradationScrollView scrollView, int x, int y, int oldx, int oldy) {

    }

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
                view.setVisibility(View.VISIBLE);
                break;
            case 1:
                view.setVisibility(View.GONE);
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


}
