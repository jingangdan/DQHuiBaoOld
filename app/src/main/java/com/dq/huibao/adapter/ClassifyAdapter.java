package com.dq.huibao.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.Interface.OnItemLongClickListener;
import com.dq.huibao.R;
import com.dq.huibao.utils.BaseRecyclerViewHolder;

/**
 * Description：分类名称适配器
 * Created by jingang on 2017/10/22.
 */
public class ClassifyAdapter extends RecyclerView.Adapter<ClassifyAdapter.MyViewHolder> {

    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;
    private int mSelect = 0;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }

    private Context mContext;
    //private List<Tese.MsgBean> msgBeen;

    public ClassifyAdapter(Context mContext){
        this.mContext = mContext;
        //this.msgBeen = msgBeen;
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

        MyViewHolder vh = new MyViewHolder(LayoutInflater.from(
                mContext).inflate(R.layout.item_classify, parent,
                false));
        return vh;
    }

    @SuppressLint("WrongConstant")
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
        //点击改变背景
        if (mSelect == position) {
            holder.linearLayout.setBackgroundColor(Color.WHITE);
            holder.name.setTextColor(Color.rgb(241,83,83));
            holder.line.setVisibility(View.VISIBLE);
        } else {
            holder.linearLayout.setBackgroundColor(Color.rgb(239, 239, 239));
            holder.name.setTextColor(Color.rgb(51,51,51));
            holder.line.setVisibility(View.GONE);
        }

        holder.name.setText("测试分类");

        //holder.name.set

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class MyViewHolder extends BaseRecyclerViewHolder {
        LinearLayout linearLayout, line;
        TextView name;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.tv_item_classify_name);
            linearLayout = (LinearLayout) view.findViewById(R.id.lin_item_classify);
            line = (LinearLayout) view.findViewById(R.id.lin_item_line);
        }
    }
}

