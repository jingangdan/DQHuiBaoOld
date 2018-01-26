package com.dq.huibao.adapter.memcen;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.dq.huibao.R;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.bean.addr.AddrReturn;
import com.dq.huibao.ui.PayActivity;
import com.dq.huibao.utils.CodeUtils;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpUtils;
import com.dq.huibao.utils.MD5Util;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 余额充值
 * Created by jingang on 2018/1/26.
 */

public class RechargeActivity extends BaseActivity {
    @Bind(R.id.et_recharge)
    EditText etRecharge;
    @Bind(R.id.but_recharge)
    Button butRecharge;

    /*接收页面传值*/
    private Intent intent;
    private String phone = "", token = "";

    /*接口地址*/
    private String MD5_PATH = "", PATH = "";
    private RequestParams params = null;

    private String money = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        ButterKnife.bind(this);
        intent = getIntent();
        phone = intent.getStringExtra("phone");
        token = intent.getStringExtra("token");


    }

    @Override
    protected void initWidght() {
        super.initWidght();
        setTitleName("余额充值");
    }


    @OnClick(R.id.but_recharge)
    public void onClick(View view) {
        money = etRecharge.getText().toString();
        switch (view.getId()) {
            case R.id.but_recharge:
                hideKeyboard();
                if (!money.equals("")) {
                    dialog(money);
                } else {
                    toast("请输入充值金额");
                }


                break;
            default:
                break;
        }
    }

    /**
     * 余额充值-生成订单
     *
     * @param money
     * @param phone
     * @param token
     */
    public void setRechargeOrder(final String money, final String phone, final String token) {
        MD5_PATH = "money=" + money + "&phone=" + phone + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token;
        PATH = HttpUtils.PATHS + HttpUtils.RECHARGE_ORDER + MD5_PATH + "&sign=" +
                MD5Util.getMD5String(MD5_PATH + HttpUtils.KEY);
        params = new RequestParams(PATH);
        System.out.println("充值 = " + PATH);
        x.http().post(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("充值 = " + result);
                        AddrReturn addrReturn = GsonUtil.gsonIntance().gsonToBean(result, AddrReturn.class);
                        if (addrReturn.getStatus() == 1) {
                            toast("" + addrReturn.getData());
                            intent = new Intent(RechargeActivity.this, PayActivity.class);
                            intent.putExtra("ordersn", addrReturn.getData().toString());
                            intent.putExtra("price", money);
                            intent.putExtra("phone", phone);
                            intent.putExtra("token", token);
                            startActivityForResult(intent, CodeUtils.RECHARGE);


                            intent = new Intent();
                            setResult(CodeUtils.RECHARGE);
                            RechargeActivity.this.finish();

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

    /*弹出框*/
    protected void dialog(final String money) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("充值金额为：" + money + "元");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                setRechargeOrder(money, phone, token);

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

    /*强制关闭软键盘*/
    private void hideKeyboard() {
        @SuppressLint("WrongConstant") InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive() && this.getCurrentFocus() != null) {
            if (this.getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CodeUtils.RECHARGE) {
            if (resultCode == CodeUtils.PAY) {

            }
        }
    }
}
