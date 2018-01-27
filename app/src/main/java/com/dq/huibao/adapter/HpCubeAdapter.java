package com.dq.huibao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.R;
import com.dq.huibao.bean.homepage.Cube;
import com.dq.huibao.utils.BaseRecyclerViewHolder;
import com.dq.huibao.utils.ImageUtils;

import java.util.List;

/**
 * Description：
 * Created by jingang on 2017/11/4.
 */

public class HpCubeAdapter extends RecyclerView.Adapter<HpCubeAdapter.MyViewHolder> {
    private Context mContext;
    private List<Cube.DataBean.ParamsBean.LayoutBean> cubeList;
    private OnItemClickListener onItemClickListener;

    public HpCubeAdapter(Context mContext, List<Cube.DataBean.ParamsBean.LayoutBean> cubeList) {
        this.mContext = mContext;
        this.cubeList = cubeList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder vh = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_hp_picture, parent, false));
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getLayoutPosition(); // 1
                    onItemClickListener.onItemClick(holder.itemView, position); // 2
                }
            });

        }
        String img = cubeList.get(position).getImgurl();

        if (cubeList.get(position).isIsempty() == false) {
            ImageUtils.loadIntoUseFitWidth(mContext, img, R.mipmap.icon_empty001, R.mipmap.icon_error001, holder.img);
        } else {

        }

    }

    @Override
    public int getItemCount() {
        return cubeList.size();
    }

    public class MyViewHolder extends BaseRecyclerViewHolder {
        private ImageView img;

        public MyViewHolder(View view) {
            super(view);
            img = (ImageView) view.findViewById(R.id.iv_hp_picture);
        }
    }
}
