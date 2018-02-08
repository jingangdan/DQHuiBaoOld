package com.dq.huibao.adapter.classify;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.R;
import com.dq.huibao.bean.goods.CateChildren;
import com.dq.huibao.utils.BaseRecyclerViewHolder;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.ImageUtils;

import java.util.List;

/**
 * 三级分类适配器
 * Created by jingang on 2018/1/6.
 */

public class ClassifyThreeAdapter extends RecyclerView.Adapter<ClassifyThreeAdapter.MyViewHolder> {
    private Context mContext;
    private List<CateChildren.DataBean.ChildrenBean> childrenBeanList;
    private OnItemClickListener onItemClickListener;

    public ClassifyThreeAdapter(Context mContext, List<CateChildren.DataBean.ChildrenBean> childrenBeanList) {
        this.mContext = mContext;
        this.childrenBeanList = childrenBeanList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        MyViewHolder vh = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_classifythree, parent, false));
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        if (onItemClickListener != null) {
            //为ItemView设置监听器
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition(); // 1
                    onItemClickListener.onItemClick(holder.itemView, position); // 2
                }
            });
        }

        ImageUtils.loadIntoUseFitWidths(mContext,
                HttpPath.IMG_HEADER + childrenBeanList.get(position).getThumb(),
                R.mipmap.icon_empty002,
                R.mipmap.icon_error002,
                holder.img);

        holder.tv_neme.setText("" + childrenBeanList.get(position).getCatename());


    }

    @Override
    public int getItemCount() {
        return childrenBeanList.size();
    }

    public class MyViewHolder extends BaseRecyclerViewHolder {
        private TextView tv_neme;
        private ImageView img;

        public MyViewHolder(View view) {
            super(view);
            tv_neme = (TextView) view.findViewById(R.id.tv_item_classifythree_name);
            img = (ImageView) view.findViewById(R.id.iv_item_classifythree_img);
        }
    }

}
