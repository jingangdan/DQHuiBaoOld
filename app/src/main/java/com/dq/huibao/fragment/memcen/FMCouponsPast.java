package com.dq.huibao.fragment.memcen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dq.huibao.R;
import com.dq.huibao.base.BaseFragment;
import com.dq.huibao.bean.LoginBean;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpUtils;
import com.dq.huibao.utils.MD5Util;
import com.dq.huibao.utils.SPUserInfo;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Description：优惠券（已过期）
 * Created by jingang on 2017/11/1.
 */

public class FMCouponsPast extends BaseFragment {
    private View view;

    /*接口地址*/
    private String PATH = "";
    private RequestParams params = null;

    /*本地轻量型缓存*/
    private SPUserInfo spUserInfo;
    private String unionid = "";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fm_order_all, null);

        initData();

        return view;
    }

    public static FMCouponsPast newInstance(String coupons) {
        Bundle bundle = new Bundle();
        bundle.putString("coupons", coupons);
        FMCouponsPast fragment = new FMCouponsPast();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            System.out.println("in 3");
            getCoupons(unionid, "", "1", 1);
        } else {
            System.out.println("move 3");

        }

    }

    public void initData() {
        spUserInfo = new SPUserInfo(getActivity().getApplication());

        if (!(spUserInfo.getLoginReturn().equals(""))) {
            LoginBean loginBean = GsonUtil.gsonIntance().gsonToBean(spUserInfo.getLoginReturn(), LoginBean.class);
            unionid = loginBean.getData().getUnionid();
            //getCoupons(unionid, "", "1", 1);


        } else {
            toast("登录状态出错，请重新登录");
        }
    }

    /**
     * 优惠券列表
     *
     * @param unionid
     * @param used    默认空  1已使用
     * @param past    默认空  1已过期
     *                <p>
     *                used 和past 都为空  未使用
     */
    public void getCoupons(String unionid, String used, String past, int page) {
        PATH = HttpUtils.PATH + HttpUtils.ShOP_MEMBER_COUPON +
                "unionid=" + unionid + "&stamp=" + (System.currentTimeMillis() / 1000) + "&doc=" +
                MD5Util.getMD5String(HttpUtils.ShOP_MEMBER_COUPON + "unionid=" + unionid + "&stamp=" + (System.currentTimeMillis() / 1000)+ "&dequanhuibaocom")  +
                "&used=" + used + "&past=" + past + "&page=" + page;

        params = new RequestParams(PATH);
        System.out.println("优惠券 已过期 = " + PATH);

        x.http().get(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("优惠券 已过期= " + result);

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

    @Override
    protected void lazyLoad() {

    }
}
