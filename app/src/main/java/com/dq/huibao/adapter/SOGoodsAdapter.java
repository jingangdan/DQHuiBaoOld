package com.dq.huibao.adapter;

/**
 * Description：提交订单中的商品列表
 * Created by jingang on 2017/10/30.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.Interface.OnItemLongClickListener;
import com.dq.huibao.R;
import com.dq.huibao.bean.cart.CheckOrder;
import com.dq.huibao.utils.BaseRecyclerViewHolder;
import com.dq.huibao.utils.HttpUtils;
import com.dq.huibao.utils.ImageUtils;

import java.util.List;

public class SOGoodsAdapter extends RecyclerView.Adapter<SOGoodsAdapter.MyViewHolder> {
    private Context mContext;
    private List<CheckOrder.DataBean.GoodslistBean> goodsList;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public SOGoodsAdapter(Context mContext, List<CheckOrder.DataBean.GoodslistBean> goodsList) {
        this.mContext = mContext;
        this.goodsList = goodsList;
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MyViewHolder vh = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_so_goods, parent, false));

        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        if (mOnItemClickListener != null) {
            //为ItemView设置监听器
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition(); // 1
                    mOnItemClickListener.onItemClick(holder.itemView, position); // 2
                }
            });
        }
        if (mOnItemLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = holder.getLayoutPosition();
                    mOnItemLongClickListener.onItemLongClick(holder.itemView, position);
                    //返回true 表示消耗了事件 事件不会继续传递
                    return true;
                }
            });
        }

        ImageUtils.loadIntoUseFitWidths(mContext,
                HttpUtils.IMG_HEADER + goodsList.get(position).getGoods().getThumb(),
                R.mipmap.icon_empty002,
                R.mipmap.icon_error002,
                holder.img);

        holder.goodsname.setText(""+goodsList.get(position).getGoods().getGoodsname());
        holder.option.setText(""+goodsList.get(position).getGoods().getOption().getTitle());
        holder.marketprice.setText("¥"+goodsList.get(position).getGoods().getMarketprice());
        holder.buycount.setText("数量×"+goodsList.get(position).getGoods().getBuycount());

    }

    @Override
    public int getItemCount() {
        return goodsList.size();
    }

    public class MyViewHolder extends BaseRecyclerViewHolder {
        private ImageView img;
        private TextView goodsname, option, marketprice, buycount;

        public MyViewHolder(View view) {
            super(view);
            img = view.findViewById(R.id.iv_item_co_img);
            goodsname = view.findViewById(R.id.tv_item_co_goodsname);
            option = view.findViewById(R.id.tv_item_co_option);
            marketprice = view.findViewById(R.id.tv_item_co_marketprice);
            buycount = view.findViewById(R.id.tv_item_co_buycount);

        }
    }

}

