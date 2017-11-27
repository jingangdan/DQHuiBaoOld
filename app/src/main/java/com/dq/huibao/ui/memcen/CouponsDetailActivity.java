package com.dq.huibao.ui.memcen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.R;
import com.dq.huibao.adapter.memcen.RecommentAdapter;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.bean.LoginBean;
import com.dq.huibao.bean.memcen.CouponsDetail;
import com.dq.huibao.bean.memcen.Recomment;
import com.dq.huibao.ui.GoodsDetailsActivity;
import com.dq.huibao.utils.DateUtils;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpUtils;
import com.dq.huibao.utils.ImageUtils;
import com.dq.huibao.utils.MD5Util;
import com.dq.huibao.utils.SPUserInfo;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description：优惠券详情
 * Created by jingang on 2017/11/24.
 */

public class CouponsDetailActivity extends BaseActivity {
    @Bind(R.id.iv_cd_respthumb)
    ImageView ivCdRespthumb;
    @Bind(R.id.tv_cd_couponname)
    TextView tvCdCouponname;
    @Bind(R.id.tv_cd_createtime)
    TextView tvCdCreatetime;
    @Bind(R.id.tv_cd_enough)
    TextView tvCdEnough;
    @Bind(R.id.tv_cd_deduct)
    TextView tvCdDeduct;
    @Bind(R.id.wv_couponsdetail)
    WebView wvCouponsdetail;
    @Bind(R.id.but_cd_ok)
    Button butCdOk;
    @Bind(R.id.tv_cd_change)
    TextView tvCdChange;
    private WebSettings webSettings;

    @Bind(R.id.rv_couponssetail)
    RecyclerView rvCouponssetail;
    private RecommentAdapter recommentAdapter;
    private List<Recomment.DataBean.GoodsBean> recommentList = new ArrayList<>();

    /*接收页面传值*/
    private Intent intent;
    private String couponsid = "";

    /*本地轻量型缓存*/
    private SPUserInfo spUserInfo;
    private String uunionid = "";
    private String index = "";

    /*接口地址*/
    private String PATH = "";
    private RequestParams params = null;

    private String respthumb = "", couponname = "", createtime = "", enough = "", deduct = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_couponsdetail);
        ButterKnife.bind(this);

        recommentAdapter = new RecommentAdapter(this, recommentList);
        rvCouponssetail.setLayoutManager(new GridLayoutManager(this, 2));
        rvCouponssetail.setAdapter(recommentAdapter);

        recommentAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                intent = new Intent(CouponsDetailActivity.this, GoodsDetailsActivity.class);
                intent.putExtra("gid", "&id=" + recommentList.get(position).getId());
                startActivity(intent);
            }
        });

        initDate();
    }

    @Override
    protected void initWidght() {
        super.initWidght();
        setTitleName("优惠券详情");
    }

    public void initDate() {
        spUserInfo = new SPUserInfo(getApplication());
        intent = getIntent();
        couponsid = intent.getStringExtra("couponsid");
        index = intent.getStringExtra("index");

        LoginBean loginBean = GsonUtil.gsonIntance().gsonToBean(spUserInfo.getLoginReturn(), LoginBean.class);
        uunionid = loginBean.getData().getUnionid();

        if (index.equals("0")) {
            butCdOk.setText("立即去选商品使用");

        } else if (index.equals("1")) {
            butCdOk.setText("立即获取");
        }

        getCouponsDetail(couponsid, uunionid);
        getRecomment();

    }

    /**
     * 优惠券详情
     *
     * @param id
     * @param unionid
     */
    public void getCouponsDetail(String id, String unionid) {
        PATH = HttpUtils.PATH + HttpUtils.SHOP_COUPON_DETAIL +
                "unionid=" + unionid + "&stamp=" + (System.currentTimeMillis() / 1000) + "&doc=" +
                MD5Util.getMD5String(HttpUtils.SHOP_COUPON_DETAIL + "unionid=" + unionid + "&stamp=" + (System.currentTimeMillis() / 1000) + "&dequanhuibaocom") +
                "&id=" + id;

        params = new RequestParams(PATH);
        System.out.println("优惠券详情 =" + PATH);

        x.http().get(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("优惠券详情 =" + result);
                        CouponsDetail couponsDetail = GsonUtil.gsonIntance().gsonToBean(result, CouponsDetail.class);

                        respthumb = couponsDetail.getData().getRespthumb();
                        couponname = couponsDetail.getData().getCouponname();
                        createtime = couponsDetail.getData().getCreatetime();
                        enough = couponsDetail.getData().getEnough();
                        deduct = couponsDetail.getData().getDeduct();

                        setInitDate();

                        getWebHTML(couponsDetail.getData().getDesc());


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
     * 获取推荐商品
     */
    public void getRecomment() {
        PATH = HttpUtils.PATH + HttpUtils.SHOP_GOODS_RECOMMENT;
        params = new RequestParams(PATH);
        System.out.println("推荐商品 = " + PATH);
        x.http().get(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("推荐商品 = " + result);

                        Recomment recomment = GsonUtil.gsonIntance().gsonToBean(result, Recomment.class);
                        recommentList.clear();
                        recommentList.addAll(recomment.getData().getGoods());

                        recommentAdapter.notifyDataSetChanged();


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
     * 组件赋值
     */
    public void setInitDate() {
        ImageUtils.loadIntoUseFitWidth(this,
                HttpUtils.HEADER + respthumb,
                R.mipmap.icon_empty002,
                R.mipmap.icon_error002,
                ivCdRespthumb);

        tvCdCouponname.setText("" + couponname);
        tvCdCreatetime.setText("到期时间：" + DateUtils.timesOne(createtime));
        tvCdEnough.setText("消费满 ¥" + enough);
        tvCdDeduct.setText("立减 " + deduct);
    }

    /**
     * 加载商品图文详情（html）
     *
     * @param html_bady
     */
    @SuppressLint("WrongConstant")
    public void getWebHTML(String html_bady) {
        wvCouponsdetail.getSettings().setJavaScriptEnabled(true);
        webSettings = wvCouponsdetail.getSettings();
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptEnabled(false);

        String head = "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
                "<style>img{max-width: 100%; width:auto; height:auto;}</style>" +
                "</head>";
        String html = "<html>" + head + "<body>" + html_bady + "</body></html>";

        wvCouponsdetail.loadDataWithBaseURL(HttpUtils.HEADER, html, "text/html", "utf-8", null);

    }

    @OnClick({R.id.but_cd_ok, R.id.tv_cd_change})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.but_cd_ok:
                if (index.equals("0")) {
                    toast("使用");

                } else if (index.equals("1")) {
                    toast("领取");
                }
                break;

            case R.id.tv_cd_change:
                //换一批
                getRecomment();
                break;
            default:
                break;
        }
    }
}
