package com.dq.huibao.ui;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dq.huibao.R;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.bean.LoginBean;
import com.dq.huibao.bean.wechat.WeChat;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpUtils;
import com.dq.huibao.utils.MD5Util;
import com.dq.huibao.utils.SPUserInfo;
import com.mob.tools.utils.UIHandler;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

import static android.R.attr.action;


/**
 * Description：会员登录
 * Created by jingang on 2017/10/26.
 */
public class LoginActivity extends BaseActivity implements PlatformActionListener, Handler.Callback, View.OnClickListener {

    private static final int MSG_ACTION_CCALLBACK = 0;
    @Bind(R.id.et_login_phone)
    EditText etLoginPhone;
    @Bind(R.id.iv_phone_clear)
    ImageView ivPhoneClear;
    @Bind(R.id.et_login_pwd)
    EditText etLoginPwd;
    @Bind(R.id.iv_pwd_clear)
    ImageView ivPwdClear;
    @Bind(R.id.tv_login_regist)
    TextView tvLoginRegist;
    @Bind(R.id.tv_forget_pwd)
    TextView tvForgetPwd;
    @Bind(R.id.but_login)
    Button butLogin;
    @Bind(R.id.iv_weixin)
    ImageView ivWeixin;
    @Bind(R.id.iv_qq)
    ImageView ivQq;

    @Bind(R.id.iv_pwd_eye)
    ImageView ivPwdEye;
    //    private ImageView ivWxLogin;
//    private ImageView ivQqLogin;
    //private ImageView ivBlog;

    @Bind(R.id.lin_login_main)
    LinearLayout linLoginMain;

    private ProgressDialog progressDialog;

    /*页面传值*/
    private Intent intent;

    /*接口地址*/
    private String PATH = "";
    private RequestParams params = null;

    /*本地轻量型缓存*/
    private SPUserInfo spUserInfo;

    private TextWatcher username_watcher;
    private TextWatcher password_watcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initWatcher();


        ShareSDK.initSDK(this);
        spUserInfo = new SPUserInfo(getApplication());

        etLoginPhone.addTextChangedListener(username_watcher);
        etLoginPwd.addTextChangedListener(password_watcher);

        linLoginMain.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                @SuppressLint("WrongConstant")
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(etLoginPhone.getWindowToken(), 0);
                return true;
            }
        });
        linLoginMain.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                @SuppressLint("WrongConstant")
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(etLoginPwd.getWindowToken(), 0);
                return true;
            }
        });

    }

    @OnClick({R.id.iv_phone_clear, R.id.iv_pwd_clear, R.id.iv_pwd_eye,
            R.id.tv_login_regist, R.id.tv_forget_pwd, R.id.but_login,
            R.id.iv_weixin, R.id.iv_qq})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_phone_clear:
                //清除用户输入
                etLoginPhone.setText("");
                etLoginPwd.setText("");
                break;
            case R.id.iv_pwd_clear:
                etLoginPwd.setText("");
                break;

            case R.id.iv_pwd_eye:
                //密码是否可见
                if (etLoginPwd.getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
                    //ivPwdEys.setBackgroundResource(R.mipmap.ic_look001);
                    etLoginPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
                } else {
                    //ivPwdEys.setBackgroundResource(R.mipmap.ic_look001);
                    etLoginPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                etLoginPwd.setSelection(etLoginPwd.getText().toString().length());

                break;
            case R.id.tv_login_regist:
                intent = new Intent(LoginActivity.this, RegistActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_forget_pwd:
                break;
            case R.id.but_login:
                break;
            case R.id.iv_weixin:
                Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
                wechat.setPlatformActionListener(this);
                wechat.SSOSetting(false);
                authorize(wechat, 1);
                break;
            case R.id.iv_qq:
                Platform qq = ShareSDK.getPlatform(QQ.NAME);
                qq.setPlatformActionListener(this);
                qq.SSOSetting(false);
                authorize(qq, 2);
                break;

            default:
                break;
        }
    }

    /**
     * 手机号，密码输入控件公用这一个watcher
     */
    private void initWatcher() {
        username_watcher = new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @SuppressLint("WrongConstant")
            public void afterTextChanged(Editable s) {
                etLoginPwd.setText("");
                if (s.toString().length() > 0) {
                    ivPhoneClear.setVisibility(View.VISIBLE);
                } else {
                    ivPhoneClear.setVisibility(View.INVISIBLE);
                }
            }
        };

        password_watcher = new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @SuppressLint("WrongConstant")
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0) {
                    ivPwdClear.setVisibility(View.VISIBLE);
                } else {
                    ivPwdClear.setVisibility(View.INVISIBLE);
                }
            }
        };
    }

    //授权
    private void authorize(Platform plat, int type) {
        switch (type) {
            case 1:
                showProgressDialog("开启微信");
                break;
            case 2:
                showProgressDialog("开启QQ");
                break;
//            case 3:
//                showProgressDialog(getString(R.string.opening_blog));
//                break;
        }
        if (plat.isValid()) { //如果授权就删除授权资料
            plat.removeAccount();
        }
        plat.showUser(null);//授权并获取用户信息
    }

    //登陆授权成功的回调
    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> res) {
        Message msg = new Message();
        msg.what = MSG_ACTION_CCALLBACK;
        msg.arg1 = 1;
        msg.arg2 = action;
        msg.obj = platform;
        UIHandler.sendMessage(msg, this);   //发送消息

    }

    //登陆授权错误的回调
    @Override
    public void onError(Platform platform, int i, Throwable t) {
        Message msg = new Message();
        msg.what = MSG_ACTION_CCALLBACK;
        msg.arg1 = 2;
        msg.arg2 = action;
        msg.obj = t;
        UIHandler.sendMessage(msg, this);
    }

    //登陆授权取消的回调
    @Override
    public void onCancel(Platform platform, int i) {
        Message msg = new Message();
        msg.what = MSG_ACTION_CCALLBACK;
        msg.arg1 = 3;
        msg.arg2 = action;
        msg.obj = platform;
        UIHandler.sendMessage(msg, this);
    }

    //登陆发送的handle消息在这里处理
    @SuppressLint("WrongConstant")
    @Override
    public boolean handleMessage(Message message) {
        hideProgressDialog();
        switch (message.arg1) {
            case 1: { // 成功
                Toast.makeText(LoginActivity.this, "授权登陆成功", Toast.LENGTH_SHORT).show();

                //获取用户资料
                Platform platform = (Platform) message.obj;
                String userId = platform.getDb().getUserId();//获取用户账号
                String userName = platform.getDb().getUserName();//获取用户名字
                String userIcon = platform.getDb().getUserIcon();//获取用户头像
                String userGender = platform.getDb().getUserGender(); //获取用户性别，m = 男, f = 女，如果微信没有设置性别,默认返回null
                Toast.makeText(LoginActivity.this, "用户信息为--用户名：" + userName + "  性别：" + userGender, Toast.LENGTH_SHORT).show();

                WeChat weChat = GsonUtil.gsonIntance().gsonToBean(platform.getDb().exportData(), WeChat.class);

                System.out.println("用户头像 = " + userIcon);

                System.out.println("用户头像 = " + weChat.getIcon());

                postLoginTest(weChat.getUnionid());

                //下面就可以利用获取的用户信息登录自己的服务器或者做自己想做的事啦!
                //。。。

            }
            break;
            case 2: { // 失败
                Toast.makeText(LoginActivity.this, "授权登陆失败", Toast.LENGTH_SHORT).show();
            }
            break;
            case 3: { // 取消
                Toast.makeText(LoginActivity.this, "授权登陆取消", Toast.LENGTH_SHORT).show();
            }
            break;
        }
        return false;
    }

    //显示dialog
    public void showProgressDialog(String message) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(true);
        progressDialog.show();
    }

    //隐藏dialog
    public void hideProgressDialog() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    /**
     * 测试登录
     *
     * @param unionid
     */
    public void postLoginTest(String unionid) {

        PATH = HttpUtils.PATH + HttpUtils.SHOP_GOODS_LOGIN +
                "unionid=" + unionid + "&stamp=" + (System.currentTimeMillis() / 1000) + "&doc=" +
                MD5Util.getMD5String(HttpUtils.SHOP_GOODS_LOGIN + "unionid=" + unionid + "&stamp=" + (System.currentTimeMillis() / 1000) + "&dequanhuibaocom");

        params = new RequestParams(PATH);
        System.out.println("测试登录 = " + PATH);
        x.http().post(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("测试登录 = " + result);
                        LoginBean loginBean = GsonUtil.gsonIntance().gsonToBean(result, LoginBean.class);
                        if (loginBean.getResult().equals("1")) {
                            toast("登录成功");
                            spUserInfo.saveLogin("1");//微信登录成功记录 1
                            spUserInfo.saveLoginReturn(result);//登录成功记录返回信息

                            intent = new Intent();
                            intent.putExtra("uid", loginBean.getData().getId());
                            setResult(2, intent);

                            LoginActivity.this.finish();

                        } else if (loginBean.getResult().equals("0")) {
                            toast("登录失败");
                        } else {
                            toast("未知原因");
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

}

