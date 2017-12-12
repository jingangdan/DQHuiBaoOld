package com.dq.huibao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.R;
import com.dq.huibao.bean.classify.Classify;
import com.dq.huibao.utils.BaseRecyclerViewHolder;
import com.dq.huibao.utils.ImageUtils;

import java.util.List;

/**
 * Description：分类下商品列表
 * Created by jingang on 2017/11/6.
 */

public class ClassifyTwoAdapter extends RecyclerView.Adapter<ClassifyTwoAdapter.MyViewHolder> {
    private Context mContext;
    private List<Classify.DataBean.ChildrenBean> childrenList;
    private OnItemClickListener mOnItemClickListener;

    public ClassifyTwoAdapter(Context mContext, List<Classify.DataBean.ChildrenBean> childrenList) {
        this.mContext = mContext;
        this.childrenList = childrenList;
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder vh = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_classifytwo, parent, false));

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

        ImageUtils.loadIntoUseFitWidth(mContext,
                "http://www.dequanhuibao.com/attachment/" + childrenList.get(position).getThumb(),
                R.mipmap.icon_empty002,
                R.mipmap.icon_error002,
                holder.img);

        holder.name.setText("" + childrenList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return childrenList.size();
    }

    public class MyViewHolder extends BaseRecyclerViewHolder {
        private ImageView img;
        private TextView name;

        public MyViewHolder(View view) {
            super(view);
            img = (ImageView) view.findViewById(R.id.iv_item_classifytwo_img);
            name = (TextView) view.findViewById(R.id.tv_item_classifytwo_name);
        }
    }
}
