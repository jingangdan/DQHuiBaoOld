package com.dq.huibao.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dq.huibao.R;
import com.dq.huibao.base.BaseFragment;
import com.dq.huibao.bean.LoginBean;
import com.dq.huibao.bean.UserInfo;
import com.dq.huibao.ui.LoginActivity;
import com.dq.huibao.ui.memcen.AddressListActivity;
import com.dq.huibao.ui.memcen.CollectActivity;
import com.dq.huibao.ui.memcen.CouponsActivity;
import com.dq.huibao.ui.memcen.FootprintActivity;
import com.dq.huibao.ui.memcen.OrderActivity;
import com.dq.huibao.ui.memcen.ShopcarActivity;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpUtils;
import com.dq.huibao.utils.MD5Util;
import com.dq.huibao.utils.SPUserInfo;
import com.dq.huibao.view.GlideCircleTransform;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description：会员中心
 * Created by jingang on 2017/10/18.
 */

public class FMMemCen extends BaseFragment {
    /*登录状态*/
    @Bind(R.id.lin_percen_login)
    LinearLayout linPercenLogin;
    @Bind(R.id.lin_percen_nologin)
    LinearLayout linPercenNoLogin;

    /*登录*/
    @Bind(R.id.but_percen_login)
    Button butLogin;

    private View view;

    /*页面跳转*/
    private Intent intent;

    /*本地轻量型缓存*/
    private SPUserInfo spUserInfo;
    private String unionid = "";

    /*接口地址*/
    private String PATH = "";
    private RequestParams params = null;

    /*用户头像*/
    @Bind(R.id.iv_memcen)
    ImageView ivMemcen;

    /*id 昵称 余额 积分 优惠券*/
    @Bind(R.id.tv_mc_id)
    TextView tvMcId;
    @Bind(R.id.tv_mc_nickname)
    TextView tvMcNickname;
    @Bind(R.id.tv_mc_credit1)
    TextView tvMcCredit1;
    @Bind(R.id.tv_mc_credit2)
    TextView tvMcCredit2;
    @Bind(R.id.tv_mc_couponcount)
    TextView tvMcCouponcount;
    @Bind(R.id.lin_mc_credit1)
    LinearLayout linMcCredit1;
    @Bind(R.id.lin_mc_credit2)
    LinearLayout linMcCredit2;
    @Bind(R.id.lin_mc_couponcount)
    LinearLayout linMcCouponcount;

    /*我的订单 待付款 待发货 待收货 待退款*/
    @Bind(R.id.rel_mc_orders)
    RelativeLayout relMcOrders;
    @Bind(R.id.but_mc_status0)
    Button butMcStatus0;
    @Bind(R.id.but_mc_status1)
    Button butMcStatus1;
    @Bind(R.id.but_mc_status2)
    Button butMcStatus2;
    @Bind(R.id.but_mc_status3)
    Button butMcStatus3;

    /*menu项*/
    @Bind(R.id.but_mc_menu0)
    Button butMcMenu0;
    @Bind(R.id.but_mc_menu1)
    Button butMcMenu1;
    @Bind(R.id.but_mc_menu2)
    Button butMcMenu2;
    @Bind(R.id.but_mc_menu3)
    Button butMcMenu3;
    @Bind(R.id.but_mc_menu4)
    Button butMcMenu4;
    @Bind(R.id.but_mc_menu5)
    Button butMcMenu5;
    @Bind(R.id.but_mc_menu6)
    Button butMcMenu6;
    @Bind(R.id.but_mc_menu7)
    Button butMcMenu7;
    @Bind(R.id.but_mc_menu8)
    Button butMcMenu8;
    @Bind(R.id.but_mc_menu9)
    Button butMcMenu9;
    @Bind(R.id.but_mc_menu10)
    Button butMcMenu10;
    @Bind(R.id.but_mc_menu11)
    Button butMcMenu11;
    @Bind(R.id.but_mc_menu12)
    Button butMcMenu12;
    @Bind(R.id.but_mc_menu13)
    Button butMcMenu13;

    /*更新UI*/
    private UserInfo userInfo;
    private UserInfo.DataBean.MemberBean memberBean;
    /*头像 昵称 余额 积分*/
    private String id, avatar, nickname, credit1, credit2, couponcount;

    @SuppressLint("WrongConstant")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_memcen, null);
        ButterKnife.bind(this, view);

        isLogin();
        return view;
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);

    }

    @OnClick({R.id.but_percen_login,
            R.id.iv_memcen, R.id.lin_mc_credit1, R.id.lin_mc_credit2, R.id.lin_mc_couponcount,
            R.id.rel_mc_orders, R.id.but_mc_status0, R.id.but_mc_status1, R.id.but_mc_status2, R.id.but_mc_status3,

            R.id.but_mc_menu0, R.id.but_mc_menu1, R.id.but_mc_menu2,
            R.id.but_mc_menu3, R.id.but_mc_menu4, R.id.but_mc_menu5,
            R.id.but_mc_menu6, R.id.but_mc_menu7, R.id.but_mc_menu8,
            R.id.but_mc_menu9, R.id.but_mc_menu10, R.id.but_mc_menu11,
            R.id.but_mc_menu12, R.id.but_mc_menu13
    })
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.but_percen_login:
                //登录
                intent = new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(intent, 1);
                break;

            /*个人信息*/
            case R.id.iv_memcen:
                //个人信息
                break;
            case R.id.lin_mc_credit1:
                //余额
                toast("余额");
                break;
            case R.id.lin_mc_credit2:
                //积分
                toast("积分");
                break;
            case R.id.lin_mc_couponcount:
                //优惠券
                toast("优惠券");
                break;

            /*我的订单*/
            case R.id.rel_mc_orders:
                //我的订单
                setIntentOrder(0);
                break;
            case R.id.but_mc_status0:
                //待付款
                setIntentOrder(1);
                break;
            case R.id.but_mc_status1:
                //待发货
                setIntentOrder(2);
                break;
            case R.id.but_mc_status2:
                //待收货
                setIntentOrder(3);
                break;
            case R.id.but_mc_status3:
                //待退款
                break;

            /*menu*/
            case R.id.but_mc_menu0:
                //分销中心
                toast("分销中心");
                break;
            case R.id.but_mc_menu1:
                //我的资料
                toast("我的资料");
                break;
            case R.id.but_mc_menu2:
                //购物车
                intent = new Intent(getActivity(), ShopcarActivity.class);
                startActivity(intent);
                break;
            case R.id.but_mc_menu3:
                //我的收藏
                intent = new Intent(getActivity(), CollectActivity.class);
                startActivity(intent);
                break;
            case R.id.but_mc_menu4:
                //我的足迹
                intent = new Intent(getActivity(), FootprintActivity.class);
                startActivity(intent);
                break;

            case R.id.but_mc_menu5:
                //优惠券
                intent = new Intent(getActivity(), CouponsActivity.class);
                startActivity(intent);
                break;

            case R.id.but_mc_menu6:
                //积分商城
                toast("积分商城");
                break;
            case R.id.but_mc_menu7:
                //绑定手机
                toast("绑定手机");
                break;
            case R.id.but_mc_menu8:
                //供应商申请
                toast("供应商申请");
                break;
            case R.id.but_mc_menu9:
                //悦读
                toast("悦读");
                break;
            case R.id.but_mc_menu10:
                //充值记录
                toast("充值记录");
                break;
            case R.id.but_mc_menu11:
                //消息提醒
                toast("消息提醒");
                break;
            case R.id.but_mc_menu12:
                //收货地址
                intent = new Intent(getActivity(), AddressListActivity.class);
                startActivity(intent);
                break;
            case R.id.but_mc_menu13:
                //退出登录
                spUserInfo.saveLogin("");
                spUserInfo.saveLoginReturn("");
                isLogin();
                break;

            default:
                break;


        }
    }

    /**
     * 跳转到订单页
     *
     * @param page
     */
    public void setIntentOrder(int page) {
        intent = new Intent(getActivity(), OrderActivity.class);
        intent.putExtra("page", page);
        startActivity(intent);
    }

    /*
    * 判断登录状态
    *  */
    @SuppressLint("WrongConstant")
    public void isLogin() {
        spUserInfo = new SPUserInfo(getActivity().getApplication());

        if (spUserInfo.getLogin().equals("1")) {

            if (!(spUserInfo.getLoginReturn().equals(""))) {
                LoginBean loginBean = GsonUtil.gsonIntance().gsonToBean(spUserInfo.getLoginReturn(), LoginBean.class);
                unionid = loginBean.getData().getUnionid();

                getUserInfo(unionid);
            }

            linPercenLogin.setVisibility(View.VISIBLE);
            linPercenNoLogin.setVisibility(View.GONE);
        } else {
            linPercenLogin.setVisibility(View.GONE);
            linPercenNoLogin.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 获取个人信息
     *
     * @param unionid
     */
    public void getUserInfo(String unionid) {
        PATH = HttpUtils.PATH + HttpUtils.SHOP_MEMBER_CENTER +
                "unionid=" + unionid + "&stamp=" + (System.currentTimeMillis() / 1000) + "&doc=" +
                MD5Util.getMD5String(HttpUtils.SHOP_MEMBER_CENTER + "unionid=" + unionid + "&stamp=" + (System.currentTimeMillis() / 1000) + "&dequanhuibaocom");

        params = new RequestParams(PATH);
        System.out.println("个人信息 = " + PATH);

        x.http().get(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("个人信息 = " + result);

                        userInfo = GsonUtil.gsonIntance().gsonToBean(result, UserInfo.class);
                        memberBean = userInfo.getData().getMember();

                        id = memberBean.getId();
                        avatar = memberBean.getAvatar();
                        nickname = memberBean.getNickname();
                        credit1 = memberBean.getCredit1();
                        credit2 = memberBean.getCredit2();
                        couponcount = userInfo.getData().getCounts().getCouponcount();


                        setUserInfo(userInfo.getData().getMember().getAvatar());

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

    /**
     * 更新UI
     *
     * @param avatar
     */
    public void setUserInfo(String avatar) {
        Glide.with(getActivity())
                .load(avatar)
                .bitmapTransform(new GlideCircleTransform(getActivity()))
                .crossFade(1000)
                .into(ivMemcen);
        tvMcId.setText("[会员ID：" + id + "]");
        tvMcNickname.setText("" + nickname);
        tvMcCredit1.setText("" + credit1);
        tvMcCredit2.setText("" + credit2);
        tvMcCouponcount.setText("" + couponcount);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == 2) {
                Bundle bundle = data.getExtras();
                String uid = bundle.getString("uid");

                isLogin();

                System.out.println("登录成功跳转 uid= " + uid);

            }
        }
    }
}
