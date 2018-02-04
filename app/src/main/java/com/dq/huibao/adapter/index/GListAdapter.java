package com.dq.huibao.adapter.index;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.R;
import com.dq.huibao.bean.index.Index;
import com.dq.huibao.ui.GoodsDetailsActivity;
import com.dq.huibao.utils.BaseRecyclerViewHolder;
import com.dq.huibao.utils.HttpUtils;
import com.dq.huibao.utils.ImageUtils;

import java.util.List;

/**
 * Created by jingang on 2018/2/2.
 */

public class GListAdapter extends RecyclerView.Adapter<GListAdapter.MyViewHolder> {
    private Context mContext;
    private List<Index.DataBean.GlistBean> glist;
    private OnItemClickListener onItemClickListener;
    private Intent intent;

    public GListAdapter(Context mContext, List<Index.DataBean.GlistBean> glist) {
        this.mContext = mContext;
        this.glist = glist;
        intent = null;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        MyViewHolder vh = new MyViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.item_glist, viewGroup, false)
        );
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int i) {
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
                HttpUtils.NEW_HEADER + glist.get(i).getThumb(),
                R.mipmap.icon_empty001,
                R.mipmap.icon_error001,
                holder.img);


        holder.recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2, GridLayoutManager.VERTICAL, false));
        final GoodsListAdapter goodsListAdapter = new GoodsListAdapter(mContext, glist.get(i).getGoodslist());
        holder.recyclerView.setAdapter(goodsListAdapter);

        goodsListAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                intent = new Intent(mContext, GoodsDetailsActivity.class);
                intent.putExtra("gid", glist.get(i).getGoodslist().get(position).getId());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return glist.size();
    }


    public class MyViewHolder extends BaseRecyclerViewHolder {
        private ImageView img;
        private RecyclerView recyclerView;

        public MyViewHolder(View view) {
            super(view);
            img = (ImageView) view.findViewById(R.id.iv_hp_glist);
            recyclerView = (RecyclerView) view.findViewById(R.id.rv_hp_glist);
        }
    }
}
