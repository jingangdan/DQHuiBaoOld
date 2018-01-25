package com.dq.huibao.ui.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.R;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.bean.order.OrderDetail;
import com.dq.huibao.ui.GoodsDetailsActivity;
import com.dq.huibao.utils.BaseRecyclerViewHolder;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpUtils;
import com.dq.huibao.utils.MD5Util;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 订单详情
 * Created by jingang on 2018/1/24.
 */

public class OrderDettailActivity extends BaseActivity {
    @Bind(R.id.tv_od_status)
    TextView tvOdStatus;
    @Bind(R.id.tv_od_pay_money)
    TextView tvOdPayMoney;
    @Bind(R.id.tv_od_dipatch_money)
    TextView tvOdDipatchMoney;
    @Bind(R.id.rv_order_detail)
    RecyclerView rvOrderDetail;
    @Bind(R.id.tv_od_ordersn)
    TextView tvOdOrdersn;
    @Bind(R.id.tv_od_money)
    TextView tvOdMoney;
    @Bind(R.id.tv_od_dispatch)
    TextView tvOdDispatch;
    @Bind(R.id.tv_od_discount_money)
    TextView tvOdDiscountMoney;
    @Bind(R.id.tv_od_pay_money1)
    TextView tvOdPayMoney1;
    @Bind(R.id.tv_od_createtime)
    TextView tvOdCreatetime;
    @Bind(R.id.tv_od_contact)
    TextView tvOdContact;
    @Bind(R.id.tv_od_addr)
    TextView tvOdAddr;
    @Bind(R.id.tv_od_shopname)
    TextView tvOdShopname;

    /*接收页面传值*/
    private Intent intent;
    private String orderid = "", phone = "", token = "";

    /*接口地址*/
    private String MD5_PATH = "", PATH = "";
    private RequestParams params = null;

    /*UI展示*/
    private String status = "", pay_money = "", dispatch = "",
            contact = "", addr = "", ordersn = "",
            money = "", discount = "", createtime = "", shopname = "";

    private List<OrderDetail.DataBean.GoodslistBean> goodsList = new ArrayList<>();
    private GoodsAdapter goodsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdetail);
        ButterKnife.bind(this);

        goodsAdapter = new GoodsAdapter(this, goodsList);
        rvOrderDetail.setLayoutManager(new LinearLayoutManager(this));
        rvOrderDetail.setAdapter(goodsAdapter);

        intent = getIntent();
        orderid = intent.getStringExtra("orderid");
        phone = intent.getStringExtra("phone");
        token = intent.getStringExtra("token");

        getOrderDetail(orderid, phone, token);

        goodsAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                intent = new Intent(OrderDettailActivity.this, GoodsDetailsActivity.class);
                intent.putExtra("gid", goodsList.get(position).getGoodsid());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initWidght() {
        super.initWidght();
        setTitleName("订单详情");
    }

    /**
     * 获取订单详情
     *
     * @param orderid
     * @param phone
     * @param token
     */
    public void getOrderDetail(final String orderid, String phone, String token) {
        MD5_PATH = "id=" + orderid + "&phone=" + phone + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token;
        PATH = HttpUtils.PATHS + HttpUtils.ORDER_DETAIL + MD5_PATH + "&sign=" +
                MD5Util.getMD5String(MD5_PATH + HttpUtils.KEY);
        params = new RequestParams(PATH);
        System.out.println("订单详情 = " + PATH);
        x.http().get(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("订单详情 = " + result);
                        OrderDetail orderDetail = GsonUtil.gsonIntance().gsonToBean(result, OrderDetail.class);
                        if (orderDetail.getStatus() == 1) {
                            goodsList.clear();
                            goodsList.addAll(orderDetail.getData().getGoodslist());
                            goodsAdapter.notifyDataSetChanged();

                            status = orderDetail.getData().getStatus();
                            pay_money = orderDetail.getData().getPay_money();
                            dispatch = orderDetail.getData().getDipatch_money();
                            contact = orderDetail.getData().getAddress().getContact() + "(" + orderDetail.getData().getAddress().getMobile() + ")";
                            addr = orderDetail.getData().getAddress().getProvince() + orderDetail.getData().getAddress().getCity() + orderDetail.getData().getAddress().getAddr();
                            ordersn = orderDetail.getData().getOrdersn();
                            money = orderDetail.getData().getMoney();
                            discount = orderDetail.getData().getDiscount_money();
                            createtime = orderDetail.getData().getCreatetime();

                            shopname = orderDetail.getData().getShopname();

                            setUI();

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

    /*组件赋值*/
    public void setUI() {
        if (status.equals("-1")) {
            tvOdStatus.setText("已关闭");
        } else if (status.equals("0")) {
            tvOdStatus.setText("待支付");
        } else if (status.equals("1")) {
            tvOdStatus.setText("待发货");
        } else if (status.equals("2")) {
            tvOdStatus.setText("待收货");
        } else if (status.equals("3")) {
            tvOdStatus.setText("已完成");
        }

        tvOdPayMoney.setText("订单金额：¥" + pay_money);
        tvOdDipatchMoney.setText("运费：¥" + dispatch);
        tvOdContact.setText("收件人：" + contact);
        tvOdAddr.setText("收货地址：" + addr);
        tvOdOrdersn.setText("" + ordersn);
        tvOdMoney.setText("¥" + money);
        tvOdDispatch.setText("¥" + dispatch);
        tvOdDiscountMoney.setText("¥" + discount);
        tvOdPayMoney1.setText("¥" + pay_money);
        tvOdCreatetime.setText("" + createtime);
        tvOdShopname.setText("" + shopname);

    }

    /**/
    public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.MyViewHolder> {
        private Context mContext;
        private List<OrderDetail.DataBean.GoodslistBean> goodsList;
        private OnItemClickListener onItemClickListener;

        public GoodsAdapter(Context mContext, List<OrderDetail.DataBean.GoodslistBean> goodsList) {
            this.mContext = mContext;
            this.goodsList = goodsList;
        }

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            MyViewHolder vh = new MyViewHolder(
                    LayoutInflater.from(mContext).inflate(R.layout.item_order_goods, viewGroup, false)
            );
            return vh;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {
            if (onItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position = holder.getLayoutPosition(); // 1
                        onItemClickListener.onItemClick(holder.itemView, position); // 2
                    }
                });

            }
            Glide.with(mContext)
                    .load(HttpUtils.IMG_HEADER + goodsList.get(position).getThumb())
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(holder.img);


            holder.goodsname.setText("" + goodsList.get(position).getGoodsname());
            holder.optionname.setText("" + goodsList.get(position).getOptionname());
            holder.price.setText("¥" + goodsList.get(position).getPrice());
            holder.count.setText("" + goodsList.get(position).getCount());

        }

        @Override
        public int getItemCount() {
            return goodsList.size();
        }

        public class MyViewHolder extends BaseRecyclerViewHolder {
            ImageView img;
            TextView goodsname;
            TextView optionname;
            TextView price;
            TextView count;

            public MyViewHolder(View view) {
                super(view);
                img = view.findViewById(R.id.iv_order_img);
                goodsname = view.findViewById(R.id.tv_order_goodsname);
                optionname = view.findViewById(R.id.tv_order_optionname);
                price = view.findViewById(R.id.tv_order_price);
                count = view.findViewById(R.id.tv_order_count);
            }
        }
    }
}
