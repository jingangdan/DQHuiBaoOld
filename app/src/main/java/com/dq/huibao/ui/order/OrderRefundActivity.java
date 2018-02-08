package com.dq.huibao.ui.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dq.huibao.R;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.bean.addr.AddrReturn;
import com.dq.huibao.utils.CodeUtils;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.MD5Util;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 退款
 * Created by jingang on 2018/1/26.
 */

public class OrderRefundActivity extends BaseActivity {
    @Bind(R.id.tv_refund_price)
    TextView tvRefundPrice;
    @Bind(R.id.cb_control1)
    CheckBox cbControl1;
    @Bind(R.id.cb_control2)
    CheckBox cbControl2;
    @Bind(R.id.cb_control3)
    CheckBox cbControl3;
    @Bind(R.id.cb_control4)
    CheckBox cbControl4;
    @Bind(R.id.cb_control5)
    CheckBox cbControl5;
    @Bind(R.id.cb_control6)
    CheckBox cbControl6;
    @Bind(R.id.cb_control7)
    CheckBox cbControl7;
    @Bind(R.id.but_refund)
    Button butRefunf;
    @Bind(R.id.lin_control_foot)
    LinearLayout linControlFoot;

    /*接收页面传值*/
    private Intent intent;
    private String orderid = "", price = "", phone = "", token = "";

    /*接口地址*/
    private String MD5_PATH = "", PATH = "";
    private RequestParams params = null;


    private String reason = "", UTF_reason = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refund);
        ButterKnife.bind(this);

        initData();

    }

    @Override
    protected void initWidght() {
        super.initWidght();
        setTitleName("申请退款");
    }

    @OnClick(R.id.but_refund)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.but_refund:

                try {
                    UTF_reason = URLEncoder.encode(reason, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                if (reason.equals("")) {
                    toast("请选择退款原因");
                } else {
                    setRefund(orderid, phone, token, reason, UTF_reason);
                }
                break;
        }
    }

    private void initData() {
        intent = getIntent();
        orderid = intent.getStringExtra("orderid");
        price = intent.getStringExtra("price");
        phone = intent.getStringExtra("phone");
        token = intent.getStringExtra("token");

        tvRefundPrice.setText("¥" + price);

        /**/
        cbControl1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    setCheckBox();
                    cbControl1.setChecked(true);
                    reason = "买错了，买多了";
                } else {
                    reason = "";
                }
            }
        });

        cbControl2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    setCheckBox();
                    cbControl2.setChecked(true);
                    reason = "地址、电话填写有误";
                } else {
                    reason = "";
                }
            }
        });

        cbControl3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    setCheckBox();
                    cbControl3.setChecked(true);
                    reason = "计划有变，不想买了";
                } else {
                    reason = "";
                }
            }
        });

        cbControl4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    setCheckBox();
                    cbControl4.setChecked(true);
                    reason = "商品品质有问题";
                } else {
                    reason = "";
                }
            }
        });

        cbControl5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    setCheckBox();
                    cbControl5.setChecked(true);
                    reason = "送的太慢，等太久了";
                } else {
                    reason = "";
                }
            }
        });

        cbControl6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    setCheckBox();
                    cbControl6.setChecked(true);
                    reason = "没有给承诺的优惠";
                } else {
                    reason = "";
                }
            }
        });

        cbControl7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    setCheckBox();
                    cbControl7.setChecked(true);
                    reason = "买其他的了";
                } else {
                    reason = "";
                }
            }
        });


    }

    public void setCheckBox() {
        cbControl1.setChecked(false);
        cbControl2.setChecked(false);
        cbControl3.setChecked(false);
        cbControl4.setChecked(false);
        cbControl5.setChecked(false);
        cbControl6.setChecked(false);
        cbControl7.setChecked(false);
    }

    /**
     * 退款
     *
     * @param id
     * @param phone
     * @param token
     * @param remark
     */
    public void setRefund(String id, String phone, String token, String reason, String remark) {
        MD5_PATH = "id=" + id + "&phone=" + phone + "&remark=" + remark + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token;
        PATH = HttpPath.PATHS + HttpPath.ORDER_REFUND + MD5_PATH + "&sign=" +
                MD5Util.getMD5String("id=" + id + "&phone=" + phone + "&remark=" + reason + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token +
                        HttpPath.KEY);

        params = new RequestParams(PATH);
        System.out.println("加密 = " + MD5_PATH);
        System.out.println("退款 = " + PATH);
        x.http().post(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("退款 = " + result);
                        AddrReturn addrReturn = GsonUtil.gsonIntance().gsonToBean(result, AddrReturn.class);
                        if (addrReturn.getStatus() == 1) {
                            toast("" + addrReturn.getData());
                            intent = new Intent();
                            setResult(CodeUtils.REFUND, intent);
                            OrderRefundActivity.this.finish();
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
