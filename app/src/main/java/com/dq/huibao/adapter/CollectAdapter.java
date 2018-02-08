package com.dq.huibao.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.Interface.OnItemLongClickListener;
import com.dq.huibao.R;
import com.dq.huibao.bean.memcen.Collect;
import com.dq.huibao.utils.BaseRecyclerViewHolder;
import com.dq.huibao.utils.HttpPath;

import java.util.List;

/**
 * Description：我的收藏 适配器
 * Created by jingang on 2017/11/1.
 */
public class CollectAdapter extends RecyclerView.Adapter<CollectAdapter.MyViewHolder> {

    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;
    private int mSelect = 0;

    private CollectInterface collectInterface;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }

    private Context mContext;
    private List<Collect.DataBean.ListBean> collectList;

    public CollectAdapter(Context mContext, List<Collect.DataBean.ListBean> collectList) {
        this.mContext = mContext;
        this.collectList = collectList;
    }

    public void setCollectInterface(CollectInterface collectInterface) {
        this.collectInterface = collectInterface;
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
                mContext).inflate(R.layout.item_collect, parent,
                false));
        return vh;
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
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

        Glide.with(mContext)
                .load(HttpPath.NEW_HEADER + collectList.get(position).getThumb())
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder.img);

        holder.goodsname.setText("" + collectList.get(position).getGoodsname());
        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                collectInterface.doD("collect", collectList.get(position).getId(), position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return collectList.size();
    }

    class MyViewHolder extends BaseRecyclerViewHolder {
        private ImageView img, del;
        private TextView goodsname, optionname;

        public MyViewHolder(View view) {
            super(view);
            img = view.findViewById(R.id.iv_item_mc);
            goodsname = view.findViewById(R.id.tv_item_mc_goodsname);
            optionname = view.findViewById(R.id.tv_item_mc_optionname);
            del = view.findViewById(R.id.iv_item_mc_del);

        }
    }

    /**
     * 删除接口
     */
    public interface CollectInterface {
        void doD(String type, String id, int position);
    }
}
