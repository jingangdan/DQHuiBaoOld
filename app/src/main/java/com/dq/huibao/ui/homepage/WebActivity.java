package com.dq.huibao.ui.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.dq.huibao.R;
import com.dq.huibao.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Description：web网页
 * Created by jingang on 2017/11/1.
 */

public class WebActivity extends BaseActivity {
    @Bind(R.id.webView)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);

        webView.loadUrl("url");

        webView.setHorizontalScrollBarEnabled(false);//水平不显示
        webView.setVerticalScrollBarEnabled(false); //垂直不显示

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
    }

    @Override
    protected void initWidght() {
        super.initWidght();
        setTitleName("web网页");
    }
}
