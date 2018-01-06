package com.dq.huibao.adapter.gd;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.R;
import com.dq.huibao.bean.goodsdetail.Items;
import com.dq.huibao.utils.BaseRecyclerViewHolder;

import java.util.List;

/**
 * Description：
 * Created by jingang on 2017/11/10.
 */

public class ChooseTwoAdapter extends RecyclerView.Adapter<ChooseTwoAdapter.MyViewHolder> {
    private Context mContext;
    //private List<GoodsDetail.DataBean.SpecsBean.ItemsBean> itemList;
    private List<Items> itemList;
    private OnItemClickListener mOnItemClickListener;
    private int mSelect = -1;

    public ChooseTwoAdapter(Context mContext, List<Items> itemList) {
        this.mContext = mContext;
        this.itemList = itemList;
    }

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    /**
     * 刷新方法
     *
     * @param positon
     */
    public void changeSelected(int positon) {
        if (positon != mSelect) {
            mSelect = positon;
            notifyDataSetChanged();
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder vh = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_gd_choose, parent, false));

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

        //点击改变背景
        if (mSelect == position) {
            holder.name.setBackgroundResource(R.drawable.kuang_choose_red);
            holder.name.setTextColor(Color.rgb(255, 255, 255));
        } else {
            holder.name.setBackgroundResource(R.drawable.kuang_choose_gary);
            holder.name.setTextColor(Color.rgb(51, 51, 51));
        }

        holder.name.setText("" + itemList.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class MyViewHolder extends BaseRecyclerViewHolder {
        private TextView name;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.tv_item_choose_name);
        }
    }
}
