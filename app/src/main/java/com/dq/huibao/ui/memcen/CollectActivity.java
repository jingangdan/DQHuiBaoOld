package com.dq.huibao.ui.memcen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dq.huibao.R;
import com.dq.huibao.adapter.CollectAdapter;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.bean.memcen.Collect;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpUtils;
import com.dq.huibao.utils.MD5Util;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Description：我的收藏
 * Created by jingang on 2017/11/1.
 */

public class CollectActivity extends BaseActivity {
    @Bind(R.id.rv_collect)
    RecyclerView rvCollect;

    /*接收页面传值*/
    private Intent intent;
    private String phone = "", token = "";

    /*借口地址*/
    private String MD5_PATH = "", PATH = "";
    private RequestParams params = null;
    private int page = 1;

    private CollectAdapter collectAdapter;
    private List<Collect.DataBean.ListBean> collectList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        ButterKnife.bind(this);

        collectAdapter = new CollectAdapter(this, collectList);
        rvCollect.setLayoutManager(new LinearLayoutManager(this));
        rvCollect.setAdapter(collectAdapter);

        intent = getIntent();
        phone = intent.getStringExtra("phone");
        token = intent.getStringExtra("token");

        getRecordList("collect", page, phone, token);

    }

    @Override
    protected void initWidght() {
        super.initWidght();
        setTitleName("我的收藏");
    }

    /**
     * 获取收藏列表
     *
     * @param type  类型--- browse  浏览历史  collect收藏商品   collect_shop收藏店铺（暂无）
     * @param page
     * @param phone
     * @param token
     */
    public void getRecordList(String type, int page, String phone, String token) {
        MD5_PATH = "page=" + page + "&phone=" + phone + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token + "&type=" + type;
        PATH = HttpUtils.PATHS + HttpUtils.MEM_RECORDLIST + MD5_PATH + "&sign=" +
                MD5Util.getMD5String(MD5_PATH + HttpUtils.KEY);

        params = new RequestParams(PATH);
        System.out.println("收藏列表 = " + PATH);
        x.http().get(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("收藏列表 = " + result);
                        Collect collect = GsonUtil.gsonIntance().gsonToBean(result, Collect.class);

                        collectList.clear();
                        collectList.addAll(collect.getData().getList());
                        collectAdapter.notifyDataSetChanged();

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
