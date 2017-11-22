package com.dq.huibao.adapter.memcen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.R;
import com.dq.huibao.bean.memcen.Coupons;
import com.dq.huibao.utils.BaseRecyclerViewHolder;
import com.dq.huibao.utils.DateUtils;
import com.dq.huibao.utils.HttpUtils;
import com.dq.huibao.utils.ImageUtils;

import java.util.List;

/**
 * Description：优惠券适配器
 * Created by jingang on 2017/11/21.
 */

public class CouponsAdapter extends RecyclerView.Adapter<CouponsAdapter.MyViewHolder> {
    private Context mContext;
    private List<Coupons.DataBean.ListBean> couponsList;
    private OnItemClickListener onItemClickListener;

    public CouponsAdapter(Context mContext, List<Coupons.DataBean.ListBean> couponsList) {
        this.mContext = mContext;
        this.couponsList = couponsList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder vh = new MyViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.item_coupons, parent, false)
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
        ImageUtils.loadIntoUseFitWidth(mContext,
                HttpUtils.HEADER + couponsList.get(position).getThumb(),
                R.mipmap.icon_empty002,
                R.mipmap.icon_error002,
                holder.iv_thumb);

        holder.tv_couponname.setText("" + couponsList.get(position).getCouponname());
        holder.tv_gettime.setText("有效期：" + DateUtils.timesOne(couponsList.get(position).getGettime()));
        holder.tv_deduct.setText("¥" + couponsList.get(position).getDeduct());


    }

    @Override
    public int getItemCount() {
        return couponsList.size();
    }

    public class MyViewHolder extends BaseRecyclerViewHolder {
        private ImageView iv_thumb;
        private TextView tv_couponname, tv_gettime, tv_deduct;

        public MyViewHolder(View view) {
            super(view);
            iv_thumb = (ImageView) view.findViewById(R.id.iv_item_coupons_thumb);
            tv_couponname = (TextView) view.findViewById(R.id.tv_item_coupons_couponname);
            tv_gettime = (TextView) view.findViewById(R.id.tv_item_coupons_gettime);
            tv_deduct = (TextView) view.findViewById(R.id.tv_item_coupons_deduct);
        }
    }
}
