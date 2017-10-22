package com.dq.huibao.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.Interface.OnItemLongClickListener;
import com.dq.huibao.R;
import com.dq.huibao.utils.BaseRecyclerViewHolder;

/**
 * Description：
 * Created by jingang on 2017/10/22.
 */

public class HotClassifyAdapter extends RecyclerView.Adapter<HotClassifyAdapter.MyViewHolder> {

    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }

    private Context mContext;
    //private List<Tese.MsgBean> msgBeen;

    public HotClassifyAdapter(Context mContext){
        this.mContext = mContext;
        //this.msgBeen = msgBeen;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MyViewHolder vh = new MyViewHolder(LayoutInflater.from(
                mContext).inflate(R.layout.item_hot_classify, parent,
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
        if(position % 2 == 0){
            holder.lin_item_line.setVisibility(View.VISIBLE);
        }else{
            holder.lin_item_line.setVisibility(View.GONE);
        }

        holder.img.setImageResource(R.mipmap.ic_hot);

//        String img = msgBeen.get(position).getImg();
//
//        if (img.equals("")) {
//            holder.img.setImageResource(R.mipmap.yibeitong001);
//
//        } else {
//            x.image().bind(holder.img, "http://www.ybt9.com/" + img);
//        }
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    class MyViewHolder extends BaseRecyclerViewHolder {

        ImageView img;
        LinearLayout lin_item_line;

        public MyViewHolder(View view) {
            super(view);
            img = (ImageView) view.findViewById(R.id.iv_item_hot_classify);
            lin_item_line = (LinearLayout) view.findViewById(R.id.lin_item_line);
        }
    }
}
