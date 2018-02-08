package com.dq.huibao.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dq.huibao.Interface.HomePageInterface;
import com.dq.huibao.R;
import com.dq.huibao.base.BaseFragment;
import com.dq.huibao.bean.account.Login;
import com.dq.huibao.bean.index.Index;
import com.dq.huibao.bean.memcen.Sign;
import com.dq.huibao.bean.memcen.SignIndex;
import com.dq.huibao.homepage.HomeRecycleViewAdapter;
import com.dq.huibao.refresh.PullToRefreshView;
import com.dq.huibao.ui.GoodsDetailsActivity;
import com.dq.huibao.ui.GoodsListActivity;
import com.dq.huibao.ui.KeywordsActivity;
import com.dq.huibao.ui.LoginActivity;
import com.dq.huibao.ui.homepage.WebActivity;
import com.dq.huibao.ui.memcen.SignRuleActivity;
import com.dq.huibao.utils.CodeUtils;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.HttpxUtils;
import com.dq.huibao.utils.MD5Util;
import com.dq.huibao.utils.SPUserInfo;
import com.dq.huibao.view.CustomProgress;

import org.xutils.common.Callback;
import org.xutils.ex.HttpException;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 首页
 * Created by jingang on 2018/1/29.
 */

public class FMHomePage extends BaseFragment implements
        HomePageInterface
        ,
        PullToRefreshView.OnFooterRefreshListener,
        PullToRefreshView.OnHeaderRefreshListener {

    @Bind(R.id.lv_home_listview)
    RecyclerView recyclerView;

    @Bind(R.id.ptrv_hp)
    PullToRefreshView pullToRefreshView;

    /*无网络或网络不佳*/
    @Bind(R.id.lin_hp_nonetwork)
    LinearLayout linHpNonetwork;
    @Bind(R.id.lin_hp_network)
    LinearLayout linHpNetwork;
    @Bind(R.id.but_refresh)
    Button butRefresh;
    private View view;
    private CustomProgress progressDialog = null;

    /*接口地址*/
    private String PATH = "", MD5_PATH = "";
    private RequestParams params = null;

    private HomeRecycleViewAdapter homeRecycleAdapter;
    List<Index.DataBean> dataList = new ArrayList<>();

    /**/
    private Intent intent;

    /*本地轻量型缓存*/
    private SPUserInfo spUserInfo;
    private String phone = "", token = "";

    /*签到信息*/
    private TextView tv_sign, tv_sign_rule, tv_sign_days;
    private Boolean cansign = false;
    private String cur_count = "", cur_money = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fm_homepage, null);
        ButterKnife.bind(this, view);

        startProgressDialog();

        getLogin();

        pullToRefreshView.setOnHeaderRefreshListener(this);
        pullToRefreshView.setOnFooterRefreshListener(this);
        pullToRefreshView.setLastUpdated(new Date().toLocaleString());
        return view;
    }

    /*获取登录返回的数据*/
    public void getLogin() {
        if (isLogin()) {
            if (!(spUserInfo.getLoginReturn().equals(""))) {
                Login login = GsonUtil.gsonIntance().gsonToBean(spUserInfo.getLoginReturn(), Login.class);
                phone = login.getData().getPhone();
                token = login.getData().getToken();
                getIndex(phone, token);

                getSignIndex(phone, token);
            }

        } else {
            getIndex(phone, token);
        }
    }

    /*登录状态*/
    public boolean isLogin() {
        spUserInfo = new SPUserInfo(getActivity().getApplication());
        if (spUserInfo.getLogin().equals("1")) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 获取首页信息
     * <p>
     * -url链接
     * article文章
     * cate分类
     * goods商品
     * custom自定义分类
     * articlecate文章分类
     * search  搜索
     * url # 不做操作
     */
    public void getIndex(String phone, String token) {
        PATH = HttpPath.PATHS + HttpPath.INDEXT_INDEX;
        System.out.println("首页 = " + PATH);
        HttpxUtils.Get(PATH, null, new Callback.CommonCallback<String>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onSuccess(String result) {
                System.out.println("首页 = " + result);
                stopProgressDialog();
                linHpNetwork.setVisibility(View.VISIBLE);
                linHpNonetwork.setVisibility(View.GONE);
                Index index = GsonUtil.gsonIntance().gsonToBean(result, Index.class);
                dataList.clear();
                dataList.add(index.getData());

                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                homeRecycleAdapter = new HomeRecycleViewAdapter(getActivity(), dataList);
                recyclerView.setAdapter(homeRecycleAdapter);

                homeRecycleAdapter.setHpInterface(FMHomePage.this);
            }

            @SuppressLint("WrongConstant")
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                stopProgressDialog();
                linHpNetwork.setVisibility(View.GONE);
                linHpNonetwork.setVisibility(View.VISIBLE);
                //toast(ex.getMessage());
                if (ex instanceof HttpException) {
                    //网络错误
                    HttpException httpEx = (HttpException) ex;
                    int responseCode = httpEx.getCode();
                    String responseMsg = httpEx.getMessage();
                    String errorResult = httpEx.getResult();
                    toast("" + responseMsg);
                } else {
                    //其他错误
                    toast("网络不佳，请重试");
                }
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
        PATH = HttpPath.PATHS + HttpPath.ACTIVITYSIGN_INDEX + MD5_PATH + "&sign=" +
                MD5Util.getMD5String(MD5_PATH + HttpPath.KEY);

        System.out.println("签到信息 = " + PATH);

        HttpxUtils.Get(PATH, null, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("签到信息 = " + result);
                SignIndex signIndex = GsonUtil.gsonIntance().gsonToBean(result, SignIndex.class);
                if (signIndex.getStatus() == 1) {
                    cansign = signIndex.getData().isCansign();
                    cur_count = signIndex.getData().getCur_count();
                    cur_money = signIndex.getData().getCur_money();

                    if (cansign) {
                        tv_sign.setText("签到");
                    } else {
                        tv_sign.setText("已签到");
                    }
                    tv_sign_days.setText("" + cur_count + "天");

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
        PATH = HttpPath.PATHS + HttpPath.ACTIVITY_SIGN + MD5_PATH + "&sign=" +
                MD5Util.getMD5String(MD5_PATH + HttpPath.KEY);

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

    @OnClick({R.id.but_refresh})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.but_refresh:
                getLogin();
                break;
            default:
                break;
        }
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void doSearch() {
        intent = new Intent(getActivity(), KeywordsActivity.class);
        startActivityForResult(intent, CodeUtils.HOMEPAGE);
    }

    @Override
    public void doHomePage(int position, String title, String type, String content) {
        switch (type) {
            case "url":
                //链接 web
                intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("url", content);
                startActivityForResult(intent, CodeUtils.HOMEPAGE);
                break;
            case "article":
                //文章
                break;
            case "cate":
                //分类（商品列表）
                intent = new Intent(getActivity(), GoodsListActivity.class);
                intent.putExtra("content", "cate=" + content);
                intent.putExtra("catename", title);
                intent.putExtra("keywords", "");
                startActivityForResult(intent, CodeUtils.HOMEPAGE);
                break;
            case "goods":
                //商品详情
                intent = new Intent(getActivity(), GoodsDetailsActivity.class);
                intent.putExtra("gid", content);
                startActivityForResult(intent, CodeUtils.HOMEPAGE);
                break;
            case "custom":
                //自定义分类
                intent = new Intent(getActivity(), GoodsListActivity.class);
                intent.putExtra("content", "cate=" + content);
                intent.putExtra("catename", title);
                intent.putExtra("keywords", "");
                startActivityForResult(intent, CodeUtils.HOMEPAGE);
                break;
            case "articlecate":
                //文章分类
                break;
            case "search":
                //搜索
                intent = new Intent(getActivity(), GoodsListActivity.class);
                intent.putExtra("content", content);
                intent.putExtra("catename", title);
                intent.putExtra("keywords", "");
                startActivityForResult(intent, CodeUtils.HOMEPAGE);
                break;
            case "#":
                //不做操作
                break;

            case "action":
                if (content.equals("sign")) {
                    if (isLogin()) {
                        if (!(spUserInfo.getLoginReturn().equals(""))) {
                            Login login = GsonUtil.gsonIntance().gsonToBean(spUserInfo.getLoginReturn(), Login.class);
                            phone = login.getData().getPhone();
                            token = login.getData().getToken();

                            final AlertDialog dlg = new AlertDialog.Builder(getActivity()).create();
                            dlg.show();
                            Window window = dlg.getWindow();
                            window.setGravity(Gravity.CENTER);//设置弹框在屏幕的下方
                            window.setContentView(R.layout.dialog_sign);
                            tv_sign = window.findViewById(R.id.tv_sign);
                            tv_sign_rule = window.findViewById(R.id.tv_sign_rule);
                            tv_sign_days = window.findViewById(R.id.tv_sign_days);

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
                        } else {
                            dialog();
                        }
                    } else {
                        /*弹出框*/
                        dialog();
                    }
                }

                break;

            default:
                break;
        }
    }

    /**/
    public void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("确定登录？");
        builder.setTitle("提示：未登录");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                intent = new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(intent, CodeUtils.HOMEPAGE);


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

    /*开始dialog*/
    private void startProgressDialog() {
        if (progressDialog == null) {
            progressDialog = CustomProgress.createDialog(getActivity());
            progressDialog.setMessage("请稍候...");
        }
        progressDialog.show();
    }

    /*结束dialog*/
    private void stopProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
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
                startProgressDialog();
                //刷新数据
                pullToRefreshView.onHeaderRefreshComplete("更新于:"
                        + Calendar.getInstance().getTime().toLocaleString());
                pullToRefreshView.onHeaderRefreshComplete();


                getLogin();

            }

        }, 1000);
    }

}