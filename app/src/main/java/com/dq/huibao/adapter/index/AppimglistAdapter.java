package com.dq.huibao.adapter.index;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.R;
import com.dq.huibao.bean.index.Index;
import com.dq.huibao.utils.BaseRecyclerViewHolder;
import com.dq.huibao.utils.HttpUtils;
import com.dq.huibao.utils.ImageUtils;

import java.util.List;

/**
 * 首页 图片组 适配器
 * Created by jingang on 2018/1/10.
 */

public class AppimglistAdapter extends RecyclerView.Adapter<AppimglistAdapter.MyViewHolder> {
    private Context mContext;
    private List<Index.DataBean.AppimglistBean> appimgList;
    private OnItemClickListener onItemClickListener;

    public AppimglistAdapter(Context mContext, List<Index.DataBean.AppimglistBean> appimgList) {
        this.mContext = mContext;
        this.appimgList = appimgList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        MyViewHolder vh = new MyViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.include_hp_picture, viewGroup, false)
        );
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int i) {
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
                HttpUtils.NEW_HEADER + appimgList.get(i).getThumb(),
                R.mipmap.icon_empty002,
                R.mipmap.icon_error002,
                holder.img);


    }

    @Override
    public int getItemCount() {
        return appimgList.size();
    }

    public class MyViewHolder extends BaseRecyclerViewHolder {
        private ImageView img;

        public MyViewHolder(View view) {
            super(view);
            img = (ImageView) view.findViewById(R.id.iv_hp_picture);
        }
    }
}
