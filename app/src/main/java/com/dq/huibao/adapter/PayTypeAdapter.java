package com.dq.huibao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.R;
import com.dq.huibao.bean.pay.PayType;
import com.dq.huibao.utils.BaseRecyclerViewHolder;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.ImageUtils;

import java.util.List;

import butterknife.Bind;

/**
 * 支付方式
 * Created by jingang on 2018/2/28.
 */

public class PayTypeAdapter extends RecyclerView.Adapter<PayTypeAdapter.MyViewHolder> {
    private Context mContext;
    private List<PayType.DataBean.PaytypeBean> list;

    public PayTypeAdapter(Context mContext, List<PayType.DataBean.PaytypeBean> list) {
        this.mContext = mContext;
        this.list = list;
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public PayTypeAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        MyViewHolder vh = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_pay_type, viewGroup, false));
        return vh;
    }

    @Override
    public void onBindViewHolder(final PayTypeAdapter.MyViewHolder holder, int i) {
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getLayoutPosition(); // 1
                    onItemClickListener.onItemClick(holder.itemView, position); // 2
                }
            });
        }

//        ImageUtils.loadIntoUseFitWidth(mContext,
//                HttpPath.NEW_HEADER + list.get(i).getImg(),
//                R.mipmap.icon_empty003,
//                R.mipmap.icon_error003,
//                holder.iv);

        Glide
                .with(mContext)
                .load(HttpPath.NEW_HEADER + list.get(i).getImg())
                .asBitmap()
                .into(holder.iv);

        holder.tv.setText("" + list.get(i).getName());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends BaseRecyclerViewHolder {
        TextView tv;
        ImageView iv;

        public MyViewHolder(View view) {
            super(view);
            iv = view.findViewById(R.id.item_iv_paytype);
            tv = view.findViewById(R.id.item_tv_paytype);
        }
    }
}
