package com.dq.huibao.ui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.dq.huibao.R;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.bean.addr.AddrReturn;
import com.dq.huibao.bean.pay.PayType;
import com.dq.huibao.ui.order.OrderActivity;
import com.dq.huibao.utils.CodeUtils;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpUtils;
import com.dq.huibao.utils.MD5Util;
import com.dq.huibao.zhifubao.AuthResult;
import com.dq.huibao.zhifubao.OrderInfoUtil2_0;
import com.dq.huibao.zhifubao.PayDemoActivity;
import com.dq.huibao.zhifubao.PayResult;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 支付界面
 * Created by jingang on 2018/1/23.
 */

public class PayActivity extends BaseActivity {

    @Bind(R.id.tv_pay_ordersn)
    TextView tvPayOrdersn;
    @Bind(R.id.tv_pay_price)
    TextView tvPayPrice;
    @Bind(R.id.rel_pay_weixin)
    RelativeLayout relPayWeixin;
    @Bind(R.id.rel_pay_yue)
    RelativeLayout relPayYue;
    @Bind(R.id.rel_pay_zhifubao)
    RelativeLayout relPayZhifubao;

    /*接收页面传值*/
    private Intent intent;
    private String ordersn = "", phone = "", token = "";

    /*接口地址*/
    private String MD5_PATH = "", PATH = "";
    private RequestParams params = null;

    private String balance = "", wxpay = "", alipay = "", price = "";

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    /**
     * 支付宝支付业务：入参app_id
     */
    public static final String APPID = "2018012502068716";

    /**
     * 支付宝账户登录授权业务：入参pid值
     */
    public static final String PID = "";
    /**
     * 支付宝账户登录授权业务：入参target_id值
     */
    public static final String TARGET_ID = "";

    /** 商户私钥，pkcs8格式 */
    /** 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个 */
    /** 如果商户两个都设置了，优先使用 RSA2_PRIVATE */
    /** RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议使用 RSA2_PRIVATE */
    /** 获取 RSA2_PRIVATE，建议使用支付宝提供的公私钥生成工具生成， */
    /**
     * 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1
     */
    public static final String RSA2_PRIVATE = "MIIEogIBAAKCAQEAttJ0WuZyg91ChHkh8wumYWmbGfVuLGnmkcG40Qo0/xatFb1hv6/Q1uV/XY1wXHeJyl3doTAwGdSgYbUoxEVBzV8QvoccVWbcC9saJmkDY0Zk3A4bWt4zMQxLTk2ZqjhjPKLUNtM51tNPIDwoe7KKqk008XKF17IMUVx4ekL/37rHdN19G6O40MIc7oIIJzH4vF93gKhHl0Sd8/Umy23yRh5+FsQMedBlo6ALTmKVQXTPvRnh+zZI6095O3nL8wRto+kJoSFpOQI57GnQi5sAXsVQJY9TMzA9Kzu56nbFoR/jixo2ZcpzJiBdc7pA8xkNo6ig/3UN4n4MDqfDJKZmsQIDAQABAoIBABzgCo6c3TNki+/+bdVEXsf99QhZkkgjhc45qaYilZJAk5/eZITxEOwQHVi6USfZnYK/5pRh1wcNZTNpeIHfanA0dqGRF3GJqHGa7ntolKdmY+G5vKpSdAVoKrC4Yu/Wrh3Sne/21qjOgS3XMpGu9mXFgfosm2Ug8v8autCcqms8gXze9TFtFmxXVHQd3twyQhy+9YSnnBq9nmqDsKq+HL7fFEZj9sHo/wp4tgCVZulmNiso9gwL5wzPk4QGbWz4P6yr7tcKvVJAk8daXGcKpdrw3ZCju5patG+bpqof29qheOikgCLcPX9UYc1uwslMJjw6soV8Bb10j2aiNvWQip0CgYEA46Iy4aLucJwerGDbayy/h5pmdSleQGdYHp/vcqYT4siiQwEbd9Yju5ESJNgh6zCt94PMsMeTHbXpWCq1+QmhsLPPdv1WjddoL5m9UVQXPl/H7R1rbF3jrxyfyFTNk8nquV6sZswqLPVu1xOO2Q7z3V6j+zB8Eup8XtU7RMtyKycCgYEAzZq2Os9JoLXkvKrG6eSUwx5vK/MY1557d/rDHvPLZRAgBtCAj4w1wSkXWTXwi6AeraaprzXp+U6C1bebmUBzN+HW27LQqPP+Di7PIK55Us/CPYaWTjXemh5WJLxaeTrFMKaFMCUBfIlvPXh3kx8CQQD1JZ5WENHpd7lyF9C+5mcCgYAYt2fm8BYLzCfYd7vba0RwqOcvGYrOW2pKCPuq6zWFjltwAxLEdE/08c+SiJVGQEbjdUAogcMLmtD3bcvMeqs/0IZaWS+ovxbk9ECGMCdF8oh2ypb7cUZjoSwISIz52xbNgeYeU1TRBOVAHTOJhgIcq0YK5DzUoPlYIrcIxT5kSQKBgCCO4VCKxrQGWcRunrUnF0+WoDArfMGYPSEQuplKLelBFnE9zz3sDA8cfLAJ1J+nqXQCMlimhviNsDY7rw4joJs2zhlYRaabjJ2eV/pq5pdwgufPSjn0Wd259BY5JXKRFezQUcL/+S5JbS4kx3qqrGayyh885ZAgTYJCAYk36yL9AoGAKMghOe3hno8fd1pMbS3SVx7Xh1Q+XDsAjfcwfLCYs21fxqQnuuNplyQdIjL7sVPv47dXu58vE7L3dyU22uLyMDjO3kzJLRIL6S74uUuxdKkFOaNZ9LIjfLYPB/7DKgXrE29xf0wkfdbNfBiX1ZlMQWTDer9QMNEamkaYtx0BtpY=";

    public static final String RSA_PRIVATE = "";

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressLint("WrongConstant")
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(PayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(PayActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        Toast.makeText(PayActivity.this,
                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(PayActivity.this,
                                "授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();

                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ButterKnife.bind(this);
        setPayType();
        intent = getIntent();
        ordersn = intent.getStringExtra("ordersn");
        phone = intent.getStringExtra("phone");
        token = intent.getStringExtra("token");

        getPayType(ordersn, phone, token);

        tvPayOrdersn.setText("" + ordersn);
    }

    @Override
    protected void initWidght() {
        super.initWidght();
        setTitleName("支付");
    }

    @OnClick({R.id.rel_pay_weixin, R.id.rel_pay_zhifubao, R.id.rel_pay_yue})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rel_pay_weixin:
                if (!wxpay.equals("")) {
                    setPayOrder(ordersn, wxpay, phone, token);
                }
                break;
            case R.id.rel_pay_zhifubao:
                if (!alipay.equals("")) {
                    setPayOrder(ordersn, alipay, phone, token);
                    payV2();
                }
                break;
            case R.id.rel_pay_yue:
                if (!balance.equals("")) {
                    setPayOrder(ordersn, balance, phone, token);
                }
                break;
        }
    }

    /**
     * 选择支付方式
     *
     * @param ordersn
     * @param phone
     * @param token
     */
    public void getPayType(String ordersn, String phone, String token) {
        MD5_PATH = "ordersn=" + ordersn + "&phone=" + phone + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token;
        PATH = HttpUtils.PATHS + HttpUtils.PAY_PAYTYPE + MD5_PATH + "&sign=" +
                MD5Util.getMD5String(MD5_PATH + HttpUtils.KEY);
        params = new RequestParams(PATH);
        System.out.println("选择支付方式 = " + PATH);
        x.http().get(params,
                new Callback.CommonCallback<String>() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("选择支付方式 = " + result);
                        PayType payType = GsonUtil.gsonIntance().gsonToBean(result, PayType.class);
                        if (payType.getStatus() == 1) {
                            price = payType.getData().getOrder().getPay_money();
                            tvPayPrice.setText("¥" + price);

                            for (int i = 0; i < payType.getData().getPaytype().size(); i++) {
                                if (payType.getData().getPaytype().get(i).equals("balance")) {
                                    relPayYue.setVisibility(View.VISIBLE);
                                    balance = payType.getData().getPaytype().get(i);
                                } else if (payType.getData().getPaytype().get(i).equals("wxpay")) {
                                    relPayWeixin.setVisibility(View.VISIBLE);
                                    wxpay = payType.getData().getPaytype().get(i);
                                } else if (payType.getData().getPaytype().get(i).equals("alipay")) {
                                    relPayZhifubao.setVisibility(View.VISIBLE);
                                    alipay = payType.getData().getPaytype().get(i);
                                }
                            }
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
     * 调用第三方支付前，在第三方下单
     *
     * @param ordersn
     * @param paytype
     * @param phone
     * @param token
     */
    public void setPayOrder(String ordersn, final String paytype, final String phone, final String token) {
        MD5_PATH = "ordersn=" + ordersn + "&paytype=" + paytype + "&phone=" + phone + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token;
        PATH = HttpUtils.PATHS + HttpUtils.PAY_ORDER + MD5_PATH + "&sign=" +
                MD5Util.getMD5String(MD5_PATH + HttpUtils.KEY);

        params = new RequestParams(PATH);
        System.out.println("第三方下单 = " + PATH);
        x.http().post(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println(result);
                        if (paytype.equals("balance")) {
                            AddrReturn addrReturn = GsonUtil.gsonIntance().gsonToBean(result, AddrReturn.class);
                            if (addrReturn.getStatus() == 1) {
                                toast("" + addrReturn.getData());
                                intent = new Intent(PayActivity.this, OrderActivity.class);
                                intent.putExtra("page", 2);
                                intent.putExtra("phone", phone);
                                intent.putExtra("token", token);
                                startActivityForResult(intent, CodeUtils.PAY);
                                PayActivity.this.finish();
                            } else if (addrReturn.getStatus() == 0) {
                                toast("" + addrReturn.getData());
                            }
                        } else {
                            if (paytype.equals("wxpay")) {
                                //微信支付
                            } else if (paytype.equals("alipay")) {
                                //支付宝支付
                                // setZhifubao("","","","","","","");
                                //setZhuFuBao(result);
                            } else if (paytype.equals("balance")) {
                                //余额支付
                            }
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

    @SuppressLint("WrongConstant")
    public void setPayType() {
        relPayWeixin.setVisibility(View.GONE);
        relPayZhifubao.setVisibility(View.GONE);
        relPayYue.setVisibility(View.GONE);
    }

    /**
     * 微信支付
     *
     * @param appid
     * @param partnerid
     * @param prepayid
     * @param noncestr
     * @param timestamp
     * @param packages
     * @param sign
     */
    public void setWeiXin(String appid, String partnerid, String prepayid,
                          String noncestr, String timestamp, String packages, String sign) {

        final IWXAPI msgApi = WXAPIFactory.createWXAPI(this, appid, false);
        msgApi.registerApp(appid);

        //Toast.makeText(this, "获取订单中...", Toast.LENGTH_SHORT).show();
        PayReq req = new PayReq();
        req.appId = appid;
        req.partnerId = partnerid;
        req.prepayId = prepayid;
        req.packageValue = packages;
        req.nonceStr = noncestr;
        req.timeStamp = timestamp;
        req.sign = sign;

        msgApi.sendReq(req);
    }


    private void setZhuFuBao(String info) {

        final String orderInfo = info;
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(PayActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * 支付宝支付业务
     */
    public void payV2() {
        if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))) {
            new AlertDialog.Builder(this).setTitle("警告").setMessage("需要配置APPID | RSA_PRIVATE")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                            //
                            finish();
                        }
                    }).show();
            return;
        }

        /**
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo的获取必须来自服务端；
         */
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;

        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);

        final String orderInfo = orderParam + "&" + sign;

        System.out.println("111 = " + orderInfo);

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(PayActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

}
