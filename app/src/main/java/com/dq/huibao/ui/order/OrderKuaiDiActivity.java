package com.dq.huibao.ui.order;

import android.content.Intent;
import android.os.Bundle;

import com.dq.huibao.R;
import com.dq.huibao.base.BaseActivity;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * 查看物流
 * Created by jingang on 2018/1/20.
 */

public class OrderKuaiDiActivity extends BaseActivity {
    /*接受页面传值*/
    private Intent intent;
    private String type = "", postid = "";

    /*接口地址*/
    private String PATH = "";
    private RequestParams params = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuaidi);
        intent = getIntent();
        type = intent.getStringExtra("type");
        postid = intent.getStringExtra("postid");

        getKuaidi(type, postid);
    }

    @Override
    protected void initWidght() {
        super.initWidght();
        setTitleName("查看物流");
    }

    /**
     * 获取物流
     *
     * @param type
     * @param postid
     */
    public void getKuaidi(String type, String postid) {
        PATH = "https://www.kuaidi100.com/query?type=" + type + "&postid=" + postid;
        params = new RequestParams(PATH);
        System.out.println("获取物流 = " + PATH);
        x.http().get(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("获取物流 = " + result);
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
