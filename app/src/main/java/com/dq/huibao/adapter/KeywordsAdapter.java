package com.dq.huibao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.R;
import com.dq.huibao.bean.classify.Keywords;
import com.dq.huibao.utils.BaseRecyclerViewHolder;

import java.util.List;

/**
 * Description：
 * Created by jingang on 2017/11/8.
 */

public class KeywordsAdapter extends RecyclerView.Adapter<KeywordsAdapter.MyViewHolder> {
    private Context mContext;
    private List<Keywords.DataBean.GoodsBean> keywordsList;
    private OnItemClickListener mOnItemClickListener;

    public KeywordsAdapter(Context mContext, List<Keywords.DataBean.GoodsBean> keywordsList) {
        this.mContext = mContext;
        this.keywordsList = keywordsList;
    }

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder vh = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_keywords, parent, false));

        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        if (mOnItemClickListener != null) {
            //
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getLayoutPosition(); // 1
                    mOnItemClickListener.onItemClick(holder.itemView, position); // 2
                }
            });

        }

        holder.tv_name.setText("" + keywordsList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return keywordsList.size();
    }

    public class MyViewHolder extends BaseRecyclerViewHolder {
        private TextView tv_name;

        public MyViewHolder(View view) {
            super(view);
            tv_name = (TextView) view.findViewById(R.id.tv_item_keywords);
        }
    }
}
