package com.dq.huibao.application;

import android.app.Application;

import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Description：
 * Created by jingang on 2017/11/13.
 */

public class MyApplication extends Application {

    public static IWXAPI mWxApi;

    @Override
    public void onCreate() {
        super.onCreate();
        registerToWX();
    }

    private void registerToWX() {
        //第二个参数是指你应用在微信开放平台上的AppID
        mWxApi = WXAPIFactory.createWXAPI(this, "MainConstant.WX.WEIXIN_APP_ID", false);
        // 将该app注册到微信
        mWxApi.registerApp("MainConstant.WX.WEIXIN_APP_ID");
    }
}