package com.dq.huibao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.R;
import com.dq.huibao.bean.order.Order;
import com.dq.huibao.utils.BaseRecyclerViewHolder;
import com.dq.huibao.utils.HttpUtils;
import com.dq.huibao.utils.ImageUtils;

import java.util.List;

/**
 * 商品列表（订单列表下）
 * Created by jingang on 2018/1/20.
 */

public class OrderGoodsAdapter extends RecyclerView.Adapter<OrderGoodsAdapter.MyViewHolder> {
    private Context mContext;
    private List<Order.DataBean.GoodslistBean> goodsList;
    private OnItemClickListener onItemClickListener;

    public OrderGoodsAdapter(Context mContext, List<Order.DataBean.GoodslistBean> goodsList) {
        this.mContext = mContext;
        this.goodsList = goodsList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        MyViewHolder vh = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_order_goods, viewGroup, false));
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int positon) {
        if (onItemClickListener != null) {
            //
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getLayoutPosition(); // 1
                    onItemClickListener.onItemClick(holder.itemView, position); // 2
                }
            });

        }
        ImageUtils.loadIntoUseFitWidth(mContext,
                HttpUtils.IMG_HEADER + goodsList.get(positon).getThumb(),
                R.mipmap.icon_empty002,
                R.mipmap.icon_error002,
                holder.img);
        holder.goodsname.setText("" + goodsList.get(positon).getGoodsname());
        holder.optionname.setText("" + goodsList.get(positon).getOptionname());
        holder.price.setText("¥" + goodsList.get(positon).getPrice());
        holder.count.setText("" + goodsList.get(positon).getCount());

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
