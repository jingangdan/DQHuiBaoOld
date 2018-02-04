package com.dq.huibao.ui.memcen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dq.huibao.R;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.utils.HttpUtils;
import com.dq.huibao.utils.MD5Util;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * 签到规则
 * Created by jingang on 2018/1/31.
 */

public class SignRuleActivity extends BaseActivity {
    /*接收页面传值*/
    private Intent intent;
    private String phone = "", token = "";

    /*接口地址*/
    private String MD5_PATH = "", PATH = "";
    private RequestParams params = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_rule);
        intent = getIntent();
        phone = intent.getStringExtra("phone");
        token = intent.getStringExtra("token");

        getSignIndex(phone, token);


    }

    @Override
    protected void initWidght() {
        super.initWidght();
        setTitleName("签到规则");
    }

    /**
     * 获取签到信息
     *
     * @param phone
     * @param token
     */
    public void getSignIndex(String phone, String token) {
        MD5_PATH = "phone=" + phone + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token;
        PATH = HttpUtils.PATHS + HttpUtils.ACTIVITYSIGN_INDEX + MD5_PATH + "&sign=" +
                MD5Util.getMD5String(MD5_PATH + HttpUtils.KEY);
        params = new RequestParams(PATH);
        System.out.println("签到信息 = " + PATH);
        x.http().get(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("签到信息 = " + result);

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
