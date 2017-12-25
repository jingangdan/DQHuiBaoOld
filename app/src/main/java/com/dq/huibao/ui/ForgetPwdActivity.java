package com.dq.huibao.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dq.huibao.R;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.bean.account.Account;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpUtils;
import com.dq.huibao.utils.VerifyType;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description：
 * Created by jingang on 2017/10/27.
 */
public class ForgetPwdActivity extends BaseActivity {
    @Bind(R.id.et_regist_phone)
    EditText etRegistPhone;
    @Bind(R.id.iv_rg_phone_clear)
    ImageView ivRgPhoneClear;
    @Bind(R.id.et_regist_code)
    EditText etRegistCode;
    @Bind(R.id.iv_rg_code_clear)
    ImageView ivRgCodeClear;
    @Bind(R.id.but_get_code)
    Button butGetCode;
    @Bind(R.id.et_regist_pwd)
    EditText etRegistPwd;
    @Bind(R.id.iv_rg_pwd_clear)
    ImageView ivRgPwdClear;
    @Bind(R.id.et_regist_pwd2)
    EditText etRegistPwd2;
    @Bind(R.id.iv_rg_pwd2_clear)
    ImageView ivRgPwd2Clear;
    @Bind(R.id.iv_rg_invitation_clear)
    ImageView ivRgInvitationClear;
    @Bind(R.id.but_regist)
    Button butRegist;
    @Bind(R.id.iv_rg_pwd_eye)
    ImageView ivRgPwdEye;
    @Bind(R.id.iv_rg_pwd2_eye)
    ImageView ivRgPwd2Eye;
    @Bind(R.id.et_rg_invitation_clear)
    EditText etRgInvitationClear;
    @Bind(R.id.lin_rg_main)
    LinearLayout linRgMain;
    @Bind(R.id.cb_register)
    CheckBox cbRegister;

    private TextWatcher tw_phone, tw_code, tw_pwd, tw_pwd2, tw_invitation;

    private String phone = "", code = "", pwd = "", pwd2 = "", invitation = "";

    //控制按钮样式是否改变
    private boolean tag = true;
    //每次验证请求需要间隔60S
    private int i = 60;

    /*借口地址*/
    private String PATH = "";
    private RequestParams params = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        ButterKnife.bind(this);

        butRegist.setText("确定找回");

        initWatcher();

        etRegistPhone.addTextChangedListener(tw_phone);
        etRegistCode.addTextChangedListener(tw_code);
        etRegistPwd.addTextChangedListener(tw_pwd);
        etRegistPwd2.addTextChangedListener(tw_pwd2);
        etRgInvitationClear.addTextChangedListener(tw_invitation);

        linRgMain.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                @SuppressLint("WrongConstant")
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(etRegistPhone.getWindowToken(), 0);
                return true;
            }
        });

    }

    @Override
    protected void initWidght() {
        super.initWidght();
        setTitleName("找回密码");
        // toast("");
    }

    @OnClick({R.id.iv_rg_phone_clear, R.id.iv_rg_code_clear, R.id.but_get_code,
            R.id.iv_rg_pwd_eye, R.id.iv_rg_pwd2_eye,
            R.id.iv_rg_pwd_clear, R.id.iv_rg_pwd2_clear, R.id.iv_rg_invitation_clear,
            R.id.but_regist, R.id.cb_register})
    public void onClick(View view) {

        phone = etRegistPhone.getText().toString();
        code = etRegistCode.getText().toString();
        pwd = etRegistPwd.getText().toString();
        pwd2 = etRegistPwd2.getText().toString();

        switch (view.getId()) {
            case R.id.iv_rg_phone_clear:
                etRegistPhone.setText("");
                break;
            case R.id.iv_rg_code_clear:
                etRegistCode.setText("");
                break;
            case R.id.but_get_code:
                if (isMobile(phone)) {
                    //手机号格式验证通过
                    toast("手机号验证通过");
                    getCheckPhone(phone);
                    changeBtnGetCode();

                } else {
                    //手机号格式验证不通过
                    toast("手机号验证未通过");
                }
                break;
            case R.id.iv_rg_pwd_clear:
                etRegistPwd.setText("");
                break;
            case R.id.iv_rg_pwd2_clear:
                etRegistPwd2.setText("");
                break;

            case R.id.iv_rg_pwd_eye:
                //密码是否可见
                if (etRegistPwd.getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
                    etRegistPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
                } else {
                    etRegistPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                etRegistPwd.setSelection(etRegistPwd.getText().toString().length());
                break;
            case R.id.iv_rg_pwd2_eye:
                //密码是否可见
                if (etRegistPwd2.getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
                    etRegistPwd2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
                } else {
                    etRegistPwd2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                etRegistPwd2.setSelection(etRegistPwd2.getText().toString().length());
                break;
            case R.id.iv_rg_invitation_clear:
                etRgInvitationClear.setText("");
                break;
            case R.id.but_regist:
                //输入手机号
                if (cbRegister.isChecked()) {
                    if (!phone.equals("")) {
                        if (!code.equals("")) {
                            if (!pwd.equals("")) {
                                if (pwd2.equals(pwd)) {
                                    //postReg(phone, code, pwd);
                                    backPwd(phone, code, pwd);
                                } else {
                                    toast("两次密码输入不一致");
                                }
                            } else {
                                toast("密码不可为空");
                            }
                        } else {
                            toast("验证码不可为空");
                        }
                    } else {
                        toast("手机号不可为空");
                    }
                } else {
                    toast("需同意协议");
                }
                break;

            case R.id.cb_register:

                break;
        }
    }

    /**
     * 手机号，密码输入控件公用这一个watcher
     */
    private void initWatcher() {
        tw_phone = new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @SuppressLint("WrongConstant")
            public void afterTextChanged(Editable s) {
                etRegistPwd.setText("");
                etRegistPwd2.setText("");
                if (s.toString().length() > 0) {
                    ivRgPhoneClear.setVisibility(View.VISIBLE);
                } else {
                    ivRgPhoneClear.setVisibility(View.INVISIBLE);
                }
            }
        };

        tw_code = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @SuppressLint("WrongConstant")
            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() > 0) {
                    ivRgCodeClear.setVisibility(View.VISIBLE);
                } else {
                    ivRgCodeClear.setVisibility(View.INVISIBLE);
                }
            }
        };

        tw_pwd = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @SuppressLint("WrongConstant")
            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() > 0) {
                    ivRgPwdClear.setVisibility(View.VISIBLE);
                } else {
                    ivRgPwdClear.setVisibility(View.INVISIBLE);
                }
            }
        };

        tw_pwd2 = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @SuppressLint("WrongConstant")
            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() > 0) {
                    ivRgPwd2Clear.setVisibility(View.VISIBLE);
                } else {
                    ivRgPwd2Clear.setVisibility(View.INVISIBLE);
                }
            }
        };


        tw_invitation = new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @SuppressLint("WrongConstant")
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0) {
                    ivRgInvitationClear.setVisibility(View.VISIBLE);
                } else {
                    ivRgInvitationClear.setVisibility(View.INVISIBLE);
                }
            }
        };
    }

    /**
     * 验证手机号是够已经存在
     *
     * @param phone
     */
    public void getCheckPhone(final String phone) {
        PATH = HttpUtils.PATHS + HttpUtils.ACCOUNT_CHECKPHONE +
                "phone=" + phone;
        params = new RequestParams(PATH);
        System.out.println("验证手机号 = " + PATH);
        x.http().get(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("验证手机号 = " + result);
                        Account account = GsonUtil.gsonIntance().gsonToBean(result, Account.class);
                        if (account.getStatus() == 0) {
                            getVerify(phone, VerifyType.REPWD);
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
     * 发送验证码
     *
     * @param phone
     * @param type
     */
    public void getVerify(String phone, String type) {
        PATH = HttpUtils.PATHS + HttpUtils.ACCOUNT_VERIFY +
                "phone=" + phone + "&type=" + type;

        params = new RequestParams(PATH);
        System.out.println("验证码 = " + PATH);
        x.http().get(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("验证码 = " + result);
                        Account account = GsonUtil.gsonIntance().gsonToBean(result, Account.class);
                        if (account.getStatus() == 1) {
                            etRegistCode.setText("" + account.getData());
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
     * 找回密码
     *
     * @param phone
     * @param verify
     * @param pwd
     */
    public void backPwd(String phone, String verify, String pwd) {
        PATH = HttpUtils.PATHS + HttpUtils.ACCOUNT_BACKPWD +
                "phone=" + phone + "&verify=" + verify + "&pwd=" + pwd;

        params = new RequestParams(PATH);
        System.out.println("找回密码 = " + PATH);
        x.http().get(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("找回密码 = " + result);
                        Account account = GsonUtil.gsonIntance().gsonToBean(result, Account.class);
                        if (account.getStatus() == 1) {
                            toast("" + account.getData());
                            ForgetPwdActivity.this.finish();
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
     * 注册
     *
     * @param phone
     * @param verify 验证码
     * @param pwd
     */
    public void postReg(String phone, String verify, String pwd) {
        PATH = HttpUtils.PATHS + HttpUtils.ACCOUNT_REG +
                "phone=" + phone + "&verify=" + verify + "&pwd=" + pwd;

        params = new RequestParams(PATH);
        System.out.println("注册 = " + PATH);

        x.http().get(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("注册 = " + result);
                        Account account = GsonUtil.gsonIntance().gsonToBean(result, Account.class);
                        if (account.getStatus() == 1) {
                            toast("" + account.getData());
                            ForgetPwdActivity.this.finish();
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
     * 手机号验证
     *
     * @param str
     * @return 验证通过返回true
     */
    public static boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }

    /**
     * 改变按钮样式
     */
    private void changeBtnGetCode() {

        Thread thread = new Thread() {
            @Override
            public void run() {
                if (tag) {
                    while (i > 0) {
                        i--;
                        //如果活动为空
                        if (this == null) {
                            break;
                        }

                        ForgetPwdActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                butGetCode.setText("获取验证码(" + i + ")");
                                butGetCode.setClickable(false);
                            }
                        });

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    tag = false;
                }
                i = 60;
                tag = true;

                if (this != null) {
                    ForgetPwdActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            butGetCode.setText("获取验证码");
                            butGetCode.setClickable(true);
                        }
                    });
                }
            }
        };
        thread.start();
    }

}
