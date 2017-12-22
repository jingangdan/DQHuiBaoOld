package com.dq.huibao.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description：
 * Created by jingang on 2017/10/27.
 */
public class RegistActivity extends BaseActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        ButterKnife.bind(this);

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
        setTitleName("会员注册");
        // toast("");
    }

    @OnClick({R.id.iv_rg_phone_clear, R.id.iv_rg_code_clear, R.id.but_get_code,
            R.id.iv_rg_pwd_eye, R.id.iv_rg_pwd2_eye,
            R.id.iv_rg_pwd_clear, R.id.iv_rg_pwd2_clear, R.id.iv_rg_invitation_clear,
            R.id.but_regist, R.id.cb_register})
    public void onClick(View view) {

        phone = etRegistPhone.getText().toString();
        code = etRegistCode.getText().toString();
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
                    //postCode(phone, "4");
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
                break;
            case R.id.iv_rg_pwd2_eye:
                break;
            case R.id.iv_rg_invitation_clear:
                etRgInvitationClear.setText("");
                break;
            case R.id.but_regist:
                //输入手机号
                if (cbRegister.isChecked()) {
//                    if (isMobile(phone)) {
//                        Toast.makeText(getActivity(), "通过验证", Toast.LENGTH_SHORT).show();
//
//                        checkphone(str_num);
//
//                    } else {
//                        Toast.makeText(getActivity(), "未通过验证", Toast.LENGTH_SHORT).show();
//                    }
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

                        RegistActivity.this.runOnUiThread(new Runnable() {
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
                    RegistActivity.this.runOnUiThread(new Runnable() {
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
