package com.dq.huibao.adapter.index;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.R;
import com.dq.huibao.bean.index.Index;
import com.dq.huibao.utils.BaseRecyclerViewHolder;
import com.dq.huibao.utils.HttpUtils;
import com.dq.huibao.utils.ImageUtils;

import java.util.List;

/**
 * 首页 商品列表适配器
 * Created by jingang on 2018/1/10.
 */

public class GoodsListAdapter extends RecyclerView.Adapter<GoodsListAdapter.MyViewHolder>{
    private Context mContext;
    private List<Index.DataBean.GlistBean.GoodslistBean> goodsList;
    private OnItemClickListener onItemClickListener;

    public GoodsListAdapter(Context mContext, List<Index.DataBean.GlistBean.GoodslistBean> goodsList) {
        this.mContext = mContext;
        this.goodsList = goodsList;
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        MyViewHolder vh = new MyViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.item_hp_goods, viewGroup, false)
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
        /*等比例显示图片*/
        ImageUtils.loadIntoUseFitWidths(mContext,
                HttpUtils.IMG_HEADER+goodsList.get(position).getThumb(),
                R.mipmap.icon_empty002,
                R.mipmap.icon_error002,
                holder.img);

        holder.tv_name.setText("" + goodsList.get(position).getGoodsname());

        holder.tv_pricenow.setText("¥" + goodsList.get(position).getMarketprice());
        holder.tv_priceold.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.tv_priceold.setText("¥" + goodsList.get(position).getProductprice());
    }

    @Override
    public int getItemCount() {
        return goodsList.size();
    }

    public class MyViewHolder extends BaseRecyclerViewHolder{
        private ImageView img;
        private TextView tv_name, tv_pricenow, tv_priceold;

        public MyViewHolder(View view) {
            super(view);
            img = (ImageView) view.findViewById(R.id.iv_item_hp_img);
            tv_name = (TextView) view.findViewById(R.id.tv_item_hp_name);
            tv_pricenow = (TextView) view.findViewById(R.id.tv_item_hp_tv_pricenow);
            tv_priceold = (TextView) view.findViewById(R.id.tv_item_hp_priceold);
        }
    }
}
