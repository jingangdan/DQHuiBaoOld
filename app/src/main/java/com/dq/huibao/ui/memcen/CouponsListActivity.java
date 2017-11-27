package com.dq.huibao.ui.memcen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.R;
import com.dq.huibao.adapter.memcen.CouponsListAdapter;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.bean.LoginBean;
import com.dq.huibao.bean.memcen.CouponsList;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpUtils;
import com.dq.huibao.utils.MD5Util;
import com.dq.huibao.utils.SPUserInfo;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Description：全部优惠券
 * Created by jingang on 2017/11/22.
 */

public class CouponsListActivity extends BaseActivity {
    @Bind(R.id.rv_couponslist)
    RecyclerView rvCouponslist;
    private CouponsListAdapter couponsListAdapter;
    private List<CouponsList.DataBean> couponsDataList = new ArrayList<>();

    /*接口地址*/
    private String PATH = "";
    private RequestParams params = null;

    /*接收页面传值*/
    private Intent intent;

    /*本地轻量型缓存*/
    private SPUserInfo spUserInfo;
    private String unionid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_couponslist);
        ButterKnife.bind(this);

        couponsListAdapter = new CouponsListAdapter(this, couponsDataList);
        rvCouponslist.setLayoutManager(new LinearLayoutManager(this));
        rvCouponslist.setAdapter(couponsListAdapter);

        couponsListAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                intent = new Intent(CouponsListActivity.this, CouponsDetailActivity.class);
                intent.putExtra("couponsid", couponsDataList.get(position).getId());
                intent.putExtra("index", "1");//0代表用户的优惠券
                startActivity(intent);
            }
        });

        initData();

    }

    @Override
    protected void initWidght() {
        super.initWidght();
        setTitleName("优惠券领取中心");
    }

    public void initData() {
        spUserInfo = new SPUserInfo(getApplication());
        if (!spUserInfo.getLoginReturn().equals("")) {
            LoginBean loginBean = GsonUtil.gsonIntance().gsonToBean(spUserInfo.getLoginReturn(), LoginBean.class);
            unionid = loginBean.getData().getUnionid();

            getCouponsList(unionid, 1);
        }
    }

    /**
     * 获取优惠券列表
     *
     * @param unionid
     * @param page
     */
    public void getCouponsList(String unionid, int page) {
        PATH = HttpUtils.PATH + HttpUtils.SHOP_COUPON_INDEX +
                "unionid=" + unionid + "&stamp=" + (System.currentTimeMillis() / 1000) + "&doc=" +
                MD5Util.getMD5String(HttpUtils.SHOP_COUPON_INDEX + "unionid=" + unionid + "&stamp=" + (System.currentTimeMillis() / 1000) + "&dequanhuibaocom") +
                "&page=" + page;

        params = new RequestParams(PATH);
        System.out.println("优惠券领取列表 = " + PATH);
        x.http().get(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("优惠券领取列表 = " + result);
                        CouponsList couponsList = GsonUtil.gsonIntance().gsonToBean(result, CouponsList.class);
                        couponsDataList.clear();
                        couponsDataList.addAll(couponsList.getData());
                        couponsListAdapter.notifyDataSetChanged();

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
