package com.dq.huibao.ui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
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
import com.dq.huibao.zhifubao.PayResult;
import com.dq.huibao.zhifubao.SignUtils;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressLint("WrongConstant")
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息

                    String resultStatus = payResult.getResultStatus();
                    System.out.println("111 = " + resultStatus);
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(PayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        //成功以后跳转到订单详情

                        //startActivity(new Intent(PayActivity.this, OrderDetaileActivity.class));

                        PayActivity.this.finish();

                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(PayActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(PayActivity.this, "支付失败", Toast.LENGTH_SHORT).show();

                        }
                    }


                    break;
                }
                default:
                    break;
            }
        }
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
                        System.out.println("第三方下单 = " + result);
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
                                setZhiFuBao(result);
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

    public void setZhiFuBao(String info) {
        final String orderInfo = info;   // 订单信息

        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(PayActivity.this);
                String result = alipay.pay(orderInfo, true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * 支付宝支付
     *
     * @param PARTNER     商户PID
     * @param SELLER      商户收款账号
     * @param RSA_PRIVATE 商户私钥，pkcs8格式
     */
    public void setZhifubao(String dno, String shopcost, String wmrid, String PARTNER, String SELLER, String RSA_PRIVATE, String notfyUrl) {

        if (TextUtils.isEmpty(PARTNER) || TextUtils.isEmpty(RSA_PRIVATE) || TextUtils.isEmpty(SELLER)) {
            new AlertDialog.Builder(this).setTitle("警告").setMessage("PARTNER | RSA_PRIVATE| SELLER")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                            //
                            finish();
                        }
                    }).show();
            return;
        }
        String orderInfo = getOrderInfo(wmrid, dno, "该测试商品的详细描述", shopcost,
                PARTNER, SELLER, notfyUrl);

        /**
         * 特别注意，这里的签名逻辑需要放在服务端，切勿将私钥泄露在代码中！
         */
        String sign = sign(orderInfo, RSA_PRIVATE);
        try {
            /**
             * 仅需对sign 做URL编码
             */
            sign = URLEncoder.encode(sign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        /**
         * 完整的符合支付宝参数规范的订单信息
         */
        final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + getSignType();

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(PayActivity.this);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(payInfo, true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * create the order info. 创建订单信息
     */
    private String getOrderInfo(String wmrid, String subject, String body, String price,
                                String PARTNER, String SELLER, String notfyUrl) {

        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + PARTNER + "\"";

        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + wmrid + "\"";

        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";

        // 商品金额
        orderInfo += "&total_fee=" + "\"" + price + "\"";

        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + notfyUrl + "\"";

        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";

        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";

        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";

        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";

        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";

        System.out.println("aaaaaa" + orderInfo);

        return orderInfo;
    }

    /**
     * sign the order info. 对订单信息进行签名
     *
     * @param content 待签名订单信息
     */
    private String sign(String content, String RSA_PRIVATE) {
        return SignUtils.sign(content, RSA_PRIVATE);
    }

    /**
     * get the sign type we use. 获取签名方式
     */
    private String getSignType() {
        return "sign_type=\"RSA\"";
    }

}
