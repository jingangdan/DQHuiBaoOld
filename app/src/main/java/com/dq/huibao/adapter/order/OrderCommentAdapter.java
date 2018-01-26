package com.dq.huibao.adapter.order;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dq.huibao.R;
import com.dq.huibao.bean.order.OrderDetail;
import com.dq.huibao.utils.BaseRecyclerViewHolder;
import com.dq.huibao.utils.HttpUtils;

import java.util.List;

/**
 * 订单评价 适配器
 * Created by jingang on 2018/1/26.
 */

public class OrderCommentAdapter extends RecyclerView.Adapter<OrderCommentAdapter.MyViewHolder> {
    private Context mContext;
    private List<OrderDetail.DataBean.GoodslistBean> goodsList;

    public OrderCommentAdapter(Context mContext, List<OrderDetail.DataBean.GoodslistBean> goodsList) {
        this.mContext = mContext;
        this.goodsList = goodsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        MyViewHolder vh = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_order_comment, viewGroup, false));
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Glide.with(mContext)
                .load(HttpUtils.IMG_HEADER + goodsList.get(position).getThumb())
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder.img);
        holder.goodsname.setText("" + goodsList.get(position).getGoodsname());
        holder.optionname.setText("" + goodsList.get(position).getOptionname());
        holder.price.setText("¥" + goodsList.get(position).getPrice());
        holder.count.setText("" + goodsList.get(position).getCount());

        holder.bar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                //item.setXing(rating + "");
                goodsList.get(position).setXing("" + rating);
            }
        });

        holder.remark.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //item.setContent(s + "");
                goodsList.get(position).setContent("" + s);
            }
        });
    }

    @Override
    public int getItemCount() {
        return goodsList.size();
    }

    public class MyViewHolder extends BaseRecyclerViewHolder {
        private ImageView img;
        private TextView goodsname, optionname, price, count;
        private RatingBar bar;
        private EditText remark;

        public MyViewHolder(View view) {
            super(view);
            img = view.findViewById(R.id.iv_item_oc_img);
            goodsname = view.findViewById(R.id.tv_item_oc_goodsname);
            optionname = view.findViewById(R.id.tv_item_oc_optionname);
            price = view.findViewById(R.id.tv_item_oc_price);
            count = view.findViewById(R.id.tv_item_oc_count);
            bar = view.findViewById(R.id.rb_item_oc);
            remark = view.findViewById(R.id.et_item_oc);
        }

    }
}

//public abstract class OrderCommentAdapter<T> extends BaseAdapter {
//    protected LayoutInflater mInflater;
//    protected Context mContext;
//    protected List<T> mDatas;
//    protected final int mItemLayoutId;
//
//    public void cleanList() {
//        mDatas.clear();
//    }
//
//    public OrderCommentAdapter(Context context, List<T> mDatas, int itemLayoutId) {
//        this.mContext = context;
//        this.mInflater = LayoutInflater.from(mContext);
//        this.mDatas = mDatas;
//        this.mItemLayoutId = itemLayoutId;
//    }
//
//    @Override
//    public int getCount() {
//        // return mDatas.size();
//
//        return mDatas.size();
//    }
//
//    @Override
//    public T getItem(int position) {
//        // return mDatas.get(position);
//        return mDatas.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        final OrderViewHolder viewHolder = getViewHolder(position, convertView,
//                parent);
//        convert(viewHolder, getItem(position), position);
//        return viewHolder.getConvertView();
//
//    }
//
//    public abstract void convert(OrderViewHolder helper, T item, int position);
//
//    private OrderViewHolder getViewHolder(int position, View convertView, ViewGroup parent) {
//        return OrderViewHolder.get(mContext, convertView, parent, mItemLayoutId, position);
//    }
//}