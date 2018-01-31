package com.dq.huibao.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dq.huibao.R;
import com.dq.huibao.adapter.memcen.RechargeActivity;
import com.dq.huibao.base.BaseFragment;
import com.dq.huibao.bean.account.Account;
import com.dq.huibao.bean.account.Login;
import com.dq.huibao.bean.memcen.Sign;
import com.dq.huibao.bean.memcen.SignIndex;
import com.dq.huibao.refresh.PullToRefreshView;
import com.dq.huibao.ui.LoginActivity;
import com.dq.huibao.ui.addr.AddressListActivity;
import com.dq.huibao.ui.memcen.AboutUsActivity;
import com.dq.huibao.ui.memcen.CollectActivity;
import com.dq.huibao.ui.memcen.CouponsActivity;
import com.dq.huibao.ui.memcen.FootprintActivity;
import com.dq.huibao.ui.memcen.MemcenActivity;
import com.dq.huibao.ui.memcen.ShopcarActivity;
import com.dq.huibao.ui.memcen.SignRuleActivity;
import com.dq.huibao.ui.order.OrderActivity;
import com.dq.huibao.utils.CodeUtils;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpUtils;
import com.dq.huibao.utils.MD5Util;
import com.dq.huibao.utils.SPUserInfo;
import com.dq.huibao.utils.ScreenUtils;
import com.dq.huibao.view.DoubleWaveView;
import com.dq.huibao.view.GlideCircleTransform;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description：会员中心
 * Created by jingang on 2017/10/18.
 */
public class FMMemCen extends BaseFragment implements
        PullToRefreshView.OnHeaderRefreshListener,
        PullToRefreshView.OnFooterRefreshListener {

    /*登录状态*/
    @Bind(R.id.lin_percen_login)
    LinearLayout linPercenLogin;
    @Bind(R.id.lin_percen_nologin)
    LinearLayout linPercenNoLogin;

    /*登录*/
    @Bind(R.id.but_percen_login)
    Button butLogin;

    /**/
    @Bind(R.id.tv_nologin_title)
    TextView tvNologinTitle;
    @Bind(R.id.ptrv_mem)
    PullToRefreshView pullToRefreshView;
    @Bind(R.id.but_mem_recharge)
    Button butMemRecharge;

    private View view;

    /*页面跳转*/
    private Intent intent;

    /*本地轻量型缓存*/
    private SPUserInfo spUserInfo;
    private String unionid = "";
    private String phone = "", token = "";

    /*接口地址*/
    private String PATH = "";
    private String MD5_PATH = "";
    private RequestParams params = null;

    /*用户头像*/
    @Bind(R.id.iv_memcen)
    ImageView ivMemcen;

    @Bind(R.id.iv_mc_setting)
    ImageView ivMcSetting;
    @Bind(R.id.iv_mc_sign)
    ImageView ivMcSign;

    /*会员等级 id 昵称 余额 积分 优惠券*/
    @Bind(R.id.tv_mc_level)
    TextView tvMcLevel;
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

    @Bind(R.id.but_mc_aboutus)
    Button butMcAboutus;

    @Bind(R.id.but_mc_collect)
    Button butMcCollect;

    /*会员等级 头像 昵称 余额 积分*/
    private String level, id, avatar, nickname, credit1, credit2, couponcount;

    @Bind(R.id.rootView)
    RelativeLayout rootView;
    private DoubleWaveView waveView, waveView2, waveView3;

    /*dialog*/
    private TextView tv_sign, tv_sign_rule, tv_sign_days;

    private Boolean cansign = false;
    private String cur_count = "", cur_money = "";

    @SuppressLint("WrongConstant")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_memcen, null);
        ButterKnife.bind(this, view);
        tvNologinTitle.setText("个人中心");

        initWaveView();

        isLogin();

        pullToRefreshView.setOnHeaderRefreshListener(this);
        pullToRefreshView.setOnFooterRefreshListener(this);
        pullToRefreshView.setLastUpdated(new Date().toLocaleString());

        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            System.out.println("离开FMShopCart");

        } else {
            System.out.println("刷新FMShopCart");
            isLogin();

        }

    }

    /*浪花效果*/
    private void initWaveView() {
        waveView = new DoubleWaveView(getActivity(), ScreenUtils.getScreenWidth(getActivity()), 200, "#30ffffff");
        waveView2 = new DoubleWaveView(getActivity(), ScreenUtils.getScreenWidth(getActivity()), 200, "#50ffffff");
        waveView3 = new DoubleWaveView(getActivity(), ScreenUtils.getScreenWidth(getActivity()), 200, "#70ffffff");

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
        rootView.addView(waveView, params);
        rootView.addView(waveView2, params);
        rootView.addView(waveView3, params);

        waveView.startAnimation(3000);
        waveView2.startAnimation(4000);
        waveView3.startAnimation(5000);
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
            R.id.iv_memcen, R.id.iv_mc_setting, R.id.iv_mc_sign,
            R.id.lin_mc_credit1, R.id.lin_mc_credit2, R.id.lin_mc_couponcount,
            R.id.rel_mc_orders, R.id.but_mc_status0, R.id.but_mc_status1, R.id.but_mc_status2, R.id.but_mc_status3,

            R.id.but_mc_menu0, R.id.but_mc_menu1, R.id.but_mc_menu2,
            R.id.but_mc_menu3, R.id.but_mc_menu4, R.id.but_mc_menu5,
            R.id.but_mc_menu6, R.id.but_mc_menu7, R.id.but_mc_menu8,
            R.id.but_mc_menu9, R.id.but_mc_menu10, R.id.but_mc_menu11,
            R.id.but_mc_menu12, R.id.but_mc_menu13,
            R.id.but_mc_aboutus, R.id.but_mc_collect,
            R.id.but_mem_recharge
    })
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_percen_login:
                //登录
                intent = new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(intent, CodeUtils.MEMBER);
                break;

            case R.id.iv_mc_setting:
                //设置
                intent = new Intent(getActivity(), MemcenActivity.class);
                startActivityForResult(intent, CodeUtils.MEMBER);
                break;

            case R.id.iv_mc_sign:
                //签到
//                View view = getLayoutInflater().inflate(R.layout.dialog_sign, null);
//                AlertDialog dialog = new AlertDialog.Builder(getActivity())
//                        .setView(view)
//                        .create();
//
//                //设置弹框的高为屏幕的一半宽是屏幕的宽
//                WindowManager windowManager = getActivity().getWindowManager();
//                Display display = windowManager.getDefaultDisplay();
//                WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
//                lp.width = (int) (display.getWidth() * 0.5); //设置宽度
//                lp.height = (int) (display.getHeight() * 0.5); //设置宽度
//                dialog.getWindow().setAttributes(lp);
//                dialog.show();

                final AlertDialog dlg = new AlertDialog.Builder(getActivity()).create();
                dlg.show();
                Window window = dlg.getWindow();
                window.setGravity(Gravity.CENTER);//设置弹框在屏幕的下方
                window.setContentView(R.layout.dialog_sign);
                tv_sign = window.findViewById(R.id.tv_sign);
                tv_sign_rule = window.findViewById(R.id.tv_sign_rule);
                tv_sign_days = window.findViewById(R.id.tv_sign_days);

                if (cansign) {
                    tv_sign.setText("签到");
                } else {
                    tv_sign.setText("已签到");
                }
                tv_sign_days.setText("" + cur_count + "天");

                //设置弹框的高为屏幕的一半宽是屏幕的宽
                WindowManager windowManager = getActivity().getWindowManager();
                Display display = windowManager.getDefaultDisplay();
                WindowManager.LayoutParams lp = dlg.getWindow().getAttributes();
                lp.width = (int) (display.getWidth() * 0.7); //设置宽度
                lp.height = (int) (display.getHeight() * 0.5); //设置宽度
                dlg.getWindow().setAttributes(lp);

                tv_sign.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //toast("签到");
                        if (cansign) {
                            setSign(phone, token);
                        } else {
                            toast("已签到");
                        }

                    }
                });

                tv_sign_rule.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        intent = new Intent(getActivity(), SignRuleActivity.class);
                        intent.putExtra("phone", phone);
                        intent.putExtra("token", token);
                        startActivity(intent);

                        dlg.dismiss();
                    }
                });

                break;

            /*个人信息*/
            case R.id.iv_memcen:
                intent = new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(intent, CodeUtils.MEMBER);
                break;

            case R.id.but_mem_recharge:
                //充值
                intent = new Intent(getActivity(), RechargeActivity.class);
                intent.putExtra("phone", phone);
                intent.putExtra("token", token);
                startActivityForResult(intent, CodeUtils.MEMBER);
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
                //toast("优惠券");
//                intent = new Intent(getActivity(), CouponsActivity.class);
//                startActivity(intent);
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
                setIntentOrder(5);
                break;

            /*menu*/
            case R.id.but_mc_menu0:
                //分销中心
                toast("分销中心");
                break;
            case R.id.but_mc_menu1:
                //我的资料
                intent = new Intent(getActivity(), MemcenActivity.class);
                startActivity(intent);
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

            case R.id.but_mc_aboutus:
                //关于我们
                intent = new Intent(getActivity(), AboutUsActivity.class);
                startActivity(intent);

                break;
            case R.id.but_mc_collect:
                intent = new Intent(getActivity(), CollectActivity.class);
                intent.putExtra("phone", phone);
                intent.putExtra("token", token);
                startActivity(intent);
                break;
            case R.id.but_mc_menu13:
                //退出登录
                dialog(phone, token);

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
        intent.putExtra("phone", phone);
        intent.putExtra("token", token);
        startActivityForResult(intent, CodeUtils.MEMBER);
        //startActivity(intent);
    }

    /*
    * 判断登录状态
    *  */
    @SuppressLint("WrongConstant")
    public void isLogin() {
        spUserInfo = new SPUserInfo(getActivity().getApplication());

        if (spUserInfo.getLogin().equals("1")) {

            if (!(spUserInfo.getLoginReturn().equals(""))) {
                Login login = GsonUtil.gsonIntance().gsonToBean(spUserInfo.getLoginReturn(), Login.class);
                phone = login.getData().getPhone();
                token = login.getData().getToken();

                getMember(phone, token);

                getSignIndex(phone, token);
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
     * @param phone
     * @param token
     */
    public void getMember(String phone, String token) {
        MD5_PATH = "phone=" + phone + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token;

        PATH = HttpUtils.PATHS + HttpUtils.MEM_MEMBER + MD5_PATH + "&sign=" +
                MD5Util.getMD5String(MD5_PATH + HttpUtils.KEY);

        params = new RequestParams(PATH);
        System.out.println("个人信息 = " + PATH);

        x.http().get(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("个人信息 = " + result);
                        Login login = GsonUtil.gsonIntance().gsonToBean(result, Login.class);

                        level = login.getData().getRole_id();
                        id = login.getData().getUid();
                        avatar = login.getData().getHeadimgurl();
                        nickname = login.getData().getNickname();
                        credit1 = login.getData().getBalance();
                        credit2 = login.getData().getScore();
                        setUserInfo(avatar);

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
                .load(HttpUtils.NEW_HEADER + avatar)
                .bitmapTransform(new GlideCircleTransform(getActivity()))
                .crossFade(1000)
                .error(R.mipmap.ic_header)
                .into(ivMemcen);

        //会员等级
        if (level.equals("0")) {
            //普通会员
            level = "普通会员";
            tvMcLevel.setText("[会员ID：" + level + "]");
        } else if (level.equals("381")) {
            //一号会员
            level = "一号会员";
            tvMcLevel.setText("[会员ID：" + level + "]");
        } else if (level.equals("382")) {
            //总部茶话会
            level = "总部茶话会";
            tvMcLevel.setText("[会员ID：" + level + "]");
        }

        tvMcId.setText("[会员ID：" + id + "]");
        tvMcNickname.setText("" + nickname);
        tvMcCredit1.setText("" + credit1);
        tvMcCredit2.setText("" + credit2);

    }

    /*弹出框*/
    protected void dialog(final String phone, final String token) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("确认退出登录吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                loginOut(phone, token);

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        builder.create().show();
    }

    /**
     * 退出登录
     *
     * @param phone
     * @param token
     */
    public void loginOut(String phone, String token) {

        PATH = HttpUtils.PATHS + HttpUtils.ACCOUNT_LOGINOUT +
                "phone=" + phone + "&token=" + token + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&sign=" +
                MD5Util.getMD5String("phone=" + phone + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token + "&key=ivKDDIZHF2b0Gjgvv2QpdzfCmhOpya5k");

        params = new RequestParams(PATH);
        System.out.println("退出登录 = " + PATH);
        x.http().get(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("退出登录 = " + result);
                        Account account = GsonUtil.gsonIntance().gsonToBean(result, Account.class);

                        if (account.getStatus() == 1) {
                            spUserInfo.saveLogin("");
                            spUserInfo.saveLoginReturn("");
                            isLogin();
                        } else {
                            toast("" + account.getData());
                        }
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
     * 签到
     *
     * @param phone
     * @param token
     */
    public void setSign(String phone, String token) {
        MD5_PATH = "phone=" + phone + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token;
        PATH = HttpUtils.PATHS + HttpUtils.ACTIVITY_SIGN + MD5_PATH + "&sign=" +
                MD5Util.getMD5String(MD5_PATH + HttpUtils.KEY);

        params = new RequestParams(PATH);
        System.out.println("签到 = " + PATH);
        x.http().post(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("签到 = " + result);
                        Sign sign = GsonUtil.gsonIntance().gsonToBean(result, Sign.class);
                        if (sign.getStatus() == 1) {
                            toast("" + sign.getData().getMsg());
                            tv_sign.setText("已签到");
                        } else if (sign.getStatus() == 0) {
                            toast("" + sign.getData().getMsg());
                        }

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
                        SignIndex signIndex = GsonUtil.gsonIntance().gsonToBean(result, SignIndex.class);
                        if (signIndex.getStatus() == 1) {
                            cansign = signIndex.getData().isCansign();
                            cur_count = signIndex.getData().getCur_count();
                            cur_money = signIndex.getData().getCur_money();
                        }

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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CodeUtils.MEMBER) {
            if (resultCode == CodeUtils.LOGIN || resultCode == CodeUtils.MEMBER_EDIT || resultCode == CodeUtils.ORDER || resultCode == CodeUtils.RECHARGE) {
                isLogin();
            }
        }
    }

    @Override
    public void onFooterRefresh(PullToRefreshView view) {
        pullToRefreshView.postDelayed(new Runnable() {

            @Override
            public void run() {
                //加载更多数据
                pullToRefreshView.onFooterRefreshComplete();

            }

        }, 1000);
    }

    @Override
    public void onHeaderRefresh(PullToRefreshView view) {
        pullToRefreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                //刷新数据
                pullToRefreshView.onHeaderRefreshComplete("更新于:"
                        + Calendar.getInstance().getTime().toLocaleString());
                pullToRefreshView.onHeaderRefreshComplete();

                isLogin();

            }

        }, 1000);
    }

}
