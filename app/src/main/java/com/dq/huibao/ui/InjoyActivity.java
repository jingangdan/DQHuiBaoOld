package com.dq.huibao.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dq.huibao.R;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.ImageUtils;
import com.dq.huibao.utils.MD5Util;

import org.xutils.http.RequestParams;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 分享页面
 * Created by jingang on 2018/1/30.
 */

public class InjoyActivity extends BaseActivity {
    @Bind(R.id.tv_injoy_money)
    TextView tvInjoyMoney;
    @Bind(R.id.tv_injoy_stock)
    TextView tvInjoyStock;
    @Bind(R.id.tv_injoy_username)
    TextView tvInjoyUsername;
    @Bind(R.id.iv_injoy)
    ImageView ivInjoy;
    @Bind(R.id.tv_injoy_goodsname)
    TextView tvInjoyGoodsname;
    @Bind(R.id.tv_injoy_price)
    TextView tvInjoyPrice;
    @Bind(R.id.but_injoy1)
    Button butInjoy1;
    @Bind(R.id.but_injoy2)
    Button butInjoy2;
    @Bind(R.id.lin_injoy_footer)
    LinearLayout linInjoyFooter;
    @Bind(R.id.wv_injoy)
    WebView wvInjoy;

    /*接收页面传值*/
    private Intent intent;
    private String gid = "", sales = "", img = "", goodsname = "", price = "", username = "", phone = "", token = "";

    /*接口地址*/
    private String MD5_PATH = "", PATH = "";
    private RequestParams params = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_injoy);
        // setContentView(R.layout.activity_web);
        ButterKnife.bind(this);

        intent = getIntent();
        gid = intent.getStringExtra("gid");
        sales = intent.getStringExtra("sales");
        img = intent.getStringExtra("thumb");
        goodsname = intent.getStringExtra("goodsname");
        price = intent.getStringExtra("price");

        username = intent.getStringExtra("username");
        phone = intent.getStringExtra("phone");
        token = intent.getStringExtra("token");

        if (!username.equals("")) {
            tvInjoyUsername.setText("" + username);
        } else {
            tvInjoyUsername.setText("" + phone);
        }

        tvInjoyStock.setText("" + sales);
        ImageUtils.loadIntoUseFitWidths(this,
                HttpPath.IMG_HEADER + img,
                R.mipmap.icon_empty,
                R.mipmap.icon_error,
                ivInjoy);

        tvInjoyGoodsname.setText("" + goodsname);
        tvInjoyPrice.setText("¥" + price);

        getPosterIndex(gid, phone, token);

    }

    @Override
    protected void initWidght() {
        super.initWidght();
        setTitleName("分享页面");
    }

    @OnClick({R.id.but_injoy1, R.id.but_injoy2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.but_injoy1:
                toast("" + PATH);
                break;
            case R.id.but_injoy2:
                toast("暂未开通");
                break;
        }
    }

    /**
     * 分享
     *
     * @param goodsid
     * @param phone
     * @param token
     */
    public void getPosterIndex(String goodsid, String phone, String token) {
        MD5_PATH = "goodsid=" + goodsid + "&phone=" + phone + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token;
        PATH = HttpPath.PATHS + HttpPath.POSTER_INDEX + MD5_PATH + "&sign=" +
                MD5Util.getMD5String(MD5_PATH + HttpPath.KEY);

        wvInjoy.loadUrl(PATH);
        wvInjoy.setHorizontalScrollBarEnabled(false);//水平不显示
        wvInjoy.setVerticalScrollBarEnabled(false); //垂直不显示
        WebSettings settings = wvInjoy.getSettings();
        settings.setJavaScriptEnabled(true);

//        params = new RequestParams(PATH);
//        System.out.println("分享 = " + PATH);
//        x.http().post(params,
//                new Callback.CommonCallback<String>() {
//                    @Override
//                    public void onSuccess(String result) {
//                        System.out.println("分享 = " + result);
//
//                        wvInjoy.loadUrl(url);
//
//                        wvInjoy.setHorizontalScrollBarEnabled(false);//水平不显示
//                        wvInjoy.setVerticalScrollBarEnabled(false); //垂直不显示
//
//                        WebSettings settings = wvInjoy.getSettings();
//                        settings.setJavaScriptEnabled(true);
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
}
