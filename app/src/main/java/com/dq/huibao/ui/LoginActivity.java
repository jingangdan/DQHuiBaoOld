package com.dq.huibao.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dq.huibao.R;
import com.dq.huibao.base.BaseActivity;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description：
 * Created by jingang on 2017/10/26.
 */
public class LoginActivity extends BaseActivity {
    /*第三方登录*/
    @Bind(R.id.iv_weixin)
    ImageView ivWeixin;
    @Bind(R.id.iv_qq)
    ImageView ivQq;

    /*输入账号*/
    @Bind(R.id.et_login_phone)
    EditText etLoginPhone;

    /*清除账号*/
    @Bind(R.id.iv_phone_clear)
    ImageView ivPhoneClear;

    /*输入密码*/
    @Bind(R.id.et_login_pwd)
    EditText etLoginPwd;

    /*清除密码*/
    @Bind(R.id.iv_pwd_clear)
    ImageView ivPwdClear;

    /*立即注册*/
    @Bind(R.id.tv_login_regist)
    TextView tvLoginRegist;

    /*忘记密码*/
    @Bind(R.id.tv_forget_pwd)
    TextView tvForgetPwd;

    /*登录*/
    @Bind(R.id.but_login)
    Button butLogin;


    private static final String TAG = "MainActivity";
    private static final String APP_ID = "1106502346";//官方获取的APPID

    private Tencent mTencent;
    private BaseUiListener mIUiListener;
    private UserInfo mUserInfo;

    /*接受页面传值*/
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }

    @SuppressLint("WrongConstant")
    @Override
    protected void initWidght() {
        super.initWidght();
        setTitleName("会员登录");
        mTencent = Tencent.createInstance(APP_ID, getApplicationContext());

    }

    @SuppressLint("WrongConstant")
    @OnClick({R.id.iv_weixin, R.id.iv_qq,R.id.iv_phone_clear, R.id.iv_pwd_clear, R.id.tv_login_regist, R.id.tv_forget_pwd, R.id.but_login})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.iv_phone_clear:

                break;
            case R.id.iv_pwd_clear:

                break;
            case R.id.tv_login_regist:
                //立即注册
                intent = new Intent(LoginActivity.this, RegistActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_forget_pwd:
                //忘记密码
                intent = new Intent(LoginActivity.this, RegistActivity.class);
                startActivity(intent);

                break;
            case R.id.but_login:

                break;

            case R.id.iv_weixin:
                Toast.makeText(LoginActivity.this, "微信登录", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_qq:

                /**通过这句代码，SDK实现了QQ的登录，这个方法有三个参数，第一个参数是context上下文，第二个参数SCOPO 是一个String类型的字符串，表示一些权限
                 官方文档中的说明：应用需要获得哪些API的权限，由“，”分隔。例如：SCOPE = “get_user_info,add_t”；所有权限用“all”
                 第三个参数，是一个事件监听器，IUiListener接口的实例，这里用的是该接口的实现类 */
                mIUiListener = new BaseUiListener();
                //all表示获取所有权限
                mTencent.login(LoginActivity.this, "all", mIUiListener);

                break;
        }
    }

    /**
     * 自定义监听器实现IUiListener接口后，需要实现的3个方法
     * onComplete完成 onError错误 onCancel取消
     */
    private class BaseUiListener implements IUiListener {

        @SuppressLint("WrongConstant")
        @Override
        public void onComplete(Object response) {
            Toast.makeText(LoginActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "response:" + response);
            JSONObject obj = (JSONObject) response;
            try {
                String openID = obj.getString("openid");
                String accessToken = obj.getString("access_token");
                String expires = obj.getString("expires_in");
                mTencent.setOpenId(openID);
                mTencent.setAccessToken(accessToken, expires);
                QQToken qqToken = mTencent.getQQToken();
                mUserInfo = new UserInfo(getApplicationContext(), qqToken);
                mUserInfo.getUserInfo(new IUiListener() {
                    @Override
                    public void onComplete(Object response) {
                        Log.e(TAG, "登录成功" + response.toString());
                    }

                    @Override
                    public void onError(UiError uiError) {
                        Log.e(TAG, "登录失败" + uiError.toString());
                    }

                    @Override
                    public void onCancel() {
                        Log.e(TAG, "登录取消");

                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @SuppressLint("WrongConstant")
        @Override
        public void onError(UiError uiError) {
            Toast.makeText(LoginActivity.this, "授权失败", Toast.LENGTH_SHORT).show();

        }

        @SuppressLint("WrongConstant")
        @Override
        public void onCancel() {
            Toast.makeText(LoginActivity.this, "授权取消", Toast.LENGTH_SHORT).show();

        }

    }

    /**
     * 在调用Login的Activity或者Fragment中重写onActivityResult方法
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_LOGIN) {
            Tencent.onActivityResultData(requestCode, resultCode, data, mIUiListener);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}

